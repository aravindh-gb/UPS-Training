import { useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { api, saveAuth } from '../services/api';

export default function Login({ onLogin }) {
  const [form, setForm] = useState({ email: '', password: '' });
  const [error, setError] = useState('');
  const [submitting, setSubmitting] = useState(false);
  const navigate = useNavigate();
  const location = useLocation();

  async function submit(event) {
    event.preventDefault();
    setError('');
    setSubmitting(true);
    try {
      const auth = await api('/auth/login', { method: 'POST', body: JSON.stringify(form) });
      saveAuth(auth);
      onLogin(auth.user);
      navigate(location.state?.from || '/');
    } catch (requestError) {
      setError(requestError.message);
    } finally {
      setSubmitting(false);
    }
  }

  return (
    <main className="auth-page">
      <div className="auth-card">
        <p className="eyebrow">Welcome back</p>
        <h1>Good to see <em>you.</em></h1>
        <p className="auth-copy">Sign in to pick up where you left off.</p>
        {error && <p className="notice error-notice">{error}</p>}
        <form onSubmit={submit} className="auth-form">
          <label>Email address<input type="email" autoComplete="email" required value={form.email} onChange={(e) => setForm({ ...form, email: e.target.value })} /></label>
          <label>Password<input type="password" autoComplete="current-password" required value={form.password} onChange={(e) => setForm({ ...form, password: e.target.value })} /></label>
          <button className="button button-dark full-button" disabled={submitting}>{submitting ? 'Signing in...' : 'Sign in'} <span>↗</span></button>
        </form>
        <p className="auth-switch">New around here? <Link to="/register">Create an account</Link></p>
      </div>
    </main>
  );
}
