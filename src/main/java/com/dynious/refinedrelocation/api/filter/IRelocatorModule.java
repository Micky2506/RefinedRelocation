package com.dynious.refinedrelocation.api.filter;

import com.dynious.refinedrelocation.api.tileentity.IRelocator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;

import java.util.List;

public interface IRelocatorModule
{
    public boolean onActivated(IRelocator relocator, EntityPlayer player, int side, ItemStack stack);

    public void onUpdate(IRelocator relocator, int side);

    @SideOnly(Side.CLIENT)
    public GuiScreen getGUI(IRelocator relocator);

    public Container getContainer(IRelocator relocator);

    public boolean passesFilter(ItemStack stack, boolean input);

    public void readFromNBT(NBTTagCompound compound);

    public void writeToNBT(NBTTagCompound  compound);

    public List<ItemStack> getDrops(IRelocator relocator, int side);

    public Icon getIcon(IRelocator relocator, int side);

    public void registerIcons(IconRegister register);
}