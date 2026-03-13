import { writable } from 'svelte/store';

// Initialize with null (no user logged in)
export const user = writable(null);

/**
 * Example of a user object structure:
 * {
 *   id: 1,
 *   username: 'admin',
 *   role: { id: 1, name: 'ADMIN' },
 *   token: 'JWT_TOKEN_IF_USED'
 * }
 */