package net.semperidem.nnvf;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@Config(name = "nnvf")
public class NNVFConfig implements ConfigData {
    public int FADE_OUT_START_TICK = 100;
}
