package org.qed.mahtreez.data;

import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.qed.mahtreez.MahTreez;

public enum Tree {
	
	YEW_EDGEVILLE (new int[]{38755, 38755}, 1515, MahTreez.AREA_BANK_EDGEVILLE, MahTreez.AREA_YEWS_EDGEVILLE, MahTreez.TILES_BANK_EAST_FALADOR, MahTreez.TILES_YEWS_RIMMINGTON),
	YEW_RIMMINGTON (new int[]{38755, 38755}, 1515, MahTreez.AREA_BANK_EAST_FALADOR, MahTreez.AREA_YEWS_RIMMINGTON, MahTreez.TILES_BANK_EAST_FALADOR, MahTreez.TILES_YEWS_RIMMINGTON);
//	WILLOW(new int[]{58006, 38627, 38616}, 1519),
//	OAK(new int[]{38731, 38732}, 1521),
//	NORMAL(new int[]{38785, 38760, 38787}, 1511);
	
	private final int[] ids;
	private final int log;
	private final Area bank;
	private final Area treeLoc;
	private Tile[] pathToBank;
	private Tile[] pathToTrees;

	private Tree (int[] ids, int log, Area bank, Area treeLoc, Tile[] pathToBank, Tile[] pathToTrees) {
		this.ids = ids;
		this.log = log;
		this.bank = bank;
		this.treeLoc = treeLoc;
		this.pathToBank = pathToBank;
		this.pathToTrees = pathToTrees;
	}
	
	public int[] getIds() {
		return ids;
	}

	public int getLog() {
		return log;
	}
	
	public Area getBank() {
		return bank;
	}
	
	public Area getTreeLoc() {
		return treeLoc;
	}
	
	public Tile[] getPathToBank() {
		return pathToBank;
	}
	
	public Tile[] getPathToTrees() {
		return pathToTrees;
	}

	@Override
	public String toString() {
		String name = super.toString();
		String f = Character.toString(name.charAt(0));
		String sub = name.substring(1, name.length());
		return f + sub.toLowerCase();
	}
}