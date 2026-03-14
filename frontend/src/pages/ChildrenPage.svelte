<script>
    import { onMount } from "svelte";
    import { loadChildren } from "../stores/childrenStore";
    import ChildTable from "../components/tables/ChildTable.svelte";

    onMount(loadChildren);
</script>

<h1 class="text-2xl font-bold mb-4">Children</h1>

<ChildTable />
