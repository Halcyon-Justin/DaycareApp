<script>
    import { onMount } from "svelte";

    import {
        children,
        loading,
        error,
        loadChildren,
    } from "../../stores/childrenStore";

    import { updateChild } from "../../api/children.js";

    import ChildRow from "../rows/ChildRow.svelte";
    import EditChildModal from "../modals/EditChildModal.svelte";
    import CreateChildModal from "../modals/CreateChildModal.svelte";
    import { createChild } from "../../api/children.js";

    import SearchBar from "../common/SearchBar.svelte";
    import Pagination from "../common/Pagination.svelte";

    let search = "";
    let currentPage = 1;
    let pageSize = 5;
    let showCreateModal = false;
    let editingChild = null;
    let showModal = false;

    onMount(loadChildren);

    function openEdit(child) {
        editingChild = { ...child };
        showModal = true;
    }

    function closeModal() {
        showModal = false;
    }

    function openCreate() {
        showCreateModal = true;
    }

    function closeCreate() {
        showCreateModal = false;
    }

    async function createNewChild(child) {
        await createChild(child);

        await loadChildren();

        closeCreate();
    }

    async function saveChild(child) {
        const payload = {
            ...child,
            dateOfBirth: child.dateOfBirth?.split("T")[0],
            enrollmentDate: child.enrollmentDate?.split("T")[0],
            withdrawalDate: child.withdrawalDate?.split("T")[0],
        };

        console.log("Saving child:", payload)
        await updateChild(child.id, payload);

        await loadChildren();

        closeModal();
    }

    $: filtered = $children.filter((child) =>
        `${child.firstName} ${child.lastName}`
            .toLowerCase()
            .includes(search.toLowerCase()),
    );

    $: totalItems = filtered.length;

    $: paginatedChildren = filtered.slice(
        (currentPage - 1) * pageSize,
        currentPage * pageSize,
    );
</script>

<div class="p-6">
    <h2 class="text-2xl font-bold mb-4">Children</h2>
    <div class="flex justify-between mb-4">
        <button
            class="px-4 py-2 bg-green-600 text-white rounded"
            on:click={openCreate}
        >
            + Add Child
        </button>
    </div>
    <SearchBar bind:value={search} placeholder="Search children..." />

    {#if $loading}
        <p>Loading children...</p>
    {:else}
        <table class="min-w-full border border-gray-300">
            <thead class="bg-gray-100">
                <tr>
                    <th class="px-4 py-2 border">ID</th>
                    <th class="px-4 py-2 border">First</th>
                    <th class="px-4 py-2 border">Last</th>
                    <th class="px-4 py-2 border">Birth</th>
                    <th class="px-4 py-2 border">Age</th>
                    <th class="px-4 py-2 border">Status</th>
                    <th class="px-4 py-2 border">Enrollment</th>
                    <th class="px-4 py-2 border">Withdrawal</th>
                    <th class="px-4 py-2 border">Notes</th>
                    <th class="px-4 py-2 border">Edit</th>
                </tr>
            </thead>

            <tbody>
                {#each paginatedChildren as child}
                    <ChildRow {child} on:edit={(e) => openEdit(e.detail)} />
                {/each}
            </tbody>
        </table>
    {/if}

    <Pagination bind:currentPage {totalItems} {pageSize} />
</div>

<EditChildModal
    open={showModal}
    child={editingChild}
    on:cancel={closeModal}
    on:save={(e) => saveChild(e.detail)}
/>

<CreateChildModal
    open={showCreateModal}
    on:cancel={closeCreate}
    on:create={(e) => createNewChild(e.detail)}
/>
