import { Link, NavLink } from "react-router-dom";
import { useLocation } from "react-router-dom";
import styles from './Navbar.module.css';

const NavBar = () => {
  const location = useLocation();
  
  return ( 
    <>
      <nav>
        <ul>
            <li className={styles.navLinks}>
              <NavLink to='/' aria-current="home" className={location.pathname === '/' ? 'active' : ''}>Home</NavLink>
            </li>
            <li className={styles.navLinks}>
              <NavLink to="/warehouses"  className={location.pathname === '/warehouses' ? 'active' : ''}>Warehouses</NavLink>
            </li>
            <li className={styles.navLinks}>
              <NavLink to="/items" className={location.pathname === '/items' ? 'active' : ''}>Items</NavLink>
            </li>
            <li className={styles.navLinks}>
              <NavLink to="/warehouses"  className={location.pathname === '/warehouses' ? 'active' : ''}>Skip to content</NavLink>
            </li>
        </ul>
      </nav>
    </>
   );
}
 
export default NavBar;