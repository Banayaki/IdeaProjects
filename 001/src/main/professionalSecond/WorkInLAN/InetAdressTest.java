package main.professionalSecond.WorkInLAN;

import java.io.IOException;
import java.net.InetAddress;

public class InetAdressTest {

    public static void main(String[] args) throws IOException{

        if (args.length > 0) {
            String host = args[0];
            InetAddress[] addresses = InetAddress.getAllByName(host);
            for (InetAddress a : addresses) {
                System.out.println(a);
            }
        }
        else {
            InetAddress localHostAddress = InetAddress.getLocalHost();
            System.out.println(localHostAddress);
        }
    }
}
