package br.com.introcdc.mapmeelv3.listeners;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import br.com.introcdc.mapmeelv3.variables.Strings;

public class MotdEvent implements Listener {

    @EventHandler
    public void onPing(final ServerListPingEvent event) {
        final ArrayList<String> motds = new ArrayList<>();
        motds.add(Strings.prefix + "MapMeel v3 by Intro!");
        motds.add(Strings.prefix + "10/01/2017 ;3");
        motds.add(Strings.prefix + "Don't let me Down!");
        motds.add(Strings.cmsPrefix + "§cEste servidor está protegido com o §4§LCMS §cv. 1.0 (Custom Map Security) - Custom Build for 'MapMeel v3'");
        event.setMotd(motds.get(new Random().nextInt(motds.size())));
    }

}
