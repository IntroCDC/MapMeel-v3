package br.com.introcdc.mapmeelv3;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Bot {

    private static HashMap<Integer, Bot> bots = new HashMap<>();

    public static Bot getBot(int id) {
        if (Bot.getBots().containsKey(id)) {
            return Bot.getBots().get(id);
        }
        return null;
    }

    public static HashMap<Integer, Bot> getBots() {
        return Bot.bots;
    }

    private DialogBot dialog;
    private int id;
    private Location location;
    private String nick;
    private String skin;

    private NPC npc;

    public Bot(String nick, String skin, Location location, DialogBot dialog) {
        this.nick = nick;
        this.skin = skin;
        this.location = location;
        this.dialog = dialog;
        this.npc = Utils.createNPC(EntityType.PLAYER, nick, location, skin);
        this.id = this.npc.getId();
        if (!Bot.getBots().containsKey(this.id)) {
            Bot.bots.put(this.id, this);
        }
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

    public void playDialog(Player player) {
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

    // Raio em que algum jogador precisa estar para valer a pena virar o NPC.
    private static final double VIEW_RADIUS_SQUARED = 64 * 64;

    /**
     * Antes cada bot tinha a própria tarefa (a cada 3 ticks), e cada uma chamava
     * isMeelOn()/getMeel() percorrendo todos os jogadores. Agora é uma tarefa só,
     * a Meel é calculada uma vez por ciclo e bots sem ninguém por perto são ignorados.
     */
    public static void startUpdater() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().isEmpty()) {
                    return;
                }
                Player meel = Utils.getMeel();
                for (Bot bot : Bot.bots.values()) {
                    bot.faceNearest(meel);
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 3);
    }

    private void faceNearest(Player meel) {
        if (this.npc == null || !this.npc.isSpawned()) {
            return;
        }
        Player p = null;
        double distancia = Double.MAX_VALUE;
        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (pl.getWorld() != this.location.getWorld()) {
                continue;
            }
            double d = pl.getLocation().distanceSquared(this.location);
            if (d <= distancia) {
                p = pl;
                distancia = d;
            }
        }
        if (p == null || distancia > Bot.VIEW_RADIUS_SQUARED) {
            return;
        }
        if (meel != null && meel.getWorld() == this.location.getWorld()) {
            p = meel;
        }
        this.npc.faceLocation(p.getLocation());
    }

}
