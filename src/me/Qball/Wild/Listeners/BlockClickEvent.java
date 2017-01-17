package me.Qball.Wild.Listeners;

import me.Qball.Wild.Wild;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Collections;
import java.util.UUID;

public class BlockClickEvent implements Listener {
    private Wild wild;

    public BlockClickEvent(Wild wild) {
        this.wild = wild;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockClick(PlayerInteractEvent e) {
        String[] tmp = Bukkit.getVersion().split("MC: ");
        String version =tmp[tmp.length-1].substring(0,3);
        if (e.getItem() == null)
            return;
        if(!e.getItem().hasItemMeta())
            return;
        if (e.getAction().equals(Action.LEFT_CLICK_BLOCK) && e.getItem().getItemMeta().hasLore()) {
            if (e.getItem().getItemMeta().getLore().equals(Collections.singletonList("Right/left click on blocks to make a region")) &&
                    !checkFirstMap(e.getPlayer().getUniqueId(), e.getClickedBlock().getLocation().toVector())) {
                e.setCancelled(true);
                wild.firstCorner.put(e.getPlayer().getUniqueId(), e.getClickedBlock().getLocation().toVector());
                e.getPlayer().sendMessage(ChatColor.GREEN + "First corner set");
            }
        } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack stack = new ItemStack(Material.STICK);
            if(version.equals("1.9") || version.equals("1.1")){
                if(e.getPlayer().getInventory().getItemInMainHand()!=null)
                    stack = e.getPlayer().getInventory().getItemInMainHand();
            }else {
                //noinspection deprecation
                if (e.getPlayer().getItemInHand() != null)
                    //noinspection deprecation
                    stack = e.getPlayer().getItemInHand();
                else
                    return;
            }
            if (!stack.getItemMeta().hasLore())
                return;
            if (!stack.getItemMeta().getLore().equals(Collections.singletonList("Right/left click on blocks to make a region")))
                return;
            if (checkSecondMap(e.getPlayer().getUniqueId(), e.getClickedBlock().getLocation().toVector()))
                return;
            e.setCancelled(true);
            wild.secondCorner.put(e.getPlayer().getUniqueId(), e.getClickedBlock().getLocation().toVector());
            e.getPlayer().sendMessage(ChatColor.GREEN + "Second corner set");
        }
    }

    private boolean checkFirstMap(UUID id, Vector vec) {
        if (wild.firstCorner.containsKey(id)) {
            if (wild.firstCorner.get(id).equals(vec))
                return true;
        } else
            return false;
        return false;
    }

    private boolean checkSecondMap(UUID id, Vector vec) {
        if (wild.secondCorner.containsKey(id)) {
            if (wild.secondCorner.get(id).equals(vec))
                return true;
        } else
            return false;
        return false;
    }

}
