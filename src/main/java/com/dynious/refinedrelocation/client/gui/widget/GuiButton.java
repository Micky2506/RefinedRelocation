package com.dynious.refinedrelocation.client.gui.widget;

import com.dynious.refinedrelocation.client.gui.IGuiParent;
import com.dynious.refinedrelocation.client.gui.IGuiWidgetBase;
import com.dynious.refinedrelocation.lib.Resources;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiButton extends GuiWidgetBase
{
    protected int textureX = 0;
    protected int textureY = 0;
    protected GuiLabel label = null;

    public GuiButton(IGuiParent parent, String labelText)
    {
        super(parent);
        this.label = new GuiLabel(this, x + w / 2, y + h / 2, labelText, 0xffffff, true);
    }

    public GuiButton(IGuiParent parent, int x, int y, int w, int h, int textureX, int textureY, String labelText)
    {
        super(parent, x, y, w, h);
        this.textureX = textureX;
        this.textureY = textureY;
        this.label = new GuiLabel(this, this.x + this.w / 2, this.y + this.h / 2, labelText, 0xffffff, true);
    }

    @Override
    public void drawBackground(int mouseX, int mouseY)
    {
        mc.getTextureManager().bindTexture(Resources.GUI_SHARED);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int offsetTextureY = 0;
        if (isMouseInsideBounds(mouseX, mouseY))
        {
            offsetTextureY += this.h;
        }
        this.drawTexturedModalRect(x, y, textureX, textureY + offsetTextureY, w, h);

        for (IGuiWidgetBase child : this.children)
        {
            if (child instanceof GuiLabel)
            {
                GuiLabel childLabel = (GuiLabel) child;
                childLabel.setColor(isMouseInsideBounds(mouseX, mouseY) ? 0xffffa0 : 0xffffff);
            }
        }

        super.drawBackground(mouseX, mouseY);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int type, boolean isShiftKeyDown)
    {
        super.mouseClicked(mouseX, mouseY, type, isShiftKeyDown);
        if (isMouseInsideBounds(mouseX, mouseY) && visible)
        {
            this.mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
        }
    }
}
