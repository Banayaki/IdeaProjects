import java.io.*;
import java.net.*;
import java.util.*;

public class MusicServer {

    ArrayList<ObjectOutputStream> clientOutStreams;

    public static void main(String[] args) {
        new MusicServer().go();

    }

    public class ClientHandler implements Runnable {

        ObjectInputStream in;
        Socket clientSocket;

        public ClientHandler(Socket socket){
            try {
                clientSocket = socket;
                in = new ObjectInputStream(clientSocket.getInputStream());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            Object o2 = null;
            Object o1 = null;
            try {
                while ((o1 = in.readObject()) != null) {
                    o2 = in.readObject();
                    System.out.println("Read two objects");
                    tellEveryone(o1, o2);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void go(){
        clientOutStreams = new ArrayList<ObjectOutputStream>();
        try {
            ServerSocket serverSocket = new ServerSocket(4242);
            while (true){
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                clientOutStreams.add(out);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("Got a connection");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void tellEveryone(Object one, Object two){
        Iterator it = clientOutStreams.iterator();
        while (it.hasNext()){
            try {
                ObjectOutputStream out = (ObjectOutputStream) it.next();
                out.writeObject(one);
                out.writeObject(two);
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
