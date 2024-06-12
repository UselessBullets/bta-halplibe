package turniplabs.halplibe.mixin.mixins.events.block;

import net.minecraft.core.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.halplibe.events.block.RegisterBlocksEvent;

@Mixin(value = Block.class, remap = false)
public class BlockRegister {
    @Inject(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/block/BlockStone;setCobblestoneType(Lnet/minecraft/core/block/Block;)V", ordinal = 0, shift = At.Shift.BEFORE))
    private static void RegisterBlocksEventMethod(CallbackInfo ci){
        RegisterBlocksEvent.getEventContainer().runMethods(new RegisterBlocksEvent());
    }
}
