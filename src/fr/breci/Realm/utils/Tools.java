package fr.breci.Realm.utils;

import java.util.logging.Logger;

public class Tools {
	private Logger logger = Logger.getLogger("Minecraft");

	public void getWorldFromName(String s){
		
	}
	
	public boolean isStringInt(String s)
	{
		try
		{
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex)
		{
			logger.info("erreur c'est pas un int");
			return false;
		}
	}
	
}
