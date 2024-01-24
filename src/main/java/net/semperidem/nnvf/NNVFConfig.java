package net.semperidem.nnvf;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@Config(name = "nnvf")
public class NNVFConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public int FADE_OUT_START_TICK = 100;
    public boolean USE_VANILLA_EFFECT = false;
    @ConfigEntry.Gui.Tooltip
    public float PULSE_FREQUENCY = 1f;
}