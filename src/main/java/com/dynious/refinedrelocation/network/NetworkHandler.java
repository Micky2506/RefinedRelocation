package com.dynious.refinedrelocation.network;

import com.dynious.refinedrelocation.lib.Reference;
import com.dynious.refinedrelocation.network.packet.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class NetworkHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

    public static void init()
    {
        INSTANCE.registerMessage(MessageMaxStackSize.class, MessageMaxStackSize.class, 0, Side.SERVER);
        INSTANCE.registerMessage(MessageInsertDirection.class, MessageInsertDirection.class, 1, Side.SERVER);
        INSTANCE.registerMessage(MessageSpread.class, MessageSpread.class, 2, Side.SERVER);
        INSTANCE.registerMessage(MessageUserFilter.class, MessageUserFilter.class, 3, Side.SERVER);
        INSTANCE.registerMessage(MessageUserFilter.class, MessageUserFilter.class, 4, Side.CLIENT);
        INSTANCE.registerMessage(MessageBlackList.class, MessageBlackList.class, 5, Side.SERVER);
        INSTANCE.registerMessage(MessageFilterOption.class, MessageFilterOption.class, 6, Side.SERVER);
        INSTANCE.registerMessage(MessageTabSync.class, MessageTabSync.class, 7, Side.CLIENT);
        INSTANCE.registerMessage(MessageRestrictExtraction.class, MessageRestrictExtraction.class, 8, Side.SERVER);
        INSTANCE.registerMessage(MessageRedstoneEnabled.class, MessageRedstoneEnabled.class, 9, Side.SERVER);
        INSTANCE.registerMessage(MessageSwitchPage.class, MessageSwitchPage.class, 10, Side.SERVER);
        INSTANCE.registerMessage(MessageSetMaxPower.class, MessageSetMaxPower.class, 11, Side.SERVER);
        INSTANCE.registerMessage(MessageSetMaxPower.class, MessageSetMaxPower.class, 12, Side.CLIENT);
        INSTANCE.registerMessage(MessagePriority.class, MessagePriority.class, 13, Side.SERVER);
        INSTANCE.registerMessage(MessageItemList.class, MessageItemList.class, 14, Side.CLIENT);
        INSTANCE.registerMessage(MessageSide.class, MessageSide.class, 15, Side.SERVER);
        INSTANCE.registerMessage(MessageHomeButtonClicked.class, MessageHomeButtonClicked.class, 16, Side.SERVER);
        INSTANCE.registerMessage(MessageKonga.class, MessageKonga.class, 17, Side.CLIENT);
        INSTANCE.registerMessage(MessageExtractionSide.class, MessageExtractionSide.class, 18, Side.SERVER);
        INSTANCE.registerMessage(MessageTicksBetweenExtraction.class, MessageTicksBetweenExtraction.class, 19, Side.SERVER);
        INSTANCE.registerMessage(MessageExtractOnRedstoneSignal.class, MessageExtractOnRedstoneSignal.class, 20, Side.SERVER);
        INSTANCE.registerMessage(MessageModuleMaxStackSize.class, MessageModuleMaxStackSize.class, 21, Side.SERVER);
        INSTANCE.registerMessage(MessageRedstoneToggle.class, MessageRedstoneToggle.class, 22, Side.SERVER);
        INSTANCE.registerMessage(MessageSetFilterOption.class, MessageSetFilterOption.class, 23, Side.CLIENT);
        INSTANCE.registerMessage(MessageMaxCraftStack.class, MessageMaxCraftStack.class, 24, Side.SERVER);
    }
}
