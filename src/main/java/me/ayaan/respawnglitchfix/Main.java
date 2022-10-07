package me.ayaan.respawnglitchfix;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
   static Long delay;
   static Boolean requirePermission;
   private static String path = Bukkit.getServer().getClass().getPackage().getName();
   private static String version;
   public static Plugin plugin;
   public Plugin nonStaticPlugin;
   public static Main m;

   static {
      version = path.substring(path.lastIndexOf(".") + 1, path.length());
   }

   public void onEnable() {
      this.getConfig().options().copyDefaults(true);
      this.saveConfig();
      this.loadConfiguration();
      if (this.is1_8OrGreater(getVersion())) {
         Bukkit.getPluginManager().registerEvents(new newVersions(), this);
      } else {
         Bukkit.getPluginManager().disablePlugin(this);
      }

      m = this;
   }

   public boolean is1_8OrGreater(String v) {
      boolean bool = false;
      ArrayList<String> a = new ArrayList();
      a.add("1");
      a.add("2");
      a.add("3");
      a.add("4");
      a.add("5");
      a.add("6");
      a.add("7");
      a.add("8");
      a.add("9");
      a.add("0");
      String s = "";

      int num;
      for(num = 0; num != v.length(); ++num) {
         if (a.contains(Character.toString(v.charAt(num)))) {
            s = s + Character.toString(v.charAt(num));
         }
      }

      num = 0;
      if (s != "") {
         num = Integer.parseInt(s);
      }

      if (num >= 183) {
         bool = true;
      }

      return bool;
   }

   boolean check(String s, String s1) {
      for(int x = 0; x != s.length(); ++x) {
         if (s.charAt(x) != s1.charAt(x)) {
            return false;
         }
      }

      return true;
   }

   static String getVersion() {
      return version;
   }

   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      if (cmd.getName().equalsIgnoreCase("instantrespawn")) {
         if (sender instanceof Player) {
            if (((Player)sender).hasPermission("instantrespawn.command")) {
               if (args.length == 0) {
                  ((Player)sender).sendMessage(this.prefix() + ChatColor.RED + "Command Usage: /instantrespawn reload");
               } else if (args.length == 1) {
                  if (args[0].equalsIgnoreCase("reload")) {
                     if (((Player)sender).hasPermission("instantrespawn.reload")) {
                        this.reload();
                        ((Player)sender).sendMessage(this.prefix() + ChatColor.GREEN + "You have successfully reloaded the configuration!");
                     } else {
                        ((Player)sender).sendMessage(this.prefix() + ChatColor.RED + "You do not have permission to perform that command!");
                     }
                  } else {
                     ((Player)sender).sendMessage(this.prefix() + ChatColor.RED + "Missing or Unknown Arugments /instantrepawn reload");
                  }
               } else {
                  ((Player)sender).sendMessage(this.prefix() + ChatColor.RED + "Invalid number of arguments!");
               }
            } else {
               ((Player)sender).sendMessage(this.prefix() + ChatColor.RED + "You do not have permission to perform that command!");
            }
         } else {
            sender.sendMessage(this.prefix() + ChatColor.RED + "You need to be a player to perform that command!");
         }
      }

      return false;
   }

   private void loadConfiguration() {
      try {
         delay = this.getConfig().getLong("delay");
      } catch (Exception var3) {
         delay = 5L;
      }

      try {
         requirePermission = this.getConfig().getBoolean("require");
      } catch (Exception var2) {
         requirePermission = true;
      }

   }

   void reload() {
      this.loadConfiguration();
   }

   String prefix() {
      return ChatColor.translateAlternateColorCodes('&', "&4[&cInstantRespawn&4] &r");
   }

   static Plugin returnPlugin() {
      return plugin;
   }
}
