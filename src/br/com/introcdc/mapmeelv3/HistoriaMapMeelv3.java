package br.com.introcdc.mapmeelv3;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.world.DataException;

import br.com.introcdc.mapmeelv3.variables.Strings;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

public class HistoriaMapMeelv3 {

    public static boolean autoStarted = false;
    public static int location = -4;
    public static Location meio = new Location(Bukkit.getWorld("world"), 5001.0, 37.0, 4873.0);
    public static Location meio_atras = new Location(Bukkit.getWorld("world"), 5001.0, 37.0, 4865.0);
    public static Location meio_frente = new Location(Bukkit.getWorld("world"), 5001.0, 36.0, 4885.0);
    public static NPC NPCbase62;
    public static NPC NPCbase64;
    public static NPC NPCbase65;
    public static NPC NPCbiidu;
    public static NPC NPCbudokkan;
    // Abril
    public static NPC NPCdeb;
    public static NPC NPCdiego;
    public static NPC NPCilari;
    // Principais
    public static NPC NPCintro;
    public static NPC NPCjorbe;
    public static NPC NPCkeroch;
    // Agosto
    public static NPC NPClogan;
    public static NPC NPCmariaum;
    // Fevereiro
    public static NPC NPCmatheus;
    public static NPC NPCmeel;
    public static NPC NPCmordecai;
    // Novembro
    public static NPC NPCraymeki;
    public static NPC NPCroberta;
    public static NPC NPCsaulinh;
    public static NPC NPCskatista;
    // Março
    public static NPC NPCsnif;
    public static NPC NPCsombra;

    public static NPC NPCsomeera;
    public static NPC NPCstela;

    // Maio
    public static NPC NPCtia;

    public static NPC NPCtio;

    public static NPC NPCultra;

    public static boolean start = false;

    public static void loadBots(boolean Continue) {
        if (HistoriaMapMeelv3.autoStarted) {
            return;
        }
        HistoriaMapMeelv3.autoStarted = true;

        HistoriaMapMeelv3.loadConstruction("teatroNada");
        HistoriaMapMeelv3.toggleLightsStage(true, "frente,direita1,direita2,direita3,esquerda1,esquerda2,esquerda3");
        Bukkit.broadcastMessage(Strings.prefix + "§fCarregando Skins dos Bots do Teatro...");

        int number = -13;
        number++;

        if (HistoriaMapMeelv3.NPCintro == null) {
            HistoriaMapMeelv3.NPCintro = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Introo");
        } else {
            HistoriaMapMeelv3.NPCintro.despawn();
        }
        HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCmeel == null) {
            HistoriaMapMeelv3.NPCmeel = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "iMeel");
        } else {
            HistoriaMapMeelv3.NPCmeel.despawn();
        }
        HistoriaMapMeelv3.NPCmeel.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCbiidu == null) {
            HistoriaMapMeelv3.NPCbiidu = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Biidu");
        } else {
            HistoriaMapMeelv3.NPCbiidu.despawn();
        }
        HistoriaMapMeelv3.NPCbiidu.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCbiidu.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCkeroch == null) {
            HistoriaMapMeelv3.NPCkeroch = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "iKeroch");
        } else {
            HistoriaMapMeelv3.NPCkeroch.despawn();
        }
        HistoriaMapMeelv3.NPCkeroch.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCkeroch.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCstela == null) {
            HistoriaMapMeelv3.NPCstela = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Stezinha");
        } else {
            HistoriaMapMeelv3.NPCstela.despawn();
        }
        HistoriaMapMeelv3.NPCstela.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCstela.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCdiego == null) {
            HistoriaMapMeelv3.NPCdiego = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "DiegoSVP");
        } else {
            HistoriaMapMeelv3.NPCdiego.despawn();
        }
        HistoriaMapMeelv3.NPCdiego.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCdiego.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCraymeki == null) {
            HistoriaMapMeelv3.NPCraymeki = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Raymeki");
        } else {
            HistoriaMapMeelv3.NPCraymeki.despawn();
        }
        HistoriaMapMeelv3.NPCraymeki.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCraymeki.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCmariaum == null) {
            HistoriaMapMeelv3.NPCmariaum = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Mariaum");
        } else {
            HistoriaMapMeelv3.NPCmariaum.despawn();
        }
        HistoriaMapMeelv3.NPCmariaum.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCmariaum.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCsombra == null) {
            HistoriaMapMeelv3.NPCsombra = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "SombraXD");
        } else {
            HistoriaMapMeelv3.NPCsombra.despawn();
        }
        HistoriaMapMeelv3.NPCsombra.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCsombra.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCroberta == null) {
            HistoriaMapMeelv3.NPCroberta = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "RobertaCoelho");
        } else {
            HistoriaMapMeelv3.NPCroberta.despawn();
        }
        HistoriaMapMeelv3.NPCroberta.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCroberta.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCbase62 == null) {
            HistoriaMapMeelv3.NPCbase62 = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Base62");
        } else {
            HistoriaMapMeelv3.NPCbase62.despawn();
        }
        HistoriaMapMeelv3.NPCbase62.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCbase62.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCbase64 == null) {
            HistoriaMapMeelv3.NPCbase64 = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Base64_");
        } else {
            HistoriaMapMeelv3.NPCbase64.despawn();
        }
        HistoriaMapMeelv3.NPCbase64.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCbase64.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCbase65 == null) {
            HistoriaMapMeelv3.NPCbase65 = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Base65");
        } else {
            HistoriaMapMeelv3.NPCbase65.despawn();
        }
        HistoriaMapMeelv3.NPCbase65.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCbase65.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCmatheus == null) {
            HistoriaMapMeelv3.NPCmatheus = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Matheus");
        } else {
            HistoriaMapMeelv3.NPCmatheus.despawn();
        }
        HistoriaMapMeelv3.NPCmatheus.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCmatheus.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCsnif == null) {
            HistoriaMapMeelv3.NPCsnif = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "SnifPvP");
        } else {
            HistoriaMapMeelv3.NPCsnif.despawn();
        }
        HistoriaMapMeelv3.NPCsnif.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCsnif.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCsomeera == null) {
            HistoriaMapMeelv3.NPCsomeera = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Someera");
        } else {
            HistoriaMapMeelv3.NPCsomeera.despawn();
        }
        HistoriaMapMeelv3.NPCsomeera.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCsomeera.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCbudokkan == null) {
            HistoriaMapMeelv3.NPCbudokkan = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Budokkan");
        } else {
            HistoriaMapMeelv3.NPCbudokkan.despawn();
        }
        HistoriaMapMeelv3.NPCbudokkan.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCbudokkan.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCdeb == null) {
            HistoriaMapMeelv3.NPCdeb = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Debb_");
        } else {
            HistoriaMapMeelv3.NPCdeb.despawn();
        }
        HistoriaMapMeelv3.NPCdeb.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCdeb.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCultra == null) {
            HistoriaMapMeelv3.NPCultra = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "yUltra_");
        } else {
            HistoriaMapMeelv3.NPCultra.despawn();
        }
        HistoriaMapMeelv3.NPCultra.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCultra.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCskatista == null) {
            HistoriaMapMeelv3.NPCskatista = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Skatista_PvP");
        } else {
            HistoriaMapMeelv3.NPCskatista.despawn();
        }
        HistoriaMapMeelv3.NPCskatista.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCskatista.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCmordecai == null) {
            HistoriaMapMeelv3.NPCmordecai = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "yMordecai");
        } else {
            HistoriaMapMeelv3.NPCmordecai.despawn();
        }
        HistoriaMapMeelv3.NPCmordecai.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCmordecai.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCjorbe == null) {
            HistoriaMapMeelv3.NPCjorbe = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Jorbee1234");
        } else {
            HistoriaMapMeelv3.NPCjorbe.despawn();
        }
        HistoriaMapMeelv3.NPCjorbe.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCjorbe.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCilari == null) {
            HistoriaMapMeelv3.NPCilari = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "iiLari");
        } else {
            HistoriaMapMeelv3.NPCilari.despawn();
        }
        HistoriaMapMeelv3.NPCilari.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCilari.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCtia == null) {
            HistoriaMapMeelv3.NPCtia = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "TiaChavi");
        } else {
            HistoriaMapMeelv3.NPCtia.despawn();
        }
        HistoriaMapMeelv3.NPCtia.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCtia.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCtio == null) {
            HistoriaMapMeelv3.NPCtio = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "LendariaSarrada");
        } else {
            HistoriaMapMeelv3.NPCtio.despawn();
        }
        HistoriaMapMeelv3.NPCtio.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCtio.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPClogan == null) {
            HistoriaMapMeelv3.NPClogan = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Loogan_");
        } else {
            HistoriaMapMeelv3.NPClogan.despawn();
        }
        HistoriaMapMeelv3.NPClogan.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPClogan.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));
        number++;

        if (HistoriaMapMeelv3.NPCsaulinh == null) {
            HistoriaMapMeelv3.NPCsaulinh = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Zaulo");
        } else {
            HistoriaMapMeelv3.NPCsaulinh.despawn();
        }
        HistoriaMapMeelv3.NPCsaulinh.spawn(HistoriaMapMeelv3.meio.clone().add(number, 0, -3));
        HistoriaMapMeelv3.NPCsaulinh.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(number, 0, -2));

        new CustomDelay(() -> {
            HistoriaMapMeelv3.toggleLightsStage(false, "frente,direita1,direita2,direita3,esquerda1,esquerda2,esquerda3");
            HistoriaMapMeelv3.NPCintro.despawn();
            HistoriaMapMeelv3.NPCmeel.despawn();
            HistoriaMapMeelv3.NPCbiidu.despawn();
            HistoriaMapMeelv3.NPCkeroch.despawn();
            HistoriaMapMeelv3.NPCstela.despawn();
            HistoriaMapMeelv3.NPCdiego.despawn();
            HistoriaMapMeelv3.NPCraymeki.despawn();
            HistoriaMapMeelv3.NPCmariaum.despawn();
            HistoriaMapMeelv3.NPCsombra.despawn();
            HistoriaMapMeelv3.NPCroberta.despawn();
            HistoriaMapMeelv3.NPCbase62.despawn();
            HistoriaMapMeelv3.NPCbase64.despawn();
            HistoriaMapMeelv3.NPCbase65.despawn();
            HistoriaMapMeelv3.NPCmatheus.despawn();
            HistoriaMapMeelv3.NPCsnif.despawn();
            HistoriaMapMeelv3.NPCsomeera.despawn();
            HistoriaMapMeelv3.NPCbudokkan.despawn();
            HistoriaMapMeelv3.NPCdeb.despawn();
            HistoriaMapMeelv3.NPCultra.despawn();
            HistoriaMapMeelv3.NPCskatista.despawn();
            HistoriaMapMeelv3.NPCmordecai.despawn();
            HistoriaMapMeelv3.NPCjorbe.despawn();
            HistoriaMapMeelv3.NPCilari.despawn();
            HistoriaMapMeelv3.NPCtia.despawn();
            HistoriaMapMeelv3.NPCtio.despawn();
            HistoriaMapMeelv3.NPClogan.despawn();
            HistoriaMapMeelv3.NPCsaulinh.despawn();
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
            Bukkit.broadcastMessage(Strings.prefix + "§fSkins dos Bots do teatro carregadas com sucesso!");
            if (Continue) {
                HistoriaMapMeelv3.startHistoriaTeste(Continue);
            }
        }, 0, 30, 0);

    }

    public static void loadConstruction(String name) {
        try {
            EditSession es = new EditSession(new BukkitWorld(Bukkit.getWorld("world")), 999999999);
            CuboidClipboard cc = CuboidClipboard.loadSchematic(new File("plugins/WorldEdit/schematics/" + name + ".schematic"));
            com.sk89q.worldedit.Vector origin = new com.sk89q.worldedit.Vector(4999, 36, 4878);
            cc.paste(es, origin, false);
        } catch (DataException | IOException | MaxChangedBlocksException e) {
            e.printStackTrace();
        }
    }

    public static void reloadAllSkins() {
        if (HistoriaMapMeelv3.NPCintro == null) {
            HistoriaMapMeelv3.NPCintro = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Introo");
        } else {
            HistoriaMapMeelv3.NPCintro.despawn();
        }
        if (HistoriaMapMeelv3.NPCmeel == null) {
            HistoriaMapMeelv3.NPCmeel = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "iMeel");
        } else {
            HistoriaMapMeelv3.NPCmeel.despawn();
        }
        if (HistoriaMapMeelv3.NPCbiidu == null) {
            HistoriaMapMeelv3.NPCbiidu = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Biidu");
        } else {
            HistoriaMapMeelv3.NPCbiidu.despawn();
        }
        if (HistoriaMapMeelv3.NPCkeroch == null) {
            HistoriaMapMeelv3.NPCkeroch = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "iKeroch");
        } else {
            HistoriaMapMeelv3.NPCkeroch.despawn();
        }
        if (HistoriaMapMeelv3.NPCstela == null) {
            HistoriaMapMeelv3.NPCstela = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Stezinha");
        } else {
            HistoriaMapMeelv3.NPCstela.despawn();
        }
        if (HistoriaMapMeelv3.NPCdiego == null) {
            HistoriaMapMeelv3.NPCdiego = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "DiegoSVP");
        } else {
            HistoriaMapMeelv3.NPCdiego.despawn();
        }
        if (HistoriaMapMeelv3.NPCraymeki == null) {
            HistoriaMapMeelv3.NPCraymeki = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Raymeki");
        } else {
            HistoriaMapMeelv3.NPCraymeki.despawn();
        }
        if (HistoriaMapMeelv3.NPCmariaum == null) {
            HistoriaMapMeelv3.NPCmariaum = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Mariaum");
        } else {
            HistoriaMapMeelv3.NPCmariaum.despawn();
        }
        if (HistoriaMapMeelv3.NPCsombra == null) {
            HistoriaMapMeelv3.NPCsombra = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "SombraXD");
        } else {
            HistoriaMapMeelv3.NPCsombra.despawn();
        }
        if (HistoriaMapMeelv3.NPCroberta == null) {
            HistoriaMapMeelv3.NPCroberta = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "RobertaCoelho");
        } else {
            HistoriaMapMeelv3.NPCroberta.despawn();
        }
        if (HistoriaMapMeelv3.NPCbase62 == null) {
            HistoriaMapMeelv3.NPCbase62 = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Base62");
        } else {
            HistoriaMapMeelv3.NPCbase62.despawn();
        }
        if (HistoriaMapMeelv3.NPCbase64 == null) {
            HistoriaMapMeelv3.NPCbase64 = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Base64_");
        } else {
            HistoriaMapMeelv3.NPCbase64.despawn();
        }
        if (HistoriaMapMeelv3.NPCbase65 == null) {
            HistoriaMapMeelv3.NPCbase65 = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Base65");
        } else {
            HistoriaMapMeelv3.NPCbase65.despawn();
        }
        if (HistoriaMapMeelv3.NPCmatheus == null) {
            HistoriaMapMeelv3.NPCmatheus = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Matheus");
        } else {
            HistoriaMapMeelv3.NPCmatheus.despawn();
        }
        if (HistoriaMapMeelv3.NPCsnif == null) {
            HistoriaMapMeelv3.NPCsnif = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "SnifPvP");
        } else {
            HistoriaMapMeelv3.NPCsnif.despawn();
        }
        if (HistoriaMapMeelv3.NPCsomeera == null) {
            HistoriaMapMeelv3.NPCsomeera = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Someera");
        } else {
            HistoriaMapMeelv3.NPCsomeera.despawn();
        }
        if (HistoriaMapMeelv3.NPCbudokkan == null) {
            HistoriaMapMeelv3.NPCbudokkan = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Budokkan");
        } else {
            HistoriaMapMeelv3.NPCbudokkan.despawn();
        }
        if (HistoriaMapMeelv3.NPCdeb == null) {
            HistoriaMapMeelv3.NPCdeb = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Debb_");
        } else {
            HistoriaMapMeelv3.NPCdeb.despawn();
        }
        if (HistoriaMapMeelv3.NPCultra == null) {
            HistoriaMapMeelv3.NPCultra = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "yUltra_");
        } else {
            HistoriaMapMeelv3.NPCultra.despawn();
        }
        if (HistoriaMapMeelv3.NPCskatista == null) {
            HistoriaMapMeelv3.NPCskatista = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Skatista_PvP");
        } else {
            HistoriaMapMeelv3.NPCskatista.despawn();
        }
        if (HistoriaMapMeelv3.NPCmordecai == null) {
            HistoriaMapMeelv3.NPCmordecai = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "yMordecai");
        } else {
            HistoriaMapMeelv3.NPCmordecai.despawn();
        }
        if (HistoriaMapMeelv3.NPCjorbe == null) {
            HistoriaMapMeelv3.NPCjorbe = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Jorbee1234");
        } else {
            HistoriaMapMeelv3.NPCjorbe.despawn();
        }
        if (HistoriaMapMeelv3.NPCilari == null) {
            HistoriaMapMeelv3.NPCilari = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "iiLari");
        } else {
            HistoriaMapMeelv3.NPCilari.despawn();
        }
        if (HistoriaMapMeelv3.NPCtia == null) {
            HistoriaMapMeelv3.NPCtia = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "TiaChavi");
        } else {
            HistoriaMapMeelv3.NPCtia.despawn();
        }
        if (HistoriaMapMeelv3.NPCtio == null) {
            HistoriaMapMeelv3.NPCtio = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "LendariaSarrada");
        } else {
            HistoriaMapMeelv3.NPCtio.despawn();
        }
        if (HistoriaMapMeelv3.NPClogan == null) {
            HistoriaMapMeelv3.NPClogan = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Loogan_");
        } else {
            HistoriaMapMeelv3.NPClogan.despawn();
        }
        if (HistoriaMapMeelv3.NPCsaulinh == null) {
            HistoriaMapMeelv3.NPCsaulinh = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Zaulo");
        } else {
            HistoriaMapMeelv3.NPCsaulinh.despawn();
        }
    }

    public static void startHistoria() {
        if (!Utils.isMeelOn()) {
            Bukkit.broadcastMessage(Strings.prefix + "§fIniciando teatro no modo teste!");
        }

        HistoriaMapMeelv3.toggleLightsAudience(false);
        HistoriaMapMeelv3.toggleLightsStage(false, "tudo");
        HistoriaMapMeelv3.loadConstruction("teatroFechado");

        if (HistoriaMapMeelv3.start) {
            return;
        }
        HistoriaMapMeelv3.start = true;

        HistoriaMapMeelv3.reloadAllSkins();

        for (Player p : Bukkit.getOnlinePlayers()) {
            Utils.playSound(p, Audio.HISTORIA);
        }

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroMel");
            HistoriaMapMeelv3.toggleLightsStage(true, "frente");
            HistoriaMapMeelv3.NPCmeel.spawn(HistoriaMapMeelv3.meio.clone().add(-1, 0, 0));
            HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(-1, 0, 0));
        }, 1, 9, 250);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3.meio.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 0));
        }, 1, 11, 260);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFNegra");
            HistoriaMapMeelv3.NPCmeel.despawn();
        }, 1, 22, 70);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCraymeki.spawn(HistoriaMapMeelv3.meio.clone().add(-1, 0, 0));
            HistoriaMapMeelv3.NPCraymeki.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(-1, 0, 0));
        }, 1, 32, 140);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCraymeki.getStoredLocation());
            HistoriaMapMeelv3.NPCraymeki.faceLocation(HistoriaMapMeelv3.NPCintro.getStoredLocation());
        }, 1, 41, 30);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.despawn();
            HistoriaMapMeelv3.NPCraymeki.despawn();
        }, 1, 49, 180);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCraymeki.spawn(HistoriaMapMeelv3.meio.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCraymeki.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 0));
        }, 1, 56, 10);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCmeel.spawn(HistoriaMapMeelv3.meio.clone().add(-1, 0, 0));
            HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(-1, 0, 0));
        }, 1, 57, 40);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCmeel.faceLocation(HistoriaMapMeelv3.NPCraymeki.getStoredLocation());
            HistoriaMapMeelv3.NPCraymeki.faceLocation(HistoriaMapMeelv3.NPCmeel.getStoredLocation());
        }, 1, 59, 0);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCmeel.despawn();
            HistoriaMapMeelv3.NPCraymeki.despawn();
            HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3.meio);
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente);
        }, 2, 0, 0);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.meio_frente.clone().add(0, -2, 2));
        }, 2, 3, 170);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.meio_frente.clone().add(0, 0, 2)), 2, 10, 280);

        new CustomDelay(() -> HistoriaMapMeelv3.loadConstruction("teatroBranco"), 2, 39, 50);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.despawn();
            HistoriaMapMeelv3.NPCbase62.spawn(HistoriaMapMeelv3.meio.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCbase62.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCbiidu.spawn(HistoriaMapMeelv3.meio.clone().add(-1, 0, 0));
            HistoriaMapMeelv3.NPCbiidu.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(-1, 0, 0));
        }, 2, 48, 140);

        new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "tudo"), 2, 51, 10);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
            HistoriaMapMeelv3.toggleLightsStage(false, "tudo");
            HistoriaMapMeelv3.NPCbase62.despawn();
            HistoriaMapMeelv3.NPCbiidu.despawn();
        }, 3, 12, 40);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroPraia");
            HistoriaMapMeelv3.toggleLightsStage(true, "tudo");
        }, 3, 23, 170);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3.meio_atras);
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente);
        }, 3, 29, 170);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.despawn();
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
        }, 3, 54, 170);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCmatheus.spawn(HistoriaMapMeelv3.meio.clone().add(4, 1, 0));
            HistoriaMapMeelv3.NPCmeel.spawn(HistoriaMapMeelv3.meio.clone().add(-4, 1, 0));
            HistoriaMapMeelv3.loadConstruction("teatroHG");
        }, 3, 56, 240);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.spawn(new Location(Bukkit.getWorld("world"), 5007.5, 39.0, 4865.5));
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCmatheus.getStoredLocation());
        }, 4, 0, 270);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.NPCmatheus.getStoredLocation().clone().add(-2, 0, 0)), 4, 20, 240);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente), 4, 33, 190);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.meio_frente.clone().add(0, 0, 2));
            HistoriaMapMeelv3.NPCmeel.despawn();
            HistoriaMapMeelv3.NPCmatheus.despawn();
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
        }, 4, 39, 100);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroHG");
            HistoriaMapMeelv3.NPCmeel.spawn(HistoriaMapMeelv3.meio.clone().add(2, 0, 0));
            HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(2, 0, 0));
        }, 4, 46, 230);

        new CustomDelay(() -> HistoriaMapMeelv3.loadConstruction("teatroFechado"), 4, 50, 0);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroHG");
            HistoriaMapMeelv3.NPCsnif.spawn(HistoriaMapMeelv3.meio.clone().add(-2, 0, 0));
            HistoriaMapMeelv3.NPCsnif.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(-2, 0, 0));
        }, 5, 3, 140);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCsomeera.spawn(HistoriaMapMeelv3.meio.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCsomeera.faceLocation(HistoriaMapMeelv3.NPCsnif.getStoredLocation());
            HistoriaMapMeelv3.NPCbudokkan.spawn(HistoriaMapMeelv3.meio.clone().add(-1, 0, 0));
            HistoriaMapMeelv3.NPCbudokkan.faceLocation(HistoriaMapMeelv3.NPCsnif.getStoredLocation());
        }, 5, 10, 10);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCsomeera.despawn();
            HistoriaMapMeelv3.NPCbudokkan.despawn();
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
        }, 5, 13, 220);

        new CustomDelay(() -> HistoriaMapMeelv3.loadConstruction("teatroHG"), 5, 25, 230);

        new CustomDelay(() -> HistoriaMapMeelv3.loadConstruction("teatroFechado"), 5, 27, 250);

        new CustomDelay(() -> HistoriaMapMeelv3.loadConstruction("teatroFNegra"), 5, 28, 270);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
            HistoriaMapMeelv3.NPCintro.despawn();
            HistoriaMapMeelv3.NPCmeel.despawn();
            HistoriaMapMeelv3.NPCsnif.despawn();
        }, 5, 40, 190);

        new CustomDelay(() -> HistoriaMapMeelv3.loadConstruction("teatroHG"), 5, 52, 0);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.location = -4;
            HistoriaMapMeelv3.location++;
            HistoriaMapMeelv3.NPCjorbe.spawn(HistoriaMapMeelv3.meio.clone().add(HistoriaMapMeelv3.location, 0, 0));
            HistoriaMapMeelv3.NPCjorbe.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(HistoriaMapMeelv3.location, 0, 0));
        }, 5, 54, 170);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.location++;
            HistoriaMapMeelv3.NPCdeb.spawn(HistoriaMapMeelv3.meio.clone().add(HistoriaMapMeelv3.location, 0, 0));
            HistoriaMapMeelv3.NPCdeb.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(HistoriaMapMeelv3.location, 0, 0));
        }, 5, 56, 40);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.location++;
            HistoriaMapMeelv3.NPCultra.spawn(HistoriaMapMeelv3.meio.clone().add(HistoriaMapMeelv3.location, 0, 0));
            HistoriaMapMeelv3.NPCultra.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(HistoriaMapMeelv3.location, 0, 0));
        }, 5, 57, 180);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.location++;
            HistoriaMapMeelv3.NPCmordecai.spawn(HistoriaMapMeelv3.meio.clone().add(HistoriaMapMeelv3.location, 0, 0));
            HistoriaMapMeelv3.NPCmordecai.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(HistoriaMapMeelv3.location, 0, 0));
        }, 5, 58, 260);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.location++;
            HistoriaMapMeelv3.NPCtia.spawn(HistoriaMapMeelv3.meio.clone().add(HistoriaMapMeelv3.location, 0, 0));
            HistoriaMapMeelv3.NPCtia.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(HistoriaMapMeelv3.location, 0, 0));
        }, 6, 0, 190);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.location++;
            HistoriaMapMeelv3.NPCtio.spawn(HistoriaMapMeelv3.meio.clone().add(HistoriaMapMeelv3.location, 0, 0));
            HistoriaMapMeelv3.NPCtio.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(HistoriaMapMeelv3.location, 0, 0));
        }, 6, 2, 20);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.location++;
            HistoriaMapMeelv3.NPCilari.spawn(HistoriaMapMeelv3.meio.clone().add(HistoriaMapMeelv3.location, 0, 0));
            HistoriaMapMeelv3.NPCilari.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(HistoriaMapMeelv3.location, 0, 0));
        }, 6, 3, 140);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCjorbe.despawn();
            HistoriaMapMeelv3.NPCdeb.despawn();
            HistoriaMapMeelv3.NPCultra.despawn();
            HistoriaMapMeelv3.NPCmordecai.despawn();
            HistoriaMapMeelv3.NPCtia.despawn();
            HistoriaMapMeelv3.NPCtio.despawn();
            HistoriaMapMeelv3.NPCilari.despawn();
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
        }, 6, 5, 220);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroNada");
            HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3.meio);
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente);
        }, 6, 7, 220);

        new CustomDelay(() -> HistoriaMapMeelv3.loadConstruction("teatroFechado"), 6, 12, 0);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroNada");
            HistoriaMapMeelv3.NPCsaulinh.spawn(HistoriaMapMeelv3.meio.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCsaulinh.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 0));
        }, 6, 56, 230);

        new CustomDelay(() -> HistoriaMapMeelv3.loadConstruction("teatroFechado"), 7, 0, 0);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFNegra");
            HistoriaMapMeelv3.NPCtia.spawn(HistoriaMapMeelv3.meio.clone().add(-2, 0, 0));
            HistoriaMapMeelv3.NPCtia.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(-2, 0, 0));
        }, 7, 16, 290);

        new CustomDelay(() -> HistoriaMapMeelv3.loadConstruction("teatroFechado"), 7, 20, 0);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFNegra");
            HistoriaMapMeelv3.NPCmeel.spawn(HistoriaMapMeelv3.meio);
        }, 7, 30, 120);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
            HistoriaMapMeelv3.NPCmeel.despawn();
        }, 7, 36, 180);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFNegra");
            HistoriaMapMeelv3.NPCsnif.spawn(HistoriaMapMeelv3.meio.clone().add(5, 0, 0));
        }, 7, 41, 50);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
            HistoriaMapMeelv3.NPCsnif.despawn();
        }, 7, 45, 130);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCtia.getStoredLocation()), 7, 49, 90);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCtia.despawn();
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.meio_frente.clone().add(0, 0, 2));
        }, 8, 6, 180);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCsaulinh.despawn(), 8, 9, 40);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroHG");
            HistoriaMapMeelv3.NPCmeel.spawn(HistoriaMapMeelv3.meio.clone().add(-3, 0, 2));
        }, 8, 17, 160);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCjorbe.spawn(HistoriaMapMeelv3.meio.clone().add(-2, 0, 2)), 8, 21, 70);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCdeb.spawn(HistoriaMapMeelv3.meio.clone().add(-1, 0, 2)), 8, 22, 30);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCultra.spawn(HistoriaMapMeelv3.meio.clone().add(0, 0, 2)), 8, 22, 240);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
            HistoriaMapMeelv3.NPCmeel.despawn();
            HistoriaMapMeelv3.NPCjorbe.despawn();
            HistoriaMapMeelv3.NPCdeb.despawn();
            HistoriaMapMeelv3.NPCultra.despawn();
        }, 8, 23, 250);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.location = 0;
            HistoriaMapMeelv3.loadConstruction("teatroNada");
            HistoriaMapMeelv3.location++;
            HistoriaMapMeelv3.NPCbiidu.spawn(HistoriaMapMeelv3.meio.clone().add(HistoriaMapMeelv3.location, 0, 0));
            HistoriaMapMeelv3.NPCbiidu.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(HistoriaMapMeelv3.location, 0, 0));
        }, 8, 29, 150);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.location++;
            HistoriaMapMeelv3.NPCkeroch.spawn(HistoriaMapMeelv3.meio.clone().add(HistoriaMapMeelv3.location, 0, 0));
            HistoriaMapMeelv3.NPCkeroch.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(HistoriaMapMeelv3.location, 0, 0));
        }, 8, 30, 70);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.location++;
            HistoriaMapMeelv3.NPCmariaum.spawn(HistoriaMapMeelv3.meio.clone().add(HistoriaMapMeelv3.location, 0, 0));
            HistoriaMapMeelv3.NPCmariaum.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(HistoriaMapMeelv3.location, 0, 0));
        }, 8, 31, 0);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.location++;
            HistoriaMapMeelv3.NPCdiego.spawn(HistoriaMapMeelv3.meio.clone().add(HistoriaMapMeelv3.location, 0, 0));
            HistoriaMapMeelv3.NPCdiego.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(HistoriaMapMeelv3.location, 0, 0));
        }, 8, 31, 280);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.location++;
            HistoriaMapMeelv3.NPClogan.spawn(HistoriaMapMeelv3.meio.clone().add(HistoriaMapMeelv3.location, 0, 0));
            HistoriaMapMeelv3.NPClogan.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(HistoriaMapMeelv3.location, 0, 0));
        }, 8, 32, 280);

        new CustomDelay(() -> HistoriaMapMeelv3.loadConstruction("teatroFechado"), 8, 37, 30);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCbiidu.despawn();
            HistoriaMapMeelv3.NPCkeroch.despawn();
            HistoriaMapMeelv3.NPCdiego.despawn();
            HistoriaMapMeelv3.NPCmariaum.despawn();
            HistoriaMapMeelv3.NPClogan.despawn();
        }, 8, 53, 200);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFNegra");
            HistoriaMapMeelv3.NPCmariaum.spawn(HistoriaMapMeelv3.meio.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCmariaum.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCsombra.spawn(HistoriaMapMeelv3.meio.clone().add(2, 0, 0));
            HistoriaMapMeelv3.NPCsombra.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(2, 0, 0));
            HistoriaMapMeelv3.NPCsaulinh.spawn(HistoriaMapMeelv3.meio.clone().add(3, 0, 0));
            HistoriaMapMeelv3.NPCsaulinh.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(3, 0, 0));
            HistoriaMapMeelv3.NPCkeroch.spawn(HistoriaMapMeelv3.meio.clone().add(4, 0, 0));
            HistoriaMapMeelv3.NPCkeroch.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(4, 0, 0));
        }, 9, 12, 280);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
            HistoriaMapMeelv3.NPCmariaum.despawn();
            HistoriaMapMeelv3.NPCsombra.despawn();
            HistoriaMapMeelv3.NPCsaulinh.despawn();
            HistoriaMapMeelv3.NPCkeroch.despawn();
        }, 9, 21, 170);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPClogan.spawn(HistoriaMapMeelv3.meio_frente.clone().add(2, 0, 0));
            HistoriaMapMeelv3.NPClogan.faceLocation(HistoriaMapMeelv3.NPCintro.getStoredLocation());
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPClogan.getStoredLocation());
        }, 9, 25, 0);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPClogan.despawn();
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.meio_frente.clone().add(0, 0, 2));
        }, 9, 29, 30);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFNegra");
            HistoriaMapMeelv3.NPCmeel.spawn(HistoriaMapMeelv3.meio.clone().add(3, 0, 0));
            HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(3, 0, 0));
        }, 9, 30, 150);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCmeel.getStoredLocation());
            HistoriaMapMeelv3.NPCmeel.faceLocation(HistoriaMapMeelv3.NPCintro.getStoredLocation());
        }, 9, 33, 20);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
            HistoriaMapMeelv3.NPCmeel.despawn();
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.meio_frente.clone().add(0, 0, 2));
        }, 9, 46, 130);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCbiidu.spawn(HistoriaMapMeelv3.meio_frente.clone().add(-2, 0, 0)), 9, 50, 170);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCbiidu.despawn();
            HistoriaMapMeelv3.NPCkeroch.spawn(HistoriaMapMeelv3.meio_frente.clone().add(-2, 0, 0));
        }, 9, 55, 120);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCkeroch.despawn();
            HistoriaMapMeelv3.NPCstela.spawn(HistoriaMapMeelv3.meio_frente.clone().add(-2, 0, 0));
        }, 9, 57, 29);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCstela.despawn(), 10, 3, 190);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCdiego.spawn(HistoriaMapMeelv3.meio_frente.clone().add(-1, 0, 0)), 10, 13, 150);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCmariaum.spawn(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 0)), 10, 14, 180);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCsombra.spawn(HistoriaMapMeelv3.meio_frente.clone().add(2, 0, 0)), 10, 16, 50);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCdiego.faceLocation(HistoriaMapMeelv3.NPCintro.getStoredLocation());
            HistoriaMapMeelv3.NPCmariaum.faceLocation(HistoriaMapMeelv3.NPCintro.getStoredLocation());
            HistoriaMapMeelv3.NPCsombra.faceLocation(HistoriaMapMeelv3.NPCintro.getStoredLocation());
        }, 10, 17, 200);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCroberta.spawn(HistoriaMapMeelv3.meio_frente.clone().add(-2, 0, 0));
            HistoriaMapMeelv3.NPCroberta.faceLocation(HistoriaMapMeelv3.NPCintro.getStoredLocation());
        }, 10, 29, 50);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCroberta.despawn();
            HistoriaMapMeelv3.NPCdiego.despawn();
            HistoriaMapMeelv3.NPCmariaum.despawn();
            HistoriaMapMeelv3.NPCsombra.despawn();
        }, 11, 10, 260);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFNegra");
            HistoriaMapMeelv3.NPCbiidu.spawn(HistoriaMapMeelv3.meio_atras.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCbiidu.getNavigator().setTarget(HistoriaMapMeelv3.meio.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio);
        }, 11, 41, 0);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCbiidu.getStoredLocation());
            HistoriaMapMeelv3.NPCbiidu.faceLocation(HistoriaMapMeelv3.NPCintro.getStoredLocation());
        }, 11, 45, 230);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.meio_frente);
            HistoriaMapMeelv3.NPCbiidu.faceLocation(HistoriaMapMeelv3.meio_frente);
        }, 11, 57, 30);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCstela.spawn(HistoriaMapMeelv3.meio_atras.clone().add(-1, 0, 0));
            HistoriaMapMeelv3.NPCstela.getNavigator().setTarget(HistoriaMapMeelv3.meio.clone().add(-1, 0, 0));
            HistoriaMapMeelv3.NPCkeroch.spawn(HistoriaMapMeelv3.meio_atras.clone().add(-2, 0, 0));
            HistoriaMapMeelv3.NPCkeroch.getNavigator().setTarget(HistoriaMapMeelv3.meio.clone().add(-2, 0, 0));
        }, 12, 7, 90);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCmeel.spawn(HistoriaMapMeelv3.meio.clone().add(0, 0, 2));
            HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente);
        }, 12, 12, 10);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCbiidu.despawn();
            HistoriaMapMeelv3.NPCstela.despawn();
            HistoriaMapMeelv3.NPCkeroch.despawn();
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(2, 0, 0));
        }, 12, 22, 230);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCmeel.getStoredLocation());
            HistoriaMapMeelv3.NPCmeel.faceLocation(HistoriaMapMeelv3.NPCintro.getStoredLocation());
        }, 12, 26, 180);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 0)), 12, 41, 130);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCdeb.spawn(HistoriaMapMeelv3.meio.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCdeb.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCultra.spawn(HistoriaMapMeelv3.meio.clone().add(3, 0, 0));
            HistoriaMapMeelv3.NPCultra.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(3, 0, 0));
        }, 12, 43, 30);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCultra.getStoredLocation()), 12, 50, 90);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCdeb.getStoredLocation()), 12, 52, 20);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.meio_frente.clone().add(0, 0, 3)), 12, 56, 290);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.despawn();
            HistoriaMapMeelv3.NPCmeel.despawn();
            HistoriaMapMeelv3.NPCdeb.despawn();
            HistoriaMapMeelv3.NPCultra.despawn();
        }, 13, 6, 200);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCsnif.spawn(HistoriaMapMeelv3.meio);
            HistoriaMapMeelv3.NPCsnif.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente);
        }, 13, 11, 210);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCsnif.despawn(), 13, 22, 20);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3.meio);
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente);
        }, 13, 29, 10);

        new CustomDelay(() -> HistoriaMapMeelv3.loadConstruction("teatroKindome"), 13, 38, 290);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCmeel.spawn(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, -3));
            HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 0));
        }, 13, 59, 240);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCmeel.faceLocation(HistoriaMapMeelv3.NPCintro.getStoredLocation());
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCmeel.getStoredLocation());
        }, 14, 5, 90);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCmeel.faceLocation(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 2));
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.meio_frente.clone().add(0, 0, 2));
        }, 14, 11, 280);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
            HistoriaMapMeelv3.NPCmeel.despawn();
            HistoriaMapMeelv3.NPCdeb.spawn(HistoriaMapMeelv3.meio_frente.clone().add(-2, 0, -3));
            HistoriaMapMeelv3.NPCdeb.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(-2, 0, 0));
        }, 14, 30, 170);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCdeb.faceLocation(HistoriaMapMeelv3.NPCintro.getStoredLocation());
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCdeb.getStoredLocation());
        }, 14, 35, 270);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCdeb.despawn();
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.meio_frente.clone().add(0, 0, 3));
        }, 14, 47, 120);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.despawn(), 18, 50, 170);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroHG");
            HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3.meio.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCmeel.spawn(HistoriaMapMeelv3.meio.clone().add(-1, 0, 0));
            HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(-1, 0, 0));
        }, 19, 12, 120);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroKindome");
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.meio.clone().add(0, 5, 0));
        }, 19, 20, 130);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.meio_frente.clone().add(0, 0, 1)), 19, 21, 250);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
            HistoriaMapMeelv3.NPCintro.despawn();
            HistoriaMapMeelv3.NPCultra.spawn(HistoriaMapMeelv3.meio_frente.clone().add(0, 0, 0));
            HistoriaMapMeelv3.NPCdeb.spawn(HistoriaMapMeelv3.meio_frente.clone().add(-2, 0, 0));
        }, 19, 29, 80);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroHG");
            HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3.meio.clone().add(3, 1, 0));
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCmeel.getStoredLocation());
        }, 19, 32, 10);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCdeb.despawn();
            HistoriaMapMeelv3.NPCultra.despawn();
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 0));
        }, 19, 38, 290);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCmeel.despawn();
            HistoriaMapMeelv3.loadConstruction("teatroKindome");
        }, 19, 51, 210);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.despawn();
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
        }, 21, 0, 0);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroHG");
            HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3.meio.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 0));
        }, 21, 17, 140);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCmeel.spawn(HistoriaMapMeelv3.meio.clone().add(2, 0, 0));
            HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(2, 0, 0));
            HistoriaMapMeelv3.NPCkeroch.spawn(HistoriaMapMeelv3.meio.clone().add(0, 0, 0));
            HistoriaMapMeelv3.NPCkeroch.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(0, 0, 0));
        }, 21, 41, 180);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCkeroch.despawn();
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCmeel.getStoredLocation());
            HistoriaMapMeelv3.NPCmeel.faceLocation(HistoriaMapMeelv3.NPCintro.getStoredLocation());
        }, 21, 45, 180);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCmeel.faceLocation(HistoriaMapMeelv3.meio_frente.clone().add(2, 0, 2));
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 2));
        }, 21, 52, 20);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroHG");
            HistoriaMapMeelv3.NPCsombra.spawn(HistoriaMapMeelv3.meio.clone().add(-5, 0, 0));
            HistoriaMapMeelv3.NPCsombra.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(-5, 0, 0));
            HistoriaMapMeelv3.NPCmariaum.spawn(HistoriaMapMeelv3.meio.clone().add(-4, 0, 0));
            HistoriaMapMeelv3.NPCmariaum.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(-4, 0, 0));
            HistoriaMapMeelv3.NPCstela.spawn(HistoriaMapMeelv3.meio.clone().add(-3, 0, 0));
            HistoriaMapMeelv3.NPCstela.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(-3, 0, 0));
            HistoriaMapMeelv3.NPCbiidu.spawn(HistoriaMapMeelv3.meio.clone().add(-2, 0, 0));
            HistoriaMapMeelv3.NPCbiidu.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(-2, 0, 0));
            HistoriaMapMeelv3.NPCkeroch.spawn(HistoriaMapMeelv3.meio.clone().add(-1, 0, 0));
            HistoriaMapMeelv3.NPCkeroch.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(-1, 0, 0));
            HistoriaMapMeelv3.NPCdiego.spawn(HistoriaMapMeelv3.meio.clone().add(0, 0, 0));
            HistoriaMapMeelv3.NPCdiego.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(0, 0, 0));
            HistoriaMapMeelv3.NPCdeb.spawn(HistoriaMapMeelv3.meio.clone().add(3, 0, 0));
            HistoriaMapMeelv3.NPCdeb.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(3, 0, 0));
            HistoriaMapMeelv3.NPCultra.spawn(HistoriaMapMeelv3.meio.clone().add(4, 0, 0));
            HistoriaMapMeelv3.NPCultra.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(4, 0, 0));
            HistoriaMapMeelv3.NPClogan.spawn(HistoriaMapMeelv3.meio.clone().add(5, 0, 0));
            HistoriaMapMeelv3.NPClogan.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(5, 0, 0));
        }, 22, 0, 0);

        new CustomDelay(() -> HistoriaMapMeelv3.loadConstruction("teatroKindome"), 22, 7, 140);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroNada");
            HistoriaMapMeelv3.NPCintro.despawn();
            HistoriaMapMeelv3.NPCmeel.despawn();
            HistoriaMapMeelv3.NPCsombra.despawn();
            HistoriaMapMeelv3.NPCmariaum.despawn();
            HistoriaMapMeelv3.NPCstela.despawn();
            HistoriaMapMeelv3.NPCdiego.despawn();
            HistoriaMapMeelv3.NPCkeroch.despawn();
            HistoriaMapMeelv3.NPCbiidu.despawn();
            HistoriaMapMeelv3.NPClogan.despawn();
            HistoriaMapMeelv3.NPCultra.despawn();
            HistoriaMapMeelv3.NPCdeb.despawn();
            HistoriaMapMeelv3.NPCbase62.spawn(HistoriaMapMeelv3.meio.clone().add(-1, 0, 0));
            HistoriaMapMeelv3.NPCbase62.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(-1, 0, 0));
            HistoriaMapMeelv3.NPCbase64.spawn(HistoriaMapMeelv3.meio.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCbase64.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCbase65.spawn(HistoriaMapMeelv3.meio.clone().add(2, 0, 0));
            HistoriaMapMeelv3.NPCbase65.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(2, 0, 0));
        }, 22, 28, 100);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
            HistoriaMapMeelv3.NPCbase64.faceLocation(HistoriaMapMeelv3.NPCbase62.getStoredLocation());
            HistoriaMapMeelv3.NPCbase65.faceLocation(HistoriaMapMeelv3.NPCbase62.getStoredLocation());
            HistoriaMapMeelv3.NPCbase62.faceLocation(HistoriaMapMeelv3.NPCbase64.getStoredLocation());
        }, 22, 34, 20);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCbase62.faceLocation(HistoriaMapMeelv3.NPCbase65.getStoredLocation()), 22, 35, 20);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCbase62.faceLocation(HistoriaMapMeelv3.NPCbase62.getStoredLocation()), 22, 37, 30);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCbase62.faceLocation(HistoriaMapMeelv3.meio_frente.clone().add(-1, 0, 2));
            HistoriaMapMeelv3.NPCbase64.faceLocation(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 2));
            HistoriaMapMeelv3.NPCbase65.faceLocation(HistoriaMapMeelv3.meio_frente.clone().add(2, 0, 2));
        }, 22, 41, 190);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroNada");
            HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3.meio);
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente);
        }, 23, 18, 0);

        new CustomDelay(() -> HistoriaMapMeelv3.loadConstruction("teatroFechado"), 23, 25, 0);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCbase62.getStoredLocation()), 23, 34, 60);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCbase64.getStoredLocation()), 23, 36, 60);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.meio_frente.clone().add(0, 0, 2)), 23, 38, 0);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroNada");
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_atras.clone().add(0, 0, 0));
            HistoriaMapMeelv3.NPCbase62.getNavigator().setTarget(HistoriaMapMeelv3.meio_atras.clone().add(-1, 0, 0));
            HistoriaMapMeelv3.NPCbase64.getNavigator().setTarget(HistoriaMapMeelv3.meio_atras.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCbase65.getNavigator().setTarget(HistoriaMapMeelv3.meio_atras.clone().add(2, 0, 0));
        }, 24, 0, 0);

        new CustomDelay(() -> HistoriaMapMeelv3.loadConstruction("teatroFechado"), 24, 4, 0);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.despawn();
            HistoriaMapMeelv3.NPCbase62.despawn();
            HistoriaMapMeelv3.NPCbase64.despawn();
            HistoriaMapMeelv3.NPCbase65.despawn();
            HistoriaMapMeelv3.toggleLightsStage(true, "tudo");
            HistoriaMapMeelv3.toggleLightsAudience(true);
        }, 24, 5, 0);

        new CustomDelay(() -> HistoriaMapMeelv3.start = false, 26, 0, 0);

    }

    public static void startHistoriaTeste(boolean Continue) {
        HistoriaMapMeelv3.toggleLightsAudience(false);
        HistoriaMapMeelv3.toggleLightsStage(false, "tudo");
        HistoriaMapMeelv3.loadConstruction("teatroIntro");

        if (HistoriaMapMeelv3.start) {
            return;
        }
        HistoriaMapMeelv3.start = true;

        if (!Utils.isMeelOn()) {
            Bukkit.broadcastMessage(Strings.prefix + "§fIniciando teatro no modo teste!");
        }

        HistoriaMapMeelv3.reloadAllSkins();

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    Utils.playSound(player, Audio.TESTE);
                }

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "frente,direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 3, 100);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "frente,direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 4, 100);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "frente,direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 9, 280);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "frente,direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 10, 280);

                new CustomDelay(() -> {
                    HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3.meio);
                    HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente);
                }, 0, 12, 0);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "frente"), 0, 15, 0);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "meio1"), 0, 21, 10);

                new CustomDelay(() -> {
                    HistoriaMapMeelv3.toggleLightsStage(false, "meio1");
                    HistoriaMapMeelv3.toggleLightsStage(true, "meio2");
                }, 0, 21, 210);

                new CustomDelay(() -> {
                    HistoriaMapMeelv3.toggleLightsStage(false, "meio2");
                    HistoriaMapMeelv3.toggleLightsStage(true, "meio3");
                }, 0, 22, 90);

                new CustomDelay(() -> {
                    HistoriaMapMeelv3.toggleLightsStage(false, "meio3");
                    HistoriaMapMeelv3.toggleLightsStage(true, "atrasbaixo");
                }, 0, 23, 30);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "atrasbaixo"), 0, 24, 10);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "frente,direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 30, 170);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "frente,direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 31, 120);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "frente,direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 31, 210);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "frente,direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 32, 210);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "frente,direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 32, 260);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "frente,direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 33, 260);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "frente,direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 41, 190);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "frente,direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 42, 210);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "meio1"), 0, 44, 90);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "meio2"), 0, 44, 220);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "meio3"), 0, 45, 50);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "atrasbaixo"), 0, 45, 180);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "tudo"), 0, 46, 10);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "meio1,meio2,meio3,atrasbaixo,atrascima,cortina,lateraldireita,lateralesquerda,direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 46, 140);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 50, 160);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 50, 250);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 51, 0);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 51, 100);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 51, 200);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 0, 52, 0);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "tudo"), 1, 0, 0);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "tudo"), 1, 1, 0);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "tudo"), 1, 2, 20);

                new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "meio1,meio2,meio3,atrasbaixo,atrascima,cortina,lateraldireita,lateralesquerda,direita1,direita2,direita3,esquerda1,esquerda2,esquerda3"), 1, 3, 0);

                new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_atras), 1, 15, 0);

                new CustomDelay(() -> {
                    HistoriaMapMeelv3.NPCintro.despawn();
                    HistoriaMapMeelv3.toggleLightsStage(false, "tudo");
                    HistoriaMapMeelv3.loadConstruction("teatroFechado");
                    HistoriaMapMeelv3.start = false;
                    if (Continue) {
                        new CustomDelay(HistoriaMapMeelv3::startHistoria, 0, 3, 0);
                    }
                }, 1, 17, 20);

            }
        }.runTaskLaterAsynchronously(MapMeelMain.getPlugin(), 20);
    }

    public static void toggleLightsAudience(boolean aberto) {
        Material before = aberto ? Material.WOOD : Material.SEA_LANTERN;
        Material after = aberto ? Material.SEA_LANTERN : Material.WOOD;
        for (int x = 4975; x <= 5028; x++) {
            for (int y = 36; y <= 55; y++) {
                for (int z = 4893; z <= 4920; z++) {
                    Block block = Bukkit.getWorld("world").getBlockAt(x, y, z);
                    if (block.getType().equals(before)) {
                        block.setType(after);
                    }
                }
            }
        }
    }

    public static void toggleLightsStage(boolean aberto, String Type) {
        new BukkitRunnable() {
            String type = Type;

            @Override
            public void run() {
                this.type = this.type.toLowerCase();
                if (this.type.contains("frente") || this.type.contains("tudo")) {
                    if (aberto) {
                        Bukkit.getWorld("world").getBlockAt(5003, 35, 4885).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(5000, 35, 4885).setType(Material.SEA_LANTERN);
                    } else {
                        Bukkit.getWorld("world").getBlockAt(5003, 35, 4885).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(5000, 35, 4885).setType(Material.QUARTZ_BLOCK);
                    }
                    Bukkit.getWorld("world").getBlockAt(5003, 36, 4885).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(5000, 36, 4885).setType(Material.AIR);
                }
                if (this.type.contains("direita1") || this.type.contains("tudo")) {
                    if (aberto) {
                        Bukkit.getWorld("world").getBlockAt(4998, 35, 4884).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(4996, 35, 4884).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(4994, 35, 4884).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(4992, 35, 4884).setType(Material.SEA_LANTERN);
                    } else {
                        Bukkit.getWorld("world").getBlockAt(4998, 35, 4884).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(4996, 35, 4884).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(4994, 35, 4884).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(4992, 35, 4884).setType(Material.QUARTZ_BLOCK);
                    }
                    Bukkit.getWorld("world").getBlockAt(4998, 36, 4884).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(4996, 36, 4884).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(4994, 36, 4884).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(4992, 36, 4884).setType(Material.AIR);
                }
                if (this.type.contains("direita2") || this.type.contains("tudo")) {
                    if (aberto) {
                        Bukkit.getWorld("world").getBlockAt(4990, 35, 4883).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(4988, 35, 4883).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(4986, 35, 4883).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(4984, 35, 4883).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(4982, 35, 4883).setType(Material.SEA_LANTERN);
                    } else {
                        Bukkit.getWorld("world").getBlockAt(4990, 35, 4883).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(4988, 35, 4883).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(4986, 35, 4883).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(4984, 35, 4883).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(4982, 35, 4883).setType(Material.QUARTZ_BLOCK);
                    }
                    Bukkit.getWorld("world").getBlockAt(4990, 36, 4883).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(4988, 36, 4883).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(4986, 36, 4883).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(4984, 36, 4883).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(4982, 36, 4883).setType(Material.AIR);
                }
                if (this.type.contains("direita3") || this.type.contains("tudo")) {
                    if (aberto) {
                        Bukkit.getWorld("world").getBlockAt(4976, 35, 4882).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(4978, 35, 4882).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(4980, 35, 4882).setType(Material.SEA_LANTERN);
                    } else {
                        Bukkit.getWorld("world").getBlockAt(4976, 35, 4882).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(4978, 35, 4882).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(4980, 35, 4882).setType(Material.QUARTZ_BLOCK);
                    }
                    Bukkit.getWorld("world").getBlockAt(4976, 36, 4882).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(4978, 36, 4882).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(4980, 36, 4882).setType(Material.AIR);
                }
                if (this.type.contains("esquerda1") || this.type.contains("tudo")) {
                    if (aberto) {
                        Bukkit.getWorld("world").getBlockAt(5005, 35, 4884).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(5007, 35, 4884).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(5009, 35, 4884).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(5011, 35, 4884).setType(Material.SEA_LANTERN);
                    } else {
                        Bukkit.getWorld("world").getBlockAt(5005, 35, 4884).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(5007, 35, 4884).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(5009, 35, 4884).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(5011, 35, 4884).setType(Material.QUARTZ_BLOCK);
                    }
                    Bukkit.getWorld("world").getBlockAt(5005, 36, 4884).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(5007, 36, 4884).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(5009, 36, 4884).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(5011, 36, 4884).setType(Material.AIR);
                }
                if (this.type.contains("esquerda2") || this.type.contains("tudo")) {
                    if (aberto) {
                        Bukkit.getWorld("world").getBlockAt(5013, 35, 4883).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(5015, 35, 4883).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(5017, 35, 4883).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(5019, 35, 4883).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(5021, 35, 4883).setType(Material.SEA_LANTERN);
                    } else {
                        Bukkit.getWorld("world").getBlockAt(5013, 35, 4883).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(5015, 35, 4883).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(5017, 35, 4883).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(5019, 35, 4883).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(5021, 35, 4883).setType(Material.QUARTZ_BLOCK);
                    }
                    Bukkit.getWorld("world").getBlockAt(5013, 36, 4883).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(5015, 36, 4883).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(5017, 36, 4883).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(5019, 36, 4883).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(5021, 36, 4883).setType(Material.AIR);
                }
                if (this.type.contains("esquerda3") || this.type.contains("tudo")) {
                    if (aberto) {
                        Bukkit.getWorld("world").getBlockAt(5023, 35, 4882).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(5025, 35, 4882).setType(Material.SEA_LANTERN);
                        Bukkit.getWorld("world").getBlockAt(5027, 35, 4882).setType(Material.SEA_LANTERN);
                    } else {
                        Bukkit.getWorld("world").getBlockAt(5023, 35, 4882).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(5025, 35, 4882).setType(Material.QUARTZ_BLOCK);
                        Bukkit.getWorld("world").getBlockAt(5027, 35, 4882).setType(Material.QUARTZ_BLOCK);
                    }
                    Bukkit.getWorld("world").getBlockAt(5023, 36, 4882).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(5025, 36, 4882).setType(Material.AIR);
                    Bukkit.getWorld("world").getBlockAt(5027, 36, 4882).setType(Material.AIR);
                }
                if (this.type.contains("meio1") || this.type.contains("tudo")) {
                    if (aberto) {
                        for (int x = 4974; x <= 5029; x = x + 2) {
                            Bukkit.getWorld("world").getBlockAt(x, 35, 4878).setType(Material.SEA_LANTERN);
                        }
                    } else {
                        for (int x = 4974; x <= 5029; x = x + 2) {
                            Bukkit.getWorld("world").getBlockAt(x, 35, 4878).setType(Material.QUARTZ_BLOCK);
                        }
                    }
                    for (int x = 4974; x <= 5029; x = x + 2) {
                        Bukkit.getWorld("world").getBlockAt(x, 36, 4878).setType(Material.AIR);
                    }
                }
                if (this.type.contains("meio2") || this.type.contains("tudo")) {
                    if (aberto) {
                        for (int x = 4974; x <= 5029; x = x + 2) {
                            Bukkit.getWorld("world").getBlockAt(x, 35, 4873).setType(Material.SEA_LANTERN);
                        }
                    } else {
                        for (int x = 4974; x <= 5029; x = x + 2) {
                            Bukkit.getWorld("world").getBlockAt(x, 35, 4873).setType(Material.QUARTZ_BLOCK);
                        }
                    }
                    for (int x = 4974; x <= 5029; x = x + 2) {
                        Bukkit.getWorld("world").getBlockAt(x, 36, 4873).setType(Material.AIR);
                    }
                }
                if (this.type.contains("meio3") || this.type.contains("tudo")) {
                    if (aberto) {
                        for (int x = 4974; x <= 5029; x = x + 2) {
                            Bukkit.getWorld("world").getBlockAt(x, 35, 4868).setType(Material.SEA_LANTERN);
                        }
                    } else {
                        for (int x = 4974; x <= 5029; x = x + 2) {
                            Bukkit.getWorld("world").getBlockAt(x, 35, 4868).setType(Material.QUARTZ_BLOCK);
                        }
                    }
                    for (int x = 4974; x <= 5029; x = x + 2) {
                        Bukkit.getWorld("world").getBlockAt(x, 36, 4868).setType(Material.AIR);
                    }
                }
                if (this.type.contains("atrascima") || this.type.contains("tudo")) {
                    if (aberto) {
                        for (int x = 4974; x <= 5029; x = x + 2) {
                            Bukkit.getWorld("world").getBlockAt(x, 59, 4863).setType(Material.SEA_LANTERN);
                        }
                    } else {
                        for (int x = 4974; x <= 5029; x = x + 2) {
                            Bukkit.getWorld("world").getBlockAt(x, 59, 4863).setType(Material.WOOD);
                        }
                    }
                    for (int x = 4974; x <= 5029; x = x + 2) {
                        Bukkit.getWorld("world").getBlockAt(x, 59, 4864).setType(Material.AIR);
                    }
                }
                if (this.type.contains("atrasbaixo") || this.type.contains("tudo")) {
                    if (aberto) {
                        for (int x = 4974; x <= 5029; x = x + 2) {
                            Bukkit.getWorld("world").getBlockAt(x, 35, 4864).setType(Material.SEA_LANTERN);
                        }
                    } else {
                        for (int x = 4974; x <= 5029; x = x + 2) {
                            Bukkit.getWorld("world").getBlockAt(x, 35, 4864).setType(Material.QUARTZ_BLOCK);
                        }
                    }
                    for (int x = 4974; x <= 5029; x = x + 2) {
                        Bukkit.getWorld("world").getBlockAt(x, 36, 4864).setType(Material.AIR);
                    }
                }
                if (this.type.contains("lateraldireita") || this.type.contains("tudo")) {
                    if (aberto) {
                        for (int z = 4864; z <= 4877; z = z + 2) {
                            Bukkit.getWorld("world").getBlockAt(4974, 35, z).setType(Material.SEA_LANTERN);
                        }
                    } else {
                        for (int z = 4864; z <= 4877; z = z + 2) {
                            Bukkit.getWorld("world").getBlockAt(4974, 35, z).setType(Material.QUARTZ_BLOCK);
                        }
                    }
                    for (int z = 4864; z <= 4877; z = z + 2) {
                        Bukkit.getWorld("world").getBlockAt(4974, 36, z).setType(Material.AIR);
                    }
                }
                if (this.type.contains("lateralesquerda") || this.type.contains("tudo")) {
                    if (aberto) {
                        for (int z = 4864; z <= 4877; z = z + 2) {
                            Bukkit.getWorld("world").getBlockAt(5029, 35, z).setType(Material.SEA_LANTERN);
                        }
                    } else {
                        for (int z = 4864; z <= 4877; z = z + 2) {
                            Bukkit.getWorld("world").getBlockAt(5029, 35, z).setType(Material.QUARTZ_BLOCK);
                        }
                    }
                    for (int z = 4864; z <= 4877; z = z + 2) {
                        Bukkit.getWorld("world").getBlockAt(5029, 36, z).setType(Material.AIR);
                    }
                }
                if (this.type.contains("cortina") || this.type.contains("tudo")) {
                    if (aberto) {
                        for (int x = 4974; x <= 5029; x = x + 2) {
                            Bukkit.getWorld("world").getBlockAt(x, 56, 4879).setType(Material.SEA_LANTERN);

                        }
                    } else {
                        for (int x = 4974; x <= 5029; x = x + 2) {
                            Bukkit.getWorld("world").getBlockAt(x, 56, 4879).setType(Material.LOG);
                        }
                    }
                    for (int x = 4974; x <= 5029; x = x + 2) {
                        Bukkit.getWorld("world").getBlockAt(x, 57, 4879).setType(Material.AIR);
                    }
                }
            }
        }.runTask(MapMeelMain.getPlugin());
    }

}
