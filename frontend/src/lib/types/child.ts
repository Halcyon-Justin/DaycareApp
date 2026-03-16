export type Child = {
  id: number
  firstName: string
  lastName: string
  age: number
  dateOfBirth: string
  enrollmentDate: string
  withdrawalDate: string | null
  familyId: number
  notes: string
  status: "ENROLLED" | "WITHDRAWN" | "SUSPENDED" | "GRADUATED"
  createdAt: string | null
  updatedAt: string | null
}