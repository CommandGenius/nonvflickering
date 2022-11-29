package net.semperidem.nnvf;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class NNVFClient implements ClientModInitializer {
    public static NNVFConfig config;
    private static KeyBinding openConfigKey;
    @Override
    public void onInitializeClient() {
        AutoConfig.register(NNVFConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(NNVFConfig.class).getConfig();
        registerConfigKey();
    }

    private void registerConfigKey(){
        openConfigKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
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
