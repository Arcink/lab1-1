package MyJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ChoiceDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	public ChoiceDialog(Graph graph) {
		Container container=getContentPane();
		container.setLayout(new GridLayout(5, 1,10,10));
		JButton jb1=new JButton("展示有向图");
		container.add(jb1);
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				graph.showDirectedGraph();
				new showGraphDialog();
			}
		});
		JButton jb2=new JButton("查询桥接词");
		container.add(jb2);
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new queryDialog(graph);
			}
		});
		JButton jb3=new JButton("根据桥接词生成新文本");
		jb3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new generateDialog(graph);
			}
		});
		container.add(jb3);
		JButton jb4=new JButton("两个单词间的最短路径");
		jb4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new calShortestPathDialog(graph);
			}
		});
		container.add(jb4);
		JButton jb5=new JButton("随机游走");
		jb5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new randomDialog(graph);
			}
		});
		container.add(jb5);
		setVisible(true);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		setBounds(screenSize.width/3, screenSize.height/3, 300, 200);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
	}
}
