package turniplabs.halplibe.mixin.mixins.events.entity;

import net.minecraft.client.render.EntityRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.halplibe.events.entity.RegisterEntityRendererEvent;

@Mixin(value = EntityRenderDispatcher.class, remap = false)
public class EntityRendererRegister {
    @Inject(method = "<init>()V", at = @At(value = "INVOKE", target = "Ljava/util/Map;values()Ljava/util/Collection;"))
    private void registerEntityRendererEventMethod(CallbackInfo ci){
        RegisterEntityRendererEvent.getEventContainer().runMethods(new RegisterEntityRendererEvent((EntityRenderDispatcher) (Object) this));
    }
}
