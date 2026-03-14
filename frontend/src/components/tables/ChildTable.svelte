<script>
    import { onMount } from "svelte";
    import {
        children,
        loading,
        error,
        loadChildren
    } from "../../stores/childrenStore";
    import { updateChild } from "../../api/children.js";

    let search = "";
    let showActiveOnly = false;
    let sortColumn = "lastName";
    let sortAsc = true;
    let currentPage = 1;
    let pageSize = 5;
    let editingChild = null;
    let showModal = false;

    onMount(loadChildren);

    function toggleSort(column) {
        if (sortColumn === column) {
            sortAsc = !sortAsc;
        } else {
            sortColumn = column;
            sortAsc = true;
        }
    }

    function openEdit(child) {
        editingChild = { ...child };
        showModal = true;
    }

    function closeModal() {
        showModal = false;
    }

    async function saveChild() {
    try {
        await updateChild(editingChild.id, editingChild);

        children.update((list) => {
            const index = list.findIndex((c) => c.id === editingChild.id);

            if (index !== -1) {
                list[index] = editingChild;
            }

            return [...list];
        });

        closeModal();
    } catch (err) {
        alert("Failed to update child");
    }
}

    $: filtered = $children
        .filter((child) =>
            `${child.firstName} ${child.lastName}`
                .toLowerCase()
                .includes(search.toLowerCase()),
        )
        .filter((child) => (showActiveOnly ? child.active : true))
        .sort((a, b) => {
            let valA = a[sortColumn];
            let valB = b[sortColumn];

            if (valA < valB) return sortAsc ? -1 : 1;
            if (valA > valB) return sortAsc ? 1 : -1;
            return 0;
        });

    $: totalItems = filtered.length;
    $: totalPages = Math.ceil(totalItems / pageSize);

    $: paginatedChildren = filtered.slice(
        (currentPage - 1) * pageSize,
        currentPage * pageSize,
    );
    $: if (currentPage > totalPages) {
        currentPage = 1;
    }
</script>

<div class="p-6">
    <h2 class="text-2xl font-bold mb-4">Children</h2>

    <div class="flex gap-4 mb-4">
        <input
            type="text"
            placeholder="Search children..."
            bind:value={search}
            class="border px-3 py-2 rounded"
        />

        <label class="flex items-center gap-2">
            <input type="checkbox" bind:checked={showActiveOnly} />
            Active Only
        </label>
    </div>

    {#if $loading}
        <p>Loading children...</p>
    {/if}

    {#if $error}
        <p class="text-red-600">{$error}</p>
    {/if}

    {#if !$loading}
        <table class="min-w-full border border-gray-300">
            <thead class="bg-gray-100">
                <tr>
                    <th
                        class="cursor-pointer px-4 py-2 border"
                        on:click={() => toggleSort("id")}
                    >
                        ID
                    </th>

                    <th
                        class="cursor-pointer px-4 py-2 border"
                        on:click={() => toggleSort("firstName")}
                    >
                        First Name
                    </th>

                    <th
                        class="cursor-pointer px-4 py-2 border"
                        on:click={() => toggleSort("lastName")}
                    >
                        Last Name
                    </th>

                    <th
                        class="cursor-pointer px-4 py-2 border"
                        on:click={() => toggleSort("dateOfBirth")}
                    >
                        Birth Date
                    </th>

                    <th
                        class="cursor-pointer px-4 py-2 border"
                        on:click={() => toggleSort("active")}
                    >
                        Active
                    </th>

                    <th class="px-4 py-2 border">Notes</th>
                    <th class="border px-4 py-2">Edit</th>
                </tr>
            </thead>

            <tbody>
                {#each paginatedChildren as child}
                    <tr
                        class={`border hover:bg-gray-50 ${!child.active ? "bg-gray-100 text-gray-500" : ""}`}
                    >
                        <td class="px-4 py-2 border">{child.id}</td>

                        <td class="px-4 py-2 border">{child.firstName}</td>

                        <td class="px-4 py-2 border">{child.lastName}</td>

                        <td class="px-4 py-2 border">{child.dateOfBirth}</td>

                        <td class="px-4 py-2 border">
                            {child.active ? "Yes" : "No"}
                        </td>

                        <td class="px-4 py-2 border">{child.notes}</td>
                        <td class="px-4 py-2 border">
                            <button
                                class="px-2 py-1 text-sm border rounded"
                                on:click={() => openEdit(child)}
                            >
                                Edit
                            </button>
                        </td>
                    </tr>
                {/each}
            </tbody>
        </table>
    {/if}
</div>
<div class="flex items-center justify-between mt-4">
    <div>
        Showing {(currentPage - 1) * pageSize + 1}
        -
        {Math.min(currentPage * pageSize, totalItems)}
        of {totalItems} children
    </div>

    <div class="flex gap-2">
        <button
            class="px-3 py-1 border rounded"
            on:click={() => currentPage--}
            disabled={currentPage === 1}
        >
            Previous
        </button>

        {#each Array(totalPages) as _, i}
            <button
                class="px-3 py-1 border rounded {currentPage === i + 1
                    ? 'bg-gray-200'
                    : ''}"
                on:click={() => (currentPage = i + 1)}
            >
                {i + 1}
            </button>
        {/each}

        <button
            class="px-3 py-1 border rounded"
            on:click={() => currentPage++}
            disabled={currentPage === totalPages}
        >
            Next
        </button>
    </div>
</div>

{#if showModal}
    <div
        class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-30"
    >
        <div class="bg-white p-6 rounded w-96">
            <h2 class="text-xl font-bold mb-4">Edit Child</h2>

            <div class="flex flex-col gap-2">
                <input
                    class="border p-2"
                    bind:value={editingChild.firstName}
                    placeholder="First Name"
                />

                <input
                    class="border p-2"
                    bind:value={editingChild.lastName}
                    placeholder="Last Name"
                />

                <input
                    class="border p-2"
                    type="date"
                    bind:value={editingChild.dateOfBirth}
                />

                <label class="flex items-center gap-2">
                    <input type="checkbox" bind:checked={editingChild.active} />

                    Active
                </label>

                <textarea
                    class="border p-2"
                    bind:value={editingChild.notes}
                    placeholder="Notes"
                />
            </div>

            <div class="flex justify-end gap-2 mt-4">
                <button class="px-3 py-1 border rounded" on:click={closeModal}>
                    Cancel
                </button>

                <button
                    class="px-3 py-1 bg-blue-600 text-white rounded"
                    on:click={saveChild}
                >
                    Save
                </button>
            </div>
        </div>
    </div>
{/if}
