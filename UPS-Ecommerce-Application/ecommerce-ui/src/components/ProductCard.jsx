import { Link } from 'react-router-dom';
import { money } from '../services/api';

export default function ProductCard({ product, onAdd }) {
  return (
    <article className="product-card">
      <Link to={`/products/${product.id}`} className="product-image-wrap">
        <img src={product.imageUrl || 'https://images.unsplash.com/photo-1490312278390-ab64016e0aa9?auto=format&fit=crop&w=900&q=85'} alt={product.name} loading="lazy" />
        {product.stock === 0 && <span className="sold-out">Sold out</span>}
      </Link>
      <div className="product-info">
        <div>
          <p className="product-category">{product.categoryName}</p>
          <Link to={`/products/${product.id}`} className="product-name">{product.name}</Link>
        </div>
        <span className="product-price">{money(product.price)}</span>
      </div>
      <button className="add-button" onClick={() => onAdd(product)} disabled={product.stock === 0}>
        {product.stock === 0 ? 'Currently unavailable' : 'Add to bag'} <span aria-hidden="true">↗</span>
      </button>
    </article>
  );
}
