package fr.breci.Realm.events;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitTask;

import fr.breci.Realm.Realm;
import fr.breci.Realm.Managers.PlotCheckManager;
import fr.breci.Realm.Resident.Resident;
import fr.breci.Realm.Resident.ResidentType;
import fr.breci.Realm.Resident.ResidentsManager;
import fr.breci.Realm.Tasks.PlotCheckTask;

public class LogginEvents implements Listener {

	static Realm plugin;
	private static Connection c = null;
	private static Statement statement = null;
	private static ResultSet res = null;

	public LogginEvents(Realm realm) {
		LogginEvents.plugin = realm;
	}

	@EventHandler
	protected static void OnPlayerLogging(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		BukkitTask task = new PlotCheckTask(p).runTaskTimer(plugin, 10, 20);
		PlotCheckManager.addPlayer(p, task);
		try {
			c = Realm.getInstance().getConn();
			statement = c.createStatement();
			res = statement.executeQuery("SELECT * FROM residents WHERE player = '"+ p.getName() +"';");
			if (!res.next()){
				Resident r = new Resident(p,0,ResidentType.RESIDENT);
				ResidentsManager.addResident(r);
				String state = "INSERT INTO residents (player, townid, status) VALUES (?,?,?);";
				PreparedStatement pstmt = c.prepareStatement(state);
				pstmt.setString(1, p.getName());
				pstmt.setInt(2, 0);
				pstmt.setString(3, "Resident");
				pstmt.executeUpdate();
			}
			else{
				Resident r = new Resident(Bukkit.getPlayer(res.getString("player")),res.getInt("townid") ,ResidentType.valueOf(res.getString("status").toUpperCase()));
				ResidentsManager.addResident(r);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
