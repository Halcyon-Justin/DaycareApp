<script lang="ts">
    import AlignEndHorizontalIcon from "@lucide/svelte/icons/align-end-horizontal";
    import ReceiptIcon from "@lucide/svelte/icons/receipt";
    import UserRoundPenIcon from "@lucide/svelte/icons/user-round-pen";
    import FileSpreadsheetIcon from "@lucide/svelte/icons/file-spreadsheet";
    import InboxIcon from "@lucide/svelte/icons/inbox";
    import SettingsIcon from "@lucide/svelte/icons/settings";
    import ChevronUp from "@lucide/svelte/icons/chevron-up";

    import * as Sidebar from "$lib/components/ui/sidebar/index.js";
    import * as DropdownMenu from "./ui/dropdown-menu";

    const items = [
        {
            title: "Dashboard",
            url: "#",
            icon: AlignEndHorizontalIcon,
        },
        {
            title: "Manage",
            icon: UserRoundPenIcon,
            children: [
                { title: "Families", url: "/families" },
                { title: "Children", url: "/children" },
                { title: "Guardians", url: "/guardians" },
            ],
        },
        {
            title: "Billing",
            url: "#",
            icon: ReceiptIcon,
        },
        {
            title: "Report Cards",
            url: "#",
            icon: FileSpreadsheetIcon,
        },
        {
            title: "Messages",
            url: "#",
            icon: InboxIcon,
        },
        {
            title: "Settings",
            url: "#",
            icon: SettingsIcon,
        },
    ];
</script>

<Sidebar.Root>
    <Sidebar.Header />
    <Sidebar.Content>
        <Sidebar.Group>
            <Sidebar.GroupLabel>Clem-N-Care</Sidebar.GroupLabel>
            <Sidebar.GroupContent>
                <Sidebar.Menu>
                    {#each items as item (item.title)}
                        {#if item.children}
                            <Sidebar.MenuItem>
                                <Sidebar.MenuButton>
                                    <item.icon />
                                    <span>{item.title}</span>
                                </Sidebar.MenuButton>

                                <Sidebar.MenuSub>
                                    {#each item.children as child (child.title)}
                                        <Sidebar.MenuSubItem>
                                            <Sidebar.MenuSubButton
                                                href={child.url}
                                            >
                                                {child.title}
                                            </Sidebar.MenuSubButton>
                                        </Sidebar.MenuSubItem>
                                    {/each}
                                </Sidebar.MenuSub>
                            </Sidebar.MenuItem>
                        {:else}
                            <!-- Normal menu item -->
                            <Sidebar.MenuItem>
                                <Sidebar.MenuButton>
                                    {#snippet child({ props })}
                                        <a href={item.url} {...props}>
                                            <item.icon />
                                            <span>{item.title}</span>
                                        </a>
                                    {/snippet}
                                </Sidebar.MenuButton>
                            </Sidebar.MenuItem>
                        {/if}
                    {/each}
                </Sidebar.Menu>
            </Sidebar.GroupContent>
        </Sidebar.Group>
    </Sidebar.Content>

    <Sidebar.Footer>
        <Sidebar.Menu>
            <Sidebar.MenuItem>
                <DropdownMenu.Root>
                    <DropdownMenu.Trigger>
                        {#snippet child({ props })}
                            <Sidebar.MenuButton
                                {...props}
                                class="data-[state=open]:bg-sidebar-accent data-[state=open]:text-sidebar-accent-foreground"
                            >
                                Username
                                <ChevronUp class="ms-auto" />
                            </Sidebar.MenuButton>
                        {/snippet}
                    </DropdownMenu.Trigger>
                    <DropdownMenu.Content
                        side="top"
                        class="w-(--bits-dropdown-menu-anchor-width)"
                    >
                        <DropdownMenu.Item>
                            <span>Account</span>
                        </DropdownMenu.Item>
                        <DropdownMenu.Item>
                            <span>Sign out</span>
                        </DropdownMenu.Item>
                    </DropdownMenu.Content>
                </DropdownMenu.Root>
            </Sidebar.MenuItem>
        </Sidebar.Menu>
    </Sidebar.Footer>
</Sidebar.Root>
