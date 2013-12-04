package fr.breci.Realm.Managers;

import java.util.HashMap;

import org.bukkit.entity.Player;

import fr.breci.Realm.data.Plot;

public class LastPlotManager {

	public static HashMap<Player,Plot> list = new HashMap<Player,Plot>();
	
	public static HashMap<Player,Plot> getList(){
		return list;
	}
	
	public static void addPlayer (Player p , Plot plot){
		if (!list.containsKey(p)){
			list.put(p, plot);
		}
	}
	
	public static void removePlayer(Player p){
		if(list.containsKey(p))
			list.remove(p);
	}
	
	public static boolean hasPlayer(Player p){
		if(list.containsKey(p))
			return true;
		return false;
	}
	
	public static void updatePlayer (Player p, Plot plot){
		if (list.containsKey(p)){
			list.put(p, plot);
		}
	}
	
	public static Plot getPlot (Player p){
		if (list.containsKey(p)){
			return list.get(p);
		}
		return null;
	}
	
	public static void setList (HashMap<Player,Plot> list){
		LastPlotManager.list = list;
	}
	
}
