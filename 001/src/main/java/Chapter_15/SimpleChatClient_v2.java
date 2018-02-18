package Chapter_15;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class SimpleChatClient_v2 {

    JTextArea incoming;
    JTextField outgoing;
    BufferedReader reader;
    PrintWriter writer;
    Socket sock;

    public static void main(String[] args) {
        SimpleChatClient_v2 client_v2 = new SimpleChatClient_v2();
        client_v2.go();
    }

    public void go(){

        JFrame frame = new JFrame("Simple chat client");
        JPanel panel = new JPanel();
        incoming = new JTextArea(15,50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send message");
        sendButton.addActionListener(new buttonListener());
        panel.add(qScroller);
        panel.add(outgoing);
        panel.add(sendButton);
        setUpNetworking();

        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(600,500);
        frame.setVisible(true);
    }

    private void setUpNetworking(){
        try{
            sock = new Socket("192.168.0.104", 5000);
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("Network is on");
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    public class buttonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                writer.println(outgoing.getText());
                writer.flush();
            } catch (Exception ex) { ex.printStackTrace(); }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    public class IncomingReader implements Runnable{
        @Override
        public void run() {
            String message;
            try {
                while((message = reader.readLine()) !=null) {
                    incoming.append(message + "\n");
                    System.out.println("read " + message);
                }
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    }
}
