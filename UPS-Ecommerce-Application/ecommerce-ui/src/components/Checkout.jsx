import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { api, money } from '../services/api';

export default function Checkout({ items, user, onOrderPlaced }) {
  const [error, setError] = useState('');
  const [placing, setPlacing] = useState(false);
  const navigate = useNavigate();
  const total = items.reduce((sum, item) => sum + item.product.price * item.quantity, 0);

  async function placeOrder() {
    setError('');
    setPlacing(true);
    try {
      const order = await api('/orders', {
        method: 'POST',
        body: JSON.stringify({ items: items.map(({ product, quantity }) => ({ productId: product.id, quantity })) }),
      });
      onOrderPlaced();
      navigate(`/orders?placed=${order.id}`);
    } catch (requestError) {
      setError(requestError.message);
    } finally {
      setPlacing(false);
    }
  }

  if (!items.length) {
    return <main className="page-shell"><div className="empty-state"><p>Your bag is empty.</p><Link className="button button-dark" to="/">Browse the collection</Link></div></main>;
  }

  return (
    <main className="page-shell checkout-page">
      <p className="eyebrow">Almost yours</p>
      <h1 className="page-title">A good choice, <em>{user.name.split(' ')[0]}.</em></h1>
      <p className="checkout-lede">Review your order before placing it. Payment and delivery arrangements are not processed by this demo store.</p>
      {error && <p className="notice error-notice">{error}</p>}
      <div className="checkout-card">
        <div className="checkout-lines">
          {items.map(({ product, quantity }) => (
            <div className="checkout-line" key={product.id}>
              <span>{product.name} <small>× {quantity}</small></span><strong>{money(product.price * quantity)}</strong>
            </div>
          ))}
        </div>
        <div className="checkout-total"><span>Order total</span><strong>{money(total)}</strong></div>
        <button className="button button-dark full-button" onClick={placeOrder} disabled={placing}>
          {placing ? 'Placing your order...' : 'Place order'} <span>↗</span>
        </button>
        <Link to="/cart" className="continue-link">← Back to your bag</Link>
      </div>
    </main>
  );
}
