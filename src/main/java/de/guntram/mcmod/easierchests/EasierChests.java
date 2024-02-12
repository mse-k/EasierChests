package de.guntram.mcmod.easierchests;

import de.guntram.mcmod.crowdintranslate.CrowdinTranslate;
import de.guntram.mcmod.fabrictools.ConfigurationProvider;
import java.io.File;
import java.util.HashMap;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.screen.ScreenHandler;
import org.apache.logging.log4j.LogManager;
import org.lwjgl.glfw.GLFW;
import org.apache.logging.log4j.Logger;

public class EasierChests implements ClientModInitializer 
{
    static final String MODID="easierchests";
    static final String MODNAME="EasierChests";
    
    private static final String category = "key.categories.easierchests";
    
    public static KeyBinding keySortChest, keyMoveToChest, 
                             keySortPlInv, keyMoveToPlInv,
                             keySearchBox;
    
    private static Logger LOGGER = LogManager.getLogger(EasierChests.class);
    
    @Override
    public void onInitializeClient() {
        CrowdinTranslate.downloadTranslations(MODID);
        ConfigurationHandler confHandler = ConfigurationHandler.getInstance();
        ConfigurationProvider.register(MODNAME, confHandler);
        confHandler.load(ConfigurationProvider.getSuggestedFile(MODID));
        confHandler.load(null);
        FrozenSlotDatabase.init(new File("config"));
        
        keySortChest = registerKey("sortchest", GLFW.GLFW_KEY_KP_7);
        keyMoveToChest = registerKey("matchup", GLFW.GLFW_KEY_KP_8);
        keySortPlInv = registerKey("sortplayer", GLFW.GLFW_KEY_KP_1);
        keyMoveToPlInv = registerKey("matchdown", GLFW.GLFW_KEY_KP_2);
        keySearchBox = registerKey("searchbox", GLFW.GLFW_KEY_UNKNOWN);
    }
    
    private KeyBinding registerKey(String key, int code) {
        KeyBinding result = new KeyBinding("key.easierchests."+key, code, category);
        KeyBindingHelper.registerKeyBinding(result);
        return result;
    }
}
