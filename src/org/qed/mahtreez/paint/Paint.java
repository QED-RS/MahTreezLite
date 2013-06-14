package org.qed.mahtreez.paint;

import java.awt.*;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.SkillData;
import org.powerbot.game.api.util.SkillData.Rate;
import org.powerbot.game.api.util.Timer;
import org.qed.mahtreez.MahTreez;

public class Paint implements PaintListener, MessageListener {

	private static final int startxp = Skills.getExperience(Skills.WOODCUTTING);
	private static int expGained;
	private static int chopped = 0;
	private static int normalsChopped = 0;
	private static int oaksChopped = 0;
	private static int willowsChopped = 0;
	private static int yewsChopped = 0;
	private static final Timer timer = new Timer(0);
	private static final SkillData data = new SkillData();
	private static int currentLevel;
	private int levelsGained;
	public static int startLevel = Skills.getLevel(Skills.WOODCUTTING);
	public static int profitGained;
	public static int profitPerHour;

	@Override
	public void messageReceived(MessageEvent e) {
		String svrmsg = e.getMessage();
		if (svrmsg.contains("You get some logs.")) {
			normalsChopped++;
			System.out.println("Paint: Normal trees chopped: " + normalsChopped);
		}
		else if (svrmsg.contains("You get some oak logs.")) {
			oaksChopped++;
			System.out.println("Paint: Oaks chopped: " + oaksChopped);
		}
		else if (svrmsg.contains("You get some willow logs.")) {
			willowsChopped++;
			System.out.println("Paint: Willows chopped: " + willowsChopped);
		}
		else if (svrmsg.contains("You get some yew logs.")) {
			yewsChopped++;
			System.out.println("Paint: Yews chopped: " + yewsChopped);
		}
		if (svrmsg.contains("You get some")) {
			chopped++;
			profitGained = (normalsChopped * MahTreez.priceNormalLog) + (oaksChopped * MahTreez.priceOakLog) + (willowsChopped * MahTreez.priceWillowLog) + (yewsChopped * MahTreez.priceYewLog);
		}
	}

	private Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}
	
	private final Image img3 = getImage("http://i.imgur.com/jkqmlYS.png"); // XP
	private final Image img2 = getImage("http://i.imgur.com/hYOZmWu.png"); // tree
	private final Image img1 = getImage("http://i.imgur.com/Y0oQq53.png"); // clock
	private final Image img4 = getImage("http://i.imgur.com/u5HdHwh.gif"); // money pouch
	private final Image img5 = getImage("http://i.imgur.com/ZJAhZ3Y.png"); // title image
	private final Image img6 = getImage("http://i.imgur.com/c4sVt37.png"); // signature

	private final Color color1 = new Color(52, 52, 52, 150);
	private final Color color2 = new Color(0, 0, 0);
	private final Color color3 = new Color(255, 255, 255);
	private final Color color4 = new Color(255, 255, 255, 150);
	private final Color color5 = new Color(180, 180, 180);
	private final Color color6 = new Color(255, 255, 255, 190);
//	private final Color color7 = new Color(210, 210, 210);

	private final BasicStroke stroke1 = new BasicStroke(1);

	private final Font font1 = new Font("Arial", 0, 14);

	public void onRepaint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		currentLevel = Skills.getLevel(Skills.WOODCUTTING);
		levelsGained = currentLevel - startLevel;
		expGained = Skills.getExperience(Skills.WOODCUTTING) - startxp;
		profitPerHour = (int) ((profitGained) / (float) timer.getElapsed() * 3600000);
		final Point p = Mouse.getLocation();
		g.setColor(Mouse.isPressed() ? Color.GREEN : Color.RED);
		g.drawOval(p.x - 3, p.y - 3, 9, 9);
		g.drawRect(p.x - 3, p.y - 3, 9, 9);
		g.setColor(Color.BLACK);
		{			
			g.drawOval(p.x - 6, p.y - 6, 14, 14);
		}
		g.setColor(color1);
		g.fillRect(285, 395, 480, 112); // Container
		g.setColor(color2);
		g.setStroke(stroke1);
		g.drawRect(285, 395, 480, 112); // Border
		{
		g.drawImage(img1, 285, 396, null);
		g.drawImage(img2, 286, 425, null);
		g.drawImage(img3, 285, 451, null);
		g.drawImage(img4, 285, 479, null);
		}
		g.setColor(color3);
		g.drawLine(286, 479, 504, 479); // bottom white row border
		g.drawLine(286, 451, 764, 451); // middle white row border
		g.drawLine(286, 423, 764, 423); // top white row border
		g.drawLine(505, 396, 505, 506); // white middle column
		g.setColor(color4); // image rectangle fill color-translucent white; (used to be gray)
		g.fillRect(507, 453, 256, 53); // fill image rectangle
		g.setColor(color5); // gray border; used to be white border with gray fill
		g.drawRect(506, 452, 258, 55); // image rectangle border
		{
			g.drawImage(img5, 535, 455, null);
			g.drawImage(img6, 675, 475, null);
		}
		g.setFont(font1); // Set font of words on paint
		g.setColor(color6); // Set color of words on paint
		g.drawString(String.format("Time Running: %s", timer.toElapsedString()), 317, 414);
		g.drawString("Logs Chopped: " + chopped, 317, 443);
		g.drawString("XP (/hr): " + expGained + " (" + data.experience(Rate.HOUR, Skills.WOODCUTTING) + ")", 317, 470);
		g.drawString("Profit (/hr): " + profitGained + " (" + profitPerHour + ")", 317, 498);
		g.drawString("Status: " + MahTreez.status, 511, 414);
		g.drawString("Current Level (Gained): " + (currentLevel) + " (" + levelsGained + ")" ,  511, 443);

	}
}