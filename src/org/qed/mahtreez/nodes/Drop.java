package org.qed.mahtreez.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;
import org.qed.mahtreez.MahTreez;

public class Drop extends Node {

	public boolean activate() {
		return Inventory.isFull() && MahTreez.task.equals("Drop");
	}

	@Override
	public void execute() {
		MahTreez.status = "Dropping";
		for (final Item i : Inventory.getItems()) {
			for (int j : MahTreez.LOG_ID) {
				if (i.getId() == j && i.getWidgetChild().interact("Drop")) {
				}
			}
		}
	}
}