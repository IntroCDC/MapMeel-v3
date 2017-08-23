package br.com.introcdc.mapmeelv3.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import br.com.introcdc.mapmeelv3.Bot;
import net.citizensnpcs.api.event.NPCRightClickEvent;

public class NPCClick implements Listener {

    @EventHandler
    public void onRightClick(final NPCRightClickEvent event) {
        if (Bot.getBot(event.getNPC().getId()) != null) {
            Bot.getBot(event.getNPC().getId()).playDialog(event.getClicker());
        }
    }

}
