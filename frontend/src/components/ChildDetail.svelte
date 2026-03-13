<script>
  import { onMount } from 'svelte';
  export let selectedChild;

  let family = null;
  let invoices = [];
  let reportCards = [];

  onMount(async () => {
    try {
      // Family
      const famRes = await fetch(`http://localhost:8080/api/families/${selectedChild.familyId}`);
      family = (await famRes.json()).data;

      // Invoices
      const invRes = await fetch(`http://localhost:8080/api/invoices/family/${selectedChild.familyId}`);
      invoices = (await invRes.json()).data;

      // Report cards
      const rcRes = await fetch(`http://localhost:8080/api/report-cards/child/${selectedChild.id}`);
      reportCards = (await rcRes.json()).data;
    } catch (err) {
      console.error('Failed to fetch child related data', err);
    }
  });
</script>

<h3>{selectedChild.firstName} {selectedChild.lastName}</h3>

{#if family}
  <h4>Family</h4>
  <p>ID: {family.id}</p>
  <p>Address: {family.address.streetAddress}, {family.address.city}, {family.address.state} {family.address.zipCode}</p>
{/if}

<h4>Invoices</h4>
<ul>
  {#each invoices as invoice}
    <li>{invoice.dueDate} - {invoice.amountDue} - {invoice.status}</li>
  {/each}
</ul>

<h4>Report Cards</h4>
<ul>
  {#each reportCards as rc}
    <li>{rc.subject}: {rc.grade}</li>
  {/each}
</ul>