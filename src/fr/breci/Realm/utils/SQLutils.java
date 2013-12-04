package fr.breci.Realm.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import fr.breci.Realm.Realm;
import fr.breci.Realm.Managers.WorldMapsManager;
import fr.breci.Realm.Town.Town;
import fr.breci.Realm.Town.TownsManager;
import fr.breci.Realm.data.Plot;

public class SQLutils {

	/**
	 * Zone concernant les villes
	 */
	private static Connection c = null;
	private static Statement statement = null;
	private static ResultSet res = null;

	public static void createTownsFromSQLData(Realm plugin){
		try{
			c = plugin.getConn();
			statement = c.createStatement();
			res = statement.executeQuery("SELECT * FROM towns;");
			while (res.next()){
				TownsManager.addTown(new Town(res.getInt("townid"), res.getString("townname"), res.getString("towndesc"), getLocationFromSQL(res.getString("respawnloc")), res.getDouble("money"),getTownList(res.getString("ally")),getTownList(res.getString("enemy"))));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}

	}

	public static Location getLocationFromSQL(String s){
		String[] strings = s.split(";");
		return new Location(Bukkit.getWorld(strings[0]),Integer.parseInt(strings[1]),Integer.parseInt(strings[2]),Integer.parseInt(strings[3]));
	}
	/**
	 * Zone concernant les Plots
	 */
	public static void getPlotsFromSQL(Realm plugin){
		try{
			c = plugin.getConn();
			statement = c.createStatement();
			res = statement.executeQuery("SELECT * FROM plots ORDER BY world;");
			while (res.next()){
				WorldMapsManager.getWorldMap(Bukkit.getWorld(res.getString("world"))).addPlot(new Plot(Bukkit.getWorld(res.getString("world")), res.getInt("x"), res.getInt("y"), res.getInt("townid")));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}

	}

	public static ArrayList<Integer> getTownList(String s){
		if (s == ""){
			return new ArrayList<Integer>();
		}
		else{
			String[] strings = s.split(";");
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (String integer : strings){
				list.add(Integer.parseInt(integer));
			}
			return list;
		}
	}

}
