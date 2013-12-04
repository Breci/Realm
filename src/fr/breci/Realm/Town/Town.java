package fr.breci.Realm.Town;

import java.util.ArrayList;

import org.bukkit.Location;

import fr.breci.Realm.Managers.WorldMapsManager;
import fr.breci.Realm.data.Plot;

public class Town {
	private Integer TownID;
	
	private String townName;
	private String townDesc;
	
	private Location respawnloc;
	private Plot homeChunk;
	
	private double townMoney;
	
	private ArrayList<Integer> alliesTowns;
	private ArrayList<Integer> enemiesTowns;
	
	private boolean isInWar = false;
/**
 * permet de créer une ville
 * @param TownID
 * @param townName
 * @param towndesc
 * @param respawnloc
 * @param townMoney
 */
	public Town(Integer TownID,String townName,String towndesc, Location respawnloc, Double townMoney , ArrayList<Integer> alliesTowns, ArrayList<Integer> enemiesTowns){
		this.townName = townName;
		this.townDesc = towndesc;
		this.TownID = TownID;
		this.respawnloc = respawnloc;
		this.townMoney = townMoney;
		this.homeChunk = WorldMapsManager.getWorldMap(respawnloc.getWorld()).getPlot(respawnloc.getWorld(), respawnloc.getChunk().getX(), respawnloc.getChunk().getZ());
		this.alliesTowns = alliesTowns;
		this.enemiesTowns = enemiesTowns;
	}
	
	private ArrayList<String> playerlist;

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public String getTownDesc() {
		return townDesc;
	}

	public void setTownDesc(String townDesc) {
		this.townDesc = townDesc;
	}

	public Location getRespawnloc() {
		return respawnloc;
	}

	public void setRespawnloc(Location respawnloc) {
		this.respawnloc = respawnloc;
	}
	
	public double getTownMoney() {
		return townMoney;
	}

	public void setTownMoney(double townMoney) {
		this.townMoney = townMoney;
	}

	public ArrayList<String> getPlayerlist() {
		return playerlist;
	}

	public void setPlayerlist(ArrayList<String> playerlist) {
		this.playerlist = playerlist;
	}

	public Integer getTownID() {
		return TownID;
	}

	public void setTownID(Integer townID) {
		TownID = townID;
	}

	public Plot getHomeChunk() {
		return homeChunk;
	}

	public void setHomeChunk(Plot homeChunk) {
		this.homeChunk = homeChunk;
	}
	
	//Gestion de la diplomatie
	
	public ArrayList<Integer> getAllies(){
		return alliesTowns;
	}
	
	public void addAlly(Integer i){
		if (!alliesTowns.contains(i))
			alliesTowns.add(i);
	}
	
	public void removeAlly(Integer i){
		if (alliesTowns.contains(i)){
			alliesTowns.remove(i);
		}
	}
	
	public boolean isAlly(Integer i){
		if (alliesTowns.contains(i))
			return true;
		return false;
	}
	
	public ArrayList<Integer> getEnemies(){
		return enemiesTowns;
	}
	
	public void addEnemy(Integer i){
		if (!enemiesTowns.contains(i))
			enemiesTowns.add(i);
	}
	
	public void removeEnemy(Integer i){
		if (enemiesTowns.contains(i)){
			enemiesTowns.remove(i);
		}
	}
	
	public boolean isEnemy(Integer i){
		if (enemiesTowns.contains(i))
			return true;
		return false;
	}
	
	public void setNeutral(Integer i){
		if (isEnemy(i))
			removeEnemy(i);
		else if (isAlly(i))
			removeAlly(i);
	}
	
	public boolean isNeutral(Integer i){
		if (!isEnemy(i) && !isAlly(i))
			return true;
		return false;
	}

	public boolean isInWar() {
		return isInWar;
	}

	public void setInWar(boolean isInWar) {
		this.isInWar = isInWar;
	}
	

	
	
	

}
