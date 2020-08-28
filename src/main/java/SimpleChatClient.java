import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleChatClient {
    JTextField outgoing;
    PrintWriter printWriter;
    Socket socket;

    public void go(){
        JFrame jFrame= new JFrame("Simple chat client");
        JPanel jPanel=new JPanel();
        outgoing= new JTextField(20);

        JButton button= new JButton("Send");
        button.addActionListener(new SendButtonListener());

        jPanel.add(outgoing);
        jPanel.add(button);
        jFrame.getContentPane().add(BorderLayout.CENTER,jPanel);
        setUpWorking();
        jFrame.setSize(400,500);
        jFrame.setVisible(true);
    }

    private void setUpWorking(){
        try {
            socket= new Socket("127.0.0.1",5000);
            printWriter=new PrintWriter(socket.getOutputStream());
            System.out.println("networking established");
        }catch (IOException ex){
            ex.getStackTrace();
        }
    }
    public  class SendButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent ex){
            try {
                printWriter.println(outgoing.getText());
                printWriter.flush();
            }catch (Exception exc){
                exc.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    public static void main(String[] args) {
        new SimpleChatClient().go();
    }
}
