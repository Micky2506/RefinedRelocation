package com.dynious.refinedrelocation.grid.relocator;

import com.dynious.refinedrelocation.api.APIUtils;
import com.dynious.refinedrelocation.api.filter.IFilterGUI;
import com.dynious.refinedrelocation.api.filter.IRelocatorModule;
import com.dynious.refinedrelocation.api.filter.RelocatorModuleBase;
import com.dynious.refinedrelocation.api.tileentity.IFilterTileGUI;
import com.dynious.refinedrelocation.api.tileentity.IRelocator;
import com.dynious.refinedrelocation.grid.FilterStandard;
import com.dynious.refinedrelocation.gui.GuiFiltered;
import com.dynious.refinedrelocation.gui.container.ContainerFiltered;
import com.dynious.refinedrelocation.item.ModItems;
import com.dynious.refinedrelocation.lib.Resources;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;

import java.util.ArrayList;
import java.util.List;

public class RelocatorModuleFilter extends RelocatorModuleBase
{
    private static Icon icon;
    private FilterStandard filter;

    @Override
    public void init(IRelocator relocator, int side)
    {
        filter = new FilterStandard(getFilterTile(this, relocator));
    }

    @Override
    public boolean onActivated(IRelocator relocator, EntityPlayer player, int side, ItemStack stack)
    {
        APIUtils.openRelocatorFilterGUI(relocator, player, side);
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen getGUI(IRelocator relocator, EntityPlayer player)
    {
        return new GuiFiltered(getFilterTile(this, relocator));
    }

    @Override
    public Container getContainer(IRelocator relocator, EntityPlayer player)
    {
        return new ContainerFiltered(getFilterTile(this, relocator));
    }

    @Override
    public boolean passesFilter(IRelocator relocator, int side, ItemStack stack, boolean input)
    {
        return filter.passesFilter(stack);
    }

    @Override
    public List<ItemStack> getDrops(IRelocator relocator, int side)
    {
        List<ItemStack> list = new ArrayList<ItemStack>();
        list.add(new ItemStack(ModItems.relocatorModule, 1, 1));
        return list;
    }

    private IFilterTileGUI getFilterTile(final RelocatorModuleFilter module, final IRelocator relocator)
    {
        return new IFilterTileGUI()
        {
            @Override
            public IFilterGUI getFilter()
            {
                return module.filter;
            }

            @Override
            public TileEntity getTileEntity()
            {
                return relocator.getTileEntity();
            }

            @Override
            public void onFilterChanged()
            {
                relocator.getTileEntity().onInventoryChanged();
            }
        };
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        filter.readFromNBT(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        filter.writeToNBT(compound);
    }

    @Override
    public Icon getIcon(IRelocator relocator, int side)
    {
        return icon;
    }

    @Override
    public void registerIcons(IconRegister register)
    {
        icon = register.registerIcon(Resources.MOD_ID + ":" + "relocatorModuleFilter");
    }
}
