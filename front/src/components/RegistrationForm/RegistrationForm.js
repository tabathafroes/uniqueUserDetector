import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { API_BASE_URL } from '../../constants/apiConstants';
import FingerprintJS from '@fingerprintjs/fingerprintjs'
import { getGPUTier } from 'detect-gpu'
import ReCAPTCHA from "react-google-recaptcha";
import $ from 'jquery';

function RegistrationForm(props) {

    var c = 0;
    var t;
    var timer_is_on = 0;

    const [tempo, setTempo]= useState()

    const { detect } = require('detect-browser');
    const browser = detect();


    useEffect(() => {
        setTimeout(() => {
           setTempo(tempo + 1);
         }, 1000);
         console.log(tempo)
    
       },[tempo]);

    $("#nome").on("input", function () {
        if ($("#nome").val().length > 1) {
            if (!timer_is_on) {
                timer_is_on = 1;
                setTempo(1)
            }
        } else {
            clearTimeout(t);
            timer_is_on = 0;
        }
    });

    function stopCount() {
        clearTimeout(t);
        timer_is_on = 0;
    }

    // const [showRegister, setShowRegister] = useState(true)

    // function onChange(value) {
    //     console.log("Captcha value:", value);
    //     setShowRegister(true)
    // }

    const [state, setState] = useState({
        nome: "",
        email: "",
        telefone: "",
        password: "",
        confirmPassword: ""
    })
    const [ip, setIP] = useState('');

    //Fingerprint
    const fpPromise = FingerprintJS.load();

    const fingerprint = async () => {

        const fp = await fpPromise
        const result = await fp.get()
        const visitorId = result.visitorId
        console.log(visitorId)
        console.log(result)
        console.log(result.confidence.score)

        return result.components
    }

    const getGPU = async () => {
        const gpuTier = await getGPUTier()
        return gpuTier
    }

    const getData = async () => {
        const res = await axios.get('https://geolocation-db.com/json/')
        console.log(res.data);
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
        if (state.password === state.confirmPassword) {
            stopCount()
            sendDetailsToServer()
        } else {
            alert("Passwords don't match")
        }
    }

    const sendDetailsToServer = async () => {

        const dadosDoUsuario = await fingerprint()
        const gpu = await getGPU()
        if (state.email.length && state.password.length) {

            // props.showError(null);
            const payload = {
                "nome": state.nome,
                "email": state.email,
                "telefone": state.telefone,
                "password": state.password,
                "cookiesEnabled": dadosDoUsuario.cookiesEnabled.value,
                "deviceMemory": dadosDoUsuario.deviceMemory.value,
                "hardwareConcurrency": dadosDoUsuario.hardwareConcurrency.value,
                "ip": ip,
                "languages": dadosDoUsuario.languages.value[0][0],
                "localStorage": dadosDoUsuario.localStorage.value,
                "platform": browser.os,
                "sessionStorage": dadosDoUsuario.sessionStorage.value,
                "timezone": dadosDoUsuario.timezone.value,
                "touchSupport": dadosDoUsuario.touchSupport.value.touchEvent,
                "browser": browser.name,
                "browserVersion": browser.version,
                "gpu": gpu.gpu,
                "tempoCadastro": tempo
            }
            console.log(payload)
            axios.post(API_BASE_URL + '/usuarios', payload)
                .then(function (response) {
                    if (response.status === 200) {
                        setState(prevState => ({
                            ...prevState,
                            'successMessage': 'Registration successful. Redirecting to home page..'
                        }))
                        // redirectToHome();
                        alert(null)
                    } else {
                        alert("Some error ocurred");
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
        } else {
            alert('Please enter valid username and password')
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
                    <small id="emailHelp" className="form-text">We'll never share your email with anyone else.</small>
                    <div className="form-group text-left" />
                    <label htmlFor="exampleInputTelefone1">telefone</label>
                    <input type="telefone"
                        className="form-control"
                        id="telefone"
                        value={state.telefone}
                        onChange={handleChange}
                    />
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
                <div className="form-group text-left">
                    <label htmlFor="exampleInputPassword1">Confirm Password</label>
                    <input type="password"
                        className="form-control"
                        id="confirmPassword"
                        value={state.confirmPassword}
                        onChange={handleChange}
                    />
                </div>
                <div className="form-group text-left">
                    <label htmlFor="exampleInputPassword1">IP Address</label>
                    <input type="text" className="form-control" value={ip} disabled />
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