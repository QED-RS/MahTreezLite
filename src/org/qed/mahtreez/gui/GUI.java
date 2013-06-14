package org.qed.mahtreez.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.qed.mahtreez.MahTreez;
import org.qed.mahtreez.data.Tree;

public class GUI extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public GUI() {
		initComponents();
	}

	public static void main(String[] args) {
		new GUI().setVisible(true);
	}

	private void startActionPerformed(ActionEvent e) {
		String location = locationBox.getSelectedItem().toString();
		String dropbank = dropOrBank.getSelectedItem().toString();
		String chop = treeBox.getSelectedItem().toString();

		if (location.equals("Edgeville") && chop.equals("Yew")) {
			MahTreez.activeTree = Tree.YEW_EDGEVILLE;
//			MahTreez.location = Tree.AREA_YEWS_EDGEVILLE;
//			MahTreez.bank = Tree.AREA_BANK_EDGEVILLE;
//			MahTreez.tilesToTrees = Tree.TILES_YEWS_EDGEVILLE;
//			MahTreez.tilesToBank = Tree.TILES_BANK_EDGEVILLE;
		} else if (location.equals("Rimmington") && chop.equals("Yew")) {
			MahTreez.activeTree = Tree.YEW_RIMMINGTON;
//			MahTreez.location = Tree.AREA_YEWS_RIMMINGTON;
//			MahTreez.bank = Tree.AREA_BANK_EAST_FALADOR;
//			MahTreez.tilesToTrees = Tree.TILES_YEWS_RIMMINGTON;
//			MahTreez.tilesToBank = Tree.TILES_BANK_EAST_FALADOR;
		}
		
		if (dropbank.equals("Bank")) {
			MahTreez.task = "Bank";
		} else {
			MahTreez.task = "Drop";
		}

//		if (chop.equals("Yew")) {
//			MahTreez.treeToChop = Tree.YEW_RIMMINGTON.getIds();
//			MahTreez.tree = Tree.YEW_RIMMINGTON.getLog();
//		}
//		if (chop.equals("Willow")) {
//			MahTreez.treeToChop = Tree.WILLOW.getIds();
//			MahTreez.tree = Tree.Willow.getLog();
//		}
//		if (chop.equals("Oak")) {
//			MahTreez.treeToChop = Tree.OAK.getIds();
//			MahTreez.tree = Tree.OAK.getLog();
//		}
//		if (chop.equals("Normal")) {
//			MahTreez.treeToChop = Tree.NORMAL.getIds();
//			MahTreez.tree = Tree.OAK.getLog();
//		}
		
		this.dispose();
	}

	private JLabel title;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JComboBox<String> locationBox;
	private JComboBox<String> dropOrBank;
	private JComboBox<String> treeBox;
//	private JComboBox<Tree> treeBox;
	private JButton start;

	private void initComponents() {

		Container contentPane = getContentPane();

		title = new JLabel();
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		locationBox = new JComboBox<String>();
		dropOrBank = new JComboBox<String>();
		treeBox = new JComboBox<String>();
//		treeBox = new JComboBox<Tree>();
		start = new JButton();

		title.setText("Mah Treez");
		title.setFont(new Font("Impact", Font.BOLD, 22));

		label1.setText("Location: ");
		label2.setText("Bank or Drop: ");
		label3.setText("Tree Type: ");

		locationBox.setModel(new DefaultComboBoxModel<>(new String[] { "Edgeville", "Rimmington" }));
		dropOrBank.setModel(new DefaultComboBoxModel<>(new String[] { "Bank", "Drop" }));
		treeBox.setModel(new DefaultComboBoxModel<>(new String[] { "Yew" }));
//		treeBox.setModel(new DefaultComboBoxModel<>( Tree.values() ));
		
		start.setText("START!");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startActionPerformed(e);
				MahTreez.isStarted = true;
			}
		});

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);

		contentPaneLayout.setHorizontalGroup(contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
						.addGroup(contentPaneLayout.createParallelGroup()
								.addGroup(contentPaneLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(label1)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(locationBox,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
									.addGroup(contentPaneLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(label2)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(dropOrBank,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
										.addGroup(contentPaneLayout.createSequentialGroup()
										.addGap(20,	20,	20)
										.addComponent(title))
													.addGroup(contentPaneLayout.createSequentialGroup()
													.addContainerGap()
													.addComponent(label3)
													.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
													.addComponent(treeBox,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
														.addGroup(contentPaneLayout.createSequentialGroup()
														.addGap(50,	50,	50)
														.addComponent(start))
						)
						.addContainerGap(7, Short.MAX_VALUE)
				));
		contentPaneLayout.setVerticalGroup(contentPaneLayout.createParallelGroup()
			.addGroup(contentPaneLayout.createSequentialGroup()
				.addContainerGap()
					.addComponent(title,GroupLayout.PREFERRED_SIZE,40,GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label1)
						.addComponent(locationBox,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label2)
								.addComponent(dropOrBank,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(label3)
										.addComponent(treeBox,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(start)
													.addContainerGap(4, Short.MAX_VALUE)
			)
		);
		pack();
		setLocationRelativeTo(getOwner());
	}
}