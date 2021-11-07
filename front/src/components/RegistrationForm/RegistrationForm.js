import axios from 'axios';
import React, { useState, useEffect } from 'react';
import FingerprintJS from '@fingerprintjs/fingerprintjs'
import { getGPUTier } from 'detect-gpu'
import ReCAPTCHA from "react-google-recaptcha";
import InputMask from 'react-input-mask';

function RegistrationForm(props) {
    const { detect } = require('detect-browser');
    const browser = detect();

    // const [showRegister, setShowRegister] = useState(true)

    // function onChange(value) {
    //     console.log("Captcha value:", value);
    //     setShowRegister(true)
    // }

    const [state, setState] = useState({
        nome: "",
        email: "",
        telefone: "",
        password: ""
    })
    const [ip, setIP] = useState('');

    //Fingerprint
    const fpPromise = FingerprintJS.load();

    const fingerprint = async () => {

        const fp = await fpPromise
        const result = await fp.get()

        return result.components
    }

    const getGPU = async () => {
        const gpuTier = await getGPUTier()
        return gpuTier
    }

    const getData = async () => {
        const res = await axios.get('https://geolocation-db.com/json/')
        setIP(res.data.IPv4)
    }

    useEffect(() => {
        getData()

    }, []);

    const handleChange = (e) => {
        const { id, value } = e.target
        setState(prevState => ({
            ...prevState,
            [id]: value
        }))
    }
    const handleSubmitClick = (e) => {
        e.preventDefault();
        sendDetailsToServer()
    }

    const sendDetailsToServer = async () => {

        const dadosDoUsuario = await fingerprint()
        const gpu = await getGPU()
        console.log(gpu.gpu)
        if (state.email.length && state.password.length) {
            const payload = {
                "email": state.email,
                "nome": state.nome,
                "password": state.password,
                "telefone": state.telefone,
                "browser": browser.name,
                "browserVersion": browser.version,
                "cookiesEnabled": dadosDoUsuario.cookiesEnabled.value,
                "deviceMemory": dadosDoUsuario.deviceMemory.value,
                "gpu": gpu.gpu,
                "hardwareConcurrency": dadosDoUsuario.hardwareConcurrency.value,
                "ip": ip,
                "languages": dadosDoUsuario.languages.value[0][0],
                "localStorage": dadosDoUsuario.localStorage.value,
                "platform": browser.os,
                "sessionStorage": dadosDoUsuario.sessionStorage.value,
                "timezone": dadosDoUsuario.timezone.value,
                "touchSupport": dadosDoUsuario.touchSupport.value.touchEvent,
                "tempoCadastro": "21"
            }

            try{
                await axios.post('http://localhost:8000/usuarios/', payload)
                .then(function (response) {
                    if (response.status === 200) {
                        setState(prevState => ({
                            ...prevState,
                            'successMessage': 'Registration successful. Redirecting to home page..'
                        }))
                        // redirectToHome();
                        alert("Cadastro realizado com sucesso!")
                    } else {
                        alert("Algo de errado aconteceu.");
                    }
                })
                .catch(error =>{
                    console.error('There was an error!', error)
                })
            } catch(e){
                console.log('error', e)
            }
            
        } else {
            alert('Por favor insira um nome, senha e email válidos.')
        }

    }

    return (
        <div className="login-card">
            <form>
                <div className="form-group text-left">
                    <label htmlFor="exampleInputNome1">Nome</label>

                    <input type="nome"
                        className="form-control"
                        id="nome"
                        value={state.nome}
                        onChange={handleChange}
                    />
                </div>
                <div className="form-group text-left">
                    <label htmlFor="exampleInputEmail1">Email address</label>
                    <input type="email"
                        className="form-control"
                        id="email"
                        aria-describedby="emailHelp"
                        value={state.email}
                        onChange={handleChange}
                    />
                    <span id="txt"></span>
                    <small id="emailHelp" className="form-text">We'll never share your email with anyone else.</small>
                    <div className="form-group text-left" />
                    <label htmlFor="exampleInputTelefone1">telefone</label>
                    <InputMask
                        type="telefone" 
                        className="form-control"
                        id="telefone"
                        mask='9999-9999' 
                        value={state.telefone} 
                        onChange={handleChange}>
                    </InputMask>
                </div>
                <div className="form-group text-left">
                    <label htmlFor="exampleInputPassword1">Password</label>
                    <input type="password"
                        className="form-control"
                        id="password"
                        value={state.password}
                        onChange={handleChange}
                    />
                </div>
                {/* <ReCAPTCHA
                    sitekey="6LcBqvwcAAAAAG0_5v7agTDh0DyQG6BdHNgL0AmK"
                    onChange={onChange}
                /> */}
                    <button
                        type="submit"
                        className="btn-send"
                        onClick={handleSubmitClick}
                    >
                        Register
                    </button>
                    {/* <label htmlFor="exampleInputPassword1">Please use ReCaptcha</label> */}
            </form>
        </div>
    )
}

export default RegistrationForm