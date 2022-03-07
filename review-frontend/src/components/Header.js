import { Link, Outlet } from "react-router-dom";
import SearchBar from "./SearchBar";

function Header() {
    return (
        <>
            <header>
                <nav className="navbar">
                    <div className='container'>
                        <Link className='navbar-brand' to='/index.html'>IT Books</Link>
                        <div className="navbar-nav">
                            <Link className='nav-link' to='/admin/login'>Login</Link>
                        </div>
                    </div>
                </nav>
            </header>
            <SearchBar />
            <Outlet />
        </>
    );
}

export default Header;