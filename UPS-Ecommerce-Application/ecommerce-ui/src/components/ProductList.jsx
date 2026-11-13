import { useEffect, useState } from 'react';
import { api } from '../services/api';
import ProductCard from './ProductCard';

export default function ProductList({ onAdd }) {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [categoryId, setCategoryId] = useState('');
  const [query, setQuery] = useState('');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    api('/categories')
      .then(setCategories)
      .catch((requestError) => setError(requestError.message));
  }, []);

  useEffect(() => {
    const params = new URLSearchParams();
    if (categoryId) params.set('categoryId', categoryId);
    if (query.trim()) params.set('q', query.trim());
    setLoading(true);
    setError('');
    api(`/products${params.size ? `?${params}` : ''}`)
      .then(setProducts)
      .catch((requestError) => setError(requestError.message))
      .finally(() => setLoading(false));
  }, [categoryId, query]);

  return (
    <>
      <section className="hero">
        <div className="hero-copy">
          <p className="eyebrow">Good things, chosen well</p>
          <h1>Make room for<br /><em>the everyday.</em></h1>
          <p className="hero-description">Considered objects for slower mornings, softer spaces, and all the little moments in between.</p>
          <a className="button button-dark" href="#collection">Explore the collection <span>↓</span></a>
        </div>
        <div className="hero-image">
          <img src="https://images.unsplash.com/photo-1616486338812-3dadae4b4ace?auto=format&fit=crop&w=1400&q=90" alt="Warm, thoughtfully styled living space" />
          <div className="image-note"><span>01 / 04</span><span>Objects with intention</span></div>
        </div>
        <div className="hero-side-note">MADE FOR THE WAY YOU LIVE</div>
      </section>

      <section id="collection" className="collection section-shell">
        <div className="section-heading">
          <div><p className="eyebrow">The good stuff</p><h2>Find your new <em>favorite.</em></h2></div>
          <p className="section-intro">Useful, beautiful, and made to be lived with.</p>
        </div>
        <div className="catalog-tools">
          <div className="category-pills">
            <button className={!categoryId ? 'active' : ''} onClick={() => setCategoryId('')}>Everything</button>
            {categories.map((category) => (
              <button key={category.id} className={categoryId === String(category.id) ? 'active' : ''}
                onClick={() => setCategoryId(String(category.id))}>{category.name}</button>
            ))}
          </div>
          <label className="search-field">
            <span aria-hidden="true">⌕</span>
            <input value={query} onChange={(event) => setQuery(event.target.value)} placeholder="Find something..." aria-label="Search products" />
          </label>
        </div>
        {error && <p className="notice error-notice">{error}</p>}
        {loading ? <p className="loading-message">Finding the good stuff...</p> : (
          products.length ? (
            <div className="product-grid">
              {products.map((product) => <ProductCard key={product.id} product={product} onAdd={onAdd} />)}
            </div>
          ) : (
            <div className="empty-state"><p>No pieces found just yet.</p><span>Try another search or category.</span></div>
          )
        )}
      </section>
      <section className="values-strip">
        <span>01&nbsp; Thoughtfully selected</span><span>02&nbsp; Made to be lived with</span><span>03&nbsp; Delivered with care</span>
      </section>
    </>
  );
}
