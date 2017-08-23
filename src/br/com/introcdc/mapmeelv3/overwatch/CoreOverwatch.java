package br.com.introcdc.mapmeelv3.overwatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.introcdc.mapmeelv3.MapMeelMain;
import br.com.introcdc.mapmeelv3.Utils;
import br.com.introcdc.mapmeelv3.variables.Strings;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.npc.entity.EntityHumanNPC;
import net.citizensnpcs.npc.entity.EntityHumanNPC.PlayerNPC;
import net.minecraft.server.v1_10_R1.EnumHand;

public class CoreOverwatch implements Listener {

    public static String currentDate = null;
    public static CoreOverwatchState currentState = null;
    public static File fileLog = null;
    public static boolean recording = false;
    public static PrintWriter writer = null;

    public static ArrayList<String> listOverwatches() {
        final ArrayList<String> list = new ArrayList<>();
        for (final File file : new File("Overwatches/").listFiles()) {
            if (file.isDirectory()) {
                list.add(file.getName());
            }
        }
        return list;
    }

    public static boolean replay(final String name, final CommandSender sender) throws FileNotFoundException {
        if (!new File("Overwatches/" + name + "/log.overwatch").exists()) {
            return false;
        }
        sender.sendMessage(Strings.prefix + "�fCarregando replay overwatch �a" + name + "�f...");
        final Scanner scanner = new Scanner(new File("Overwatches/" + name + "/log.overwatch"));
        final HashMap<String, NPC> allNpcs = new HashMap<>();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (scanner.hasNext()) {
                    final String line = scanner.nextLine();
                    if (line.startsWith("!!!")) {
                        sender.sendMessage(Strings.prefix + "�fNota do Overwatch: �a" + line.replace("!!!", ""));
                    }
                    final String[] args = line.split(";");
                    for (final String log : args) {
                        if (log.startsWith("///")) {
                            Bukkit.broadcastMessage("�8(OVERWATCH) �f" + log.replace("///", ""));
                        } else {
                            final String[] states = log.split(":");
                            if (states.length >= 15) {
                                final String name = states[0];
                                final String world = states[1];
                                final double x = Double.parseDouble(states[2]);
                                final double y = Double.parseDouble(states[3]);
                                final double z = Double.parseDouble(states[4]);
                                final float yaw = Float.parseFloat(states[5]);
                                final float pitch = Float.parseFloat(states[6]);
                                final boolean sneaking = Boolean.parseBoolean(states[7]);
                                final boolean hitting = Boolean.parseBoolean(states[8]);
                                final boolean damage = Boolean.parseBoolean(states[9]);
                                final double fireTicks = Double.parseDouble(states[10]);
                                final double health = Double.parseDouble(states[11]);
                                final double food = Double.parseDouble(states[12]);
                                final boolean sprinting = Boolean.parseBoolean(states[13]);
                                final String itemInv = states[14];
                                if (allNpcs.containsKey(name)) {
                                    if (damage) {
                                        ((LivingEntity) allNpcs.get(name).getEntity()).damage(0);
                                    }
                                    final PlayerNPC playerNPC = (PlayerNPC) allNpcs.get(name).getEntity();
                                    try {
                                        playerNPC.getInventory().setItem(0, new ItemStack(Material.valueOf(itemInv)));
                                    } catch (final Exception ignored) {
                                    }
                                    final EntityHumanNPC entityHumanNPC = playerNPC.getHandle();
                                    playerNPC.setHealth(health);
                                    playerNPC.setFoodLevel((int) food);
                                    playerNPC.setFireTicks((int) fireTicks);
                                    entityHumanNPC.setSneaking(sneaking);
                                    entityHumanNPC.setSprinting(sprinting);
                                    entityHumanNPC.setLocation(x, y, z, yaw, pitch);
                                    entityHumanNPC.aQ = yaw;
                                    if (hitting) {
                                        entityHumanNPC.a(EnumHand.MAIN_HAND);
                                    }
                                } else {
                                    final NPC npc = Utils.createNPC(EntityType.PLAYER, name, new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch), null);
                                    final PlayerNPC playerNPC = (PlayerNPC) npc.getEntity();
                                    final EntityHumanNPC entityHumanNPC = playerNPC.getHandle();
                                    entityHumanNPC.abilities.canFly = true;
                                    entityHumanNPC.abilities.isFlying = true;
                                    allNpcs.put(name, npc);
                                }
                            }
                        }
                    }
                } else {
                    sender.sendMessage(Strings.prefix + "�fReplay do overwatch �a" + name + "�f terminou de ser executado com sucesso...");
                    for (final String key : allNpcs.keySet()) {
                        allNpcs.get(key).despawn();
                    }
                    allNpcs.clear();
                    scanner.close();
                    this.cancel();
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 20, 1);
        return true;
    }

    public static String startRecord(final String message) {
        if (CoreOverwatch.recording) {
            return null;
        }
        CoreOverwatch.currentDate = Utils.convertToDate(System.currentTimeMillis()).replace(" ", "").replace("/", "-").replace(":", "-");
        new BukkitRunnable() {
            @Override
            public void run() {
                new File("Overwatches/" + "OW-" + CoreOverwatch.currentDate).mkdir();
                CoreOverwatch.fileLog = new File("Overwatches/" + "OW-" + CoreOverwatch.currentDate + "/log.overwatch");
                try {
                    CoreOverwatch.fileLog.createNewFile();
                } catch (final IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    CoreOverwatch.writer = new PrintWriter(CoreOverwatch.fileLog);
                    if (message != null) {
                        CoreOverwatch.writer.println("!!!" + message);
                        CoreOverwatch.writer.flush();
                    }
                } catch (final FileNotFoundException e) {
                    e.printStackTrace();
                }
                CoreOverwatch.recording = true;
            }
        }.runTaskAsynchronously(MapMeelMain.getPlugin());
        return "OW-" + CoreOverwatch.currentDate;
    }

    public static void stopRecording() {
        CoreOverwatch.recording = false;
        CoreOverwatch.currentDate = null;
        CoreOverwatch.currentState = null;
        CoreOverwatch.writer = null;
    }

    public static void updater() {
        if (!new File("Overwatches/").exists()) {
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if (CoreOverwatch.recording && CoreOverwatch.writer != null) {
                    if (CoreOverwatch.currentState != null) {
                        CoreOverwatch.currentState.write(CoreOverwatch.writer);
                    }
                    CoreOverwatch.currentState = new CoreOverwatchState();
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        CoreOverwatch.currentState.getStates().add(new CoreOverwatchPlayerState(player));
                    }
                }
            }
        }.runTaskTimerAsynchronously(MapMeelMain.getPlugin(), 0, 1);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(final AsyncPlayerChatEvent event) {
        if (!CoreOverwatch.recording || CoreOverwatch.currentState == null || CoreOverwatch.writer == null) {
            return;
        }
        if (!event.isCancelled()) {
            CoreOverwatch.currentState.setMessage(event.getFormat());
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDamage(final EntityDamageEvent event) {
        if (!CoreOverwatch.recording || CoreOverwatch.currentState == null || CoreOverwatch.writer == null) {
            return;
        }
        if (event.getEntity() instanceof Player) {
            if (!event.isCancelled()) {
                CoreOverwatch.currentState.getState((Player) event.getEntity()).setDamage(true);
            }
        }
    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        if (!CoreOverwatch.recording || CoreOverwatch.currentState == null || CoreOverwatch.writer == null) {
            return;
        }
        if (event.getAction().equals(Action.PHYSICAL)) {
            return;
        }
        CoreOverwatch.currentState.getState(event.getPlayer()).setHitting(true);
    }

}
