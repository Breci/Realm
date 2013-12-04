package fr.breci.Realm;

import java.sql.Connection;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.breci.Realm.Managers.LastPlotManager;
import fr.breci.Realm.Managers.SQLManager;
import fr.breci.Realm.Managers.SettingsManager;
import fr.breci.Realm.Managers.WorldMapsManager;
import fr.breci.Realm.Resident.ResidentsManager;
import fr.breci.Realm.Town.TownsManager;
import fr.breci.Realm.data.Plot;
import fr.breci.Realm.data.WorldMap;
import fr.breci.Realm.events.LeavingEvents;
import fr.breci.Realm.events.LogginEvents;
import fr.breci.Realm.utils.SQLutils;


public class Realm extends JavaPlugin {


	private Logger logger = Logger.getLogger("Minecraft");
	static Realm instance = null;
	static SettingsManager settings = new SettingsManager();
	
	private SQLManager MySQL = null;
	private Connection c = null;

	public static Realm getInstance() {
		return instance;
	}
	public void onEnable()
	{
		instance = this;
		settings.setup(this);
		MySQL = new SQLManager(settings.getConfig().getString("sql.hostname"), settings.getConfig().getString("sql.port"),settings.getConfig().getString("sql.database"), settings.getConfig().getString("sql.user"), settings.getConfig().getString("sql.password"));
		c = MySQL.open();
		SQLutils.createTownsFromSQLData(this);
		SQLutils.getPlotsFromSQL(this);
		getServer().getPluginManager().registerEvents(new LogginEvents(this), this);
		getServer().getPluginManager().registerEvents(new LeavingEvents(), this);
		logger.info("Realm enabled");
	}

	public void onDisable()
	{
		instance = null;
		MySQL.closeConnection(c);
		LastPlotManager.getList().clear();
		ResidentsManager.getList().clear();
		TownsManager.getList().clear();
		WorldMapsManager.getMap().clear();
		logger.info("Realm closed");

	}

	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)	
	{
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can claim!");
			return true;
		}
		Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("realm")); 
		{
			if(args.length == 0) // On vérifie combien il y a d'arguments
			{
				sender.sendMessage(ChatColor.GOLD + "/realm claim 1");
				return true; // Tous a bien marché, on retourne vrai
			}

			if(args.length == 1) // On vérifie combien il y a d'arguments
			{
				String cmd = args[0]; // Si il y en a un seul, on le met dans une variable.
				if(cmd.equalsIgnoreCase("claim")){
					sender.sendMessage(ChatColor.GOLD + "/realm claim 1");
					return true;
				}
			}
			if(args.length == 2) // for 2 args
			{
				String cmd = args[0]; // Si il y en a un seul, on le met dans une variable.
				String cmd2 = args[1]; // Si il y en a un seul, on le met dans une variable.
				if(cmd.equalsIgnoreCase("claim")) // On la compare pour savoir si c'est message
				{
					p.sendMessage("test1");
					Integer i = Integer.parseInt(cmd2);
					WorldMap map = WorldMapsManager.getWorldMap(p.getWorld());
					Plot plot =map.getPlot(p.getWorld(), p.getLocation().getChunk().getX(), p.getLocation().getChunk().getZ());
					plot.setTown(TownsManager.getTown(i));
					plot.setTownID(i);
					map.updatePlot(map.getPlot(p.getWorld(), p.getLocation().getChunk().getX(), p.getLocation().getChunk().getZ()), plot);
					WorldMapsManager.updateMap(p.getWorld(), WorldMapsManager.getWorldMap(p.getWorld()), map);
					return true;
				}

			}

		}

		return false;
	}
	
	public Connection getConn(){
		return c;
	}


	
}
