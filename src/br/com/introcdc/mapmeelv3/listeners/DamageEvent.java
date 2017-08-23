package br.com.introcdc.mapmeelv3.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.introcdc.mapmeelv3.MapMeelMain;
import br.com.introcdc.mapmeelv3.Portal;
import br.com.introcdc.mapmeelv3.Utils;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class DamageEvent implements Listener {

    @EventHandler
    public void onDamage(final EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();
            if (MoveEvent.spongeInvenciliby.contains(player.getUniqueId())) {
                event.setCancelled(true);
                MoveEvent.spongeInvenciliby.remove(player.getUniqueId());
                return;
            }
            if (event.getCause().equals(DamageCause.FIRE) || event.getCause().equals(DamageCause.FIRE_TICK) || event.getCause().equals(DamageCause.LAVA)) {
                if (InteractEvent.Lava.contains(player.getUniqueId())) {
                    event.setCancelled(true);
                }
            }
            if (event.getCause().equals(DamageCause.FALL)) {
                if (Portal.noFallDamage.contains(player.getUniqueId())) {
                    event.setCancelled(true);
                    return;
                }
            }
            if (player.getHealth() - event.getDamage() <= 0) {
                Utils.errou(player);
                event.setCancelled(true);
                player.sendMessage(Strings.prefix + "�cVoc� morreu!");
                player.setHealth(20);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.setFireTicks(0);
                    }
                }.runTaskLater(MapMeelMain.getPlugin(), 5);
            }
        }
    }

}
