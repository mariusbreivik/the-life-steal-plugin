package no.breaks.tlsp.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class ReviveCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0){
            sender.sendMessage(ChatColor.RED + "No player to revive selected (Ex: /lsp-revive PLAYER)");
            return false;
        }
        Player killedPlayer = Bukkit.getPlayerExact(args[0]);
        if(killedPlayer != null){
            Location location = killedPlayer.getLocation();
            killedPlayer.setGameMode(GameMode.SURVIVAL);
            killedPlayer.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
            return true;
        }

        return false;
    }
}
