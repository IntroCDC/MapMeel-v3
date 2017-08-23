package br.com.introcdc.mapmeelv3;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

public class Bot {

    private static HashMap<Integer, Bot> bots = new HashMap<>();

    public static Bot getBot(final int id) {
        if (Bot.getBots().containsKey(id)) {
            return Bot.getBots().get(id);
        }
        return null;
    }

    public static HashMap<Integer, Bot> getBots() {
        return Bot.bots;
    }

    private final DialogBot dialog;
    private final int id;
    private final Location location;
    private final String nick;

    private final NPC npc;

    public Bot(final String nick, final Location location, final DialogBot dialog) {
        this.nick = nick;
        this.location = location;
        this.dialog = dialog;
        this.npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, nick);
        this.npc.spawn(location);
        this.id = this.npc.getId();
        if (!Bot.getBots().containsKey(this.id)) {
            Bot.bots.put(this.id, this);
        }
        this.update();
    }

    public DialogBot getDialog() {
        return this.dialog;
    }

    public int getId() {
        return this.id;
    }

    public Location getLocation() {
        return this.location;
    }

    public String getNick() {
        return this.nick;
    }

    public NPC getNpc() {
        return this.npc;
    }

    public void playDialog(final Player player) {
        if (player == null) {
            this.getNpc().setName(this.getNick());
            return;
        }
        if (this.dialog != null && !Profile.getProfile(player.getName()).getLocation().getName().equalsIgnoreCase(Warp.FINAL.getName())) {
            Utils.playSound(player, Audio.PARAR);
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (Bot.this.dialog.equals(DialogBot.SEJA_BEM_VINDA)) {
                        if (Nivel.checkLevel(Nivel.L_1A, Nivel.L_1B, Nivel.L_1C, Nivel.L_1D, Nivel.EASTEREGG_1, Nivel.L_2A, Nivel.L_2B, Nivel.L_2C, Nivel.L_2D, Nivel.EASTEREGG_2, Nivel.L_3A, Nivel.L_3B, Nivel.L_3C, Nivel.L_3D, Nivel.EASTEREGG_3, Nivel.L_4A, Nivel.L_4B, Nivel.L_4C, Nivel.L_4D)) {
                            Nivel.EASTEREGG_4.setCleared(true);
                            Bot.this.getNpc().setName("Introo");
                            Utils.playDialog(player, DialogBot.MENSAGEM_ANIVESARIO);
                        } else {
                            Bot.this.getNpc().setName(Bot.this.getNick());
                            Utils.playDialog(player, Bot.this.dialog);
                        }
                    } else {
                        Utils.playDialog(player, Bot.this.dialog);
                    }
                }
            }.runTaskLater(MapMeelMain.getPlugin(), 20);
        }
    }

    public void update() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().size() >= 1) {
                    Player p = null;
                    double distancia = 10000000;
                    for (final Player pl : Bukkit.getOnlinePlayers()) {
                        if (!pl.getWorld().equals(Bot.this.location.getWorld())) {
                            continue;
                        }
                        if (pl.getLocation().distance(Bot.this.location) <= distancia) {
                            p = pl;
                            distancia = pl.getLocation().distance(Bot.this.location);
                        }
                    }
                    if (Utils.isMeelOn()) {
                        if (Utils.getMeel().getWorld().equals(Bot.this.location.getWorld())) {
                            p = Utils.getMeel();
                        }
                    }
                    if (p != null) {
                        Bot.this.npc.faceLocation(p.getLocation());
                    }
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 3);
    }

}
