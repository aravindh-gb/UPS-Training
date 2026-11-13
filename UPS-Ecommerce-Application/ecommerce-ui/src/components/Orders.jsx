import { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { api, money } from '../services/api';

export default function Orders() {
  const [orders, setOrders] = useState([]);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(true);
  const location = useLocation();
  const placedId = new URLSearchParams(location.search).get('placed');

  useEffect(() => {
    api('/orders')
      .then(setOrders)
      .catch((requestError) => setError(requestError.message))
      .finally(() => setLoading(false));
  }, []);

  return (
    <main className="page-shell">
      <p className="eyebrow">A little look back</p>
      <h1 className="page-title">Your <em>orders.</em></h1>
      {placedId && <p className="notice success-notice">Order #{placedId} is in. Thanks for choosing us.</p>}
      {error && <p className="notice error-notice">{error}</p>}
      {loading ? <p className="loading-message">Gathering your orders...</p> : orders.length ? (
        <div className="orders-list">
          {orders.map((order) => (
            <article className="order-card" key={order.id}>
              <div className="order-card-heading">
                <div><p className="product-category">Order #{order.id}</p><time>{new Date(order.createdAt).toLocaleDateString(undefined, { year: 'numeric', month: 'long', day: 'numeric' })}</time></div>
                <div className="order-meta"><span className={`status status-${order.status.toLowerCase()}`}>{order.status}</span><strong>{money(order.total)}</strong></div>
              </div>
              <div className="order-products">
                {order.items.map((item) => <p key={`${order.id}-${item.productId}`}>{item.productName} <span>× {item.quantity}</span></p>)}
              </div>
            </article>
          ))}
        </div>
      ) : !error && !loading ? (
        <div className="empty-state"><p>No orders yet. There’s always a first favorite.</p><Link className="button button-dark" to="/">Explore the collection</Link></div>
      ) : null}
    </main>
  );
}
