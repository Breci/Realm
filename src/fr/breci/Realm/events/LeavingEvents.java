package fr.breci.Realm.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.breci.Realm.Managers.PlotCheckManager;
import fr.breci.Realm.Resident.ResidentsManager;

public class LeavingEvents implements Listener{
	
	
	@EventHandler
	protected static void OnPlayerLeaving(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		ResidentsManager.removeResident(ResidentsManager.getResidentFromPlayer(p));
		PlotCheckManager.getTask(p).cancel();
		PlotCheckManager.removePlayer(p);
	}

}
