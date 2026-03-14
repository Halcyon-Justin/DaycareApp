<script>
    import { createEventDispatcher } from "svelte";

    export let open = false;

    const dispatch = createEventDispatcher();

    let child = {
        firstName: "",
        lastName: "",
        dateOfBirth: "",
        status: "WAITLIST",
        notes: "",
    };

    function cancel() {
        dispatch("cancel");
    }

    function save() {
        dispatch("create", child);

        child = {
            firstName: "",
            lastName: "",
            dateOfBirth: "",
            status: "WAITLIST",
            notes: "",
        };
    }
</script>

{#if open}
    <div
        class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-30"
    >
        <div class="bg-white p-6 rounded w-96">
            <h2 class="text-xl font-bold mb-4">Create Child</h2>

            <div class="flex flex-col gap-2">
                <input
                    class="border p-2"
                    bind:value={child.firstName}
                    placeholder="First Name"
                />

                <input
                    class="border p-2"
                    bind:value={child.lastName}
                    placeholder="Last Name"
                />

                <input
                    class="border p-2"
                    type="date"
                    bind:value={child.dateOfBirth}
                />

                <select class="border p-2" bind:value={child.status}>
                    <option value="WAITLIST">Waitlist</option>
                    <option value="ENROLLED">Enrolled</option>
                    <option value="SUSPENDED">Suspended</option>
                    <option value="WITHDRAWN">Withdrawn</option>
                    <option value="GRADUATED">Graduated</option>
                </select>

                <textarea
                    class="border p-2"
                    bind:value={child.notes}
                    placeholder="Notes"
                ></textarea>
            </div>

            <div class="flex justify-end gap-2 mt-4">
                <button class="px-3 py-1 border rounded" on:click={cancel}>
                    Cancel
                </button>

                <button
                    class="px-3 py-1 bg-green-600 text-white rounded"
                    on:click={save}
                >
                    Create
                </button>
            </div>
        </div>
    </div>
{/if}
