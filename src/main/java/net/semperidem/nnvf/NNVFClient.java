package net.semperidem.nnvf;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class NNVFClient implements ClientModInitializer {

    public static NNVFConfig getConfig() {
        return AutoConfig.getConfigHolder(NNVFConfig.class).getConfig();
    }
    @Override
    public void onInitializeClient() {
        AutoConfig.register(NNVFConfig.class, GsonConfigSerializer::new);
    }
}
