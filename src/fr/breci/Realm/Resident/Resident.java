package fr.breci.Realm.Resident;

import org.bukkit.entity.Player;

public class Resident {

	private Player player;
	private Integer townID;
	private ResidentType status;
	
	private boolean isInWar = false;
	private boolean stillInWar = false;
	
	public Resident(Player player, Integer townID, ResidentType status){
		this.player = player;
		this.townID = townID;
		this.status = status;
	}

	public Integer getTownID() {
		return townID;
	}


	public void setTownID(Integer townID) {
		this.townID = townID;
	}
	
	public ResidentType getStatus() {
		return status;
	}


	public void setStatus(ResidentType status) {
		this.status = status;
	}
	
	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player p) {
		this.player = p;
	}
	
	public boolean isMayor(){
		if (status == ResidentType.MAYOR)
			return true;
		return false;
	}
	public boolean isResident(){
		if (status == ResidentType.RESIDENT)
			return true;
		return false;
	}
	
	public boolean isGeneral(){
		if (status == ResidentType.GENERAL)
			return true;
		return false;
	}

	public boolean isInWar() {
		return isInWar;
	}

	public void setInWar(boolean isInWar) {
		this.isInWar = isInWar;
	}

	public boolean isStillInWar() {
		return stillInWar;
	}

	public void setStillInWar(boolean stillInWar) {
		this.stillInWar = stillInWar;
	}


}
