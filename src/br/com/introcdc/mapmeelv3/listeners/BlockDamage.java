package br.com.introcdc.mapmeelv3.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

import br.com.introcdc.mapmeelv3.Utils;

public class BlockDamage implements Listener {

    @EventHandler
    public void onBlockDamage(BlockExplodeEvent event) {
        Utils.sendAlertCMS("Explosão de entidade cancelado!");
        event.setCancelled(true);
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        Utils.sendAlertCMS("Explosão de entidade cancelada!");
        event.setYield(0.0f);
        event.setCancelled(true);
    }

    @EventHandler
    public void onFire(BlockBurnEvent event) {
        event.setCancelled(true);
        Utils.sendAlertCMS("§4§lFOGO ESPALHANDO!!!!! §f§lMundo: §a§l" + event.getBlock().getWorld().getName() + " §f§l- Coords: X: §a§l" + (int) event.getBlock().getLocation().getX() + " §f§l- Y: §a§l" + (int) event.getBlock().getLocation().getY() + " §f§l- Z: §a§l" + (int) event.getBlock().getLocation().getZ() + "§f§l!");
    }

    @EventHandler
    public void onPrime(ExplosionPrimeEvent event) {
        Utils.sendAlertCMS("Inicio de explosão cancelada!");
        event.setCancelled(true);
    }

}
