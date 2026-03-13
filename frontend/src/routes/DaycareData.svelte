<script>
  import { writable } from 'svelte/store';

  // Stores for data
  export const children = writable([]);
  export const families = writable([]);
  export const invoices = writable([]);
  export const addresses = writable([]);

  // Local component state
  let selectedChild = null;
  let modalVisible = false;

  // Fetch data from backend
  async function fetchData() {
    const [cRes, fRes, iRes, aRes] = await Promise.all([
      fetch('/api/children/'),
      fetch('/api/families/'),
      fetch('/api/invoices/'),
      fetch('/api/addresses/')
    ]);
    children.set(await cRes.json());
    families.set(await fRes.json());
    invoices.set(await iRes.json());
    addresses.set(await aRes.json());
  }

  fetchData();

  // Open child modal
  function openChildModal(child) {
    selectedChild = child;
    modalVisible = true;
  }

  function closeModal() {
    selectedChild = null;
    modalVisible = false;
  }
</script>

<section class="p-4">
  <h2 class="text-2xl font-bold mb-4">Daycare Data</h2>

  <ul class="space-y-2">
    {#each $children as child}
      <li>
        <button
          class="w-full text-left p-4 bg-base-200 rounded shadow cursor-pointer hover:bg-base-300"
          on:click={() => openChildModal(child)}
        >
          {child.firstName} {child.lastName} - Active: {child.isActive ? 'Yes' : 'No'}
        </button>
      </li>
    {/each}
  </ul>

  {#if modalVisible}
    <div class="modal modal-open">
      <div class="modal-box">
        <h3 class="font-bold text-lg mb-4">{selectedChild.firstName} {selectedChild.lastName}</h3>

        <!-- Family / Guardians -->
        <div class="mb-4">
          <h4 class="font-semibold mb-2">Family / Guardians</h4>
          <ul class="ml-4 list-disc">
            {#each $families.filter(f => f.id === selectedChild.familyId) as fam}
              <li>{fam.guardianName} ({fam.relationship})</li>
            {/each}
          </ul>
        </div>

        <div class="modal-action">
          <button class="btn btn-primary" on:click={closeModal}>Close</button>
        </div>
      </div>
    </div>
  {/if}
</section>