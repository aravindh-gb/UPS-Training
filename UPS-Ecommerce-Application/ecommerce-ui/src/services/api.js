const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';

export async function api(path, options = {}) {
  const token = localStorage.getItem('shop-token');
  const response = await fetch(`${API_URL}${path}`, {
    ...options,
    headers: {
      ...(options.body ? { 'Content-Type': 'application/json' } : {}),
      ...(token ? { Authorization: `Bearer ${token}` } : {}),
      ...options.headers,
    },
  });

  if (!response.ok) {
    const body = await response.json().catch(() => null);
    const message = typeof body?.message === 'string'
      ? body.message
      : body && typeof body === 'object'
        ? Object.values(body).join(' ')
        : `Request failed (${response.status})`;
    const error = new Error(message || `Request failed (${response.status})`);
    error.status = response.status;
    throw error;
  }

  if (response.status === 204) return null;
  return response.json();
}

export function saveAuth(auth) {
  localStorage.setItem('shop-token', auth.token);
  localStorage.setItem('shop-user', JSON.stringify(auth.user));
}

export function clearAuth() {
  localStorage.removeItem('shop-token');
  localStorage.removeItem('shop-user');
}

export function money(value) {
  return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(value);
}
