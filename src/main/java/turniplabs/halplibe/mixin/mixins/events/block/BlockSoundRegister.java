package turniplabs.halplibe.mixin.mixins.events.block;

import net.minecraft.core.sound.BlockSoundDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.halplibe.events.block.RegisterBlockSoundsEvent;

@Mixin(value = BlockSoundDispatcher.class, remap = false)
public class BlockSoundRegister {
    @Inject(method = "<init>()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/sound/BlockSoundDispatcher;validate()V", shift = At.Shift.BEFORE))
    private void RegisterBlockSoundsEventMethod(CallbackInfo ci){
        RegisterBlockSoundsEvent.getEventContainer().runMethods(new RegisterBlockSoundsEvent((BlockSoundDispatcher) (Object) this));
    }
}
