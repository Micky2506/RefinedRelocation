package com.dynious.refinedrelocation.api.tileentity;

import com.dynious.refinedrelocation.api.tileentity.handlers.SortingInventoryHandler;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * This extends the {@link ISortingMember} interface.
 * Tile that implement this interface will be part of the Sorting Network
 * and their inventory will actively be used in the network.
 * The Filter set in the IFilterTile interface will be used as the filter.
 *
 * Make sure you call all required methods of {@link ISortingMember} as well as
 * setInventorySlotContents(...) when this is called in your tile.
 *
 * To open the Filtering GUI for this TileEntity also implement {@link IFilterTileGUI}).
 *
 * THIS DOES NOT AUTOMATICALLY SYNC THE INVENTORY WITH THE CLIENT!
 *
 * To sync the inventory follow these steps:
 * Make sure you call addCrafter(...) when a player opens the container of this
 * tile (in constructor of {@link net.minecraft.inventory.Container})
 * and removeCrafter(...) when a container is closed (onContainerClosed(...) in {@link net.minecraft.inventory.Container}).
 * You also need to override putStackInSlot(...) in the container of your TileEntity and call putStackInSlot(...).
 */
public interface ISortingInventory extends ISortingMember, IInventory, IFilterTile
{
    /**
     * This should return the SortingInventoryHandler of this tile. It cannot be null.
     *
     * @return The SortingInventoryHandler of this tile
     */
    public SortingInventoryHandler getSortingHandler();

    /**
     * Should return all stored ItemStacks in this tile.
     *
     * @return The stored ItemStacks
     */
    public ItemStack[] getInventory();

    /**
     * The Sorting System will try to put items in the highest priority inventory.
     * Blacklisting Chests have a low Priority, while whitelisting chests have a normal priority.
     * Barrels have the high priority, because they only accept one type of item.
     * This will not affect items that do not pass the Filter.
     *
     * @return The Priority of this ISortingInventory
     */
    public Priority getPriority();

    enum Priority
    {
        HIGH,
        NORMAL,
        LOW
    }
}