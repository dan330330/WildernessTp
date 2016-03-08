package me.Qball.Wild;


import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.event.Listener; 
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Sounds extends JavaPlugin implements Listener{
	public static Sound sound;
	public static Plugin wild = Wild.getInstance();
	static Map<String,String> soundMap = new HashMap<String,String>();
	public Sounds()
	{
		String[] tmp = Bukkit.getVersion().split("MC: ");
		String version =tmp[tmp.length-1].substring(0,3);
		if(version.equals("1.9"))
		{
			soundMap.put("Enderman_Teleport", "ENTITY_ENDERMAN_TELEPORT");
			soundMap.put("Egg_Pop", "ENTITY_CHICKEN_EGG");
			soundMap.put("Enderdragon_Growl", "ENITY_ENDERDRAGON_GROWL");
			soundMap.put("Enderman_Scream", "ENTITY_ENDERMEN_SCREAM");
			soundMap.put("Portal_Travel","BLOCK_PORTAL_TRAVEL");
			soundMap.put("Ghast_Moan", "ENTITY_GHAST_WARN");
			soundMap.put("Ghast_Scream", "ENTITY_GHAST_SCREAM");
			soundMap.put("Explode", "ENTITY_GENERIC_EXPLODE");		
			soundMap.put("Cave", "AMBIENT_CAVE");
		}
		else
		{
			soundMap.put("Enderman_Teleport","ENDERMAN_TELEPORT");
			soundMap.put("Egg_Pop", "CHICKEN_EGG_POP");
			soundMap.put("Enderdragon_Growl","ENDERDRAGON_GROWL");
			soundMap.put("Enderman_Scream", "ENDERMAN_SCREAM");
			soundMap.put("Portal_Travel","PORTAL_TRAVEL");
			soundMap.put("Ghast_Moan", "GHATS_MOAN");
			soundMap.put("Ghast_Scream","GHAST_SCREAM");
			soundMap.put("Explode","EXPLODE");
			soundMap.put("Cave","AMBIENCE_CAVE");
		}
		
	}
public  static  Sound getSound()
{
	String sounds = wild.getConfig().getString("Sound");
	sounds = sounds.toLowerCase();
	try
	{
	switch(sounds)
	{
	case "enderman teleport":
		sound = Sound.valueOf(soundMap.get("Enderman_Teleport"));
		break;
	case "egg pop":
		sound = Sound.valueOf(soundMap.get("Egg_Pop"));
		break;
	case "dragon growl":
		sound = Sound.valueOf(soundMap.get("Enderdragon_Growl"));
		break;
	case "enderman scream":
		sound = Sound.valueOf(soundMap.get("Enderman_Scream"));
		break;
	case "portal travel":
		sound = Sound.valueOf(soundMap.get("Portal_Travel"));
		break;
	case "ghast moan":
		sound = Sound.valueOf(soundMap.get("Ghast_Moan"));
		break;
	case "ghast scream":
		sound = Sound.valueOf(soundMap.get("Ghast_Screan"));
		break;
	case "explosion":
		sound = Sound.valueOf(soundMap.get("Explode"));
		break;
	default:
		sound = Sound.valueOf(soundMap.get("Cave"));
		throw new IllegalArgumentException("Error cannot find spefied sound. Please check config");
		
		
		
	}
	}
	catch(NullPointerException e)
	{
		System.err.println(e +"I am aware of this error");
	}
	return sound;

}



}
