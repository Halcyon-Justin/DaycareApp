const API_BASE = 'http://localhost:8080/api';

export async function fetchChildren() {
    const res = await fetch(`${API_BASE}/children/`);
    if (!res.ok) throw new Error('Failed to fetch children');
    return res.json();
}

export async function fetchFamilies() {
    const res = await fetch(`${API_BASE}/families/`);
    if (!res.ok) throw new Error('Failed to fetch families');
    return res.json();
}