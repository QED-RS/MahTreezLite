package org.qed.mahtreez;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.bot.Context;
import org.qed.mahtreez.gui.GUI;
import org.qed.mahtreez.nodes.Banking;
import org.qed.mahtreez.nodes.Chop;
import org.qed.mahtreez.nodes.Drop;
import org.qed.mahtreez.nodes.WalkToBank;
import org.qed.mahtreez.nodes.WalkToTree;
import org.qed.mahtreez.paint.Paint;

@Manifest (authors = { "QED" }, description = "Chops treez up in Edgeville 'n Rimmy", name = "Mah Treez")

public class MahTreez extends ActiveScript {                                                 

	private static final Paint paint = new Paint();
	private static final GUI g = new GUI();
	private static Tree container;

	public static boolean isStarted = false;
	public static final int[] LOG_ID = { 1521, 1511, 1519, 1515 };
	public static int[] treeToChop = { 0 };
	public static int tree;
	public static Area location;
	public static Area bank;
	public static Tile[] tilesToTrees;
	public static Tile[] tilesToBank;
	public static String task = "";
	public static String status;
	public static int priceNormalLog;
	public static int priceOakLog;
	public static int priceWillowLog;
	public static int priceYewLog;

	public void onStart() {
		Context.resolve().getEventManager().addListener(paint);
		g.setVisible(true);
		Node[] jobs = { new WalkToTree(), new WalkToBank(), new Chop(), new Banking(), new Drop() };
		container = new Tree(jobs);

		System.out.println("Welcome to Mah Treez!");
		priceNormalLog = getPrice(1511);
		priceOakLog = getPrice(1521);
		priceWillowLog = getPrice(1519);
		priceYewLog = getPrice(1515);
	}

	public void onStop() {
		Context.resolve().getEventManager().removeListener(paint);
	}

	@Override
	public int loop() {
		while (!isStarted) {
			sleep(500);
		}

		if (Players.getLocal().isIdle()) {
			status = "Waiting";
		}

		if (container != null) {
			final Node job = container.state();
			if (job != null) {
				container.set(job);
				getContainer().submit(job);
				job.join();
			}
		}
		
		if(Random.nextInt(1, 200) >= 199) {
			antiban();
		}

		return 1;
	}

	public static int getPrice(final int id) {
        final String add = "http://scriptwith.us/api/?return=text&item=" + id;
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(
                new URL(add).openConnection().getInputStream()))) {
            final String line = in.readLine();
            return Integer.parseInt(line.split("[:]")[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
	
	private void antiban() {
		switch(Random.nextInt(1, 25)) {
		case 1:
			System.out.println("Antiban: Small yaw change.");
			Camera.setAngle(Camera.getYaw()+Random.nextInt(-40, 43));
			break;
		case 2:
			System.out.println("Antiban: Large yaw change.");
			Camera.setAngle(Camera.getYaw()+Random.nextInt(-100, 100));
			break;
		case 3:
			System.out.println("Antiban: Small pitch change.");
			Camera.setPitch(Camera.getPitch()+Random.nextInt(-50, 53));
			break;
		case 4:
			System.out.println("Antiban: Large pitch change.");
			Camera.setPitch(Camera.getPitch()+Random.nextInt(-125, 125));
			break;
		case 5:
			System.out.println("Antiban: Large yaw and large pitch change.");
			Camera.setAngle(Camera.getYaw()+Random.nextInt(-100, 100));
			Camera.setPitch(Camera.getPitch()+Random.nextInt(-50, 50));
			break;
		case 6:
			System.out.println("Antiban: Small yaw and small pitch change.");
			Camera.setAngle(Camera.getYaw()+Random.nextInt(-40, 43));
			Camera.setPitch(Camera.getPitch()+Random.nextInt(-50, 53));
			break;
		case 7:
			if (!(MahTreez.status == "Banking")) {
				System.out.println("Antiban: STATS tab open and temporary sleep.");
				Tabs.STATS.open();
				Mouse.move(675+Random.nextInt(1, 50), 400+Random.nextInt(1, 25));
				Timer timeout = new Timer(7000);
				if ((MahTreez.status == "Chopping" || MahTreez.status == "Waiting") && timeout.isRunning()) {
					sleep(Random.nextInt(1950, 4500));
				} else if (!(MahTreez.status == "Chopping") && !(MahTreez.status == "Banking") && timeout.isRunning()) {
					sleep(Random.nextInt(500, 950));
				}
			}
			break;
		case 10:
			if (!(MahTreez.status == "Banking")) {
				System.out.println("Antiban: FRIENDS tab open.");
				Tabs.FRIENDS.open();
				Mouse.move(550+Random.nextInt(1, 150), 290+Random.nextInt(1, 200));
				Timer timeOut = new Timer(7000);
				if ((MahTreez.status == "Chopping" || MahTreez.status == "Waiting") && timeOut.isRunning()) {
					sleep(Random.nextInt(1600, 3000));
				} else if (!(MahTreez.status == "Chopping") && timeOut.isRunning()) {
					sleep(Random.nextInt(400, 800));
				}
			}
			break;
		case 11:
			System.out.println("Antiban: Random mouse move.");
			Mouse.move(Random.nextInt(1, 750), Random.nextInt(60,370));
			break;
		}
	}
}