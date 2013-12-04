package fr.breci.Realm.Resident;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class ResidentsManager {
	private static ArrayList<Resident> list = new ArrayList<Resident>();

	public static ArrayList<Resident> getList(){
		return list;
	}
	
	public static void addResident(Resident r){
		if (!list.contains(r))
			list.add(r);
	}
	
	public static void removeResident(Resident r){
		if(list.contains(r))
			list.remove(r);
	}
	
	public static boolean hasResident(Resident r){
		if (list.contains(r))
			return true;
		return false;
	}
	
	public static Resident getResidentFromPlayer(Player p){
		for (Resident r : list){
			if (r.getPlayer() == p)
				return r;
		}
		return null;
	}
}
