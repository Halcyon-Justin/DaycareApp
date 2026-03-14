<script>
    export let value = "";
    export let placeholder = "Search...";
</script>

<input
    type="text"
    bind:value
    {placeholder}
    class="border px-3 py-2 rounded mb-4 w-full"
/>
