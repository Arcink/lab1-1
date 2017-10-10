package MyJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class randThread extends Thread{
	JTextArea textArea;
	Graph graph;
	public randThread(JTextArea textArea,Graph graph) {
		this.textArea=textArea;
		this.graph=graph;
	}
	public void run() {
		StringBuilder builder=new StringBuilder();
		String s=new String();
		while(true) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s=graph.randomWalk();
			if(s.equals("")) {
				if(graph.randEnd!=null)
				builder.append(graph.randEnd.vertex+" ");
				textArea.setText(builder.toString());
				JOptionPane.showMessageDialog(null, "已完成");
				break;
			}else {
				builder.append(s+" ");
				textArea.setText(builder.toString());
			}
		}
	}
}
@SuppressWarnings("serial")
public class randomDialog extends JDialog {
	static int flag=1;
	randThread rth=null;
	public randomDialog(Graph graph) {
		Container container=getContentPane();
		container.setLayout(new BorderLayout());
		
		JButton startButton=new JButton("开始");
		JButton endButton=new JButton("终止");
		
		JPanel jp1=new JPanel();
		jp1.setLayout(new GridLayout(1, 5));
		jp1.add(new JLabel());
		jp1.add(startButton);
		jp1.add(new JLabel());
		jp1.add(endButton);
		jp1.add(new JLabel());
		
		JTextArea textArea=new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane js=new JScrollPane(textArea);
		startButton.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(rth!=null) rth.stop();
				graph.randInit();
				rth=new randThread(textArea,graph);
				rth.start();
			}
		});
		endButton.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(rth!=null) rth.stop();

				graph.randInit();
				rth=null;
			}
		});
	
	
		
		container.add(js, BorderLayout.CENTER);
		container.add(jp1, BorderLayout.SOUTH);
		
		setVisible(true);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		setBounds(screenSize.width/3, screenSize.height/3, 300, 200);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[]args) throws IOException {


	}
}
