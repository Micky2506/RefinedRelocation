package com.dynious.refinedrelocation.grid.relocator;

import com.dynious.refinedrelocation.api.filter.IRelocatorModule;
import com.dynious.refinedrelocation.api.tileentity.IRelocator;
import com.dynious.refinedrelocation.helper.IOHelper;
import com.dynious.refinedrelocation.lib.Settings;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class RelocatorModuleExtraction implements IRelocatorModule
{
    private byte tick = 0;
    private int lastCheckedSlot;

    @Override
    public void onActivated(IRelocator relocator, EntityPlayer player, int side, ItemStack stack)
    {
        //NO-OP
    }

    @Override
    public void onUpdate(IRelocator relocator, int side)
    {
        tick++;
        if (tick >= Settings.RELOCATOR_MIN_TICKS_PER_EXTRACTION)
        {
            TileEntity tile = relocator.getConnectedInventories()[side];
            if (tile instanceof IInventory)
            {
                tryExtraction(relocator, (IInventory) tile, side, lastCheckedSlot);
            }
            tick = 0;
        }
    }

    public void tryExtraction(IRelocator relocator, IInventory inventory, int side, int firstChecked)
    {
        int slot = getNextSlot(inventory, ForgeDirection.getOrientation(side).getOpposite());
        ItemStack stack = inventory.getStackInSlot(slot);
        if (stack != null)
        {
            if (IOHelper.canExtractItemFromInventory(inventory, stack, slot, ForgeDirection.OPPOSITES[side]))
            {
                ItemStack returnedStack = relocator.insert(stack.copy(), side, false);
                if (returnedStack == null || stack.stackSize != returnedStack.stackSize)
                {
                    inventory.setInventorySlotContents(slot, returnedStack);
                }
            }
        }
        else if (firstChecked != lastCheckedSlot)
        {
            tryExtraction(relocator, inventory, side, firstChecked);
        }
    }

    public int getNextSlot(IInventory inventory, ForgeDirection direction)
    {
        if (inventory instanceof ISidedInventory)
        {
            ISidedInventory isidedinventory = (ISidedInventory)inventory;
            int[] accessibleSlotsFromSide = isidedinventory.getAccessibleSlotsFromSide(direction.ordinal());

            if (lastCheckedSlot < accessibleSlotsFromSide.length - 1)
            {
                lastCheckedSlot++;
            }
            else
            {
                lastCheckedSlot = 0;
            }
            return accessibleSlotsFromSide[lastCheckedSlot];
        }
        else
        {
            if (lastCheckedSlot < inventory.getSizeInventory() - 1)
            {
                lastCheckedSlot++;
            }
            else
            {
                lastCheckedSlot = 0;
            }
            return lastCheckedSlot;
        }
    }

    @Override
    public GuiScreen getGUI(IRelocator relocator)
    {
        return null;
    }

    @Override
    public Container getContainer(IRelocator relocator)
    {
        return null;
    }

    @Override
    public boolean passesFilter(ItemStack stack, boolean input)
    {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
    }
}