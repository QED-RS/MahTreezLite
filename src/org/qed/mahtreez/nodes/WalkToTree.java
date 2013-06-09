package org.qed.mahtreez.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.qed.mahtreez.MahTreez;

public class WalkToTree extends Node {
	
	@Override
	public boolean activate() {
		return !Inventory.isFull() && !MahTreez.location.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		MahTreez.status = "Walking to Trees";
		System.out.println("We are walking to the tree.");
//		System.out.println("We are walking to the tree..");
//		System.out.println("We are walking to the tree...");
		Walking.newTilePath(MahTreez.tilesToTrees).traverse();
	}

}