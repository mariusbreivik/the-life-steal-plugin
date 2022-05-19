package no.breaks.tlsp.command;

import no.breaks.tlsp.TheLifeStealPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.logging.Logger;

public class ReviveCommandExecutor implements CommandExecutor {
    private final TheLifeStealPlugin plugin;

    private final Logger log;
    public ReviveCommandExecutor(TheLifeStealPlugin plugin) {
        this.plugin = plugin;
        this.log = plugin.getLogger();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0){
            sender.sendMessage(ChatColor.RED + "No player to revive selected (Ex: /lsp-revive PLAYER)");
            return false;
        }
        Player playerToBeRevived = Bukkit.getPlayerExact(args[0]);

        if (playerToBeRevived == null) {
            log.info("Player " + args[0] + " not found");
            sender.sendMessage("Player " + args[0] + " not found");
            return false;
        }

        if (!playerToBeRevived.isDead()) {
            log.info("Player " + args[0] + " not dead");
            sender.sendMessage("Player " + args[0] + " not dead");
            return false;
        }

        Location location = playerToBeRevived.getLocation();
        playerToBeRevived.setGameMode(GameMode.SURVIVAL);
        playerToBeRevived.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
        playerToBeRevived.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        log.info(sender.getName() + " revived " + playerToBeRevived.getDisplayName());
        return true;

    }
}
