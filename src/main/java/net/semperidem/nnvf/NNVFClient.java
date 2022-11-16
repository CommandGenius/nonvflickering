package net.semperidem.nnvf;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class NoNVF implements ClientModInitializer {

    public static NoNVFConfig getConfig() {
        return AutoConfig.getConfigHolder(NoNVFConfig.class).getConfig();
    }
    @Override
    public void onInitializeClient() {
        AutoConfig.register(NoNVFConfig.class, GsonConfigSerializer::new);
    }
}
