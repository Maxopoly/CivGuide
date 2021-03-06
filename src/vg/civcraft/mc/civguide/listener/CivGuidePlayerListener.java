package vg.civcraft.mc.civguide.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;






import org.bukkit.event.player.PlayerQuitEvent;

import vg.civcraft.mc.civguide.CivGuide;

@SuppressWarnings("unused")
public class CivGuidePlayerListener implements Listener {
	private static CivGuide cg;
	
	public CivGuidePlayerListener(){
		cg = CivGuide.getInstance();
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onItemDrop(PlayerDropItemEvent event){
		if (!cg.hasBook(event.getPlayer())){
			return;
		}
		event.getItemDrop().remove();
		cg.removeBook(event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onInvSwitch(PlayerItemHeldEvent event){
		if (!cg.hasBook(event.getPlayer())){
			return;
		}
		cg.removeBook(event.getPlayer(), event.getPreviousSlot());	
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onInvOpen(InventoryOpenEvent event){
		if (!cg.hasBook((Player)event.getPlayer())){
			return;
		}
		cg.removeBook((Player)event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onInvMove(InventoryClickEvent event){
		if (!cg.hasBook((Player)event.getWhoClicked())){
			return;
		}
		event.getCursor().setType(Material.AIR);
		cg.removeBook((Player)event.getWhoClicked());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onLogout(PlayerQuitEvent event){
		if (!cg.hasBook(event.getPlayer())){
			return;
		}
		cg.removeBook(event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = false)
	public void onDeath(PlayerDeathEvent event){
		if (!cg.hasBook((Player)event.getEntity())){
			return;
		}
		cg.removeBook((Player)event.getEntity());
		cg.removeBooks(event.getDrops());
	}

}
