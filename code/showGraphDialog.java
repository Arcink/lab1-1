package MyJava;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class showGraphDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public showGraphDialog()  {
		Container container=getContentPane();
		container.setLayout(new BorderLayout());
		JLabel jl=new JLabel();
		Icon icon=new ImageIcon("/Users/joker/DeskTop/DotGraph.jpg");
		jl.setIcon(icon);
		container.add(jl, BorderLayout.CENTER);
		setVisible(true);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		setBounds(screenSize.width/3, screenSize.height/3,icon.getIconWidth(),icon.getIconHeight());
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[]args)  {

	}
}
