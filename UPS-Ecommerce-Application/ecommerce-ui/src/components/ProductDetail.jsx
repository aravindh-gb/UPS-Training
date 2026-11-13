import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { api, money } from '../services/api';

export default function ProductDetail({ onAdd }) {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const [error, setError] = useState('');
  const [added, setAdded] = useState(false);

  useEffect(() => {
    api(`/products/${id}`).then(setProduct).catch((requestError) => setError(requestError.message));
  }, [id]);

  if (error) return <main className="page-shell"><p className="notice error-notice">{error}</p><Link to="/">Back to the collection</Link></main>;
  if (!product) return <main className="page-shell"><p className="loading-message">Finding your piece...</p></main>;

  return (
    <main className="page-shell detail-page">
      <Link to="/" className="continue-link">← Back to the collection</Link>
      <div className="detail-layout">
        <img className="detail-image" src={product.imageUrl} alt={product.name} />
        <div className="detail-copy">
          <p className="eyebrow">{product.categoryName}</p>
          <h1>{product.name}</h1>
          <p className="detail-price">{money(product.price)}</p>
          <p className="detail-description">{product.description}</p>
          <p className="stock-note">{product.stock > 0 ? `${product.stock} ready to find a home` : 'Currently out of stock'}</p>
          <button className="button button-dark full-button" disabled={product.stock === 0} onClick={() => { onAdd(product); setAdded(true); }}>
            {added ? 'Added to your bag' : 'Add to bag'} <span>{added ? '✓' : '↗'}</span>
          </button>
          <p className="detail-footnote">Thoughtfully chosen, ready for everyday.</p>
        </div>
      </div>
    </main>
  );
}
