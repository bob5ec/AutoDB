package bz.asd.poc.jtree;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.text.Position;
import javax.swing.tree.TreePath;

public class SearchTree extends JFrame {

	private Vector<String> data;
	private JTree tree;
	
	public SearchTree() {
		
		data = new Vector<String>();
		data.add("a");
		data.add("ab");
		data.add("aa");
		data.add("b");
		data.add("aaaaaaa");
		data.add("bbbbbbb");
		data.add("bbbbbba");
		
		tree = new JTree(data);
		add(tree);
		
		
		TreePath match = tree.getNextMatch("bbb",0, Position.Bias.Forward);
		tree.setSelectionPath(match);
		tree.scrollPathToVisible(match);

		setPreferredSize(new Dimension(450, 110));

		pack();

		//setSize(100, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new SearchTree();

	}

}
