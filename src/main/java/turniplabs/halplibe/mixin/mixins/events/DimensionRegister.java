package turniplabs.halplibe.mixin.mixins.events;

import net.minecraft.core.world.Dimension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.halplibe.events.RegisterDimensionsEvent;

@Mixin(value = Dimension.class, remap = false)
public class DimensionRegister {
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void RegisterDimensionsEventMethod(CallbackInfo ci){
        RegisterDimensionsEvent.getEventContainer().runMethods(new RegisterDimensionsEvent());
    }
}
