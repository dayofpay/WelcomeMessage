package me.dayofpay.welcomemessage;
import org.bukkit.OfflinePlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class Placeholders extends PlaceholderExpansion {
    private Events plugin; // This instance is assigned in canRegister()
    @Override
    public String getAuthor() {
        return "dayofpay";
    }

    @Override
    public String getIdentifier() {
        return "welcomemessage";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }
    @Override
    public String onRequest(OfflinePlayer player, String params) {

        if(params.equalsIgnoreCase("name")) {
            return player.getName();
        }


        return null; // Placeholder is unknown by the Expansion
    }
}