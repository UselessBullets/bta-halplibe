package turniplabs.halplibe;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import turniplabs.halplibe.event.EventManager;
import turniplabs.halplibe.helper.ModVersionHelper;

public class HalplibePreStartup implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        EventManager.addAllEventBuses();
        // Initialize Block and Item static fields
        try {
            Class.forName("net.minecraft.core.block.Block");
            Class.forName("net.minecraft.core.item.Item");
        } catch (ClassNotFoundException ignored) {
        }
        ModVersionHelper.initialize();
    }
}
