package fr.breci.Realm.Town;

import java.util.ArrayList;

public class TownsManager {

	private static ArrayList<Town> list = new ArrayList<Town>();

	/**
	 * 
	 * @return
	 */
	public static ArrayList<Town> getList() {
		return list;
	}

	/**
	 * 
	 * @param list
	 */
	public static void setList(ArrayList<Town> list) {
		TownsManager.list = list;
	}

	/**
	 * 
	 * @param t
	 */
	public static void addTown(Town t){
		if (!list.contains(t))
			list.add(t);
	}

	/**
	 * 
	 * @param t
	 */
	public static void removeTown(Town t){
		if(list.contains(t))
			list.remove(t);
	}

	/**
	 * 
	 * @param t
	 * @return
	 */
	public static boolean hadTown (Town t){
		if (list.contains(t))
			return true;
		return false;
	}

	/**
	 * 
	 * @param i
	 * @return
	 */
	public static Town getTown (Integer i){
		for (Town t : list){
			if (t.getTownID() ==i)
				return t;
		}
		return null;
	}

}
