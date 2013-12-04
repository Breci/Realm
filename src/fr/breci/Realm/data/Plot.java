package fr.breci.Realm.data;

import org.bukkit.World;

import fr.breci.Realm.Town.Town;

public class Plot {

	private World w;
	private Integer x;
	private Integer z;
	private Integer townID;

	private Town town;// ville possédant les chunks

	public Plot(World w, Integer x, Integer y, Integer townID) {
		this.w = w;
		this.x = x;
		this.z = y;
		this.townID = townID;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getZ() {
		return z;
	}

	public void setZ(Integer z) {
		this.z = z;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public Integer getTownID() {
		return townID;
	}

	public void setTownID(Integer townID) {
		this.townID = townID;
	}

	public World getW() {
		return w;
	}

	public void setW(World w) {
		this.w = w;
	}

}
