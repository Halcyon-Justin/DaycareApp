<script lang="ts">

  import type { Child } from "$lib/types/child";
    import { columns } from "./columns";
    import DataTable from "./data-table.svelte";

  const { data } = $props<{
    data: {
      children: Child[];
    };
  }>();
</script>

<h1 class="text-2xl font-bold mb-4">Children</h1>
<DataTable data={data.children} {columns} />
