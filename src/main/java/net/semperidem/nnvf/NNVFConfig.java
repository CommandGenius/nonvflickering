package net.semperidem.nnvf;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "nonvflickering")
public class NoNVFConfig implements ConfigData {
    public int FADE_OUT_START_TICK = 100;
}
