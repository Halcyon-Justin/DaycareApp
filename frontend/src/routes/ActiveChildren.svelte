<script>
  import { onMount } from 'svelte';
  let activeChildren = [];

  async function fetchActive() {
    const res = await fetch('http://localhost:8080/api/children/');
    const all = await res.json();
    activeChildren = all.filter(c => c.isActive);
  }

  onMount(fetchActive);
</script>

<h2>Active Children</h2>
<ul>
  {#each activeChildren as child}
    <li>{child.firstName} {child.lastName}</li>
  {/each}
</ul>