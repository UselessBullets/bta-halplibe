package turniplabs.halplibe.events.tileentity;

import net.minecraft.client.render.TileEntityRenderDispatcher;
import org.jetbrains.annotations.NotNull;
import turniplabs.halplibe.event.Event;
import turniplabs.halplibe.event.EventContainer;

import java.util.Objects;

public class RegisterTileEntityRendererEvent extends Event {
    @NotNull
    public final TileEntityRenderDispatcher tileEntityRenderDispatcher;
    public RegisterTileEntityRendererEvent(final @NotNull TileEntityRenderDispatcher tileEntityRenderDispatcher){
        this.tileEntityRenderDispatcher = Objects.requireNonNull(tileEntityRenderDispatcher);
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
