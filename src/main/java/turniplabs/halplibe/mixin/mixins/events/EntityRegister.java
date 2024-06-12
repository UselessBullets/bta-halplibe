package turniplabs.halplibe.mixin.mixins.events;

import net.minecraft.core.entity.EntityDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.halplibe.events.RegisterEntityEvent;

@Mixin(value = EntityDispatcher.class, remap = false)
public class EntityRegister {
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void registerEntityEventMethod(CallbackInfo ci){
        RegisterEntityEvent.getEventContainer().runMethods(new RegisterEntityEvent());
    }
}
