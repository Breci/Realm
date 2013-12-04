package fr.breci.Realm.War;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.breci.Realm.Resident.ResidentsManager;
import fr.breci.Realm.Town.Town;
import fr.breci.Realm.data.Plot;

public class War {

	private Town townUnderAttack;
	private ArrayList<Player> attackList;
	private ArrayList<Player> defenseList;

	private  Integer attackTickets;
	private  Integer defenseTickets;

	private Location respawnAttack;
	private Location respawnDefense;

	private Location throneLocation;

	private ArrayList<Plot> warZone;
	
	private HashMap<Player,Location> oldLocations;
	private HashMap<Player,ItemStack[]> oldInventory;

	public Town getTownUnderAttack() {
		return townUnderAttack;
	}

	public void setTownUnderAttack(Town townUnderAttack) {
		this.townUnderAttack = townUnderAttack;
	}

	public ArrayList<Player> getAttackList() {
		return attackList;
	}

	public void setAttackList(ArrayList<Player> attackList) {
		this.attackList = attackList;
	}

	public ArrayList<Player> getDefenseList() {
		return defenseList;
	}

	public void setDefenseList(ArrayList<Player> defenseList) {
		this.defenseList = defenseList;
	}

	public Integer getAttackTickets() {
		return attackTickets;
	}

	public void setAttackTickets(Integer attackTickets) {
		this.attackTickets = attackTickets;
	}

	public Integer getDefenseTickets() {
		return defenseTickets;
	}

	public void setDefenseTickets(Integer defenseTickets) {
		this.defenseTickets = defenseTickets;
	}

	public Location getRespawnAttack() {
		return respawnAttack;
	}

	public void setRespawnAttack(Location respawnAttack) {
		this.respawnAttack = respawnAttack;
	}

	public Location getRespawnDefense() {
		return respawnDefense;
	}

	public void setRespawnDefense(Location respawnDefense) {
		this.respawnDefense = respawnDefense;
	}

	public Location getThroneLocation() {
		return throneLocation;
	}

	public void setThroneLocation(Location throneLocation) {
		this.throneLocation = throneLocation;
	}

	public ArrayList<Plot> getWarZone() {
		return warZone;
	}

	public void setWarZone(ArrayList<Plot> warZone) {
		this.warZone = warZone;
	}



	//Fonctions nécessaires

	public void addPlayerToAttack(Player p){
		if (!attackList.contains(p)){
			if (!ResidentsManager.getResidentFromPlayer(p).isInWar()){
				ResidentsManager.getResidentFromPlayer(p).setInWar(true);
				ResidentsManager.getResidentFromPlayer(p).setStillInWar(true);
				attackList.add(p);
				Bukkit.getServer().getPluginManager().callEvent(new PlayerJoinWarEvent(this, p));
			}
		}
	}

	public void removePlayerToAttack(Player p){
		if(attackList.contains(p)){
			attackList.remove(p);
			ResidentsManager.getResidentFromPlayer(p).setInWar(false);
		}
	}

	public void addPlayerToDefense(Player p){
		if (!defenseList.contains(p)){
			if (!ResidentsManager.getResidentFromPlayer(p).isInWar()){
				ResidentsManager.getResidentFromPlayer(p).setInWar(true);
				ResidentsManager.getResidentFromPlayer(p).setStillInWar(true);
				defenseList.add(p);
				Bukkit.getServer().getPluginManager().callEvent(new PlayerJoinWarEvent(this, p));
			}
		}
	}

	public void removePlayerToDefense(Player p){
		if(defenseList.contains(p)){
			defenseList.remove(p);
			ResidentsManager.getResidentFromPlayer(p).setInWar(false);
		}
	}
	
	//Gestion des coordonnées avant guerre
	
	public void addOldLocation(Player p , Location l){
		oldLocations.put(p, l);
	}
	public void removeOldLocation(Player p){
		oldLocations.remove(p);
	}
	
	//Gestion des inventaires avant guerre
	
	public void addOldInventory(Player p , ItemStack[] i){
		oldInventory.put(p, i);
	}
	public void removeOldInventory(Player p){
		oldInventory.remove(p);
	}
	
	
	
	//gestion des tickets
	
	public void removeDefenseTicket(){
		attackTickets = attackTickets -1;
		if (attackTickets == 0 && !hasPlayersAlive(attackList)){
			//TODO FAIRE LA FUCKING FONCTION POUR LA VICTOIRE
		}
	}
	
	public void removeAttackTicket(){
		defenseTickets = defenseTickets -1;
		if (defenseTickets == 0 && !hasPlayersAlive(defenseList)){
			//TODO FAIRE LA FUCKING FONCTION POUR LA VICTOIRE
		}
	}
	
	public boolean hasPlayersAlive(ArrayList<Player> list){
		for (Player p : list){
			if (ResidentsManager.getResidentFromPlayer(p).isStillInWar()){
				return true;
			}
			else{
				continue;
			}
		}
		return false;
	}
	
	
	//Fonctions gérant la victoire
	
	public void victory(Boolean defenseWin){
		ArrayList<Player> winners = null;
		if (defenseWin){
			winners = defenseList;
		}
		else{
			winners = attackList;
		}
		for (Player p : winners){
			//TODO récompenses
			ResidentsManager.getResidentFromPlayer(p).setInWar(false);
			ResidentsManager.getResidentFromPlayer(p).setStillInWar(false);
			townUnderAttack.setInWar(false);
		}
		for (Player p : attackList ){
			p.teleport(oldLocations.get(p));
			p.getInventory().setContents(oldInventory.get(p));
			oldInventory.remove(p);
			oldLocations.remove(p);
		}
		for (Player p : defenseList ){
			p.teleport(oldLocations.get(p));
			p.getInventory().setContents(oldInventory.get(p));
			oldInventory.remove(p);
			oldLocations.remove(p);
		}
		
	}
	
	


}
