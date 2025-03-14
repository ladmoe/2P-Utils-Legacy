package LadsMod.Modules;

import LadsMod.Util.ConfigManager;
import LadsMod.Util.SoundPlayer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockWall;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
public class RayTeleport {
    public static void SightTeleport() {
        final Minecraft instance = Minecraft.getMinecraft();

        BlockPos objective = instance.thePlayer.rayTrace(200, 1).getBlockPos();

        Block block = instance.theWorld.getBlockState(objective).getBlock();

        if (block.isAir(Minecraft.getMinecraft().theWorld,objective)&&ConfigManager.ignoreTpIfAir) {
            SoundPlayer.fail();
            return;
        }

        double boundsY = block.getBlockBoundsMaxY();

        double x = objective.getX() + 0.5;
        double y = objective.getY() + boundsY;
        double z = objective.getZ() + 0.5;

        if (block instanceof BlockSnow) y -= 0.125;
        if (block instanceof BlockFence || block instanceof BlockWall) y += 0.5;

        instance.thePlayer.sendChatMessage("/tp " + x + " " + y + " " + z);
    }
}
