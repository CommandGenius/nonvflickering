package sidem.nonvflickering.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sidem.nonvflickering.NoNVF;


@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    private final static float FADE_OUT_START_TICK = 100;

    @Inject(method = "getNightVisionStrength", at = @At("TAIL"), cancellable = true)
    private static void getNightVisionScale(LivingEntity entity, float tickDelta, CallbackInfoReturnable<Float> cir) {
        if (entity.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
            int nvDuration = entity.getStatusEffect(StatusEffects.NIGHT_VISION).getDuration();
            cir.setReturnValue(nvDuration > NoNVF.getConfig().FADE_OUT_START_TICK ? 1.0F : (nvDuration / FADE_OUT_START_TICK));
        }
    }
}
