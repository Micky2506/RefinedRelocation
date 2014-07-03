package com.dynious.refinedrelocation.grid.relocator;

import com.dynious.refinedrelocation.api.relocator.IItemRelocator;
import com.dynious.refinedrelocation.api.relocator.IRelocatorModule;
import com.dynious.refinedrelocation.api.relocator.RelocatorModuleBase;
import com.dynious.refinedrelocation.grid.relocator.RelocatorModuleRegistry;
import com.dynious.refinedrelocation.gui.GuiHome;
import com.dynious.refinedrelocation.gui.GuiModularTest;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

import java.util.ArrayList;
import java.util.List;

public class RelocatorMultiModule extends RelocatorModuleBase
{
    private IRelocatorModule[] modules = new IRelocatorModule[2];

    @Override
    public void init(IItemRelocator relocator, int side)
    {
        for (IRelocatorModule module : modules)
        {
            if (module != null)
                module.init(relocator, side);
        }
    }

    @Override
    public boolean onActivated(IItemRelocator relocator, EntityPlayer player, int side, ItemStack stack)
    {
        return super.onActivated(relocator, player, side, stack);
    }

    @Override
    public void onUpdate(IItemRelocator relocator, int side)
    {
        for (IRelocatorModule module : modules)
        {
            if (module != null)
                module.onUpdate(relocator, side);
        }
    }

    @Override
    public ItemStack outputToSide(IItemRelocator relocator, int side, TileEntity inventory, ItemStack stack, boolean simulate)
    {
        ItemStack returned = stack;
        for (IRelocatorModule module : modules)
        {
            if (module != null)
                returned = module.outputToSide(relocator, side, inventory, stack, simulate);
            if (returned == null)
                return null;
        }
        return returned;
    }

    @Override
    public void onRedstonePowerChange(boolean isPowered)
    {
        for (IRelocatorModule module : modules)
        {
            if (module != null)
                module.onRedstonePowerChange(isPowered);
        }
    }

    @Override
    public int strongRedstonePower(int side)
    {
        int power = 0;
        for (IRelocatorModule module : modules)
        {
            if (module != null)
            {
                int p = module.strongRedstonePower(side);
                if (p > power)
                    power = p;
            }
        }
        return power;
    }

    @Override
    public boolean connectsToRedstone()
    {
        for (IRelocatorModule module : modules)
        {
            if (module != null && module.connectsToRedstone())
                return true;
        }
        return false;
    }

    @Override
    public GuiScreen getGUI(IItemRelocator relocator, EntityPlayer player)
    {
        return super.getGUI(relocator, player);
    }

    @Override
    public Container getContainer(IItemRelocator relocator, EntityPlayer player)
    {
        return super.getContainer(relocator, player);
    }

    @Override
    public boolean passesFilter(IItemRelocator relocator, int side, ItemStack stack, boolean input, boolean simulate)
    {
        for (IRelocatorModule module : modules)
        {
            if (module != null && !module.passesFilter(relocator, side, stack, input, simulate))
                return false;
        }
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        NBTTagList list = compound.getTagList("multiModules", 10);
        for (int i = 0; i < list.tagCount(); i++)
        {
            NBTTagCompound compound1 = list.getCompoundTagAt(i);
            IRelocatorModule module = RelocatorModuleRegistry.getModule(compound1.getString("clazzIdentifier"));
            if (module != null)
            {
                modules[i] = module;
                modules[i].readFromNBT(compound1);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        NBTTagList list = new NBTTagList();
        for (IRelocatorModule module : modules)
        {
            if (module != null)
            {
                NBTTagCompound compound1 = new NBTTagCompound();
                compound1.setString("clazzIdentifier", RelocatorModuleRegistry.getIdentifier(module.getClass()));
                module.writeToNBT(compound1);
                list.appendTag(compound1);
            }
        }
        compound.setTag("multiModules", list);
    }

    @Override
    public List<ItemStack> getDrops(IItemRelocator relocator, int side)
    {
        List<ItemStack> drops = new ArrayList<ItemStack>();
        for (IRelocatorModule module : modules)
        {
            if (module != null)
                drops.addAll(module.getDrops(relocator, side));
        }
        return drops;
    }

    @Override
    public IIcon getIcon(IItemRelocator relocator, int side)
    {
        return null;
    }

    @Override
    public void registerIcons(IIconRegister register)
    {
        super.registerIcons(register);
    }
}
