<script>
  import { onMount } from 'svelte';

  let children = [];
  let families = [];

  let showChildForm = false;
  let formChild = null; // this will always be bound to the form

  let newChildTemplate = { firstName: '', lastName: '', isActive: true, familyId: null };

  async function fetchData() {
    const [cRes, fRes] = await Promise.all([
      fetch('http://localhost:8080/api/children/'),
      fetch('http://localhost:8080/api/families/')
    ]);
    children = await cRes.json();
    families = await fRes.json();
  }

  onMount(fetchData);

  function addChild() {
    formChild = { ...newChildTemplate };
    showChildForm = true;
  }

  function editChild(child) {
    formChild = { ...child };
    showChildForm = true;
  }

  async function saveChild() {
    if (formChild.id) {
      // update
      const res = await fetch(`http://localhost:8080/api/children/${formChild.id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formChild)
      });
      if (res.ok) await fetchData();
    } else {
      // create
      const res = await fetch('http://localhost:8080/api/children/', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formChild)
      });
      if (res.ok) await fetchData();
    }
    showChildForm = false;
    formChild = null;
  }

  async function deleteChild(id) {
    const res = await fetch(`http://localhost:8080/api/children/${id}`, { method: 'DELETE' });
    if (res.ok) await fetchData();
  }
</script>

<section>
  <h3>Children</h3>
  <button on:click={addChild}>Add New Child</button>

  {#if showChildForm}
    <div class="form">
      <input placeholder="First Name" bind:value={formChild.firstName} />
      <input placeholder="Last Name" bind:value={formChild.lastName} />
      <select bind:value={formChild.familyId}>
        <option value="" disabled>Select Family</option>
        {#each families as family}
          <option value={family.id}>Family {family.id}</option>
        {/each}
      </select>
      <label>
        Active:
        <input type="checkbox" bind:checked={formChild.isActive} />
      </label>
      <button on:click={saveChild}>{formChild.id ? 'Update' : 'Create'}</button>
      <button on:click={() => { showChildForm = false; formChild = null; }}>Cancel</button>
    </div>
  {/if}

  <ul>
    {#each children as child}
      <li>
        {child.firstName} {child.lastName} - Active: {child.isActive ? 'Yes' : 'No'}
        <button on:click={() => editChild(child)}>Edit</button>
        <button on:click={() => deleteChild(child.id)}>Delete</button>
      </li>
    {/each}
  </ul>
</section>