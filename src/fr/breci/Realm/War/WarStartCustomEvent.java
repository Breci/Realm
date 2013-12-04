package fr.breci.Realm.War;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WarStartCustomEvent extends Event{
	
    private static final HandlerList handlers = new HandlerList();
    
    private War war;
    
    public WarStartCustomEvent (War war){
    	this.setWar(war);
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
