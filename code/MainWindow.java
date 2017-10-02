package MyJava;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MainWindow() {
		Container container=getContentPane();
		container.setLayout(null);
		JLabel info=new JLabel("请输入文件名",JLabel.CENTER);
		info.setBounds(110, 37, 80, 30);
		container.add(info);
		JTextField fileName=new JTextField(30);		
		fileName.setBounds(100, 67, 100, 30);
		container.add(fileName);
		JButton okButton=new JButton("确认");
		okButton.setBounds(130,107,40,20);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入文件名！");
				}
				else {
						// TODO Auto-generated catch block
						File file=new File("/Users/joker/Desktop/"+fileName.getText()+".txt");
						if(!file.exists()){
							JOptionPane.showMessageDialog(null, "文件不存在！");
						}
						else {
							Graph graph=null;
							try {
								graph = new Graph(fileName.getText());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							new ChoiceDialog(graph);
						}
					
				}
			}
		});
		container.add(okButton);
		setVisible(true);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		setBounds(screenSize.width/3, screenSize.height/3, 300, 200);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String[]args) {
		new MainWindow();
	}
}
