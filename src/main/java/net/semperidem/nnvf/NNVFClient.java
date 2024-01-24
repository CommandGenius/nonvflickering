package net.semperidem.nnvf;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class NNVFClient implements ClientModInitializer {
    @Nullable
    public static NNVFConfig config;
    @Override
    public void onInitializeClient() {
        if (!FabricLoader.getInstance().isModLoaded("cloth-config")) {
            return;
        }

        AutoConfig.register(NNVFConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(NNVFConfig.class).getConfig();
        registerConfigKey();
    }

    private void registerConfigKey(){
        KeyBinding openConfigKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nnvf.openConfig",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                "text.autoconfig.nnvf.title"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(openConfigKey.wasPressed()){
                client.setScreen(AutoConfig.getConfigScreen(NNVFConfig.class, client.currentScreen).get());
            }
        });
    }
}
