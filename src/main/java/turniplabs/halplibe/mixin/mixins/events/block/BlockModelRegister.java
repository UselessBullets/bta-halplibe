package turniplabs.halplibe.mixin.mixins.events.block;

import net.minecraft.client.render.block.model.BlockModelDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.halplibe.events.block.RegisterBlockModelsEvent;

@Mixin(value = BlockModelDispatcher.class, remap = false)
public class BlockModelRegister {
    @Inject(method = "<init>()V", at = @At(value = "TAIL"))
    private void RegisterBlockSoundsEventMethod(CallbackInfo ci){
        RegisterBlockModelsEvent.getEventContainer().runMethods(new RegisterBlockModelsEvent((BlockModelDispatcher)(Object)this));
    }
}
