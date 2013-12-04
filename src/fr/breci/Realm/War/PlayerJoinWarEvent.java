package fr.breci.Realm.War;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerJoinWarEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	
	private Player player;
	private War war;
	
	public PlayerJoinWarEvent (War war, Player player){
		this.setWar(war);
		this.setPlayer(player);
	}
	
	
	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public War getWar() {
		return war;
	}


	public void setWar(War war) {
		this.war = war;
	}


	public HandlerList getHandlers() {
        return handlers;
    }
     
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
