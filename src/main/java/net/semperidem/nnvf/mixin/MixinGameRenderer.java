package net.semperidem.nnvf.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.MathHelper;
import net.semperidem.nnvf.NNVFClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.entity.effect.StatusEffects.NIGHT_VISION;


@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {

    @Inject(method = "getNightVisionStrength", at = @At("TAIL"), cancellable = true)
    private static void injectGetNightVisionStrength(LivingEntity entity, float tickDelta, CallbackInfoReturnable<Float> cir) {
        StatusEffectInstance se = entity.getStatusEffect(NIGHT_VISION);
        if (se == null) {
            return;
        }

        cir.setReturnValue(getNightVisionStrengthAt(se.getDuration(), tickDelta));
    }

    @Unique
    private static float getNightVisionStrengthAt(float duration, float tickDelta){
        boolean shouldPulse = false;
        int startTick = 100;

        if (NNVFClient.config != null) {
            startTick = NNVFClient.config.FADE_OUT_START_TICK;
            shouldPulse = NNVFClient.config.USE_VANILLA_EFFECT;
        }

        if (duration > startTick) {
            return 1;
        }

        if (shouldPulse) {
            return 0.7f + 0.3f * MathHelper.sin((duration - tickDelta) * 0.628F * NNVFClient.config.PULSE_FREQUENCY);
        }

        return duration / startTick;
    }
}
