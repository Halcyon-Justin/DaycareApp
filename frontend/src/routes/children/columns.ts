import type { ColumnDef } from "@tanstack/table-core"
import type { Child } from "$lib/types/child"
import { renderComponent } from "$lib/components/ui/data-table";
import DataTableActions from "./data-table-actions.svelte";
import DataTableSortingButton from "./data-table-sorting-button.svelte";
import { Checkbox } from "$lib/components/ui/checkbox/index.js";


export const columns: ColumnDef<Child>[] = [
  {
    id: "select",
    header: ({ table }) =>
      renderComponent(Checkbox, {
        checked: table.getIsAllPageRowsSelected(),
        indeterminate:
          table.getIsSomePageRowsSelected() &&
          !table.getIsAllPageRowsSelected(),
        onCheckedChange: (value) => table.toggleAllPageRowsSelected(!!value),
        "aria-label": "Select all",
      }),
    cell: ({ row }) =>
      renderComponent(Checkbox, {
        checked: row.getIsSelected(),
        onCheckedChange: (value) => row.toggleSelected(!!value),
        "aria-label": "Select row",
      }),
    enableSorting: false,
    enableHiding: false,
  },
  {
    accessorKey: "firstName",
    header: "First Name"
  },
  {
    accessorKey: "lastName",
    header: "Last Name"
  },
  {
    accessorKey: "age",
    header: "Age"
  },
  {
    accessorKey: "dateOfBirth",
    header: "DOB"
  },
  {
    accessorKey: "status",
    header: "Status"
  },
  {
    accessorKey: "enrollmentDate",
    header: "Enrollment Date"
  },
  {
    accessorKey: "withdrawalDate",
    header: "Withdrawal Date"
  },
  {
    id: "actions",
    cell: ({ row }) => {
      // You can pass whatever you need from `row.original` to the component
      return renderComponent(DataTableActions, { id: row.original.id });
    },
  },

]