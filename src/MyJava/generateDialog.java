package MyJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class generateDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	public generateDialog(Graph graph) {
		Container container=getContentPane();
		container.setLayout(new GridLayout(2, 1));
		
		JLabel inputLabel=new JLabel("请输入源文本",JLabel.CENTER);
		JTextArea inputText=new JTextArea();
		JButton generate=new JButton("转换");
		
		JPanel jp1=new JPanel();
		jp1.setLayout(new GridLayout(1,	 3));
		jp1.add(new JLabel());
		jp1.add(new JLabel());
		jp1.add(generate);
		
		JPanel jp2=new JPanel();
		jp2.setLayout(new BorderLayout());
		jp2.add(inputLabel, BorderLayout.NORTH);
		jp2.add(inputText,BorderLayout.CENTER);
		jp2.add(jp1,BorderLayout.SOUTH);
		
		JLabel outputLabel=new JLabel("转换文本如下",JLabel.CENTER);
		JTextArea outputText=new JTextArea();
		outputText.setEditable(false);
		generate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String s=graph.generateNewText(inputText.getText());
				outputText.setText(s);
			}
		});
		
		JPanel jp3=new JPanel();
		jp3.setLayout(new BorderLayout());
		jp3.add(outputLabel, BorderLayout.NORTH);
		jp3.add(outputText,BorderLayout.CENTER);
		
		container.add(jp2);
		container.add(jp3);
		setVisible(true);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		setBounds(screenSize.width/3, screenSize.height/3,500,400);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {

	}
}
