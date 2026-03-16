import { getChildren } from "$lib/api/children"

export async function load({ fetch }) {
  const children = await getChildren(fetch)

  return {
    children
  }
}