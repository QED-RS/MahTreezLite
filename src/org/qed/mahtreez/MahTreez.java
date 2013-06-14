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
//	public static int[] treeToChop = { 0 };
//	public static int tree;
//	public static Area location;
//	public static Area bank;
//	public static Tile[] tilesToTrees;
//	public static Tile[] tilesToBank;
	public static String task = "";
	public static String status;
	public static int priceNormalLog;
	public static int priceOakLog;
	public static int priceWillowLog;
	public static int priceYewLog;
	public static org.qed.mahtreez.data.Tree activeTree;
		
	public static final Area AREA_BANK_EDGEVILLE = new Area();
	public static final Area AREA_BANK_EAST_FALADOR = new Area(new Tile(3008,3353,0), new Tile(3008,3358,0),
			new Tile(3019, 3358, 0), new Tile(3019, 3353, 0));
	public static final Area AREA_YEWS_EDGEVILLE = new Area();
	public static final Area AREA_YEWS_RIMMINGTON = new Area(new Tile(2924,3237,0), new Tile(2924,3225,0),
			new Tile(2944, 3224, 0), new Tile(2944, 3238, 0));
	
	public static final Tile[] TILES_BANK_EAST_FALADOR = {new Tile(2939, 3234, 0),new Tile(2946, 3240, 0),new Tile(2953, 3244, 0),new Tile(2962, 3258, 0),new Tile(2974, 3269, 0),new Tile(2983, 3282, 0),new Tile(2994, 3295, 0),new Tile(3004, 3308, 0),new Tile(3006, 3320, 0),new Tile(3006, 3330, 0),new Tile(3006, 3345, 0),new Tile(2995,3366,0),new Tile(3006,3359,0),new Tile(3012, 3356, 0)};
	public static final Tile[] TILES_YEWS_RIMMINGTON = {new Tile(3007, 100, 0), new Tile(3006, 3340, 0), new Tile(3006, 3324, 0), new Tile(2998, 3309, 0), new Tile(2993, 3300, 0), new Tile(2989, 3294, 0), new Tile(2981, 3280, 0), new Tile(2975, 3265, 0), new Tile(2963, 3253, 0), new Tile(2955, 3240, 0), new Tile(2941, 3236, 0)};

	public void onStart() {
		Context.resolve().getEventManager().addListener(paint);
		g.setVisible(true);
//		activeTree = new org.qed.mahtreez.data.Tree();
		Node[] jobs = { new WalkToTree(), new WalkToBank(), new Chop(), new Banking(), new Drop() };
		container = new Tree(jobs);

		System.out.println("Welcome to Mah Treez!");
		priceNormalLog = getPrice(1511);
		priceOakLog = getPrice(1521);
		priceWillowLog = getPrice(1519);
		priceYewLog = getPrice(1515);
//		System.out.println("Price of your chosen log: " + MahTreez.getPrice(MahTreez.activeTree.getLog()));
	}

	public void onStop() {
		Context.resolve().getEventManager().removeListener(paint);
	}

	@Override
	public int loop() {
		while (!isStarted) {
			sleep(500);
		}
		while (isStarted) {
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

			if(Random.nextInt(1, 201) == 200) {
				antiban();
			}
		}
		return Random.nextInt(50, 101);
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
			if (!(MahTreez.status.equals("Banking"))) {
				System.out.println("Antiban: STATS tab open and temporary sleep.");
				Tabs.STATS.open();
				Mouse.move(675+Random.nextInt(1, 50), 400+Random.nextInt(1, 25));
				Timer timeout = new Timer(7000);
				if ((MahTreez.status.equals("Chopping") || MahTreez.status.equals("Waiting")) && timeout.isRunning()) {
					sleep(Random.nextInt(1950, 4500));
				} else if (!(MahTreez.status.equals("Chopping")) && !(MahTreez.status.equals("Banking")) && timeout.isRunning()) {
					sleep(Random.nextInt(500, 950));
				}
			}
			break;
		case 10:
			if (!(MahTreez.status.equals("Banking"))) {
				System.out.println("Antiban: FRIENDS tab open.");
				Tabs.FRIENDS.open();
				Mouse.move(550+Random.nextInt(1, 150), 290+Random.nextInt(1, 200));
				Timer timeOut = new Timer(7000);
				if ((MahTreez.status.equals("Chopping") || MahTreez.status.equals("Waiting")) && timeOut.isRunning()) {
					sleep(Random.nextInt(1600, 3000));
				} else if (!(MahTreez.status.equals("Chopping")) && timeOut.isRunning()) {
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