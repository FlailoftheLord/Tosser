package me.flail.tosser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.tosser.core.Tossable;
import me.flail.tosser.core.tossables.BlockTossable;
import me.flail.tosser.core.tossables.TntTossable;

public class Tosser extends JavaPlugin {

	public Server server;

	public Set<Material> blockWhitelist = new HashSet<>();

	@Override
	public void onEnable() {
		server = getServer();

		saveDefaultConfig();
		settings().load();

		if ((boolean) settings().get("Tossables.Tnt.Enabled", true)) {
			server.getPluginManager().registerEvents(new TntTossable(this), this);
		}

		if ((boolean) settings().get("Tossables.Blocks.Enabled", true)) {
			server.getPluginManager().registerEvents(new BlockTossable(this), this);
		}

		blockWhitelist.clear();
		List<String> list = settings().file().getList("Tossables.Blocks.DisabledBlocks");
		for (String s : list) {
			Material m = Material.matchMaterial(s);

			if (m != null) {
				blockWhitelist.add(m);
			}

		}

	}

	@Override
	public void onDisable() {

	}


	public Tossable getCore() {
		return Tossable.inst;
	}

	public Settings settings() {

		return new Settings(this);

	}

}
