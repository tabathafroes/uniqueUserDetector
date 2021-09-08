import { Route, Switch } from 'react-router';
import { BrowserRouter as Router } from 'react-router-dom'
import './App.css';
import Header from './components/Header/Header';
import IpGetter from './components/IpGetter/IpGetter';
import RegistrationForm from './components/RegistrationForm/RegistrationForm'

function App() {
  return (
    <Router>
      <div className="App">
        <Header/>
        <div className="container d-flex align-itens-center flex-column">
          <Switch>
            <Route path="/" exact={true}>
              <RegistrationForm/>
              <IpGetter/>
            </Route>
          </Switch>
        </div>
      </div>
    </Router>
  );
}

export default App;
