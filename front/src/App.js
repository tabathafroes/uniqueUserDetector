import { Route, Switch } from 'react-router';
import { BrowserRouter as Router } from 'react-router-dom'
import './App.css';
import Header from './components/Header/Header';
import RegistrationForm from './components/RegistrationForm/RegistrationForm'
import Usuarios from './components/Usuarios/Usuarios';

function App() {
  return (
    <Router>
      <div className="App">
        <Header/>
        <div className="container d-flex align-itens-center flex-column">
          <Switch>
            <Route path="/" exact={true}>
              <RegistrationForm/>
            </Route>
            <Route path="/usuarios">
              <Usuarios/>
            </Route>
          </Switch>
        </div>
      </div>
    </Router>
  );
}

export default App;
