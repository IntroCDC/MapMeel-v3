package br.com.introcdc.mapmeelv3.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.introcdc.mapmeelv3.Cargo;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.Utils;
import br.com.introcdc.mapmeelv3.listeners.LeaveJoin;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class CommandBloqueador implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            if (Profile.getProfile(sender.getName()).getCargo().isBefore(Cargo.ADMIN)) {
                sender.sendMessage(Strings.prefix + "�cVoc� n�o tem permiss�o para isto!");
                return false;
            }
        }
        if (args.length != 2) {
            sender.sendMessage(Strings.prefix + "�cUso correto: /" + command.getName() + " [convidados/meel/jogadores] [bloquear/desbloquear/check]");
            return false;
        } else {
            if (args[0].equalsIgnoreCase("convidados")) {
                if (args[1].equalsIgnoreCase("check")) {
                    if (LeaveJoin.convidados) {
                        sender.sendMessage(Strings.prefix + "�fO Servidor est� liberado para convidados!");
                    } else {
                        sender.sendMessage(Strings.prefix + "�fO Servidor est� bloqueado para convidados!");
                    }
                } else if (args[1].equalsIgnoreCase("bloquear")) {
                    LeaveJoin.convidados = false;
                    sender.sendMessage(Strings.prefix + "�fVoc� bloqueou o servidor para convidados com sucesso!");
                    Utils.sendAlert(sender.getName() + " bloqueou o servidor para convidados!");
                } else if (args[1].equalsIgnoreCase("desbloquear")) {
                    LeaveJoin.convidados = true;
                    sender.sendMessage(Strings.prefix + "�fVoc� desbloqueou o servidor para convidados com sucesso!");
                    Utils.sendAlert(sender.getName() + " desbloqueou o servidor para convidados!");
                } else {
                    sender.sendMessage(Strings.prefix + "�cUso correto: /" + command.getName() + " [convidados/meel/jogadores] [bloquear/desbloquear/check]");
                    return false;
                }
            } else if (args[0].equalsIgnoreCase("meel")) {
                if (args[1].equalsIgnoreCase("check")) {
                    if (LeaveJoin.liberado) {
                        sender.sendMessage(Strings.prefix + "�fO Servidor est� liberado para a Meel!");
                    } else {
                        sender.sendMessage(Strings.prefix + "�fO Servidor est� bloqueado para a Meel!");
                    }
                } else if (args[1].equalsIgnoreCase("bloquear")) {
                    LeaveJoin.liberado = false;
                    sender.sendMessage(Strings.prefix + "�fVoc� bloqueou o servidor para a Meel com sucesso!");
                    Utils.sendAlert(sender.getName() + " bloqueou o servidor para a Meel!");
                } else if (args[1].equalsIgnoreCase("desbloquear")) {
                    LeaveJoin.liberado = true;
                    sender.sendMessage(Strings.prefix + "�fVoc� desbloqueou o servidor para a Meel com sucesso!");
                    Utils.sendAlert(sender.getName() + " desbloqueou o servidor para a Meel!");
                } else {
                    sender.sendMessage(Strings.prefix + "�cUso correto: /" + command.getName() + " [convidados/meel/jogadores] [bloquear/desbloquear/check]");
                    return false;
                }
            } else if (args[0].equalsIgnoreCase("jogadores")) {
                if (args[1].equalsIgnoreCase("check")) {
                    if (LeaveJoin.jogadores) {
                        sender.sendMessage(Strings.prefix + "�fO Servidor est� liberado para jogadores!");
                    } else {
                        sender.sendMessage(Strings.prefix + "�fO Servidor est� bloqueado para jogadores!");
                    }
                } else if (args[1].equalsIgnoreCase("bloquear")) {
                    LeaveJoin.jogadores = false;
                    sender.sendMessage(Strings.prefix + "�fVoc� bloqueou o servidor para jogadores com sucesso!");
                    Utils.sendAlert(sender.getName() + " bloqueou o servidor para jogadores!");
                } else if (args[1].equalsIgnoreCase("desbloquear")) {
                    LeaveJoin.jogadores = true;
                    sender.sendMessage(Strings.prefix + "�fVoc� desbloqueou o servidor para jogadores com sucesso!");
                    Utils.sendAlert(sender.getName() + " bloqueou o servidor para jogadores!");
                } else {
                    sender.sendMessage(Strings.prefix + "�cUso correto: /" + command.getName() + " [convidados/meel/jogadores] [bloquear/desbloquear/check]");
                    return false;
                }
            } else {
                sender.sendMessage(Strings.prefix + "�cUso correto: /" + command.getName() + " [convidados/meel/jogadores] [bloquear/desbloquear]");
                return false;
            }
        }
        return false;
    }

}
