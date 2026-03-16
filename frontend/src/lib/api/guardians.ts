type ApiResponse<T> = {
  data: T
  httpStatus: number
  message: string
}

export async function getGuardians(fetchFn: typeof fetch) {
  const res = await fetchFn("http://localhost:8080/api/guardians/")

  if (!res.ok) {
    throw new Error("Failed to fetch guardians")
  }

  const json: ApiResponse<any[]> = await res.json()

  return json.data
}