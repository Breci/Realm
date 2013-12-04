package fr.breci.Realm.Managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

import fr.breci.Realm.Realm;
 
public class SettingsManager {
	
 
        private static Realm p;
        static SettingsManager instance = new SettingsManager();
       
        public static SettingsManager getInstance() {
            return instance;
    }
       
        private FileConfiguration config;
        private File cfile;
       
        public void setup (Realm p){
        	SettingsManager.p = p;
        	cfile = new File(p.getDataFolder(), "config.yml");
        	if(!cfile.exists())
        	{
        		
        		// On initialise le fileConfig en ouvrant une configuration, ici notre fichier
        		config = YamlConfiguration.loadConfiguration(cfile);
        		// définition des variables
        		config.set("sql.enable", true);
        		config.set("sql.hostname", "localhost");
        		config.set("sql.database", "realm");
        		config.set("sql.port", "3306");
        		config.set("sql.user", "root");
        		config.set("sql.password", "");

        		try { // On essait de sauvegarder
        			config.save(cfile);
        		} catch(IOException ex)	{

        		}
        		
        		
        	}
        }
       
        public FileConfiguration getConfig() {
                return config = YamlConfiguration.loadConfiguration(cfile);
        }
       
        public void saveConfig() {
                try {
                        config.save(cfile);
                }
                catch (IOException e) {
                        Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
                }
        }
        
       
        public void reloadConfig() {
                config = YamlConfiguration.loadConfiguration(cfile);
        }
        
       
       
        public PluginDescriptionFile getDesc() {
                return p.getDescription();
        }
}