package myjava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JDialog;

public class ChoiceDialog extends JDialog {
    private static final long serialVersionUID = 2L;

    public ChoiceDialog(final Graph graph) {
        Container container = getContentPane();
        container.setLayout(new GridLayout(5, 1, 10, 10));
        JButton jb1 = new JButton("展示有向图");
        container.add(jb1);
        jb1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                // TODO Auto-generated method stub
                graph.showDirectedGraph();
                new ShowGraphDialog();
            }
        });
        JButton jb2 = new JButton("查询桥接词");
        container.add(jb2);
        jb2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                // TODO Auto-generated method stub
                new QueryDialog(graph);
            }
        });
        JButton jb3 = new JButton("根据桥接词生成新文本");
        jb3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                // TODO Auto-generated method stub
                new GenerateDialog(graph);
            }
        });
        container.add(jb3);
        JButton jb4 = new JButton("两个单词间的最短路径");
        jb4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                // TODO Auto-generated method stub
                new CalShortestPathDialog(graph);
            }
        });
        container.add(jb4);
        JButton jb5 = new JButton("随机游走");
        jb5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                // TODO Auto-generated method stub
                new RandomDialog(graph);
            }
        });
        container.add(jb5);
        setVisible(true);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setBounds(screenSize.width / 3, screenSize.height / 3, 300, 200);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
