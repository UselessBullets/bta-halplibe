package turniplabs.halplibe.events.startup;

import org.jetbrains.annotations.NotNull;
import turniplabs.halplibe.event.EventContainer;
import turniplabs.halplibe.event.Event;

public class RegisterBlocksEvent extends Event {
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
