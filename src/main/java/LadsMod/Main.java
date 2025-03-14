package LadsMod;

import LadsMod.Listeners.EventListener;
import LadsMod.Listeners.KeyInputHandler;
import LadsMod.Modules.MeasureInput;
import LadsMod.Modules.TeleportLine;
import LadsMod.Util.ConfigManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        modid="ladsmod",
        name="2PUtils",
        version= "1.2",
        guiFactory = "LadsMod.Guis.GuiFactory",
        canBeDeactivated = true,
        clientSideOnly = true,
        acceptedMinecraftVersions="[1.8.9]"
)
public class Main
{

    public static KeyBinding zipKey = new KeyBinding("Jump", Keyboard.CHAR_NONE, "2P Utils" +
            "");
    public static KeyBinding extendLine = new KeyBinding("Extend line", Keyboard.CHAR_NONE, "2P Utils" +
            "");
    public static KeyBinding openDebug = new KeyBinding("Reload Resources (aka f3 s)", Keyboard.CHAR_NONE, "2P Utils" +
            "");
    public static KeyBinding measure = new KeyBinding("Measure",Keyboard.CHAR_NONE,"2P Utils" +
            "");
    public static KeyBinding toggleSprint = new KeyBinding("Toggle Sprint",Keyboard.CHAR_NONE,"key.categories.movement");
    public static KeyBinding seeBarrier = new KeyBinding("See barriers", Keyboard.CHAR_NONE, "2P Utils" + "");
    public static KeyBinding openConfig = new KeyBinding("Open Settings",Keyboard.CHAR_NONE,"2P Utils");
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigManager.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new ConfigManager());

    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ClientRegistry.registerKeyBinding(zipKey);
        ClientRegistry.registerKeyBinding(extendLine);
        ClientRegistry.registerKeyBinding(openDebug);
        ClientRegistry.registerKeyBinding(measure);
        ClientRegistry.registerKeyBinding(seeBarrier);
        ClientRegistry.registerKeyBinding(toggleSprint);
        ClientRegistry.registerKeyBinding(openConfig);
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
        MinecraftForge.EVENT_BUS.register(new TeleportLine());
        MinecraftForge.EVENT_BUS.register(new EventListener());
        MinecraftForge.EVENT_BUS.register(new MeasureInput());
    }
}