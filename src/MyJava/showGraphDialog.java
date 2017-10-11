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
	private static final long serialVersionUID = 4L;
	public showGraphDialog()  {
		Container container=getContentPane();
		container.setLayout(new BorderLayout());
		JLabel jl=new JLabel();
		Icon icon=new ImageIcon("/Users/joker/Desktop/DotGraph.jpg");
		jl.setIcon(icon);
		JScrollPane sp = new JScrollPane();
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setViewportView(jl);
		container.add(sp);
		//container.add(jl, BorderLayout.CENTER);
		setVisible(true);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		setBounds(screenSize.width/2, screenSize.height/2,Math.min(icon.getIconWidth(),screenSize.width/2 ),screenSize.height/2);
		setResizable(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[]args)  {

	}
}
