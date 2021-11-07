import React from 'react';
import { Link } from 'react-router-dom';
function Header() {

    return(
        <nav class="navbar navbar-dark bg-primary">
            <div className="row col-12 d-flex justify-content-center text-white">
            <Link to="/" className="btn btn-primary">Registrar  </Link>
            <span className="h3">UOL</span>
            <Link to="/usuarios" className="btn btn-primary">Usuarios</Link>
            </div>
        </nav>
    )
}
export default Header;