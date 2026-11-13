import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { api, saveAuth } from '../services/api';

export default function Register({ onLogin }) {
  const [form, setForm] = useState({ name: '', email: '', password: '' });
  const [error, setError] = useState('');
  const [submitting, setSubmitting] = useState(false);
  const navigate = useNavigate();

  async function submit(event) {
    event.preventDefault();
    setError('');
    setSubmitting(true);
    try {
      const auth = await api('/auth/register', { method: 'POST', body: JSON.stringify(form) });
      saveAuth(auth);
      onLogin(auth.user);
      navigate('/');
    } catch (requestError) {
      setError(requestError.message);
    } finally {
      setSubmitting(false);
    }
  }

  return (
    <main className="auth-page">
      <div className="auth-card">
        <p className="eyebrow">Join the good things</p>
        <h1>Come on <em>in.</em></h1>
        <p className="auth-copy">Make an account and keep all your favorites in one place.</p>
        {error && <p className="notice error-notice">{error}</p>}
        <form onSubmit={submit} className="auth-form">
          <label>Your name<input autoComplete="name" required maxLength="100" value={form.name} onChange={(e) => setForm({ ...form, name: e.target.value })} /></label>
          <label>Email address<input type="email" autoComplete="email" required value={form.email} onChange={(e) => setForm({ ...form, email: e.target.value })} /></label>
          <label>Password<input type="password" autoComplete="new-password" minLength="8" required value={form.password} onChange={(e) => setForm({ ...form, password: e.target.value })} /><small>Use at least 8 characters.</small></label>
          <button className="button button-dark full-button" disabled={submitting}>{submitting ? 'Creating account...' : 'Create account'} <span>↗</span></button>
        </form>
        <p className="auth-switch">Already have an account? <Link to="/login">Sign in</Link></p>
      </div>
    </main>
  );
}
