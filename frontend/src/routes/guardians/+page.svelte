<script lang="ts">
  import * as Table from "$lib/components/ui/table";
  import type { Guardian } from "$lib/types/guardian";

  const { data } = $props<{
    data: {
      guardian: Guardian[];
    };
  }>();
</script>

<h1 class="text-2xl font-bold mb-4">Guardians</h1>

<Table.Root class="w-full">
  <Table.Header>
    <Table.Row>
      <Table.Head>ID</Table.Head>
      <Table.Head>First Name</Table.Head>
      <Table.Head>Last Name</Table.Head>
      <Table.Head>Email</Table.Head>
      <Table.Head>Phone Number</Table.Head>
    </Table.Row>
  </Table.Header>

  <Table.Body>
    {#each data.guardian as guardian}
      <Table.Row>
        <Table.Cell>{guardian.id}</Table.Cell>
        <Table.Cell>{guardian.firstName}</Table.Cell>
        <Table.Cell>{guardian.lastName}</Table.Cell>
        <Table.Cell>{guardian.emailAddress}</Table.Cell>
        <Table.Cell>{guardian.phoneNumber}</Table.Cell>
      </Table.Row>
    {/each}
  </Table.Body>
</Table.Root>
