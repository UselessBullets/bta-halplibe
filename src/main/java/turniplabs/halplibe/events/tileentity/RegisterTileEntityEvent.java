package turniplabs.halplibe.events.tileentity;

import org.jetbrains.annotations.NotNull;
import turniplabs.halplibe.event.Event;
import turniplabs.halplibe.event.EventContainer;

public class RegisterTileEntityEvent extends Event {
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
