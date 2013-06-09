package org.qed.mahtreez.data;

import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

public enum Tree {

	YEW(new int[]{38755, 38755}, 1515);
//	WILLOW(new int[]{58006, 38627, 38616}, 1519),
//	OAK(new int[]{38731, 38732}, 1521),
//	NORMAL(new int[]{38785, 38760, 38787}, 1511);

	public static final Area AREA_BANK_EAST_FALADOR = new Area(new Tile(3008,3353,0), new Tile(3008,3358,0),
			new Tile(3019, 3358, 0), new Tile(3019, 3353, 0));
	public static final Area AREA_YEWS_RIMMINGTON = new Area(new Tile(2924,3237,0), new Tile(2924,3225,0),
			new Tile(2944, 3224, 0), new Tile(2944, 3238, 0));

	public static final Tile[] TILES_BANK_EAST_FALADOR = {new Tile(2939, 3234, 0),new Tile(2946, 3240, 0),new Tile(2953, 3244, 0),new Tile(2962, 3258, 0),new Tile(2974, 3269, 0),new Tile(2983, 3282, 0),new Tile(2994, 3295, 0),new Tile(3004, 3308, 0),new Tile(3006, 3320, 0),new Tile(3006, 3330, 0),new Tile(3006, 3345, 0),new Tile(2995,3366,0),new Tile(3006,3359,0),new Tile(3012, 3356, 0)};
	public static final Tile[] TILES_YEWS_RIMMINGTON = {new Tile(3007, 100, 0), new Tile(3006, 3340, 0), new Tile(3006, 3324, 0), new Tile(2998, 3309, 0), new Tile(2993, 3300, 0), new Tile(2989, 3294, 0), new Tile(2981, 3280, 0), new Tile(2975, 3265, 0), new Tile(2963, 3253, 0), new Tile(2955, 3240, 0), new Tile(2941, 3236, 0)};
	
	private final int[] ids;
	private final int log;

	Tree (int[] ids, int log) {
		this.ids = ids;
		this.log = log;
	}

	public int[] getIds(){
		return ids;
	}

	public int getLog() {
		return log;
	}

	@Override
	public String toString() {
		String name = super.toString();
		String f = Character.toString(name.charAt(0));
		String sub = name.substring(1, name.length());
		return f + sub.toLowerCase();
	}
}