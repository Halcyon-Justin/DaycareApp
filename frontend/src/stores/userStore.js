
import { writable } from 'svelte/store';

// Store the currently logged-in user
// Example shape: { id: 1, username: 'admin', role: 'ADMIN' }
export const user = writable(null);

// Convenience function to set user after login
export function setUser(userData) {
    // Ensure role is uppercase for consistent checking
    if (userData?.role) {
        userData.role = userData.role.toUpperCase();
    }
    user.set(userData);
}

// Convenience function to log out
export function clearUser() {
    user.set(null);
}