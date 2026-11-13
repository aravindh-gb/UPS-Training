import { useEffect, useState } from 'react';
import { Link, Navigate, Route, Routes, useLocation } from 'react-router-dom';
import Navbar from './components/Navbar';
import ProductList from './components/ProductList';
import ProductDetail from './components/ProductDetail';
import Cart from './components/Cart';
import Checkout from './components/Checkout';
import Login from './components/Login';
import Register from './components/Register';
import Orders from './components/Orders';
import { api, clearAuth } from './services/api';

function ProtectedRoute({ user, children }) {
  const location = useLocation();
  return user ? children : <Navigate to="/login" replace state={{ from: location.pathname }} />;
}

function readUser() {
  try {
    return JSON.parse(localStorage.getItem('shop-user') || 'null');
  } catch {
    localStorage.removeItem('shop-user');
    return null;
  }
}

export default function App() {
  const [user, setUser] = useState(readUser);
  const [cart, setCart] = useState(() => {
    try {
      return JSON.parse(localStorage.getItem('shop-cart') || '[]');
    } catch {
      return [];
    }
  });

  useEffect(() => {
    localStorage.setItem('shop-cart', JSON.stringify(cart));
  }, [cart]);

  useEffect(() => {
    if (!localStorage.getItem('shop-token')) return;
    api('/auth/me').then((currentUser) => {
      setUser(currentUser);
      localStorage.setItem('shop-user', JSON.stringify(currentUser));
    }).catch((requestError) => {
      if (requestError.status === 401) {
        clearAuth();
        setUser(null);
      }
    });
  }, []);

  function addToCart(product) {
    setCart((current) => {
      const existing = current.find((item) => item.product.id === product.id);
      if (existing) {
        return current.map((item) => item.product.id === product.id
          ? { ...item, product, quantity: Math.min(item.quantity + 1, product.stock) }
          : item);
      }
      return product.stock > 0 ? [...current, { product, quantity: 1 }] : current;
    });
  }

  function changeQuantity(productId, quantity) {
    if (quantity < 1) {
      setCart((current) => current.filter((item) => item.product.id !== productId));
      return;
    }
    setCart((current) => current.map((item) => item.product.id === productId
      ? { ...item, quantity: Math.min(quantity, item.product.stock) }
      : item));
  }

  function logout() {
    clearAuth();
    setUser(null);
  }

  const cartCount = cart.reduce((total, item) => total + item.quantity, 0);

  return (
    <div className="app">
      <Navbar user={user} cartCount={cartCount} onLogout={logout} />
      <Routes>
        <Route path="/" element={<ProductList onAdd={addToCart} />} />
        <Route path="/products/:id" element={<ProductDetail onAdd={addToCart} />} />
        <Route path="/cart" element={<Cart items={cart} onChangeQuantity={changeQuantity} onRemove={(id) => changeQuantity(id, 0)} />} />
        <Route path="/checkout" element={<ProtectedRoute user={user}><Checkout items={cart} user={user} onOrderPlaced={() => setCart([])} /></ProtectedRoute>} />
        <Route path="/login" element={user ? <Navigate to="/" replace /> : <Login onLogin={setUser} />} />
        <Route path="/register" element={user ? <Navigate to="/" replace /> : <Register onLogin={setUser} />} />
        <Route path="/orders" element={<ProtectedRoute user={user}><Orders /></ProtectedRoute>} />
        <Route path="*" element={<main className="page-shell empty-state"><p>That page wandered off.</p><a className="button button-dark" href="/">Back to the shop</a></main>} />
      </Routes>
      <footer className="site-footer">
        <Link className="brand" to="/"><span className="brand-mark">F.</span><span>form <i>&</i> field</span></Link>
        <p>Good things, chosen well. Made for your everyday.</p>
        <span>© {new Date().getFullYear()} Form & Field</span>
      </footer>
    </div>
  );
}
