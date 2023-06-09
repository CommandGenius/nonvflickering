package net.semperidem.nnvf.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
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
        float nightVisionDuration =  Optional.of(Objects.requireNonNull(entity.getStatusEffect(StatusEffects.NIGHT_VISION)).getDuration()).orElse(0);;
        cir.setReturnValue(nightVisionDuration > 100 ? 1 : nightVisionDuration / 100);
    }
}
