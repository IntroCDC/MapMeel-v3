package br.com.introcdc.mapmeelv3.overwatch;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CoreOverwatchPlayerState {

    private boolean damage;
    private int fireTicks;
    private double health;
    private boolean hitting;
    private double hunger;
    private ItemStack itemInv;
    private Player player;
    private boolean sneaking;
    private boolean sprinting;

    public CoreOverwatchPlayerState(Player player) {
        this.player = player;
        this.itemInv = player.getInventory().getItemInHand();
        this.fireTicks = player.getFireTicks();
        this.sneaking = player.isSneaking();
        this.health = player.getHealth();
        this.hunger = player.getFoodLevel();
        this.sprinting = player.isSprinting();
    }

    public int getFireTicks() {
        return this.fireTicks;
    }

    public double getHealth() {
        return this.health;
    }

    public double getHunger() {
        return this.hunger;
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean isDamage() {
        return this.damage;
    }

    public boolean isHitting() {
        return this.hitting;
    }

    public boolean isSneaking() {
        return this.sneaking;
    }

    public boolean isSprinting() {
        return this.sprinting;
    }

    public void setDamage(boolean damage) {
        this.damage = damage;
    }

    public void setHitting(boolean hitting) {
        this.hitting = hitting;
    }

    @Override
    public String toString() {
        return this.player.getName() + ":" + this.player.getLocation().getWorld().getName() + ":" + this.player.getLocation().getX() + ":" + this.player.getLocation().getY() + ":" + this.player.getLocation().getZ() + ":" + this.player.getLocation().getYaw() + ":" + this.player.getLocation().getPitch() + ":" + this.isSneaking() + ":" + this.isHitting() + ":" + this.isDamage() + ":" + this.getFireTicks() + ":" + this.getHealth() + ":" + this.getHunger() + ":" + this
                .isSprinting() + ":" + (this.itemInv != null ? this.itemInv.getType().toString().toUpperCase() : "AIR");
    }

}
