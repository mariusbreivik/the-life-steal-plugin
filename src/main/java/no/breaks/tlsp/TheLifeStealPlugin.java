package no.breaks.tlsp;

import no.breaks.tlsp.command.ReviveCommandExecutor;
import no.breaks.tlsp.command.VersionCommandExecutor;
import no.breaks.tlsp.listener.PlayerListener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

public final class TheLifeStealPlugin extends JavaPlugin {

    public TheLifeStealPlugin(){

    }

    protected TheLifeStealPlugin(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }
    @Override
    public void onEnable() {
        saveDefaultConfig();
        // listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this );

        // commands
        getServer().getPluginCommand("lsp-revive").setExecutor(new ReviveCommandExecutor(this));
        getServer().getPluginCommand("lsp-version").setExecutor(new VersionCommandExecutor(this));
    }

    @Override
    public void onDisable() {

    }
}
