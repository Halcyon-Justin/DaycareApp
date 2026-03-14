<script>
    import { createEventDispatcher } from "svelte";
    import { formatDate } from "../../utils/date.js";

    export let child;

    const dispatch = createEventDispatcher();

    function editChild() {
        dispatch("edit", child);
    }
</script>

<tr class="border hover:bg-gray-50">
    <td class="px-4 py-2 border">{child.id}</td>

    <td class="px-4 py-2 border">{child.firstName}</td>

    <td class="px-4 py-2 border">{child.lastName}</td>

    <td class="px-4 py-2 border">
        {formatDate(child.dateOfBirth)}
    </td>

    <td class="px-4 py-2 border">
        {child.age}
    </td>

    <td class="px-4 py-2 border">
        {child.status}
    </td>

    <td class="px-4 py-2 border">
        {formatDate(child.enrollmentDate)}
    </td>

    <td class="px-4 py-2 border">
        {formatDate(child.withdrawalDate)}
    </td>

    <td class="px-4 py-2 border">
        {child.notes}
    </td>

    <td class="px-4 py-2 border">
        <button class="px-2 py-1 border rounded" on:click={editChild}>
            Edit
        </button>
    </td>
</tr>
