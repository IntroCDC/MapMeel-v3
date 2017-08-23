package br.com.introcdc.mapmeelv3.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.introcdc.mapmeelv3.Audio;
import br.com.introcdc.mapmeelv3.Cargo;
import br.com.introcdc.mapmeelv3.HistoriaMapMeelv3;
import br.com.introcdc.mapmeelv3.MapMeelMain;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.Utils;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class CommandTeatro implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            if (Profile.getProfile(sender.getName()).getCargo().isBefore(Cargo.ADMIN)) {
                sender.sendMessage(Strings.prefix + "§cVocê não tem permissão para isto!");
                return false;
            }
        }
        if (args.length == 0) {
            sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " [luzes/historia/loadbots/schematic]");
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("historia")) {
                sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " historia [iniciar/parar]");
            } else if (args[0].equalsIgnoreCase("luzes")) {
                sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " luzes [palco/plateia]");
            } else if (args[0].equalsIgnoreCase("schematic")) {
                sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " schematic [tipo]");
            } else if (args[0].equalsIgnoreCase("loadbots")) {
                HistoriaMapMeelv3.loadBots(false);
            } else {
                sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " [luzes/historia/schematic]");
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("historia")) {
                if (args[1].equalsIgnoreCase("iniciar")) {
                    for (final Player p : Bukkit.getOnlinePlayers()) {
                        Utils.playSound(p, Audio.PARAR);
                    }
                    sender.sendMessage(Strings.prefix + "§fIniciando história...");
                    if (HistoriaMapMeelv3.start) {
                        sender.sendMessage(Strings.prefix + "§cA História já começou!");
                        return false;
                    }
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            HistoriaMapMeelv3.startHistoria();
                        }
                    }.runTaskLater(MapMeelMain.getPlugin(), 20);
                } else if (args[1].equalsIgnoreCase("parar")) {
                    sender.sendMessage(Strings.prefix + "§fParando história...");
                    HistoriaMapMeelv3.start = false;
                    HistoriaMapMeelv3.autoStarted = false;
                    for (final Player p : Bukkit.getOnlinePlayers()) {
                        Utils.playSound(p, Audio.PARAR);
                    }
                } else if (args[1].equalsIgnoreCase("teste")) {
                    for (final Player p : Bukkit.getOnlinePlayers()) {
                        Utils.playSound(p, Audio.PARAR);
                    }
                    sender.sendMessage(Strings.prefix + "§fIniciando história...");
                    if (HistoriaMapMeelv3.start) {
                        sender.sendMessage(Strings.prefix + "§cA História já começou!");
                        return false;
                    }
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            HistoriaMapMeelv3.startHistoriaTeste(false);
                        }
                    }.runTaskLater(MapMeelMain.getPlugin(), 20);
                } else {
                    sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " historia [iniciar/parar]");
                }
            } else if (args[0].equalsIgnoreCase("schematic")) {
                sender.sendMessage(Strings.prefix + "§fCarregando schematic §a" + args[1] + "§f...");
                HistoriaMapMeelv3.loadConstruction(args[1]);
                sender.sendMessage(Strings.prefix + "§fSchematic §a" + args[1] + "§f carregada com sucesso!");
            } else if (args[0].equalsIgnoreCase("luzes")) {
                sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " luzes [palco/plateia]");
            } else {
                sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " [luzes/historia/loadbots/schematic]");
            }
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("luzes")) {
                if (args[1].equalsIgnoreCase("palco")) {
                    sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " luzes [palco/plateia] [ligar/desligar] [tudo/meio/direira[1/2/3]/esquerda[1/2/3]/meio[1/2/3]/atras[cima/baixo]/lateral[direita/esquerda]/cortina]");
                } else if (args[1].equalsIgnoreCase("plateia")) {
                    if (args[2].equalsIgnoreCase("ligar")) {
                        sender.sendMessage(Strings.prefix + "§fLuzes da plateia ligadas!");
                        HistoriaMapMeelv3.toggleLightsAudience(true);
                    } else if (args[2].equalsIgnoreCase("desligar")) {
                        sender.sendMessage(Strings.prefix + "§fLuzes da plateia desligadas!");
                        HistoriaMapMeelv3.toggleLightsAudience(false);
                    } else {
                        sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " luzes [palco/plateia] [ligar/desligar]");
                    }
                } else {
                    sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " luzes [palco/plateia] [ligar/desligar]");
                }
            } else {
                sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " [luzes/historia/loadbots/schematic]");
            }
        } else {
            if (args[0].equalsIgnoreCase("luzes")) {
                if (args[1].equalsIgnoreCase("palco")) {
                    if (args[2].equalsIgnoreCase("ligar")) {
                        sender.sendMessage(Strings.prefix + "§fLuzes do palco do tipo " + args[3].toLowerCase() + " ligadas");
                        HistoriaMapMeelv3.toggleLightsStage(true, args[3].toLowerCase());
                    } else if (args[2].equalsIgnoreCase("desligar")) {
                        sender.sendMessage(Strings.prefix + "§fLuzes do palco do tipo " + args[3].toLowerCase() + " desligadas");
                        HistoriaMapMeelv3.toggleLightsStage(false, args[3].toLowerCase());
                    } else {
                        sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " luzes [palco/plateia] [ligar/desligar] [tudo/meio/direira[1/2/3]/esquerda[1/2/3]/meio[1/2/3]/atras[cima/baixo]/lateral[direita/esquerda]/cortina]");
                    }
                } else if (args[1].equalsIgnoreCase("plateia")) {
                    sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " luzes [palco/plateia] [ligar/desligar]");
                } else {
                    sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " luzes [palco/plateia] [ligar/desligar]");
                }
            } else {
                sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " [luzes/historia/loadbots/schematic]");
            }
        }
        return false;
    }

}
