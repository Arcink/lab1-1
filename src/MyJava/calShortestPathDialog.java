package MyJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class calShortestPathDialog extends JDialog {
	private static final long serialVersionUID = 2L;
	public calShortestPathDialog(Graph graph){
		Container container=getContentPane();
		container.setLayout(new BorderLayout(20,20));
		JPanel jp=new JPanel();
		jp.setLayout(new GridLayout(1, 4));
		JLabel wordLabel1=new JLabel("请输入源单词");
		jp.add(wordLabel1);
		JTextField wordName1=new JTextField();
		jp.add(wordName1,CENTER_ALIGNMENT);
		JLabel wordLabel2=new JLabel("请输入目的单词");
		jp.add(wordLabel2);
		JTextField wordName2=new JTextField();
		jp.add(wordName2,LEFT_ALIGNMENT);

		JPanel jp1=new JPanel();
		JButton query=new JButton("查询");
		jp1.setLayout(new GridLayout(1,3));
		jp1.add(new JLabel());
		jp1.add(query);
		jp1.add(new JLabel());
		
		JPanel jp2=new JPanel();
		jp2.setLayout(new GridLayout(2, 1,20,30));
		jp2.add(jp);
		jp2.add(jp1);
		
		container.add(jp2,BorderLayout.NORTH);
		
		JTextArea textArea=new JTextArea();
		query.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<List<String>>list=graph.calcShortestPath(wordName1.getText(), wordName2.getText());
				StringBuilder format=new StringBuilder();
				if(wordName2.getText().equals("")) {
					for(int i=0;i!=list.size();i++) {
						for(int j=0;j!=list.get(i).size();j++) {
							format.append(list.get(i).get(j)+"\n");
						}
						format.append("\n");
					}
				}else {
					for(int i=0;i!=list.get(0).size();i++) {
						format.append(list.get(0).get(i)+"\n");
					}					
				}
				textArea.setText(format.toString());
			}
		});
		textArea.setEditable(false);
		JScrollPane js=new JScrollPane(textArea);
		
		container.add(js, BorderLayout.CENTER);
		setVisible(true);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		setBounds(screenSize.width/3, screenSize.height/3,500,400);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[]args) {

	}
}
