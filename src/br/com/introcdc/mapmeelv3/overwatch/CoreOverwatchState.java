package br.com.introcdc.mapmeelv3.overwatch;

import java.io.PrintWriter;
import java.util.ArrayList;

import org.bukkit.entity.Player;

public class CoreOverwatchState {

    private String message;
    private final ArrayList<CoreOverwatchPlayerState> states;

    public CoreOverwatchState() {
        this.states = new ArrayList<>();
        this.message = null;
    }

    public String getMessage() {
        return this.message;
    }

    public CoreOverwatchPlayerState getState(final Player player) {
        for (final CoreOverwatchPlayerState state : this.states) {
            if (state.getPlayer().getUniqueId().equals(player.getUniqueId())) {
                return state;
            }
        }
        return null;
    }

    public ArrayList<CoreOverwatchPlayerState> getStates() {
        return this.states;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void write(final PrintWriter writer) {
        StringBuilder line = new StringBuilder();
        for (final CoreOverwatchPlayerState state : this.states) {
            line.append(state.toString()).append(";");
        }
        if (this.getMessage() != null) {
            line.append("///").append(this.getMessage());
        }
        writer.println(line);
        writer.flush();
    }

}
