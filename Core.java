package me.xDoritosAreYumx.GUITest;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements Listener {	

    @Override
    public void onEnable() { 	
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {}
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;
         if (cmd.getName().equalsIgnoreCase("gui")) {
           testGUI(p);
         }
         return false;
    } 
    
	private void testGUI(Player p) {
	    Inventory inv = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Test GUI");
	    
	    ItemStack test1 = new ItemStack(Material.ENDER_PEARL, 1);
	    ItemMeta test1meta = test1.getItemMeta();
	    test1meta.setDisplayName(ChatColor.AQUA + "Example One");
	    test1.setItemMeta(test1meta);
	    
        ItemStack test2 = new ItemStack(Material.DRAGON_EGG, 1);
        ItemMeta test2meta = test2.getItemMeta();
        test2meta.setDisplayName(ChatColor.WHITE + "Example Two");
        test2.setItemMeta(test2meta);
        
	    inv.setItem(0, test1);
	    inv.setItem(1, test2);
	    
	    p.openInventory(inv);  
	}
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
    	
    	if(e.getInventory().getName().equals(ChatColor.BLUE + "Test GUI"))
    	return;
    	
    	Player p = (Player) e.getWhoClicked();
    	e.setCancelled(true);
    	
    	if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || !e.getCurrentItem().hasItemMeta()) {
    	return;
    	}
    	
    	else if(e.getCurrentItem().getType() == Material.ENDER_PEARL) {
         if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Example One")) {
          p.sendMessage("You clicked: " + e.getCurrentItem().getItemMeta().getDisplayName());
          p.closeInventory();	
         }
    	}
    	
    	else if(e.getCurrentItem().getType() == Material.DRAGON_EGG) {
         if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Example Two")) {
          p.sendMessage("You clicked: " + e.getCurrentItem().getItemMeta().getDisplayName());
          p.closeInventory();	
         }    	
    	}
    	
    	e.setCancelled(true);
    	
   }
	
}
