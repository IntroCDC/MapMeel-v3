package br.com.introcdc.mapmeelv3;

import java.util.HashMap;

import org.bukkit.Bukkit;

public class Profile {

    private static HashMap<String, Profile> profiles = new HashMap<>();

    public static Profile getProfile(String name) {
        if (Profile.getProfiles().containsKey(name)) {
            return Profile.getProfiles().get(name);
        }
        return new Profile(name);
    }

    public static HashMap<String, Profile> getProfiles() {
        return Profile.profiles;
    }

    public static void unloadProfile(String name) {
        Profile.getProfiles().remove(name);
    }

    private boolean audioFundo;
    private Cargo cargo;
    private String color;
    private int fails;
    private double flySpeed;
    private Warp location;
    private String name;

    private int time;

    public Profile(String name) {
        this.name = name;
        if (MapMeelMain.getPlugin().getConfig().getString("Profiles." + name + ".Color") != null) {
            this.setColor(MapMeelMain.getPlugin().getConfig().getString("Profiles." + name + ".Color").replace("&", "§"));
        } else {
            this.setColor("§f§l");
        }
        if (MapMeelMain.getPlugin().getConfig().getString("Profiles." + name + ".Cargo") != null) {
            this.setCargo(Cargo.byName(MapMeelMain.getPlugin().getConfig().getString("Profiles." + name + ".Cargo")));
        } else {
            this.setCargo(Cargo.JOGADOR);
        }
        if (MapMeelMain.getPlugin().getConfig().getInt("Profiles." + name + ".Fails") != 0) {
            this.fails = MapMeelMain.getPlugin().getConfig().getInt("Profiles." + name + ".Fails");
        } else {
            this.fails = -1;
            this.addFail();
        }
        if (MapMeelMain.getPlugin().getConfig().getString("Profiles." + name + ".Location") != null) {
            this.location = Warp.byName(MapMeelMain.getPlugin().getConfig().getString("Profiles." + name + ".Location"));
        } else {
            this.setLocation(Warp.LOBBY);
        }
        if (MapMeelMain.getPlugin().getConfig().getDouble("Profiles." + name + ".flySpeed") != 0.0) {
            this.setFlySpeed(MapMeelMain.getPlugin().getConfig().getDouble("Profiles." + name + ".flySpeed"));
        } else {
            this.setFlySpeed(0.1);
        }
        if (MapMeelMain.getPlugin().getConfig().getBoolean("Profiles." + name + ".audioFundo")) {
            this.setAudioFundoEnable(true);
        } else {
            this.setAudioFundoEnable(false);
        }
        if (MapMeelMain.getPlugin().getConfig().getInt("Profiles." + name + ".time") != 0) {
            this.time = MapMeelMain.getPlugin().getConfig().getInt("Profiles." + name + ".time");
        } else {
            this.time = -1;
            this.addTime();
        }
        if (Bukkit.getPlayer(name) != null) {
            if (!Profile.getProfiles().containsKey(name)) {
                Profile.getProfiles().put(name, this);
            }
        } else {
            Profile.unloadProfile(name);
        }
    }

    public void addFail() {
        this.fails++;
        MapMeelMain.getPlugin().getConfig().set("Profiles." + this.name + ".Fails", this.fails);
        MapMeelMain.getPlugin().saveConfig();
    }

    public void addTime() {
        this.time++;
        this.setTime(this.time);
    }

    public Cargo getCargo() {
        return this.cargo;
    }

    public String getColor() {
        return this.color;
    }

    public int getFails() {
        return this.fails;
    }

    public double getFlySpeed() {
        return this.flySpeed;
    }

    public Warp getLocation() {
        return this.location;
    }

    public String getName() {
        return this.name;
    }

    public int getTime() {
        return this.time;
    }

    public boolean isAudioFundoOn() {
        return this.audioFundo;
    }

    public void setAudioFundoEnable(boolean audioFundo) {
        this.audioFundo = audioFundo;
        MapMeelMain.getPlugin().getConfig().set("Profiles." + this.name + ".audioFundo", audioFundo);
        MapMeelMain.getPlugin().saveConfig();
    }

    public void setCargo(Cargo cargo) {
        MapMeelMain.getPlugin().getConfig().set("Profiles." + this.name + ".Cargo", cargo.getName());
        MapMeelMain.getPlugin().saveConfig();
        this.cargo = cargo;
    }

    public void setColor(String color) {
        MapMeelMain.getPlugin().getConfig().set("Profiles." + this.name + ".Color", color.replace("§", "&"));
        MapMeelMain.getPlugin().saveConfig();
        this.color = color;
    }

    public void setFlySpeed(double flySpeed) {
        MapMeelMain.getPlugin().getConfig().set("Profiles." + this.name + ".flySpeed", flySpeed);
        MapMeelMain.getPlugin().saveConfig();
        this.flySpeed = flySpeed;
        if (Bukkit.getPlayer(this.name) != null) {
            Bukkit.getPlayer(this.name).setFlySpeed((float) flySpeed);
        }
    }

    public void setLocation(Warp location) {
        this.location = location;
        MapMeelMain.getPlugin().getConfig().set("Profiles." + this.name + ".Location", location.getName());
        MapMeelMain.getPlugin().saveConfig();
    }

    public void setTime(int time) {
        this.time = time;
        MapMeelMain.getPlugin().getConfig().set("Profiles." + this.name + ".time", time);
        MapMeelMain.getPlugin().saveConfig();
    }

}
