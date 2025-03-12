package LadsMod.Util;

import net.minecraft.client.Minecraft;

public class SoundPlayer {
    public static void success() {
        if (ConfigManager.teleportFeedback)
            Minecraft.getMinecraft().thePlayer.playSound(ConfigManager.teleportSoundType, 0.3f, 1);
    }
    public static void fail() {
        if (ConfigManager.teleportFeedback)
            Minecraft.getMinecraft().thePlayer.playSound(ConfigManager.teleportFailSoundType, 0.5f, 1);
    }
}
