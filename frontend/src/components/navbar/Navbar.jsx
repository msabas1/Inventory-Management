import { Link, NavLink } from "react-router-dom";
import { useLocation } from "react-router-dom";
import styles from './Navbar.module.css';

const NavBar = () => {
  const location = useLocation();
  
  return ( 
    <>
      <nav>
        <ul>
          <div>
            <li>
              <NavLink to='/' className={location.pathname === '/' ? 'active' : ''}>Home</NavLink>
            </li>
          </div>
          <div className={styles.navLinks}>
            <li>
              <NavLink to="/warehouses"  className={location.pathname === '/warehouses' ? 'active' : ''}>Warehouses</NavLink>
            </li>
            <li>
              <NavLink to="/items" className={location.pathname === '/items' ? 'active' : ''}>Items</NavLink>
            </li>
          </div>
        </ul>
      </nav>
    </>
   );
}
 
export default NavBar;