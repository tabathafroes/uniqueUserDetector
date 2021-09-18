import axios from 'axios';
import React, {useState, useEffect} from 'react';
import { API_BASE_URL } from '../../constants/apiConstants';

function RegistrationForm(props) {
    const [ip, setIP] = useState('');

    const getData = async () => {
      const res = await axios.get('https://geolocation-db.com/json/')
      console.log(res.data);
      setIP(res.data.IPv4)
    }
    
    useEffect( () => {
      getData()
  
    }, []);
    const [state , setState] = useState({
        nome : "",
        email : "",
        telefone: "",
        password : ""
    })
    const handleChange = (e) => {
        const {id , value} = e.target   
        setState(prevState => ({
            ...prevState,
            [id] : value
        }))
    }
    const handleSubmitClick = (e) => {
        e.preventDefault();
        if(state.password === state.confirmPassword) {
            sendDetailsToServer()    
        } else {
            props.showError('Passwords do not match');
        }
    }

    const sendDetailsToServer = () => {
        if(state.email.length && state.password.length) {
            props.showError(null);
            const payload={
                "nome": state.nome,
                "email":state.email,
                "telefone": state.telefone,
                "password":state.password,
            }
            axios.post(API_BASE_URL+'/user/register', payload)
                .then(function (response) {
                    if(response.status === 200){
                        setState(prevState => ({
                            ...prevState,
                            'successMessage' : 'Registration successful. Redirecting to home page..'
                        }))
                        // redirectToHome();
                        props.showError(null)
                    } else{
                        props.showError("Some error ocurred");
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });    
        } else {
            props.showError('Please enter valid username and password')    
        }
        
    }

    return(
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
                    <div className="form-group text-left"/>
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
                        />
                    </div>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputPassword1">IP Address</label>
                        <input type="text" className="form-control" value={ip} disabled/>
                    </div>
                    <button 
                        type="submit" 
                        className="btn-send"
                        onClick={handleSubmitClick}
                    >
                        Register
                    </button>
                </form>
            </div>
        )
    }

    export default RegistrationForm