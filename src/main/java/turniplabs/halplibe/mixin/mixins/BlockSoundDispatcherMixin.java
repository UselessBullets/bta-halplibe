package turniplabs.halplibe.mixin.mixins;

import net.minecraft.client.sound.block.BlockSound;
import net.minecraft.client.sound.block.BlockSoundDispatcher;
import net.minecraft.client.util.dispatch.Dispatcher;
import net.minecraft.core.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BlockSoundDispatcher.class, remap = false)
public abstract class BlockSoundDispatcherMixin extends Dispatcher<Block, BlockSound> {

    @Inject(method = "validate", at = @At("HEAD"), cancellable = true)
    public void cancelValidate(CallbackInfo ci) {
        ci.cancel();
    }

}
