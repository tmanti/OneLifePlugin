package me.tmanti.literaldeath;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class LiteralDeath extends JavaPlugin implements Listener{

    HashMap<Player, String> toDie = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Literal Death is enabling");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Literal Death is disabling");
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        if(toDie.get(event.getEntity())==null) {
            for (Player p : getServer().getOnlinePlayers()) {
                if(!(p == event.getEntity())) {
                    toDie.put(p, event.getDeathMessage());
                    p.setHealth(0);
                }
            }
            String str = "Congrats " + event.getEntity().getDisplayName() + ", you just killed everyone!";
            str = ChatColor.RED + str;
            str = ChatColor.BOLD + str;
            Bukkit.broadcastMessage(str);
        } else {
            event.setDeathMessage(toDie.get(event.getEntity()));
        }
    }

}
