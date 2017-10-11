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
    int git=1;
	public MainWindow() {
		Container container=getContentPane();
		container.setLayout(null);
		JLabel info=new JLabel("浏览您计算机中的文件",JLabel.CENTER);
		info.setBounds(80, 20, 150, 100);
		container.add(info);
		//JTextField fileName=new JTextField(30);		
		//fileName.setBounds(100, 67, 100, 30);
		//container.add(fileName);
		JButton okButton=new JButton("浏览");
		okButton.setBounds(120,97,60,40);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  JFileChooser jfc=new JFileChooser();  
			        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
			        jfc.showDialog(new JLabel(), "选择");  
			        File file=jfc.getSelectedFile();  
			        JTextField fileName=new JTextField(file.getAbsolutePath());
						// TODO Auto-generated catch block
						
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
