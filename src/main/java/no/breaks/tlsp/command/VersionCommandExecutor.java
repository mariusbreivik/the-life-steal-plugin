package no.breaks.tlsp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class VersionCommandExecutor implements CommandExecutor {

    private final JavaPlugin plugin;
    private final Logger log;

    public VersionCommandExecutor(JavaPlugin plugin) {
        this.plugin = plugin;
        this.log = plugin.getLogger();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PluginDescriptionFile pdf = plugin.getDescription();
        log.info("Current version of the-life-steal-plugin v." + pdf.getVersion());
        return true;
    }
}
