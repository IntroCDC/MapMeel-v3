package br.com.introcdc.mapmeelv3.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.introcdc.mapmeelv3.Audio;
import br.com.introcdc.mapmeelv3.Cargo;
import br.com.introcdc.mapmeelv3.MapMeelMain;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.Utils;
import br.com.introcdc.mapmeelv3.variables.Strings;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandAudio implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            if (Profile.getProfile(sender.getName()).getCargo().isBefore(Cargo.ADMIN)) {
                sender.sendMessage(Strings.prefix + "§cVocê não tem permissão para isto!");
                return false;
            }
        }
        if (args.length == 0) {
            sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " <Áudio/Fundo> [Jogador/ALL(OPCIONAL)] [delay(segundos/OPCIONAL)]");
            if (!(sender instanceof Player)) {
                StringBuilder audios = new StringBuilder();
                for (final Audio a : Audio.values()) {
                    audios.append("§a").append(a.getName()).append("§f,");
                }
                sender.sendMessage(Strings.prefix + "§fÁudios: " + audios);
                return false;
            } else {
                final TextComponent audios = new TextComponent(Strings.prefix + "§fÁudios: ");
                for (final Audio a : Audio.values()) {
                    final TextComponent audio = new TextComponent("§a" + a.getName() + "§f,");
                    audio.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7§l! §7Clique para tocar a música!").create()));
                    audio.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/audio " + a.getName()));
                    audios.addExtra(audio);
                }
                ((Player) sender).spigot().sendMessage(audios);
            }
            return false;
        }
        int tempo = 0;
        if (args.length == 3) {
            try {
                tempo = Integer.parseInt(args[2]);
            } catch (final Exception e) {
                sender.sendMessage(Strings.prefix + "§cNúmero inválido!");
                return false;
            }
        }
        if (tempo < 0) {
            sender.sendMessage(Strings.prefix + "§cUtilize números maiores que 0!");
            return false;
        }
        if (tempo > 1000) {
            sender.sendMessage(Strings.prefix + "§cNão utilize números maiores que 1000!");
            return false;
        }
        if (!Audio.existsAudio(args[0])) {
            sender.sendMessage(Strings.prefix + "§cÁudio inexistente!");
            StringBuilder audios = new StringBuilder();
            for (final Audio a : Audio.values()) {
                audios.append("§a").append(a.getName()).append("§f,");
            }
            sender.sendMessage(Strings.prefix + "§fÁudios: " + audios);
            return false;
        }
        final Audio audio = Audio.byName(args[0]);
        if (args.length == 1 || args[1].equalsIgnoreCase("all")) {
            if (audio.equals(Audio.PARAR)) {
                if (tempo == 0) {
                    sender.sendMessage(Strings.prefix + "§fParando todos os áudios em execução de todos os jogadores online!");
                } else {
                    sender.sendMessage(Strings.prefix + "§fParando todos os áudios em execução de todos os jogadores online com um delay de §b" + tempo + " §f" + (tempo == 1 ? "segundo!" : "segundos!"));
                }
            } else if (audio.equals(Audio.FUNDO)) {
                if (tempo == 0) {
                    sender.sendMessage(Strings.prefix + "§fExecutando áudio individual de fundo para todos os jogadores online!");
                } else {
                    sender.sendMessage(Strings.prefix + "§fExecutando áudio individual de fundo para todos os jogadores online com um delay de §b" + tempo + " §f" + (tempo == 1 ? "segundo!" : "segundos!"));
                }
            } else {
                if (tempo == 0) {
                    sender.sendMessage(Strings.prefix + "§fExecutando o áudio §a" + audio.getName() + "§f para todos os jogadores online!");
                } else {
                    sender.sendMessage(Strings.prefix + "§fExecutando o áudio §a" + audio.getName() + "§f para todos os jogadores online com um delay de §b" + tempo + " §f" + (tempo == 1 ? "segundo!" : "segundos!"));
                }
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (final Player p : Bukkit.getOnlinePlayers()) {
                        Utils.playSound(p, audio);
                    }
                    if (audio.equals(Audio.PARAR)) {
                        sender.sendMessage(Strings.prefix + "§fParando...");
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                sender.sendMessage(Strings.prefix + "§fÁudio parado com sucesso!");
                            }
                        }.runTaskLater(MapMeelMain.getPlugin(), 20L);
                    } else {
                        sender.sendMessage(Strings.prefix + "§fExecutando...");
                    }
                }
            }.runTaskLater(MapMeelMain.getPlugin(), tempo * 20);
        } else {
            if (Bukkit.getPlayer(args[1]) == null) {
                sender.sendMessage(Strings.prefix + "§cEste jogador está offline...");
                return false;
            }
            final Player pl = Bukkit.getPlayer(args[1]);
            if (audio.equals(Audio.PARAR)) {
                if (tempo == 0) {
                    sender.sendMessage(Strings.prefix + "§fParando todos os áudios em execução de §a" + pl.getName() + "§f!");
                } else {
                    sender.sendMessage(Strings.prefix + "§fParando todos os áudios em execução de §a" + pl.getName() + "§f com um delay de §b" + tempo + " §f" + (tempo == 1 ? "segundo!" : "segundos!"));
                }
            } else if (audio.equals(Audio.FUNDO)) {
                if (tempo == 0) {
                    sender.sendMessage(Strings.prefix + "§fExecutando áudio individual de §a" + pl.getName() + "§f!");
                } else {
                    sender.sendMessage(Strings.prefix + "§fExecutando áudio individual de §a" + pl.getName() + "§f com um delay de §b" + tempo + " §f" + (tempo == 1 ? "segundo!" : "segundos!"));
                }
            } else {
                if (tempo == 0) {
                    sender.sendMessage(Strings.prefix + "§fExecutando o áudio §a" + audio.getName() + "§f para §a" + pl.getName() + "§f!");
                } else {
                    sender.sendMessage(Strings.prefix + "§fExecutando o áudio §a" + audio.getName() + "§f para §a" + pl.getName() + "§f com um delay de §b" + tempo + " §f" + (tempo == 1 ? "segundo!" : "segundos!"));
                }
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (Bukkit.getPlayer(args[1]) == null) {
                        sender.sendMessage(Strings.prefix + "§cNão foi possível executar o áudio com delay pois o jogador está offline!");
                    } else {
                        Utils.playSound(pl, audio);
                        if (audio.equals(Audio.PARAR)) {
                            sender.sendMessage(Strings.prefix + "§fParando...");
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    sender.sendMessage(Strings.prefix + "§fÁudio parado com sucesso!");
                                }
                            }.runTaskLater(MapMeelMain.getPlugin(), 20L);
                        } else {
                            sender.sendMessage(Strings.prefix + "§fExecutando...");
                        }
                    }
                }
            }.runTaskLater(MapMeelMain.getPlugin(), tempo * 20);
        }
        return false;
    }

}
