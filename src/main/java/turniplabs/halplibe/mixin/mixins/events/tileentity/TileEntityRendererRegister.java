package turniplabs.halplibe.mixin.mixins.events.tileentity;

import net.minecraft.client.render.TileEntityRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.halplibe.events.tileentity.RegisterTileEntityRendererEvent;

@Mixin(value = TileEntityRenderDispatcher.class, remap = false)
public class TileEntityRendererRegister {
    @Inject(method = "<init>()V", at = @At(value = "INVOKE", target = "Ljava/util/Map;values()Ljava/util/Collection;"))
    private void registerTileEntityRendererEventMethod(CallbackInfo ci){
        RegisterTileEntityRendererEvent.getEventContainer().runMethods(new RegisterTileEntityRendererEvent((TileEntityRenderDispatcher) (Object) this));
    }
}
