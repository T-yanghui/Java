import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SimpleChatClient
{
    JTextArea incoming;
    JTextField outgoing;
    BufferedReader reader;
    PrintWriter writer;
    Socket sock;

    public void go() {
        final JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel(){
            public void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon("D:\\Code\\Java\\SimpleChatClient\\src\\back.jpg");
                g.drawImage(icon.getImage(), 0, 0, 650,600,frame);
            }
        };

        Font bigFont = new Font("serif",Font.BOLD,20);
        incoming = new JTextArea(15, 50);
        //incoming.setOpaque(false);
        incoming.setFont(bigFont);
        incoming.setSelectionColor(Color.darkGray);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane qScroller = new JScrollPane(incoming);
        //qScroller.setOpaque(false);
        //qScroller.getViewport().setOpaque(false);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        outgoing = new JTextField(20);
        outgoing.setSelectionColor(Color.darkGray);
        //outgoing.setOpaque(false);
        outgoing.setFont(bigFont);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(qScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        setUpNetworking();

        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();

        frame.setSize(800,600);
        frame.setDefaultCloseOperation(TestFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private void setUpNetworking() {
        try {
            sock = new Socket("158.247.203.185", 5000);
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("networking established");
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            try {
                writer.println(outgoing.getText());
                writer.flush();

            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    public static void main(String[] args) {
        new SimpleChatClient().go();
    }

    class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("client read " + message);
                    incoming.append(message + "\n");
                }
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}

