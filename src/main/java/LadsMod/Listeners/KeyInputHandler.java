package LadsMod.Listeners;

import LadsMod.Guis.ModGuiConfig;
import LadsMod.Modules.RayTeleport;
import LadsMod.Main;
import LadsMod.Modules.SeeBarriers;
import LadsMod.Util.BlockChanger;
import LadsMod.Util.ConfigManager;
import LadsMod.Util.SoundPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KeyInputHandler {

    private static boolean tpKeyWasPressed = false;
    public static boolean ignoreTeleport = false;
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onKey(KeyInputEvent event) {
        if (Main.openConfig.isPressed()) {
            FMLClientHandler.instance().getClient().displayGuiScreen(new ModGuiConfig(null));
        }

        if (Main.openDebug.isPressed()) {
            Minecraft.getMinecraft().refreshResources();
        }

        if (Main.seeBarrier.isKeyDown()) {
            if (ConfigManager.doWoolBarrier)
                SeeBarriers.grabBlocks(true);
            SeeBarriers.grabBlocks(false);

        } else {
            BlockChanger.revertAllBlocks();
            SeeBarriers.blocks.clear();
        }

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase.equals(TickEvent.Phase.END)) {
            if (Main.zipKey.isKeyDown()) {
                if (!tpKeyWasPressed) {
                    if (!ignoreTeleport) {
                        RayTeleport.SightTeleport();
                        SoundPlayer.success();
                    }
                    else {
                        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Ignored teleport binding: in parkour"));
                        SoundPlayer.fail();
                    }
                    tpKeyWasPressed = true;

                }
            } else tpKeyWasPressed = false;
        }
    }
}