package me.flail.tosser.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.ChatColor;

/**
 * Basically, make all your classes extend this one.
 * Contains all the basic utilities all bundled neatly into one. (including an instance of the
 * plugin)
 * 
 * @author FlailoftheLord
 */
public class Logger extends Tools {

	public void console(String string) {
		plugin.server.getConsoleSender().sendMessage(chat("[" + plugin.getDescription().getPrefix() + "] " + string));
	}

	public Logger nl() {
		console(" ");
		return this;
	}

	public void log(String msg) {
		BufferedWriter logWriter = null;

		Time time = new Time();

		try {

			// create a temporary file
			String timeLog = new SimpleDateFormat("MMM dd_yyyy").format(Calendar.getInstance().getTime()).toString();

			File logFile = new File(plugin.getDataFolder() + "/logs/" + timeLog + ".txt");
			if (!logFile.exists()) {
				logFile.createNewFile();

			}


			logWriter = new BufferedWriter(new FileWriter(logFile, true));

			logWriter.newLine();
			logWriter.write(time.currentDayTime() + " " + ChatColor.stripColor(msg));
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				logWriter.close();

			} catch (Exception e) {
			}

		}

	}

}
