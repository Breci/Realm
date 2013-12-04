package fr.breci.Realm.Managers;

import java.util.HashMap;

import org.bukkit.World;

import fr.breci.Realm.data.WorldMap;

public class WorldMapsManager {

	private static HashMap<World,WorldMap> map = new HashMap<World,WorldMap>();
	public static WorldMapsManager instance;

	public static HashMap<World,WorldMap> getMap() {
		return map;
	}

	public static void setMap(HashMap<World,WorldMap> map) {
		WorldMapsManager.map = map;
	}
	
	/**
	 * Ajoute une nouvelle worldmap
	 * @param world
	 * @param wm
	 */
	public static void addToMap(World world,WorldMap wm){
		map.put(world, wm);
	}
	
	/**
	 * retourne la worldmap du monde concernée
	 * @param world
	 * @return
	 */
	
	public static WorldMap getWorldMap(World world){
		if (!map.containsKey(world))
			addToMap(world,new WorldMap());
		return map.get(world);
	}
	/**
	 * Permet de retirer un worldmap. Peut utile mais on sait jamais.
	 * @param world
	 */
	
	public static void removeWorldMap(World world){
		if (map.containsKey(world)){
			map.remove(world);
		}
	}
	
	public static void updateMap(World w,WorldMap oldMap, WorldMap newMap){
		map.remove(oldMap);
		map.put(w, newMap);
	}
	
}
