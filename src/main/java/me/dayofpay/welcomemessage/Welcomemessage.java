package me.dayofpay.welcomemessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Welcomemessage extends JavaPlugin implements Listener {
    private static Welcomemessage plugin;
    File cfile;
    FileConfiguration config;
    @Override
    public void onEnable() {
        this.config = this.getConfig();
        this.config.options().copyDefaults(true);
        this.saveConfig();
        this.cfile = new File(this.getDataFolder(), "config.yml");
        plugin = this;
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[WelcomeMessage] Loaded Successfully ...");
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholders().register();
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[WelcomeMessage] PlaceHolderAPI Loaded ...");
            Bukkit.getPluginManager().registerEvents(new Events(), this);
        }else{
            getLogger().warning(ChatColor.RED + "[WelcomeMessage] Could not find PlaceHolderAPI");
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("welcomemessage") && args.length < 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&4&l&OWelcome&e&l&oMessage &7|  &b&OV" + Info.getVersion.toString()));
        }
        else if(command.getName().equals("welcomemessage") && args[0].toString().equals("reload") && sender.hasPermission(getConfig().getString("adminPermission"))){
            String pluginPrefix = getConfig().getString("pluginPrefix");
            String pluginResponse = getConfig().getString("reloadMessage");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',pluginPrefix + pluginResponse));
            onDisable();
            onEnable();

        }
        else if(command.getName().equals("welcomemessage") && args[0].toString().equals("reload") && !sender.hasPermission(getConfig().getString("adminPermission"))) {
            String pluginPrefix = getConfig().getString("pluginPrefix");
            String pluginResponse = getConfig().getString("noPermission");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pluginPrefix + pluginResponse));
        }
        return true;
    }
    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[WelcomeMessage] Disabled ...");
    }
    public static Welcomemessage getInstance () {
        return plugin;
    }
}
