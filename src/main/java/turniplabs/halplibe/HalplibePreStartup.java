package turniplabs.halplibe;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import turniplabs.halplibe.helper.ModVersionHelper;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

class HalplibePreStartup implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        Toml toml = new Toml();
        toml.addCategory("Experimental");
        toml.addEntry("Experimental.CompatibilityMode", "Attempt allowing compatibility with older halplibe versions", true);
        toml.addCategory("Debug");
        toml.addEntry("Debug.ExportRecipes", "Writes all the loaded game recipes to dumpRecipes after startup", false);


        HalpLibe.CONFIG = new TomlConfigHandler(HalpLibe.MOD_ID, toml);

        HalpLibe.exportRecipes = HalpLibe.CONFIG.getBoolean("Debug.ExportRecipes");
        HalpLibe.compatibilityMode = HalpLibe.CONFIG.getBoolean("Experimental.CompatibilityMode");

        // Initialize Block and Item static fields
        try {
            Class.forName("net.minecraft.core.block.Block");
            Class.forName("net.minecraft.core.item.Item");
        } catch (ClassNotFoundException ignored) {
        }
        ModVersionHelper.initialize();
    }
}
