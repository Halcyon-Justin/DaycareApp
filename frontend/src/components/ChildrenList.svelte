<script>
  import { createEventDispatcher } from 'svelte';
  export let children = [];

  const dispatch = createEventDispatcher();

  function handleSelect(child) {
    dispatch('selectChild', child);
  }
</script>

<ul>
  {#each children as child}
    <li>
      <button on:click={() => handleSelect(child)}>
        {child.firstName} {child.lastName} {child.isActive ? '(Active)' : '(Inactive)'}
      </button>
    </li>
  {/each}
</ul>