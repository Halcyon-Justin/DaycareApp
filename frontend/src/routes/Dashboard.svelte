<script>
  import { user } from '../stores/user.js';
  import ActiveChildren from './ActiveChildren.svelte';
  import DaycareData from './DaycareData.svelte';

  let activeTab = 'activeChildren';

  // Check if user is admin
  const isAdmin = $user?.role?.name === 'ADMIN';
</script>

<nav>
  <button on:click={() => activeTab = 'activeChildren'}>Active Children</button>
  {#if isAdmin}
    <button on:click={() => activeTab = 'daycareData'}>Daycare Data</button>
  {/if}
</nav>

<main>
  {#if activeTab === 'activeChildren'}
    <ActiveChildren />
  {:else if activeTab === 'daycareData'}
    <DaycareData />
  {/if}
</main>