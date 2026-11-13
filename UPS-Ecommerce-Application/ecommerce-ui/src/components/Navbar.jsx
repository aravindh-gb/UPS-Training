import { Link, NavLink } from 'react-router-dom';

export default function Navbar({ user, cartCount, onLogout }) {
  return (
    <header className="site-header">
      <div className="announcement">A little more thoughtful. A lot more everyday.</div>
      <nav className="nav-shell">
        <Link className="brand" to="/" aria-label="Form and Field home">
          <span className="brand-mark">F.</span>
          <span>form <i>&</i> field</span>
        </Link>
        <div className="nav-links">
          <NavLink to="/" end>Shop</NavLink>
          {user && <NavLink to="/orders">Orders</NavLink>}
        </div>
        <div className="nav-actions">
          {user ? (
            <>
              <span className="nav-greeting">Hi, {user.name.split(' ')[0]}</span>
              <button className="text-button" onClick={onLogout}>Sign out</button>
            </>
          ) : (
            <NavLink to="/login" className="sign-in-link">Sign in</NavLink>
          )}
          <NavLink to="/cart" className="cart-link" aria-label={`Cart with ${cartCount} items`}>
            Bag <span>{cartCount}</span>
          </NavLink>
        </div>
      </nav>
    </header>
  );
}
