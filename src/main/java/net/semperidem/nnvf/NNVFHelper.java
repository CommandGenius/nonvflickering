package net.semperidem.nnvf;

import net.minecraft.util.math.MathHelper;

public class NNVFHelper {
    public static float getNightVisionStrength(float duration, float tickDelta){
        boolean useVanillaEffect = NNVFClient.config == null ?
                false : NNVFClient.config.USE_VANILLA_EFFECT;
        if (!useVanillaEffect) {
            int fadeOutStartTick = NNVFClient.config == null ?
                    100 : NNVFClient.config.FADE_OUT_START_TICK;
            return (duration / fadeOutStartTick);
        } else {
            float flat = 1 - 0.3f;
            float fluctuation = 1 - flat;
            float sinus = MathHelper.sin((duration - tickDelta) * 0.628F * NNVFClient.config.VANILLA_EFFECT_SPEED);
            return flat + fluctuation * sinus;
        }
    }
}
