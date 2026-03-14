export function formatDate(date) {
  if (!date) return "";

  // If backend sends ISO datetime
  if (date.includes("T")) {
    return date.split("T")[0];
  }

  // Already yyyy-MM-dd
  return date;
}