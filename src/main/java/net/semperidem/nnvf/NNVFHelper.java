package net.semperidem.nnvf;

import net.minecraft.util.math.MathHelper;

public class NNVFHelper {
    public static float getNightVisionStrength(float duration, float tickDelta){
        if (!NNVFClient.config.USE_VANILLA_EFFECT) {
            return (duration / NNVFClient.config.FADE_OUT_START_TICK);
        } else {
            float flat = 1 - 0.3f;
            float fluctuation = 1 - flat;
            float sinus = MathHelper.sin((duration - tickDelta) * 0.628F * NNVFClient.config.VANILLA_EFFECT_SPEED);
            return flat + fluctuation * sinus;
        }
    }
}
