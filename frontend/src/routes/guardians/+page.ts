import { getGuardians } from "$lib/api/guardians.js"

export async function load({ fetch }) {
  const guardian = await getGuardians(fetch)

  return {
    guardian
  }
}