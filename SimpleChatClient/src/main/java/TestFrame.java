import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 在JTextArea中显示一个图片背景（背景不随滚动而移动位置）
 *
 * @author 五斗米 <如转载请保留作者和出处>
 * @blog http://blog.csdn.net/mq612
 */
public class TestFrame extends JFrame {

    private static final long serialVersionUID = 4785452373598819719L;

    private JScrollPane sp = null;

    private JTextArea text = null;

    private ImageIcon imageIcon = null;

    public TestFrame() {
        super("Ludicrously Simple Chat Client");
        this.setSize(650, 600);
        imageIcon = new ImageIcon("D:\\Code\\Java\\SimpleChatClient\\src\\back.jpg");
        // 构造文本组件并使之透明
        text = new JTextArea();
        text.setOpaque(false);
        // 构造滚动组件并使之透明
        sp = new JScrollPane(text);
        sp.setOpaque(false);
        sp.getViewport().setOpaque(false);
        // 构造一个背景JPanel
        JPanel backdrop = new JPanel() {
            private static final long serialVersionUID = 1957203784117943458L;
            {
                this.setOpaque(false);
                this.setLayout(new BorderLayout());
            }

            public void paintComponent(Graphics g) {
                g.drawImage(imageIcon.getImage(), 0, 0, this);
                super.paintComponents(g);
            }
        };
        // 将滚动组件加入
        backdrop.add(sp);
        // 将背景组件加入窗体
        this.getContentPane().add(backdrop);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(360, 260);
        this.setVisible(true);
    }
}

