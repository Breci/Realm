package fr.breci.Realm.Tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.breci.Realm.Managers.LastPlotManager;
import fr.breci.Realm.Managers.WorldMapsManager;
import fr.breci.Realm.Town.Town;
import fr.breci.Realm.Town.TownsManager;
import fr.breci.Realm.data.Plot;
import fr.breci.Realm.data.WorldMap;

public class PlotCheckTask extends BukkitRunnable{

	private Player p;

	public PlotCheckTask(Player p) {
		this.p = p;
	}

	@Override
	public void run() {
		if (p.isOnline()){
			WorldMap map = WorldMapsManager.getWorldMap(p.getWorld());
			Plot plot = map.getPlot(p.getWorld(), p.getLocation().getChunk().getX(), p.getLocation().getChunk().getZ());
			Town town = TownsManager.getTown(plot.getTownID());
			if (!LastPlotManager.hasPlayer(p)){
				p.sendMessage(town.getTownName());
				LastPlotManager.addPlayer(p, plot);
			}
			if (LastPlotManager.getPlot(p).getTownID() != plot.getTownID()){
				p.sendMessage(town.getTownName());
				LastPlotManager.updatePlayer(p, plot);
			}
		}

	}

}
