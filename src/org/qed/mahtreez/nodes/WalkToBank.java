package org.qed.mahtreez.nodes;

import org.qed.mahtreez.MahTreez;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

public class WalkToBank extends Node {
		
	@Override
	public boolean activate() {
		return Inventory.isFull() && !MahTreez.bank.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {	
		MahTreez.status = "Walking to Bank";
		System.out.println("We are walking to the bank.");
//		System.out.println("We are walking to the bank..");
//		System.out.println("We are walking to the bank...");
		Walking.newTilePath(MahTreez.tilesToBank).traverse();
	}

}