import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Menu {

	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 300;


	public static void main(String[] args) {
		JFrame frame = new JFrame("Gnarly Knapsack");
		frame.setLayout(new GridLayout(2, 2));

		JPanel leftPanel1 = new JPanel();
		JLabel algorithm = new JLabel("Which Algorithm should be used?");
		Font font = new Font("Georgia", Font.PLAIN, 13);
		algorithm.setFont(font);
		JRadioButton a = new JRadioButton("A");
		JRadioButton b = new JRadioButton("B");
		JRadioButton c = new JRadioButton("C");
		JRadioButton d = new JRadioButton("D");
		ButtonGroup group = new ButtonGroup();
		group.add(a);
		group.add(b);
		group.add(c);
		group.add(d);
		leftPanel1.add(algorithm);
		leftPanel1.add(a);
		leftPanel1.add(b);
		leftPanel1.add(c);
		leftPanel1.add(d);

		JPanel leftPanel2 = new JPanel();
		JLabel spaceSize = new JLabel("Fill the length, width and height of the cargo space in here.");
		spaceSize.setFont(font);
		JTextField sl = new JTextField("16.5", 3);
		JTextField sw = new JTextField("4", 3);
		JTextField sh = new JTextField("2.5", 3);
		leftPanel2.add(spaceSize);
		leftPanel2.add(sl);
		leftPanel2.add(sw);
		leftPanel2.add(sh);

		JPanel rightPanel1 = new JPanel();
		rightPanel1.setLayout(new GridLayout(2, 1));
		JPanel rightPanel11 = new JPanel();
		JLabel xbox = new JLabel("Fill the length, width, and height in here for the respective boxes.");
		rightPanel11.add(xbox);
		JPanel rightPanel12 = new JPanel();
		rightPanel12.setLayout(new GridLayout(3, 5));
		JTextField aLength = new JTextField("1", 3);
		JTextField aWidth = new JTextField("2", 3);
		JTextField aHeight = new JTextField("1", 3);
		JTextField bLength = new JTextField("1", 3);
		JTextField bWidth = new JTextField("2", 3);
		JTextField bHeight = new JTextField("1.5", 3);
		JTextField cLength = new JTextField("1.5", 3);
		JTextField cWidth = new JTextField("1.5", 3);
		JTextField cHeight = new JTextField("1.5", 3);
		JLabel aa = new JLabel("A");
		JLabel bb = new JLabel("B");
		JLabel cc = new JLabel("C");
		JTextField aValue = new JTextField("3", 3);
		JTextField bValue = new JTextField("4", 3);
		JTextField cValue = new JTextField("5", 3);
		rightPanel12.add(aLength);
		rightPanel12.add(aWidth);
		rightPanel12.add(aHeight);
		rightPanel12.add(aa);
		rightPanel12.add(aValue);
		rightPanel12.add(bLength);
		rightPanel12.add(bWidth);
		rightPanel12.add(bHeight);
		rightPanel12.add(bb);
		rightPanel12.add(bValue);
		rightPanel12.add(cLength);
		rightPanel12.add(cWidth);
		rightPanel12.add(cHeight);
		rightPanel12.add(cc);
		rightPanel12.add(cValue);
		rightPanel1.add(rightPanel11);
		rightPanel1.add(rightPanel12);

		JPanel rightPanel2 = new JPanel();
		JButton go = new JButton("Compute!");
		go.setPreferredSize(new Dimension(120, 50));
		rightPanel2.add(go, BorderLayout.CENTER);

		frame.add(leftPanel1);
		frame.add(rightPanel1);
		frame.add(leftPanel2);
		frame.add(rightPanel2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
	}
}