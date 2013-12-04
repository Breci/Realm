package fr.breci.Realm.Managers;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class PlotCheckManager {
	
	private static HashMap<Player,BukkitTask> list = new HashMap<Player,BukkitTask> ();

	public static HashMap<Player,BukkitTask> getList() {
		return list;
	}

	public static void setList(HashMap<Player,BukkitTask> list) {
		PlotCheckManager.list = list;
	}
	
	public static boolean hasPlayer(Player p){
		if (list.containsKey(p))
			return true;
		return false;
	}
	
	public static void addPlayer (Player p, BukkitTask task){
		list.put(p, task);
	}
	
	public static void removePlayer (Player p){
		list.remove(p);
	}
	
	public static BukkitTask getTask(Player p){
		if (list.containsKey(p)){
			list.get(p);
		}
		return null;
	}

}
