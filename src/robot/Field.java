package robot;

public class Field {

	/**
	 * Enum containing the two types of goals the robot can make.
	 * <li>{@link #LOW}</li>
	 * <li>{@link #HIGH}</li>
	 */
	public enum Goal {
		/**
		 * Set up for scoring on the low goal.
		 */
		LOW("Low"),
		/**
		 * Set up for scoring on the high goal.
		 */
		HIGH("High");

		Goal(String stringValue) {
			this.stringValue = stringValue;
		}

		private final String stringValue;

		/**
		 * Converts a string to a value of this enum (if the string contains a
		 * valid enum type).
		 * 
		 * @param stringValue
		 *            String to convert to this enum.
		 * @return Goal constant.
		 */
		public static Goal toEnum(String stringValue) {
			for (Goal goal : Goal.values()) {
				if (goal.stringValue.equals(stringValue)) {
					return goal;
				}
			}
			System.out.println("Goal value (" + stringValue + ") is not a valid goal string");
			return null;
		}
	}

	/**
	 * Enum containing the three different tower targets. The targets are at
	 * specific distances from the left wall.
	 * <li>{@link #LEFT}</li>
	 * <li>{@link #CENTER}</li>
	 * <li>{@link #RIGHT}</li>
	 */
	public enum Target {
		/**
		 * Left tower target. This target is 60 inches away from the left wall.
		 */
		LEFT("Left", 60),
		/**
		 * Center tower target. This target is 152 inches away from the left
		 * wall.
		 */
		CENTER("Center", 145),
		/**
		 * Right tower target. This target is 235 inches away from the left
		 * wall.
		 */
		RIGHT("Right", 235);

		private final String stringValue;
		private final int requiredDistance;

		Target(String stringValue, int requiredDistance) {
			this.stringValue = stringValue;
			this.requiredDistance = requiredDistance;
		}

		/**
		 * Converts a string to a value of this enum (if the string contains a
		 * valid enum type).
		 * 
		 * @param stringValue
		 *            String to convert to this enum.
		 * @return Target constant.
		 */
		public static Target toEnum(String stringValue) {
			for (Target target : Target.values()) {
				if (target.stringValue.equals(stringValue)) {
					return target;
				}
			}
			System.out.println("Target value (" + stringValue + ") is not a valid target string");
			return null;
		}

		/**
		 * 
		 * @return Required distance from the left wall to arrive at this
		 *         target.
		 */
		public int getRequiredDistance() {
			return this.requiredDistance;
		}
	}

	/**
	 * Enum containing the "lanes". A "lane" is how far the robot will travel
	 * after crossing a defense.
	 * <li>{@link #CLOSE}</li>
	 * <li>{@link #FAR}</li>
	 */
	public enum Lane {
		/**
		 * Close turn. The robot will turn and drive up to the batter as soon as
		 * it crosses a defense.
		 */
		CLOSE("Close"),

		/**
		 * Far turn. The robot will turn after driving a few extra inches. This
		 * option is usally safer.
		 */
		FAR("Far");

		private final String stringValue;

		Lane(String stringValue) {
			this.stringValue = stringValue;
		}

		/**
		 * Converts a string to a value of this enum (if the string contains a
		 * valid enum type).
		 * 
		 * @param stringValue
		 *            String to convert to this enum.
		 * @return Lane constant.
		 */
		public static Lane toEnum(String stringValue) {
			for (Lane lane : Lane.values()) {
				if (lane.stringValue.equals(stringValue)) {
					return lane;
				}
			}
			System.out.println("Lane value (" + stringValue + ") is not a valid lane string");
			return null;
		}
	}

	/**
	 * Enum containing the "slot" the robot is in. A "slot" is the defense
	 * position the robot starts at.
	 * <li>{@link #ONE}</li>
	 * <li>{@link #TWO}</li>
	 * <li>{@link #THREE}</li>
	 * <li>{@link #FOUR}</li>
	 * <li>{@link #FIVE}</li>
	 */
	public enum Slot {
		/**
		 * Slot one. In slot one, the robot is 0 inches from the left wall.
		 */
		ONE(1, 0),
		/**
		 * Slot two. In slot two, the robot is 48 inches from the left wall.
		 */
		TWO(2, 48),
		/**
		 * Slot three. In slot three, the robot is 96 inches from the left wall.
		 */
		THREE(3, 96),
		/**
		 * Slot four. In slot four, the robot is 144 inches from the left wall.
		 */
		FOUR(4, 144),
		/**
		 * Slot five. In slot five, the robot is 192 inches from the left wall.
		 */
		FIVE(5, 192);

		private final int intValue;

		private final double distanceToLeftWall;

		Slot(int intValue, double distanceToLeftWall) {
			this.intValue = intValue;
			this.distanceToLeftWall = distanceToLeftWall;
		}

		/**
		 * Converts a string to a value of this enum (if the string contains a
		 * valid enum type).
		 * 
		 * @param stringValue
		 *            String to convert to this enum.
		 * @return Target constant.
		 */
		public static Slot toEnum(int intValue) {
			for (Slot slot : Slot.values()) {
				if (slot.intValue == intValue) {
					return slot;
				}
			}
			System.out.println("Slot value (" + intValue + ") is not a valid slot");
			return null;
		}

		/**
		 * 
		 * @return Distance from the left wall.
		 */
		public double getDistanceToLeftWall() {
			return distanceToLeftWall;
		}

	}

	/**
	 * Enum containing all of the defenses the robot is capable of breaching.
	 * <li>{@link #LOW_BAR}</li>
	 * <li>{@link #RAMPARTS}</li>
	 * <li>{@link #MOAT}</li>
	 * <li>{@link #ROCK_WALL}</li>
	 * <li>{@link #ROUGH_TERRAIN}</li>
	 * <li>{@link #PORTCULLIS}</li>
	 * <li>{@link #CHEVAL_DE_FRISE}</li>
	 */
	public enum Defense {
		/**
		 * This enum constant indicates that the autonomous should be setup for
		 * crossing the Low Bar.
		 */
		LOW_BAR("Low Bar"),
		/**
		 * This enum constant indicates that the autonomous should be setup for
		 * crossing the Ramparts.
		 */
		RAMPARTS("Ramparts"),
		/**
		 * This enum constant indicates that the autonomous should be setup for
		 * crossing the Moat.
		 */
		MOAT("Moat"),
		/**
		 * This enum constant indicates that the autonomous should be setup for
		 * crossing the Rock Wall.
		 */
		ROCK_WALL("Rock Wall"),
		/**
		 * This enum constant indicates that the autonomous should be setup for
		 * crossing the Rough Terrain.
		 */
		ROUGH_TERRAIN("Rough Terrain"),
		/**
		 * This enum constant indicates that the autonomous should be setup for
		 * crossing the Portcullis.
		 */
		PORTCULLIS("Portcullis"),
		/**
		 * This enum constant indicates that the autonomous should be setup for
		 * crossing the Cheval de Frise.
		 */
		CHEVAL_DE_FRISE("Cheval de Frise");

		private final String stringValue;

		Defense(String stringValue) {
			this.stringValue = stringValue;
		}

		/**
		 * Converts a string to a value of this enum (if the string contains a
		 * valid enum type).
		 * 
		 * @param stringValue
		 *            String to convert to this enum.
		 * @return Target constant.
		 */
		public static Defense toEnum(String stringValue) {
			for (Defense defense : Defense.values()) {
				if (defense.stringValue.equals(stringValue)) {
					return defense;
				}
			}
			System.out.println("Defense value (" + stringValue + ") is not a valid defense string");
			return null;
		}
	}

	/**
	 * Enum containing all of the auto modes.
	 * <li>{@link #SINGLE_BOULDER}</li>
	 * <li>{@link #TWO_BOULDER}</li>
	 */
	public enum AutoMode {
		/**
		 * Single boulder auto. This auto does the normal patter of crossing a
		 * defense, and scoring.
		 */
		SINGLE_BOULDER("Single Boulder"),
		/**
		 * Two boulder auto. This auto starts at the
		 */
		TWO_BOULDER("Two Boulder");
		
		private final String stringValue;

		AutoMode(String stringValue) {
			this.stringValue = stringValue;
		}

		/**
		 * Converts a string to a value of this enum (if the string contains a
		 * valid enum type).
		 * 
		 * @param stringValue
		 *            String to convert to this enum.
		 * @return Target constant.
		 */
		public static AutoMode toEnum(String stringValue) {
			for (AutoMode autoMode : AutoMode.values()) {
				if (autoMode.stringValue.equals(stringValue)) {
					return autoMode;
				}
			}
			System.out.println("Defense value (" + stringValue + ") is not a valid defense string");
			return null;
		}
	}
}
