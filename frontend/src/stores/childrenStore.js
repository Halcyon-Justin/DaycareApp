import { writable } from "svelte/store";
import { fetchChildren } from "../api/children";

export const children = writable([]);
export const loading = writable(false);
export const error = writable(null);

export async function loadChildren() {
  loading.set(true);
  error.set(null);

  try {
    const data = await fetchChildren();
    children.set(data);
  } catch (err) {
    error.set(err.message);
  } finally {
    loading.set(false);
  }
}