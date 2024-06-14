package turniplabs.halplibe.events.entity;

import net.minecraft.client.render.EntityRenderDispatcher;
import org.jetbrains.annotations.NotNull;
import turniplabs.halplibe.event.Event;
import turniplabs.halplibe.event.EventContainer;

import java.util.Objects;

public class RegisterEntityRendererEvent extends Event {
    @NotNull
    public final EntityRenderDispatcher entityRenderDispatcher;
    public RegisterEntityRendererEvent(final @NotNull EntityRenderDispatcher entityRenderDispatcher){
        this.entityRenderDispatcher = Objects.requireNonNull(entityRenderDispatcher);
    }
    private static final EventContainer eventContainer = new EventContainer();
    @NotNull
    public EventContainer getEvents() {
        return eventContainer;
    }

    @NotNull
    public static EventContainer getEventContainer() {
        return eventContainer;
    }
}
