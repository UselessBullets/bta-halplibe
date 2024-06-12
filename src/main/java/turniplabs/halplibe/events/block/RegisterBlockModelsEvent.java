package turniplabs.halplibe.events.block;

import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import org.jetbrains.annotations.NotNull;
import turniplabs.halplibe.event.Event;
import turniplabs.halplibe.event.EventContainer;

import java.util.Objects;

public class RegisterBlockModelsEvent extends Event {
    @NotNull
    public final BlockModelDispatcher blockModelDispatcher;
    public RegisterBlockModelsEvent(final @NotNull BlockModelDispatcher blockModelDispatcher){
        this.blockModelDispatcher = Objects.requireNonNull(blockModelDispatcher);
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
