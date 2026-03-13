<script>
  import Login from './routes/Login.svelte';
  import Dashboard from './routes/Dashboard.svelte';
  import { user } from './stores/userStore.js';
</script>

{#if $user}
  <Dashboard />
{:else}
  <Login />
{/if}
