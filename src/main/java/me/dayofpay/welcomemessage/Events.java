package me.dayofpay.welcomemessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import me.clip.placeholderapi.PlaceholderAPI;

public class Events implements Listener {
    private static Events plugin;
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeave(PlayerQuitEvent leaveEvent){
        if(Welcomemessage.getInstance().getConfig().getBoolean("globalAnnounce") == true){
            String leaveMessage = Welcomemessage.getInstance().getConfig().getString("globalLeaveMessage");
            leaveMessage = PlaceholderAPI.setPlaceholders(leaveEvent.getPlayer(),leaveMessage);
            Welcomemessage.getInstance().getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',leaveMessage));
        }
        else{

        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent joinEvent) {
        if(Welcomemessage.getInstance().getConfig().getBoolean("globalAnnounce") == true){
            String joinMessage = Welcomemessage.getInstance().getConfig().getString("globalJoinMessage");
            joinMessage = PlaceholderAPI.setPlaceholders(joinEvent.getPlayer(),joinMessage);
            Welcomemessage.getInstance().getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',joinMessage));
        }
        else{

        }
        if(Welcomemessage.getInstance().getConfig().getBoolean("enableSpecialMessage") == true && joinEvent.getPlayer().hasPermission(Welcomemessage.getInstance().getConfig().getString("specialMessagePermission"))){
            String specialMessage = Welcomemessage.getInstance().getConfig().getString("specialMessage");
            specialMessage = PlaceholderAPI.setPlaceholders(joinEvent.getPlayer(),specialMessage);
            Welcomemessage.getInstance().getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',specialMessage));
        }else{

        }
        if(Welcomemessage.getInstance().getConfig().getBoolean("logConsole") == true){
            Welcomemessage.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.GREEN + joinEvent.getPlayer().getName() + " Joined the server ");
        }
        else{

        }
        for (String msg : Welcomemessage.getInstance().getConfig().getStringList("message")) {
            String joinText = msg;
            joinText = PlaceholderAPI.setPlaceholders(joinEvent.getPlayer(), joinText);

            joinEvent.getPlayer().sendMessage(joinText);
        }
    }
}