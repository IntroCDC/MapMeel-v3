package br.com.introcdc.mapmeelv3.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.Utils;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class BlockPlace implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (!event.getPlayer().getName().equalsIgnoreCase("Introo")) {
            if (Utils.isMeelOn()) {
                event.getPlayer().sendMessage(Strings.prefix + "§cA construção não é permitida com a Meel online!");
                event.setCancelled(true);
                return;
            }
        }
        if (Profile.getProfile(event.getPlayer().getName()).getCargo().isOp()) {
            if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                event.setCancelled(true);
            }
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEmpty(PlayerBucketEmptyEvent event) {
        if (!event.getPlayer().getName().equalsIgnoreCase("Introo")) {
            if (Utils.isMeelOn()) {
                event.getPlayer().sendMessage(Strings.prefix + "§cA construção não é permitida com a Meel online!");
                event.setCancelled(true);
                return;
            }
        }
        if (Profile.getProfile(event.getPlayer().getName()).getCargo().isOp()) {
            if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                event.setCancelled(true);
            }
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFill(PlayerBucketFillEvent event) {
        if (!event.getPlayer().getName().equalsIgnoreCase("Introo")) {
            if (Utils.isMeelOn()) {
                event.getPlayer().sendMessage(Strings.prefix + "§cA construção não é permitida com a Meel online!");
                event.setCancelled(true);
                return;
            }
        }
        if (Profile.getProfile(event.getPlayer().getName()).getCargo().isOp()) {
            if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                event.setCancelled(true);
            }
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (!event.getPlayer().getName().equalsIgnoreCase("Introo")) {
            if (Utils.isMeelOn()) {
                event.getPlayer().sendMessage(Strings.prefix + "§cA construção não é permitida com a Meel online!");
                event.setCancelled(true);
                return;
            }
        }
        if (event.getBlock().getType().equals(Material.TNT)) {
            event.setCancelled(true);
            event.getPlayer().getInventory().clear();
            event.getPlayer().sendMessage(Strings.prefix + "\n\n§4§lO USO DE TNT NESTE MAPA É EXTREMAMENTE PROIBIDO!");
        }
        if (Profile.getProfile(event.getPlayer().getName()).getCargo().isOp()) {
            if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                event.setCancelled(true);
            }
        } else {
            event.setCancelled(true);
        }
    }

}
