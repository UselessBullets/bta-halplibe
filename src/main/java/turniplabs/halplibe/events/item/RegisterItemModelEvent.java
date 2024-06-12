package turniplabs.halplibe.events.item;

import net.minecraft.client.render.item.model.ItemModelDispatcher;
import org.jetbrains.annotations.NotNull;
import turniplabs.halplibe.event.Event;
import turniplabs.halplibe.event.EventContainer;

import java.util.Objects;

public class RegisterItemModelEvent extends Event {
    @NotNull
    public final ItemModelDispatcher itemModelDispatcher;
    public RegisterItemModelEvent(final @NotNull ItemModelDispatcher itemModelDispatcher){
        this.itemModelDispatcher = Objects.requireNonNull(itemModelDispatcher);
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
