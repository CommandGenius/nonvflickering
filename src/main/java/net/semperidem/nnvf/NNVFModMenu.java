package net.semperidem.nnvf;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;

@Environment(EnvType.CLIENT)
public class NNVFModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (FabricLoader.getInstance().isModLoaded("cloth-config")) {
            return (ConfigScreenFactory<Screen>) screen -> AutoConfig.getConfigScreen(NNVFConfig.class, screen).get();
        } else {
            return null;
        }
    }
}