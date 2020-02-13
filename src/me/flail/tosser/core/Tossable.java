package me.flail.tosser.core;

import org.bukkit.Material;
import org.bukkit.util.Vector;

import me.flail.tosser.Tosser;
import me.flail.tosser.utilities.Logger;

public class Tossable extends Logger {
	public static Tossable inst;

	protected Material itemType;
	protected Material projetcileType;
	protected boolean hasGravity;
	protected Vector velocity;


	protected Tossable(Tosser plugin) {

		inst = this;
	}


	public Tossable getInstance() {
		return inst;
	}


}
