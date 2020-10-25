import javax.swing.*;
import  java.awt.event.*;
import  java.awt.*;

public class SimpleButton {
   JFrame frame;
   JButton b;
   Image image;


   public static void main(String [] args){
       SimpleButton gui = new SimpleButton();
       gui.go();
   }
   public void go(){
       frame = new JFrame("SimpleButton");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       b = new JButton("A");
       b.addActionListener(new BListener());

       frame.add(BorderLayout.SOUTH,b);
       frame.setSize(300,300);
       frame.setVisible(true);
   }

   class BListener implements ActionListener{
       public void actionPerformed(ActionEvent e){
           if (b.getText().equals("A")){
               b.setText("B");
           }else {
               b.setText("A");
           }
       }
   }
}

