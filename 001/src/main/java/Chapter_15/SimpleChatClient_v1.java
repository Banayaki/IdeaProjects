package Chapter_15;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleChatClient_v1 {
    JTextField outgoing;
    PrintWriter writer;
    Socket socket;

    public void go(){
        JFrame frame = new JFrame("Simple chat ALPHA");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send message");
        sendButton.addActionListener(new ButtonListener());
        panel.add(outgoing);
        panel.add(sendButton);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        setUpNetworking();
        frame.setSize(500,200);
        frame.setVisible(true);
    }

    private void setUpNetworking(){
        try{
            socket = new Socket("192.168.0.1", 5000);
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("Network ON");
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                writer.println(outgoing.getText());
                writer.flush();
            } catch (Exception ex) {ex.printStackTrace();}
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    public static void main(String[] args) {
        SimpleChatClient_v1 chat = new SimpleChatClient_v1();
        chat.go();
    }
}
