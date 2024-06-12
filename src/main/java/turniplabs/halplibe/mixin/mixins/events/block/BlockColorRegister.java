package turniplabs.halplibe.mixin.mixins.events.block;

import net.minecraft.client.render.block.color.BlockColorDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.halplibe.events.block.RegisterBlockColorsEvent;

@Mixin(value = BlockColorDispatcher.class, remap = false)
public class BlockColorRegister {
    @Inject(method = "<init>()V", at = @At(value = "TAIL"))
    private void RegisterBlockColorsEventMethod(CallbackInfo ci){
        RegisterBlockColorsEvent.getEventContainer().runMethods(new RegisterBlockColorsEvent((BlockColorDispatcher)(Object) this));
    }
}
