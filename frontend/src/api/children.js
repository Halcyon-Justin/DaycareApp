import { apiRequest } from "./client";

export function fetchChildren() {
  return apiRequest("/children/").then(res => res.data);
}

export async function createChild(child) {

  return apiRequest("/children", {
    method: "POST",
    body: JSON.stringify(child)
  });

}

export function updateChild(id, data) {
  return apiRequest(`/children/${id}`, {
    method: "PUT",
    body: JSON.stringify(data)
  });
}