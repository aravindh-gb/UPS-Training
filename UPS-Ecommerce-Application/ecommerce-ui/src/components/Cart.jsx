import { Link } from 'react-router-dom';
import { money } from '../services/api';

export default function Cart({ items, onChangeQuantity, onRemove }) {
  const total = items.reduce((sum, item) => sum + item.product.price * item.quantity, 0);

  return (
    <main className="page-shell">
      <p className="eyebrow">Your selection</p>
      <h1 className="page-title">The <em>bag.</em></h1>
      {!items.length ? (
        <div className="empty-state cart-empty"><p>Your bag is taking a little breather.</p><Link className="button button-dark" to="/">Find something lovely</Link></div>
      ) : (
        <div className="cart-layout">
          <div className="cart-items">
            {items.map(({ product, quantity }) => (
              <article className="cart-item" key={product.id}>
                <img src={product.imageUrl} alt={product.name} />
                <div className="cart-item-info">
                  <p className="product-category">{product.categoryName}</p>
                  <h2>{product.name}</h2>
                  <span>{money(product.price)}</span>
                  <div className="quantity-control">
                    <button onClick={() => onChangeQuantity(product.id, quantity - 1)} aria-label="Decrease quantity">−</button>
                    <span>{quantity}</span>
                    <button onClick={() => onChangeQuantity(product.id, quantity + 1)} aria-label="Increase quantity" disabled={quantity >= product.stock}>+</button>
                    <button className="remove-button" onClick={() => onRemove(product.id)}>Remove</button>
                  </div>
                </div>
                <strong>{money(product.price * quantity)}</strong>
              </article>
            ))}
          </div>
          <aside className="order-summary">
            <h2>A little summary</h2>
            <div><span>Subtotal</span><strong>{money(total)}</strong></div>
            <div><span>Shipping</span><span>Calculated at checkout</span></div>
            <p>Shipping and any applicable taxes will be confirmed before your order is placed.</p>
            <Link to="/checkout" className="button button-dark full-button">Continue to checkout <span>↗</span></Link>
            <Link to="/" className="continue-link">← Keep looking around</Link>
          </aside>
        </div>
      )}
    </main>
  );
}
