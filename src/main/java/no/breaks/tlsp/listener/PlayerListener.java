package no.breaks.tlsp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class PlayerListener implements Listener {

    private final JavaPlugin plugin;
    private final Logger log;

    public PlayerListener(JavaPlugin plugin) {
        this.plugin = plugin;
        this.log = plugin.getLogger();
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoined(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        log.info("Sending join message to " + player.getDisplayName());
        player.sendMessage(plugin.getConfig().getString("player.messages.join"));
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerKilled(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player killedPlayer) {

            Player killer = null;
            if (killedPlayer.getKiller() != null) {
                killer = killedPlayer.getKiller();
                log.info(killedPlayer.getDisplayName() + " killed by " + killer.getDisplayName());
            } else {
                if (killedPlayer.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
                    EntityDamageByEntityEvent ev = (EntityDamageByEntityEvent) killedPlayer.getLastDamageCause();
                    log.info(killedPlayer.getDisplayName() + " killed by " + ev.getEntity().getName());
                }
            }
        }
    }
}
