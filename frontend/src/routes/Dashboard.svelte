<script>
  import ActiveChildren from './ActiveChildren.svelte';
  import DaycareData from './DaycareData.svelte';

  let activeTab = 'activeChildren';

  function switchTab(tab) {
    activeTab = tab;
  }
</script>

<!-- Tabs -->
<div class="tabs mb-4">
  <button class="tab tab-lifted" class:tab-active={activeTab === 'activeChildren'} on:click={() => switchTab('activeChildren')}>
    Active Children
  </button>
  <button class="tab tab-lifted" class:tab-active={activeTab === 'daycareData'} on:click={() => switchTab('daycareData')}>
    Daycare Data
  </button>
</div>

<!-- Tab content -->
<div class="mt-4">
  {#if activeTab === 'activeChildren'}
    <ActiveChildren />
  {:else if activeTab === 'daycareData'}
    <DaycareData />
  {/if}
</div>