package org.qed.mahtreez.nodes;import org.qed.mahtreez.MahTreez;import org.powerbot.core.script.job.Task;import org.powerbot.core.script.job.state.Node;import org.powerbot.game.api.methods.interactive.Players;import org.powerbot.game.api.methods.tab.Inventory;import org.powerbot.game.api.methods.widget.Bank;import org.powerbot.game.api.methods.widget.Bank.Amount;import org.powerbot.game.api.util.Random;public class Banking extends Node {	@Override	public boolean activate() {		return Inventory.isFull() && MahTreez.task.equals("Bank") && MahTreez.activeTree.getBank().contains(Players.getLocal().getLocation());	}	@Override	public void execute() {		MahTreez.status = "Banking";		System.out.println("Executing banking.");			if (!Bank.isOpen()) {				Bank.open();				Task.sleep(Random.nextInt(300, 700));//				while ((Inventory.contains(MahTreez.LOG_ID))) {				while (Inventory.contains(1511))					Bank.deposit(1511, Amount.ALL);				while (Inventory.contains(1515))					Bank.deposit(1515, Amount.ALL);				while (Inventory.contains(1519))					Bank.deposit(1519, Amount.ALL);				while (Inventory.contains(1521))					Bank.deposit(1521, Amount.ALL);//				}				Bank.close();				////				Task.sleep(Random.nextInt(1200, 1400));//				if (Bank.deposit(1515, Amount.ALL));////				Task.sleep(Random.nextInt(1200, 1400));//				if (Bank.deposit(1519, Amount.ALL));////				Task.sleep(Random.nextInt(1200, 1400));//				if (Bank.deposit(1521, Amount.ALL));//				Task.sleep(Random.nextInt(300, 700));//				Bank.close();			}		System.out.println("Finished banking.");	}}