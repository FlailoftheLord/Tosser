package me.flail.tosser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.flail.tosser.utilities.DataFile;
import me.flail.tosser.utilities.Logger;

public class Settings extends Logger {

	private DataFile file;

	public Settings(Tosser inst) {

		file = new DataFile("config.yml");

		file.setHeader("=========================== #\r\n" +
				"                            #\r\n" +
				"  TOSSER v1.0               #\r\n" +
				"                            #\r\n" +
				"    by FlailoftheLord.      #\r\n" +
				"                            #\r\n" +
				"  Toss all the things!      #\r\n" +
				"                            #\r\n" +
				"=========================== #\r\n" +
				"\r\n" +
				"---");

	}

	public void load() {

		loadSettings();
	}

	public DataFile file() {
		return file;
	}

	public Object get(String key, Object fallback) {
		Object obj = file.getObj(key);
		return obj != null ? obj : fallback;
	}

	private void loadSettings() {
		Map<String, Object> values = new HashMap<>();

		values.put("Tossables.Tnt.Enabled", true);
		values.put("Tossables.Tnt.Strength", 1.0);
		values.put("Tossables.Blocks.Enabled", true);
		values.put("Tossables.Blocks.Strength", 0.7);
		values.put("Tossables.Blocks.DisabledBlocks", new ArrayList<>());

		for (String s : values.keySet()) {
			if (file.hasValue(s)) {
				continue;
			}

			file.setValue(s, values.get(s));

		}


	}

}
