package br.com.introcdc.mapmeelv3.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.introcdc.mapmeelv3.MapFinal;
import br.com.introcdc.mapmeelv3.MapMeelMain;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.Utils;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent event) {
        if (event.getMessage().equalsIgnoreCase("!STARTFINALEVENT!") && Profile.getProfile(event.getPlayer().getName()).getCargo().isOp()) {
            event.setCancelled(true);
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (Utils.isMeelOn()) {
                        MapFinal.start(Utils.getMeel());
                        return;
                    }
                    MapFinal.start(event.getPlayer());
                }
            }.runTask(MapMeelMain.getPlugin());
            return;
        }
        event.setMessage(event.getMessage().replace("&", "§"));
        event.setMessage(event.getMessage().replace("%", ""));
        event.setFormat(Profile.getProfile(event.getPlayer().getName()).getColor() + event.getPlayer().getName() + " §f§l>" + Profile.getProfile(event.getPlayer().getName()).getColor() + "> §f" + event.getMessage());
    }

}
