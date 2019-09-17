package br.com.introcdc.mapmeelv3;

import br.com.introcdc.mapmeelv3.listeners.MoveEvent;
import br.com.introcdc.mapmeelv3.variables.Strings;
import com.google.common.base.Preconditions;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.world.DataException;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.npc.skin.SkinnableEntity;
import net.citizensnpcs.util.NMS;
import net.minecraft.server.v1_10_R1.*;
import net.minecraft.server.v1_10_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_10_R1.PacketPlayOutTitle.EnumTitleAction;
import org.bukkit.Material;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;

public class Utils {

    public static Random random = new Random();

    public static void checkForPlayAudio(Player player, boolean stop) {
        if (Profile.getProfile(player.getName()).isAudioFundoOn()) {
            if (stop) {
                Utils.playSound(player, Audio.PARAR);
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    Utils.playSound(player, Profile.getProfile(player.getName()).getLocation().getFundo());
                }
            }.runTaskLater(MapMeelMain.getPlugin(), stop ? 20 : 1);
        }
    }

    public static String convertToDate(long number) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(number);
        return Utils.placeZero(calendar.get(Calendar.DAY_OF_MONTH)) + "/" + Utils.placeZero(calendar.get(Calendar.MONTH) + 1) + "/" + Utils.placeZero(calendar.get(Calendar.YEAR)) + " - " + Utils.placeZero(calendar.get(Calendar.HOUR_OF_DAY) + 1) + ":" + Utils.placeZero(calendar.get(Calendar.MINUTE)) + ":" + Utils.placeZero(calendar.get(Calendar.SECOND));
    }

    public static long convertToMilliSeconds(long tempo, String tipo) {
        tempo = tempo * 1000;
        if (tipo.equalsIgnoreCase("s")) {
            return tempo;
        }
        tempo = tempo * 60;
        if (tipo.equalsIgnoreCase("m")) {
            return tempo;
        }
        tempo = tempo * 60;
        if (tipo.equalsIgnoreCase("h")) {
            return tempo;
        }
        tempo = tempo * 24;
        if (tipo.equalsIgnoreCase("d")) {
            return tempo;
        } else if (tipo.equalsIgnoreCase("semana")) {
            tempo = tempo * 7;
            return tempo;
        }
        tempo = tempo * 31;
        if (tipo.equalsIgnoreCase("mes")) {
            return tempo;
        }
        tempo = tempo * 12;
        if (tipo.equalsIgnoreCase("a")) {
            return tempo;
        } else {
            return Long.parseLong("ERROR");
        }
    }

    public static NPC createNPC(EntityType type, String name, Location location, String skin) {
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(type, name);
        if (skin != null) {
            SkinnableEntity skinnable = NMS.getSkinnable(npc.getEntity());
            if (skinnable != null) {
                skinnable.setSkinName(skin);
            }
        }
        npc.spawn(location);
        return npc;
    }

    public static void errou(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setFireTicks(0);
        player.sendMessage(Strings.prefix + "§4§lERRROOOOUUU!!!!!");
        Audio a = Audio.ERROU;
        String pitch;
        if (Utils.random.nextBoolean()) {
            pitch = "0.";
        } else {
            pitch = "1.";
        }
        pitch += Utils.random.nextInt(10);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "playsound " + a.getPatch() + " master " + player.getName() + " " + (int) player.getLocation().getX() + " " + (int) player.getLocation().getY() + " " + (int) player.getLocation().getZ() + " 50000 " + pitch);
        Profile.getProfile(player.getName()).addFail();
        if (Utils.isMeelOn()) {
            if (Utils.getMeel().getName().equalsIgnoreCase(player.getName())) {
                MoveEvent.teleporting = true;
            }
        }
        if (CheckpointManager.getCurrentCheckpoint(player) != null) {
            player.teleport(CheckpointManager.getCurrentCheckpoint(player).clone().add(0, 1, 0));
        } else {
            player.teleport(Profile.getProfile(player.getName()).getLocation().getLocation());
        }
        if (Utils.isMeelOn()) {
            if (Utils.getMeel().getName().equalsIgnoreCase(player.getName())) {
                MoveEvent.teleporting = false;
            }
        }
    }

    public static Player getMeel() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (Profile.getProfile(player.getName()).getCargo().equals(Cargo.MEEL)) {
                return player;
            }
        }
        return null;
    }

    public static Class<?> getNmsClass(String nmsClassName) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + nmsClassName);
    }

    public static int getPing(Player p) {
        CraftPlayer cp = (CraftPlayer) p;
        EntityPlayer ep = cp.getHandle();
        return ep.ping;
    }

    public static String getServerTPS() {
        StringBuilder tps = new StringBuilder();
        String message;
        for (int i = 0; i < 4; i++) {
            tps.append(String.valueOf(MinecraftServer.getServer().recentTps[0]).toCharArray()[i]);
        }
        if (MinecraftServer.getServer().recentTps[0] >= 20) {
            message = "§fTPS atual do servidor: §b§l" + tps + "§f (§2Perfeito§f)";
        } else if (MinecraftServer.getServer().recentTps[0] >= 19 && MinecraftServer.getServer().recentTps[0] <= 20) {
            message = "§fTPS atual do servidor: §b§l" + tps + "§f (§aÓtimo§f)";
        } else if (MinecraftServer.getServer().recentTps[0] >= 18 && MinecraftServer.getServer().recentTps[0] <= 19) {
            message = "§fTPS atual do servidor: §b§l" + tps + "§f (§eBom§f)";
        } else if (MinecraftServer.getServer().recentTps[0] >= 17 && MinecraftServer.getServer().recentTps[0] <= 18) {
            message = "§fTPS atual do servidor: §b§l" + tps + "§f (§eMais ou Menos§f)";
        } else if (MinecraftServer.getServer().recentTps[0] >= 16 && MinecraftServer.getServer().recentTps[0] <= 17) {
            message = "§fTPS atual do servidor: §b§l" + tps + "§f (§cRuim§f)";
        } else if (MinecraftServer.getServer().recentTps[0] >= 15 && MinecraftServer.getServer().recentTps[0] <= 16) {
            message = "§fTPS atual do servidor: §b§l" + tps + "§f (§4Muito Ruim§f)";
        } else {
            message = "§fTPS atual do servidor: §b§l" + tps + "§f (§4§lPÉSSIMO, LAGADO§f)";
        }
        return message;
    }

    public static boolean isMeelOn() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (Profile.getProfile(player.getName()).getCargo().equals(Cargo.MEEL)) {
                return true;
            }
        }
        return false;
    }

    public static void launchFireworks(Location location) {
        Firework f = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        FireworkMeta fMeta = f.getFireworkMeta();
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.AQUA);
        colors.add(Color.BLACK);
        colors.add(Color.BLUE);
        colors.add(Color.FUCHSIA);
        colors.add(Color.GRAY);
        colors.add(Color.GREEN);
        colors.add(Color.LIME);
        colors.add(Color.MAROON);
        colors.add(Color.NAVY);
        colors.add(Color.OLIVE);
        colors.add(Color.ORANGE);
        colors.add(Color.PURPLE);
        colors.add(Color.RED);
        colors.add(Color.SILVER);
        colors.add(Color.WHITE);
        colors.add(Color.YELLOW);
        FireworkEffect effect = FireworkEffect.builder().flicker(true).trail(true).withFade(colors.get(Utils.random.nextInt(colors.size())), colors.get(Utils.random.nextInt(colors.size()))).withColor(colors.get(Utils.random.nextInt(colors.size())), colors.get(Utils.random.nextInt(colors.size()))).flicker(true).trail(true).build();
        fMeta.addEffect(effect);
        f.setFireworkMeta(fMeta);
        f.setCustomName("Firework");
    }

    public static void loadAllCheckers() {
        new LevelChecker("LEVEL-1", () -> Bukkit.getWorld("world").getBlockAt(992, 28, 988).setType(Material.REDSTONE_BLOCK), () -> Bukkit.getWorld("world").getBlockAt(992, 28, 988).setType(Material.AIR), new Nivel[]{Nivel.EASTEREGG_1, Nivel.L_1A, Nivel.L_1B, Nivel.L_1C, Nivel.L_1D});
        new LevelChecker("LEVEL-2", () -> Bukkit.getWorld("world").getBlockAt(2011, 4, 2020).setType(Material.REDSTONE_BLOCK), () -> Bukkit.getWorld("world").getBlockAt(2011, 4, 2020).setType(Material.AIR), new Nivel[]{Nivel.EASTEREGG_2, Nivel.L_2A, Nivel.L_2B, Nivel.L_2C, Nivel.L_2D});
        new LevelChecker("LEVEL-3", () -> Bukkit.getWorld("world_nether").getBlockAt(558, 73, 409).setType(Material.REDSTONE_BLOCK), () -> Bukkit.getWorld("world_nether").getBlockAt(558, 73, 409).setType(Material.AIR), new Nivel[]{Nivel.EASTEREGG_3, Nivel.L_3A, Nivel.L_3B, Nivel.L_3C, Nivel.L_3D});
        new LevelChecker("EASTEREGG-4", () -> Bukkit.getWorld("world_the_end").getBlockAt(1937, 59, 2579).setType(Material.REDSTONE_BLOCK), () -> Bukkit.getWorld("world_the_end").getBlockAt(1937, 59, 2579).setType(Material.AIR), new Nivel[]{Nivel.L_4A, Nivel.L_4B, Nivel.L_4C, Nivel.L_4D});
        new LevelChecker("FINAL", () -> {
            for (int x = 5; x <= 11; x++) {
                for (int y = 8; y <= 13; y++) {
                    for (int z = 54; z <= 76; z++) {
                        Bukkit.getWorld("world").getBlockAt(x, y, z).setType(Material.AIR);
                    }
                }
            }
            for (int x = 5; x <= 11; x++) {
                for (int y = 14; y <= 16; y++) {
                    for (int z = 54; z <= 76; z++) {
                        Bukkit.getWorld("world").getBlockAt(x, y, z).setType(Material.AIR);
                    }
                }
            }
        }, () -> {
            for (int x = 5; x <= 11; x++) {
                for (int y = 8; y <= 13; y++) {
                    for (int z = 54; z <= 76; z++) {
                        Bukkit.getWorld("world").getBlockAt(x, y, z).setType(Material.STONE);
                    }
                }
            }
            for (int x = 5; x <= 11; x++) {
                for (int y = 14; y <= 16; y++) {
                    for (int z = 54; z <= 76; z++) {
                        Bukkit.getWorld("world").getBlockAt(x, y, z).setType(Material.SAND);
                    }
                }
            }
        }, Nivel.values());
    }

    public static void loadBots() {
        new Bot("Base62", new Location(Bukkit.getWorld("world"), 0.5, 19.0, 20.5), DialogBot.SEJA_BEM_VINDA);
        new Bot("Base62", new Location(Bukkit.getWorld("world"), 991.5, 30.0, 991.5), DialogBot.LEVEL_1_LOBBY);
        new Bot("Base62", new Location(Bukkit.getWorld("world"), 1500.5, 32.0, 1010.5), DialogBot.LEVEL_1_1C);
        new Bot("Base62", new Location(Bukkit.getWorld("world"), 1498.5, 7.0, 931.5), DialogBot.OLA);
        new Bot("Base62", new Location(Bukkit.getWorld("world"), 1998.5, 30.0, 1002.5), DialogBot.LEVEL_1_1D);
        new Bot("Base62", new Location(Bukkit.getWorld("world"), -999.5, 30.0, -1002.5), null);
        new Bot("Base64_", new Location(Bukkit.getWorld("world"), -1001.5, 30.0, -1002.5), DialogBot.LEVEL_1_EASTEREGG_1);
        new Bot("Base65", new Location(Bukkit.getWorld("world"), -997.5, 36.0, -1085.5), DialogBot.LEVEL_1_EASTEREGG_2);
        new Bot("Base62", new Location(Bukkit.getWorld("world"), -997.5, 42.0, -1089.5), DialogBot.LEVEL_1_EASTEREGG_3);
        new Bot("Base62", new Location(Bukkit.getWorld("world"), -997.5, 36.0, -1095.5), DialogBot.LEVEL_1_EASTEREGG_4);
        new Bot("Base62", new Location(Bukkit.getWorld("world"), 1958.5, 53.0, 1963.5), DialogBot.LEVEL_2_LOBBY);
        new Bot("Base62", new Location(Bukkit.getWorld("world"), 2059.5, 51.0, 1967.5), DialogBot.OLA);
        new Bot("Base62", new Location(Bukkit.getWorld("world"), 3100.5, 14.0, 3104.5), DialogBot.LEVEL_2_2B);
        new Bot("Base62", new Location(Bukkit.getWorld("world"), 5011.5, 31.0, 4948.5), DialogBot.LEVEL_2_EASTEREGG_1);
        new Bot("Base62", new Location(Bukkit.getWorld("world"), 5054.5, 31.0, 4874.5), DialogBot.LEVEL_2_EASTEREGG_2);
        new Bot("Base62", new Location(Bukkit.getWorld("world_nether"), 498.5, 74.0, 414.5), DialogBot.LEVEL_3_LOBBY);
        new Bot("Base62", new Location(Bukkit.getWorld("world_nether"), 456.5, 91.0, 683.5), DialogBot.LEVEL_3_3B1);
        new Bot("Base64_", new Location(Bukkit.getWorld("world_nether"), 395.5, 85.0, 738.5), DialogBot.LEVEL_3_3B);
        new Bot("Base64_", new Location(Bukkit.getWorld("world_nether"), 4876.5, 59.0, 4707.5), DialogBot.LEVEL_3_E_1);
        new Bot("Base62", new Location(Bukkit.getWorld("world_nether"), 4978.5, 60.0, 4710.5), DialogBot.LEVEL_3_E_R1);
        new Bot("Base62", new Location(Bukkit.getWorld("world_nether"), 5003.5, 59.0, 4709.5), DialogBot.LEVEL_3_E_R2);
        new Bot("Base62", new Location(Bukkit.getWorld("world_nether"), 5022.5, 59.0, 4709.5), DialogBot.LEVEL_3_E_R3);
        new Bot("Base62", new Location(Bukkit.getWorld("world_nether"), 4978.5, 67.0, 4672.5), DialogBot.LEVEL_3_E_R4);
        new Bot("Base62", new Location(Bukkit.getWorld("world_nether"), 4975.5, 64.0, 4688.5), DialogBot.LEVEL_3_E_R5);
        new Bot("Base62", new Location(Bukkit.getWorld("world_nether"), 4923.5, 53.0, 4689.5), DialogBot.LEVEL_3_E_R6);
        new Bot("Base62", new Location(Bukkit.getWorld("world_nether"), 5003.5, 67.0, 4644.5), DialogBot.LEVEL_3_E_R7);
        new Bot("Base62", new Location(Bukkit.getWorld("world_the_end"), 1859.5, 64.0, 2071.5), DialogBot.LEVEL_4_LOBBY);
        new Bot("Base62", new Location(Bukkit.getWorld("world_the_end"), 1757.5, 241.0, 1743.5), DialogBot.LEVEL_4_4B);
        new Bot("Base62", new Location(Bukkit.getWorld("world_the_end"), 2390.5, 53.0, 2147.5), DialogBot.BOA_SORTE);
        new Bot("Base62", new Location(Bukkit.getWorld("world_the_end"), 2395.5, 105.0, 2176.5), DialogBot.ZERAR_VIDA);
        new Bot("Base62", new Location(Bukkit.getWorld("world_the_end"), 4129.5, 64.0, 3387.5), DialogBot.LEVEL_4_4D);
        new Bot("Base62", new Location(Bukkit.getWorld("world_the_end"), 5808.5, 58.0, 4950.5), DialogBot.LEVEL_4_EASTEREGG_1);
        new Bot("Base65", new Location(Bukkit.getWorld("world_the_end"), 5796.5, 57.0, 4892.5), DialogBot.LEVEL_4_EASTEREGG_2);
    }

    public static void loadCheckpoints() throws MaxChangedBlocksException, DataException, IOException {
        new Checkpoint("1A-1", new Location(Bukkit.getWorld("world"), 1454.5, 23.0, 1477.5, 180, 0));
        new Checkpoint("1B-1", new Location(Bukkit.getWorld("world"), 1000.5, 25.0, 1447.5, 180, 0));
        new Checkpoint("1C-1", new Location(Bukkit.getWorld("world"), 1503.5, 1.0, 958.5, 140, 0));
        new Checkpoint("1C-2", new Location(Bukkit.getWorld("world"), 1499.5, 7.0, 927.5, -80, 0));
        new Checkpoint("1C-3", new Location(Bukkit.getWorld("world"), 1546.5, 7.0, 930.5, 180, 0));
        new Checkpoint("1C-4", new Location(Bukkit.getWorld("world"), 1548.5, 7.0, 881.5, 90, 0));
        new Checkpoint("1D-1", new Location(Bukkit.getWorld("world"), 1987.5, 13.0, 1026.5, 180, 0));
        new Checkpoint("1D-2", new Location(Bukkit.getWorld("world"), 1984.5, 21.0, 1004.5, 180, 0));
        new Checkpoint("1D-3", new Location(Bukkit.getWorld("world"), 1988.5, 26.0, 971.5, 180, 0));
        new Checkpoint("1D-4", new Location(Bukkit.getWorld("world"), 1985.5, 25.0, 926.5, 180, 0));
        new Checkpoint("2A-1", new Location(Bukkit.getWorld("world"), 2161.5, 14.0, 2176.5, 90, 0));
        new Checkpoint("2A-2", new Location(Bukkit.getWorld("world"), 2157.5, 9.0, 2136.5, -140, 0));
        new Checkpoint("2A-3", new Location(Bukkit.getWorld("world"), 2186.5, 15.0, 2132.5, -90, 0));
        new Checkpoint("2A-4", new Location(Bukkit.getWorld("world"), 2239.5, 19.0, 2096.5, -90, 0));
        new Checkpoint("2A-5", new Location(Bukkit.getWorld("world"), 2236.5, 37.0, 2116.5, -40, 0));
        new Checkpoint("2A-6", new Location(Bukkit.getWorld("world"), 2241.5, 48.0, 2113.5, -50, 0));
        new Checkpoint("2B-1", new Location(Bukkit.getWorld("world"), 3098.5, 39.0, 3099.5, -110, 0));
        new Checkpoint("2B-2", new Location(Bukkit.getWorld("world"), 3098.5, 1.0, 3121.5, 160, 0));
        new Checkpoint("2B-3", new Location(Bukkit.getWorld("world"), 3108.5, 14.0, 3145.5, -150, 0));
        new Checkpoint("2B-4", new Location(Bukkit.getWorld("world"), 3146.5, 35.0, 3134.5, 70, 0));
        new Checkpoint("2C-1", new Location(Bukkit.getWorld("world"), 2109.5, 10.0, 1568.5, -140, 0));
        new Checkpoint("2C-2", new Location(Bukkit.getWorld("world"), 2148.5, 7.0, 1552.5, 40, 0));
        new Checkpoint("2C-3", new Location(Bukkit.getWorld("world"), 2147.5, 7.0, 1589.5, 60, 0));
        new Checkpoint("2C-4", new Location(Bukkit.getWorld("world"), 2136.5, 5.0, 1641.5, 40, 0));
        new Checkpoint("2C-5", new Location(Bukkit.getWorld("world"), 2129.5, 32.0, 1578.5, 90, 0));
        new Checkpoint("2D-1", new Location(Bukkit.getWorld("world"), 2899.5, 53.0, 2906.5, 120, 0));
        new Checkpoint("2D-2", new Location(Bukkit.getWorld("world"), 2896.5, 63.0, 2847.5, 80, 0));
        new Checkpoint("2D-3", new Location(Bukkit.getWorld("world"), 2849.5, 61.0, 2870.5, -160, 0));
        new Checkpoint("3A-1", new Location(Bukkit.getWorld("world_nether"), 871.5, 64.0, 330.5, 180, 0));
        new Checkpoint("3A-2", new Location(Bukkit.getWorld("world_nether"), 873.5, 64.0, 299.5, 90, 0));
        new Checkpoint("3A-3", new Location(Bukkit.getWorld("world_nether"), 823.5, 63.0, 303.5, 180, 0));
        new Checkpoint("3B-1", new Location(Bukkit.getWorld("world_nether"), 406.5, 85.0, 731.5, 30, 0));
        new Checkpoint("3B-2", new Location(Bukkit.getWorld("world_nether"), 437.5, 32.0, 695.5, 90, 0));
        new Checkpoint("3B-3", new Location(Bukkit.getWorld("world_nether"), 411.5, 106.0, 692.5, 150, 0));
        new Checkpoint("3C-1", new Location(Bukkit.getWorld("world_nether"), 155.5, 63.0, 436.5, -30, 0));
        new Checkpoint("3C-2", new Location(Bukkit.getWorld("world_nether"), 216.5, 73.0, 444.5, -150, 0));
        new Checkpoint("3C-3", new Location(Bukkit.getWorld("world_nether"), 164.5, 65.0, 469.5, 10, 0));
        new Checkpoint("3D-1", new Location(Bukkit.getWorld("world_nether"), 500.5, 36.0, -48.5, 180, 0));
        new Checkpoint("4A-1", new Location(Bukkit.getWorld("world_the_end"), 1288.5, 68.0, 2334.5, 90, 0));
        new Checkpoint("4A-2", new Location(Bukkit.getWorld("world_the_end"), 1230.5, 73.0, 2365.5, -20, 0));
        new Checkpoint("4C-1", new Location(Bukkit.getWorld("world_the_end"), 2383.5, 53.0, 2146.5, -90, 0));
        new Checkpoint("4C-2", new Location(Bukkit.getWorld("world_the_end"), 2421.5, 103.0, 2170.5, 90, 0));
        new Checkpoint("4D-1", new Location(Bukkit.getWorld("world_the_end"), 4125.5, 99.0, 3382.5, 140, 0));
        new Checkpoint("4D-2", new Location(Bukkit.getWorld("world_the_end"), 4136.5, 122.0, 3375.5, -130, 0));
        new Checkpoint("4D-3", new Location(Bukkit.getWorld("world_the_end"), 4171.5, 122.0, 3366.5, 60, 0));
        new Checkpoint("EG-4", new Location(Bukkit.getWorld("world_the_end"), 5816.5, 57.0, 4885.5, 170, 0));
    }

    public static void loadPortals() throws MaxChangedBlocksException, DataException, IOException {
        new Portal("L_L1", Warp.LOBBY_1.getLocation(), new Location(Bukkit.getWorld("world"), 8.5, 19.0, -274.5), null);
        new Portal("L1_1A", Warp.L_1A.getLocation(), new Location(Bukkit.getWorld("world"), 898.5, 30.0, 1000.5), null);
        new Portal("1A_L1", Warp.LOBBY_1.getLocation(), new Location(Bukkit.getWorld("world"), 1446.5, 27.0, 1443.5), Nivel.L_1A);
        new Portal("L1_1B", Warp.L_1B.getLocation(), new Location(Bukkit.getWorld("world"), 1000.5, 30.0, 898.5), null);
        new Portal("1B_L1", Warp.LOBBY_1.getLocation(), new Location(Bukkit.getWorld("world"), 1000.5, 28.0, 1403.5), Nivel.L_1B);
        new Portal("L1_1C", Warp.L_1C.getLocation(), new Location(Bukkit.getWorld("world"), 1102.5, 30.0, 1000.5), null);
        new Portal("1C_EG1", Warp.EASTEREGG_1.getLocation(), new Location(Bukkit.getWorld("world"), 1567.5, 2.0, 913.5), null);
        new Portal("EG1_1C", Warp.L_1C.getLocation(), new Location(Bukkit.getWorld("world"), -1000.5, 18.0, -1116.5), Nivel.EASTEREGG_1);
        new Portal("1C_L1", Warp.LOBBY_1.getLocation(), new Location(Bukkit.getWorld("world"), 1514.5, 30.0, 883.5), Nivel.L_1C);
        new Portal("L1_1D", Warp.L_1D.getLocation(), new Location(Bukkit.getWorld("world"), 1000.5, 30.0, 1102.5), null);
        new Portal("1D_L1", Warp.LOBBY_1.getLocation(), new Location(Bukkit.getWorld("world"), 1987.5, 6.0, 900.5), Nivel.L_1D);
        new Portal("L1_L2", Warp.LOBBY_2.getLocation(), new Location(Bukkit.getWorld("world"), 1006.5, 7.0, 988.5), null);
        new Portal("L2_2A", Warp.L_2A.getLocation(), new Location(Bukkit.getWorld("world"), 1981.5, 59.0, 1936.5), null);
        new Portal("2A_L2", Warp.LOBBY_2.getLocation(), new Location(Bukkit.getWorld("world"), 2247.5, 1.0, 2125.5), Nivel.L_2A);
        new Portal("L2_2B", Warp.L_2B.getLocation(), new Location(Bukkit.getWorld("world"), 2041.5, 60.0, 1990.5), null);
        new Portal("2B_L2", Warp.LOBBY_2.getLocation(), new Location(Bukkit.getWorld("world"), 3143.5, 20.0, 3165.5), Nivel.L_2B);
        new Portal("L2_2C", Warp.L_2C.getLocation(), new Location(Bukkit.getWorld("world"), 1956.5, 52.0, 2026.5), null);
        new Portal("2C_L2", Warp.LOBBY_2.getLocation(), new Location(Bukkit.getWorld("world"), 2125.5, 46.0, 1566.5), Nivel.L_2C);
        new Portal("L2_2D", Warp.L_2D.getLocation(), new Location(Bukkit.getWorld("world"), 1897.5, 60.0, 1962.5), null);
        new Portal("2D_L2", Warp.LOBBY_2.getLocation(), new Location(Bukkit.getWorld("world"), 2851.5, 78.0, 2868.5), Nivel.L_2D);
        new Portal("L2_EG2", Warp.EASTEREGG_2.getLocation(), new Location(Bukkit.getWorld("world"), 2046.5, 1.0, 1996.5), null);
        new Portal("EG2_L2", Warp.LOBBY_2.getLocation(), new Location(Bukkit.getWorld("world"), 5068.5, 31.0, 4873.5), Nivel.EASTEREGG_2);
        new Portal("L2_L3", Warp.LOBBY_3.getLocation(), new Location(Bukkit.getWorld("world_nether"), 341.5, 53.0, 206.5), null);
        new Portal("L3_3A", Warp.L_3A.getLocation(), new Location(Bukkit.getWorld("world_nether"), 443.5, 74.0, 377.5), null);
        new Portal("3A_L3", Warp.LOBBY_3.getLocation(), new Location(Bukkit.getWorld("world_nether"), 797.5, 33.0, 266.5), Nivel.L_3A);
        new Portal("L3_3B", Warp.L_3B.getLocation(), new Location(Bukkit.getWorld("world_nether"), 527.5, 60.0, 517.5), null);
        new Portal("3B_L3", Warp.LOBBY_3.getLocation(), new Location(Bukkit.getWorld("world_nether"), 301.5, 86.0, 680.5), Nivel.L_3B);
        new Portal("L3_3C", Warp.L_3C.getLocation(), new Location(Bukkit.getWorld("world_nether"), 447.5, 60.0, 457.5), null);
        new Portal("3C_L3", Warp.LOBBY_3.getLocation(), new Location(Bukkit.getWorld("world_nether"), 176.5, 82.0, 506.5), Nivel.L_3C);
        new Portal("L3_3D", Warp.L_3D.getLocation(), new Location(Bukkit.getWorld("world_nether"), 479.5, 54.0, 437.5), null);
        new Portal("3D_L3", Warp.LOBBY_3.getLocation(), new Location(Bukkit.getWorld("world_nether"), 460.5, 38.0, -130.5), Nivel.L_3D);
        new Portal("L3_EG3", Warp.EASTEREGG_3.getLocation(), new Location(Bukkit.getWorld("world_nether"), 297.5, 1.0, 453.5), null);
        new Portal("EG3_L3", Warp.LOBBY_3.getLocation(), new Location(Bukkit.getWorld("world_nether"), 4978.5, 75.0, 4622.5), Nivel.EASTEREGG_3);
        new Portal("L3_L4", Warp.LOBBY_4.getLocation(), new Location(Bukkit.getWorld("world_the_end"), 57.5, 59.0, -33.5), null);
        new Portal("L4_4A", Warp.L_4A.getLocation(), new Location(Bukkit.getWorld("world_the_end"), 1779.5, 57.0, 2084.5), null);
        new Portal("4A_L4", Warp.LOBBY_4.getLocation(), new Location(Bukkit.getWorld("world_the_end"), 1280.5, 1.0, 2333.5), Nivel.L_4A);
        new Portal("L4_4B", Warp.L_4B.getLocation(), new Location(Bukkit.getWorld("world_the_end"), 1857.5, 57.0, 1995.5), null);
        new Portal("4B_L4", Warp.LOBBY_4.getLocation(), new Location(Bukkit.getWorld("world_the_end"), 1809.5, 61.0, 975.5), Nivel.L_4B);
        new Portal("L4_4C", Warp.L_4C.getLocation(), new Location(Bukkit.getWorld("world_the_end"), 1932.5, 57.0, 2077.5), null);
        new Portal("4C_L4", Warp.LOBBY_4.getLocation(), new Location(Bukkit.getWorld("world_the_end"), 2387.5, 103.0, 2174.5), Nivel.L_4C);
        new Portal("L4_4D", Warp.L_4D.getLocation(), new Location(Bukkit.getWorld("world_the_end"), 1857.5, 57.0, 2132.5), null);
        new Portal("4D_L4", Warp.LOBBY_4.getLocation(), new Location(Bukkit.getWorld("world_the_end"), 4231.5, 132.0, 3370.5), Nivel.L_4D);
        new Portal("L4_EG4", Warp.EASTEREGG_4.getLocation(), new Location(Bukkit.getWorld("world_the_end"), 1957.5, 61.0, 2581.5), null);
        new Portal("L_FINAL", Warp.EASTEREGG_2.getLocation(), new Location(Bukkit.getWorld("world"), 8.5, 4.0, 30.5), null);
    }

    public static HashSet<Block> nearBlocks(Location loc, int radius) {
        HashSet<Block> blocks = new HashSet<>();
        for (int x = -radius; x <= radius; ++x) {
            for (int z = -radius; z <= radius; ++z) {
                for (int y = -radius; y <= radius; ++y) {
                    blocks.add(loc.clone().add(x, y, z).getBlock());
                }
            }
        }
        return blocks;
    }

    public static String placeZero(long number) {
        return number >= 10 ? Long.toString(number) : String.format("0%s", Long.toString(number));
    }

    public static void playDialog(Player player, DialogBot dialog) {
        if (dialog != null && player != null) {
            Utils.playSound(player, Audio.PARAR);
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "playsound " + dialog.getPatch() + " master " + player.getName() + " 0 0 0 50000 1");
                }
            }.runTaskLater(MapMeelMain.getPlugin(), 20);
        }
    }

    public static void playSound(Player player, Audio audio) {
        if (player == null || audio == null) {
            return;
        }
        if (audio.equals(Audio.FUNDO)) {
            if (Profile.getProfile(player.getName()).isAudioFundoOn()) {
                Utils.playSound(player, Profile.getProfile(player.getName()).getLocation().getFundo());
            }
            return;
        } else {
            player.playSound(player.getLocation(), audio.getPatch(), 50000, 1);
        }
        if (audio.equals(Audio.PARAR)) {
            player.stopSound("");
        }
    }

    public static void sendActionBar(Player player, String message) {
        PacketPlayOutChat chat = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + message + "\"}"), (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(chat);
    }

    public static void sendAlert(String message) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (Profile.getProfile(p.getName()).getCargo().isOp()) {
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 1, 50000);
                p.sendMessage("§4§l§m==============================");
                p.sendMessage("§c§lAVISO: §f§l" + message.replace("&", "§"));
                p.sendMessage("§4§l§m==============================");
            }
        }
        Bukkit.getConsoleSender().sendMessage("§4§l§m==============================");
        Bukkit.getConsoleSender().sendMessage("§c§lAVISO: §f§l" + message.replace("&", "§"));
        Bukkit.getConsoleSender().sendMessage("§4§l§m==============================");
    }

    public static void sendAlertCMS(String message) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (Profile.getProfile(p.getName()).getCargo().isOp()) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BASS, 0, 50000);
                p.sendMessage(Strings.cmsPrefix + "§f§l" + message.replace("&", "§"));
            }
        }
        Bukkit.getConsoleSender().sendMessage(Strings.cmsPrefix + "§f§l" + message.replace("&", "§"));
    }

    public static void sendTablist(Player player, String Header, String Footer) {
        try {
            Object header = Utils.getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", String.class).invoke(null, "{\"text\": \"" + Header + "\"}");
            Object footer = Utils.getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", String.class).invoke(null, "{\"text\": \"" + Footer + "\"}");
            Object ppoplhf = Utils.getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(new Class[]{Utils.getNmsClass("IChatBaseComponent")}).newInstance(header);
            Field f = ppoplhf.getClass().getDeclaredField("b");
            f.setAccessible(true);
            f.set(ppoplhf, footer);
            Object nmsp = player.getClass().getMethod("getHandle").invoke(player);
            Object pcon = nmsp.getClass().getField("playerConnection").get(nmsp);
            pcon.getClass().getMethod("sendPacket", Utils.getNmsClass("Packet")).invoke(pcon, ppoplhf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int duration, int fadeOut) {
        Preconditions.checkNotNull(title, "Title cannot be null");
        Preconditions.checkNotNull(subtitle, "Subtitle cannot be null");
        PacketPlayOutTitle timingsPacket = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, fadeIn, duration, fadeOut);
        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\"" + title + "\"}"));
        PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\":\"" + subtitle + "\"}"));
        PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
        playerConnection.sendPacket(timingsPacket);
        playerConnection.sendPacket(titlePacket);
        playerConnection.sendPacket(subtitlePacket);
    }

    public static String sToTime(long seconds) {
        long years = seconds / 32140800;
        long months = seconds % 32140800 / 2678400;
        long days = seconds % 2678400 / 86400;
        long hours = seconds % 86400 / 3600;
        long minutes = seconds % 3600 / 60;
        seconds = seconds % 60;
        return (years > 0 ? Utils.placeZero(years) + "A " : "") + (months > 0 ? Utils.placeZero(months) + "M " : "") + (days > 0 ? Utils.placeZero(days) + "d " : "") + (hours > 0 ? Utils.placeZero(hours) + "h " : "") + (minutes > 0 ? Utils.placeZero(minutes) + "m " : "") + (seconds > 0 ? Utils.placeZero(seconds) + "s" : "");
    }

    public static String sToTimeComplete(long seconds) {
        long years = seconds / 32140800;
        long months = seconds % 32140800 / 2678400;
        long days = seconds % 2678400 / 86400;
        long hours = seconds % 86400 / 3600;
        long minutes = seconds % 3600 / 60;
        seconds = seconds % 60;
        return (years > 0 ? Utils.placeZero(years) + (years == 1 ? " Ano " : " Anos ") : "") + (months > 0 ? Utils.placeZero(months) + (months == 1 ? " Mês " : " Meses ") : "") + (days > 0 ? Utils.placeZero(days) + (days == 1 ? " Dia " : " Dias ") : "") + (hours > 0 ? Utils.placeZero(hours) + (hours == 1 ? " Hora " : " Horas ") : "") + (minutes > 0 ? Utils.placeZero(minutes) + (minutes == 1 ? " Minuto " : " Minutos ") : "") + Utils.placeZero(seconds) + (seconds == 1 ? " Segundo" : " Segundos");
    }

}
