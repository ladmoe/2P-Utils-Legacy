package LadsMod.Guis;

import LadsMod.Util.ConfigManager;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

public class ModGuiConfig extends GuiConfig {
    public ModGuiConfig(GuiScreen screen) {
        super(screen, getConfigElements(),
                "ladsmod",
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigManager.config.toString()));
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<IConfigElement>();

        // Add each category separately
        list.add(new ConfigElement(ConfigManager.config.getCategory("teleport")));
        list.add(new ConfigElement(ConfigManager.config.getCategory("barriers")));
        list.add(new ConfigElement(ConfigManager.config.getCategory("general")));
        list.add(new ConfigElement(ConfigManager.config.getCategory("other")));

        return list;
    }

    @Override
    public void onGuiClosed() {
        if (ConfigManager.config.hasChanged()) {
            ConfigManager.config.save();
            ConfigManager.loadConfiguration();
        }
        super.onGuiClosed();
    }

}
