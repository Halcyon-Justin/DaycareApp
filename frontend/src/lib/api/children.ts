type ApiResponse<T> = {
  data: T
  httpStatus: number
  message: string
}

export async function getChildren(fetchFn: typeof fetch) {
  const res = await fetchFn("http://localhost:8080/api/children/")

  if (!res.ok) {
    throw new Error("Failed to fetch children")
  }

  const json: ApiResponse<any[]> = await res.json()

  return json.data
}