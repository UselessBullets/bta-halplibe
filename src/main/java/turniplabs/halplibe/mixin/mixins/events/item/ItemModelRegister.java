package turniplabs.halplibe.mixin.mixins.events.item;

import net.minecraft.client.render.item.model.ItemModelDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.halplibe.events.item.RegisterItemModelEvent;

@Mixin(value = ItemModelDispatcher.class, remap = false)
public class ItemModelRegister {
    @Inject(method = "addDispatch(Lnet/minecraft/client/render/item/model/ItemModel;)V", at = @At(value = "TAIL"))
    private void RegisterItemModelsEventMethod(CallbackInfo ci){
        RegisterItemModelEvent.getEventContainer().runMethods(new RegisterItemModelEvent((ItemModelDispatcher) (Object) this));
    }
}
