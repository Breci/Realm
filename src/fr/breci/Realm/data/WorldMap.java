package fr.breci.Realm.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;

import fr.breci.Realm.Realm;

/**
 * Permet de construire des tableaux dynamiques à 2 dimensions
 */
public class WorldMap
{
	private static Connection c = null;
	private static Statement statement = null;
	private static ResultSet res = null;
	static ArrayList<Plot>  array;

	public WorldMap()
	{
		array = new ArrayList<Plot>();
	}
	/**
	 * retourne la worldmap sous forme d'arrayList
	 * @return
	 */

	public ArrayList<Plot> getMap (){
		return array;
	}

	/**
	 * change la worldmap (principalement utilisé pour le setup)
	 * @param map
	 */
	public void setMap (ArrayList<Plot> map){
		array = map;
	}
	/**
	 * Ajoute un chunk à la worldmap.
	 * @param p
	 */

	public void addPlot (Plot p){
		if (!array.contains(p)){
			array.add(p);
			try{
				Bukkit.getServer().broadcastMessage("ajout plot normal");
				c = Realm.getInstance().getConn();
				statement = c.createStatement();
				res = statement.executeQuery("SELECT * FROM plots WHERE world = " + p.getW().getName() +" AND x = "+p.getX()+" AND y = "+p.getZ()+" AND townid = "+p.getTownID()+";");
				if (!res.next()){
					Bukkit.getServer().broadcastMessage(p.getW().getName());
					String state = "INSERT INTO plots(world, x, y, townid) VALUES (?,?,?,?);";
					PreparedStatement pstmt = c.prepareStatement(state);
					pstmt.setString(1, p.getW().getName());
					pstmt.setInt(2,  p.getX());
					pstmt.setInt(3,  p.getZ());
					pstmt.setInt(4, p.getTownID());
					pstmt.executeUpdate();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}

	}

	/** Retire un chunk dans la worldmap (peut utile sauf si on doit forcer un reset)
	 * @param p
	 */
	public void removePlot(Plot p){
		if(array.contains(p))
			array.remove(p);
	}
	/**
	 * Permet de savoir si la worldmap a déjà ce chunk enregistrer
	 * @param p
	 * @return
	 */

	public boolean hasPlot(Plot p){
		if (array.contains(p))
			return true;
		else
			return false;
	}
	/**
	 * retourne le chunk déterminé par ses coordonnées
	 * @param x
	 * @param y
	 * @return
	 */

	public Plot getPlot(World w,Integer x , Integer y){
		for(Plot p : array){
			if (p.getW() == w && p.getX() == x && p.getZ()==y){
				return p;
			}
		}
		Plot plot = new Plot(w,x,y,0);
		addPlot(plot);
		return plot;
	}

	public void updatePlot(Plot oldPlot, Plot newPlot){
		array.set(array.indexOf(oldPlot), newPlot);
	}


}