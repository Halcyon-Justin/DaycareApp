<script>
    export let currentPage = 1;
    export let totalItems = 0;
    export let pageSize = 100;

    $: totalPages = Math.ceil(totalItems / pageSize);

    function prev() {
        if (currentPage > 1) currentPage -= 1;
    }

    function next() {
        if (currentPage < totalPages) currentPage += 1;
    }
</script>

<div class="flex items-center justify-between mt-4">
    <div>
        Showing {(currentPage - 1) * pageSize + 1}
        -
        {Math.min(currentPage * pageSize, totalItems)}
        of {totalItems}
    </div>

    <div class="flex gap-2">
        <button
            class="px-3 py-1 border rounded"
            on:click={prev}
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
            on:click={next}
            disabled={currentPage === totalPages}
        >
            Next
        </button>
    </div>
</div>
