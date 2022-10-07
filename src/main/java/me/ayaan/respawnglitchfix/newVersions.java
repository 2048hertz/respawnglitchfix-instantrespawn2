package me.ayaan.respawnglitchfix;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class newVersions implements Listener {
   @EventHandler
   void death(PlayerDeathEvent e) {
      final Player p = e.getEntity();
      if (Main.requirePermission) {
         if (p.hasPermission("instantrespawn.respawn")) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.m, new Runnable() {
               public void run() {
                  p.setCanPickupItems(true);
                  p.spigot().respawn();
               }
            }, Main.delay);
         }
      } else {
         Bukkit.getScheduler().scheduleSyncDelayedTask(Main.m, new Runnable() {
            public void run() {
               p.setCanPickupItems(true);
               p.spigot().respawn();
            }
         }, Main.delay);
      }

   }

   @EventHandler
   public void respawn(PlayerRespawnEvent e) {
      e.getPlayer().setCanPickupItems(true);
   }
}
