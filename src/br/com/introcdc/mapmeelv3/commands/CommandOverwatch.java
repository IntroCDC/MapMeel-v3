package br.com.introcdc.mapmeelv3.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.introcdc.mapmeelv3.Cargo;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.overwatch.CoreOverwatch;
import br.com.introcdc.mapmeelv3.variables.Strings;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandOverwatch implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            if (Profile.getProfile(sender.getName()).getCargo().isBefore(Cargo.ADMIN)) {
                sender.sendMessage(Strings.prefix + "§cVocê não tem permissão para isto!");
                return false;
            }
        }
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("start")) {
                if (CoreOverwatch.recording) {
                    sender.sendMessage(Strings.prefix + "§cJá está sendo gravado um overwatch!");
                    return false;
                }
                sender.sendMessage(Strings.prefix + "§fGravando overwatch com o nome §a" + CoreOverwatch.startRecord("Overwatch gravado via comando por " + sender.getName()) + "§f...");
                return false;
            }
            if (args[0].equalsIgnoreCase("stop")) {
                if (!CoreOverwatch.recording) {
                    sender.sendMessage(Strings.prefix + "§cNão há nenhuma gravação overwatch sendo gravada!");
                    return false;
                }
                CoreOverwatch.stopRecording();
                sender.sendMessage(Strings.prefix + "§fGravação do overwatch atual parada!");
                return false;
            }
            if (args[0].equalsIgnoreCase("list")) {
                if (CoreOverwatch.listOverwatches().isEmpty()) {
                    sender.sendMessage(Strings.prefix + "§cNão há nenhum overwatch pendente!");
                    return false;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Strings.prefix + "§cEste comando só pode ser executado por jogadores inGame!");
                    return false;
                }
                final TextComponent message = new TextComponent(Strings.prefix + "§fOverwatches: §a");
                for (final String name : CoreOverwatch.listOverwatches()) {
                    final TextComponent overwatch = new TextComponent("§a" + name + "§f, ");
                    overwatch.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§f§l! §fColocar este nome no seu chat!").create()));
                    overwatch.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/overwatch replay/delete " + name));
                    message.addExtra(overwatch);
                }
                ((Player) sender).spigot().sendMessage(message);
                return false;
            }
            if (args[0].equalsIgnoreCase("replay") && args.length == 2) {
                if (!CoreOverwatch.listOverwatches().contains(args[1])) {
                    sender.sendMessage(Strings.prefix + "§cOverwatch não encontrado!");
                    return false;
                }
                final String name = args[1];
                try {
                    CoreOverwatch.replay(name, sender);
                } catch (final FileNotFoundException e) {
                    sender.sendMessage(Strings.prefix + "§cOcorreu um erro ao iniciar o replay da gravação...");
                }
                return false;
            }
            if (args[0].equalsIgnoreCase("delete") && args.length == 2) {
                if (!CoreOverwatch.listOverwatches().contains(args[1])) {
                    sender.sendMessage(Strings.prefix + "§cOverwatch não encontrado!");
                    return false;
                }
                final String name = args[1];
                try {
                    FileUtils.deleteDirectory(new File("Overwatches/" + name));
                    sender.sendMessage(Strings.prefix + "§fVocê deletou o overwatch com o nome §a" + name + "§f com sucesso!");
                } catch (final IOException e) {
                    sender.sendMessage(Strings.prefix + "§cOcorreu um erro ao deletar o overwatch...");
                    e.printStackTrace();
                }
                return false;
            }
        }
        sender.sendMessage(Strings.prefix + "§fUso correto: /" + label + " [start/stop/list/replay/delete] [name]");
        return false;
    }

}
