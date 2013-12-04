package fr.breci.Realm.Town;

import java.util.ArrayList;

import org.bukkit.Location;

public class OutPost extends Town {

	private Integer townLeader;
	private Addon[] townAddon;

	public OutPost(Integer TownID, String townName, String towndesc,
			Location respawnloc, Double townMoney, 
			Integer townLeader, Addon[] townAddon, ArrayList<Integer> alliesList, ArrayList<Integer> enemiesList) {
		super(TownID, townName, towndesc, respawnloc, townMoney,alliesList,enemiesList);
		this.townLeader = townLeader;
		this.townAddon = townAddon;
	}

	public Integer getTownLeader() {
		return townLeader;
	}

	public void setTownLeader(Integer townLeader) {
		this.townLeader = townLeader;
	}
	
	public Addon[] getAddon(){
		return townAddon;
	}

		public enum Addon {
			FORGE("Forge"),
			MOULIN("Moulin"),
			ECURIE("Ecurie"),
			SCIERIE("Scierie"),
			DONJON("Donjon"); 
			private String name = "";

			//Constructeur
			Addon(String name){
				this.name = name;
		}
			public String getName(){
				return this.name;
			}
			
			public Addon getAddon(String s){
				return Addon.valueOf(s);
			}
	}

}
