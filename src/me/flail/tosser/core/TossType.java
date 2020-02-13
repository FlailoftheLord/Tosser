package me.flail.tosser.core;

public enum TossType {

	ARC, ARC_EXPLOSIVE, STRAIGHT, STRAIGHT_EXPLOSIVE;

	public TossType fromString(String value) {
		String name = value.toLowerCase();

		if (name.contains("arc")) {
			if (name.contains("exp")) {
				return ARC_EXPLOSIVE;
			}
			return ARC;
		} else if (name.startsWith("str")) {
			if (name.contains("exp")) {
				return STRAIGHT_EXPLOSIVE;
			}
			return STRAIGHT;
		}

		return null;
	}

	public boolean isExplosive() {
		if (this.toString().contains("EXP")) {
			return true;
		}

		return false;
	}

}
