<script>
  import { onMount } from 'svelte';

  let activeChildren = [];

  async function fetchActiveChildren() {
    const res = await fetch('/api/children/');
    const allChildren = await res.json();
    activeChildren = allChildren.filter(c => c.isActive);
  }

  onMount(fetchActiveChildren);
</script>

<h2 class="text-2xl font-bold mb-4">Active Children</h2>

<ul class="space-y-2">
  {#each activeChildren as child}
    <li class="p-4 bg-green-100 rounded shadow cursor-pointer hover:bg-green-200">
      {child.firstName} {child.lastName}
    </li>
  {/each}
</ul>