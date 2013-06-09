package org.qed.mahtreez.nodes;

import org.qed.mahtreez.MahTreez;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Chop extends Node {

	@Override
	public boolean activate() {
		return !Inventory.isFull() && SceneEntities.getNearest(MahTreez.treeToChop) != null;
	}

	@Override
	public void execute() {
		MahTreez.status = "Chopping";
		SceneObject tree = SceneEntities.getNearest(MahTreez.treeToChop);

		if (tree == null) {
			System.out.println("Chop: cannot find the tree! Camera rotation (for next tree).");
			if (Random.nextInt(1, 3) == 1) {
				Camera.setAngle(Camera.getYaw()+Random.nextInt(-90, -70));
				// Set pitch angle?
			} else {
				Camera.setAngle(Camera.getYaw()+Random.nextInt(70, 90));
				// Set pitch angle?
			}
			return;
		}
		if (!tree.isOnScreen()) {
			System.out.println("Chop: tree is not on screen. Camera rotation and walk towards tree.");
			switch (Random.nextInt(1, 5)) {
			case 1:
				Camera.setAngle(Camera.getYaw()+Random.nextInt(-90, -70));
				Camera.setPitch(Random.nextInt(1, 20));
				break;
			case 2:
				Camera.setAngle(Camera.getYaw()+Random.nextInt(70, 90));
				Camera.setPitch(Random.nextInt(1, 20));
				break;
			case 3:
				Camera.setAngle(Camera.getYaw()+Random.nextInt(-90, -70));
				Camera.setPitch(Random.nextInt(67, 87));
				break;
			case 4:
				Camera.setAngle(Camera.getYaw()+Random.nextInt(70, 90));
				Camera.setPitch(Random.nextInt(67, 87));
				break;
			}
//			Walking.walk(tree.getLocation());
				Task.sleep(200, 300);
			return;
		}
		if (Players.getLocal().isIdle()) {
			System.out.println("Chop: 1-player is idle.");
			tree.interact("Chop");
			System.out.println("Chop: 2-interaction with tree.");
				Task.sleep(200, 300);
			if (Random.nextInt(1, 3) == 1) {
				Camera.setAngle(Camera.getYaw()+Random.nextInt(-90, -70));
			} else {
				Camera.setAngle(Camera.getYaw()+Random.nextInt(70, 90));
			}
		}
		return;
	}
}