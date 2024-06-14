package turniplabs.halplibe.mixin.mixins.events.tileentity;

import net.minecraft.core.block.entity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.halplibe.events.tileentity.RegisterTileEntityEvent;

@Mixin(value = TileEntity.class, remap = false)
public class TileEntityRegister {
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void RegisterTileEntitiesEventMethod(CallbackInfo ci){
        RegisterTileEntityEvent.getEventContainer().runMethods(new RegisterTileEntityEvent());
    }
}
