package br.com.introcdc.mapmeelv3.listeners;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Explosive;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PotionSplashEvent;

import br.com.introcdc.mapmeelv3.Utils;

public class EntitySpawn implements Listener {

    public static boolean antiFlood = false;

    @EventHandler
    public void onPotion(final PotionSplashEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onSpawn(final EntitySpawnEvent event) {
        if (event.getEntity().getType().equals(EntityType.PRIMED_TNT) | event.getEntity() instanceof TNTPrimed | event.getEntity().getType().equals(EntityType.ENDER_CRYSTAL) | event.getEntity() instanceof EnderCrystal | event.getEntity() instanceof EnderDragon | event.getEntity().getType().equals(
            EntityType.SPLASH_POTION) | event.getEntity() instanceof Explosive | event.getEntity() instanceof ExplosiveMinecart | event.getEntity() instanceof Wither | event.getEntity() instanceof WitherSkull | event.getEntity() instanceof Fireball | event.getEntity() instanceof Enderman | event.getEntity() instanceof Creeper) {
            Utils.sendAlertCMS("Spawn de " + event.getEntityType() + " cancelado!");
            event.setCancelled(true);
        }
    }

}
