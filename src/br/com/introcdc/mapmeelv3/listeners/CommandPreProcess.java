package br.com.introcdc.mapmeelv3.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.server.ServerCommandEvent;

import br.com.introcdc.mapmeelv3.Cargo;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.Utils;
import br.com.introcdc.mapmeelv3.overwatch.CoreOverwatch;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class CommandPreProcess implements Listener {

    @EventHandler
    public void onCMD(ServerCommandEvent event) {
        String message = event.getCommand().toLowerCase();
        if (message.contains("tnt") || message.contains("46") || message.contains("explosive")) {
            event.setCancelled(true);
            event.getSender().sendMessage(Strings.prefix + "§4§lUSO DE COMANDO COM 'TNT', '46' ou 'EXPLOSIVE' SÃO EXTREMAMENTE PROIBIDOS!");
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage().toLowerCase();
        if (CoreOverwatch.recording) {
            if (CoreOverwatch.currentState != null) {
                CoreOverwatch.currentState.setMessage(event.getPlayer().getName() + " executou o comando: " + message);
            }
        }
        if (!Profile.getProfile(event.getPlayer().getName()).getCargo().isOp()) {
            if (!message.startsWith("/checkpoint") && !message.startsWith("/meel") && !message.startsWith("/invsee") && !message.startsWith("/top") && !message.startsWith("/lobby")) {
                event.setCancelled(true);
            }
        }
        if (Utils.isMeelOn()) {
            if (message.contains("//") || message.contains("setblock") || message.contains("fill") || message.contains("clone")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(Strings.prefix + "§cComando desativado com a Meel on.");
                return;
            }
        }
        if (message.toLowerCase().startsWith("/afcr")) {
            return;
        }
        if (message.toLowerCase().startsWith("/tp")) {
            return;
        }
        if (message.contains("tnt") || message.contains("46") || message.contains("explosive") && event.getPlayer().isOp()) {
            event.setCancelled(true);
            event.getPlayer().getInventory().clear();
            event.getPlayer().sendMessage(Strings.prefix + "\n\n§4§lUSO DE COMANDO COM 'TNT', '46' ou 'EXPLOSIVE' SÃO EXTREMAMENTE PROIBIDOS!");
        }
    }

    @EventHandler
    public void onGamemode(PlayerGameModeChangeEvent event) {
        if (Profile.getProfile(event.getPlayer().getName()).getCargo().equals(Cargo.JOGADOR) && !event.getNewGameMode().equals(GameMode.ADVENTURE)) {
            event.setCancelled(true);
            event.getPlayer().setGameMode(GameMode.ADVENTURE);
        }
        if (Profile.getProfile(event.getPlayer().getName()).getCargo().equals(Cargo.MEEL) && !event.getNewGameMode().equals(GameMode.ADVENTURE)) {
            event.setCancelled(true);
            event.getPlayer().setGameMode(GameMode.ADVENTURE);
        }
        if (Profile.getProfile(event.getPlayer().getName()).getCargo().equals(Cargo.CONVIDADO) && !event.getNewGameMode().equals(GameMode.SPECTATOR)) {
            event.setCancelled(true);
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
        }
    }

}
