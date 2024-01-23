package net.semperidem.nnvf.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.semperidem.nnvf.NNVFClient;
import net.semperidem.nnvf.NNVFHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import java.util.Optional;


@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {
    @Inject(method = "getNightVisionStrength", at = @At("TAIL"), cancellable = true)
    private static void injectGetNightVisionStrength(LivingEntity entity, float tickDelta, CallbackInfoReturnable<Float> cir) {
        float nvDuration = Optional.of(
            Objects.requireNonNull(entity
                    .getStatusEffect(StatusEffects.NIGHT_VISION))
                    .getDuration())
                    .orElse(0);
        int fadeOutStartTick = NNVFClient.config == null ?
                100 : NNVFClient.config.FADE_OUT_START_TICK;
        cir.setReturnValue(
                nvDuration > fadeOutStartTick ?
                        1 : NNVFHelper.getNightVisionStrength(nvDuration, tickDelta)
        );
    }

}
