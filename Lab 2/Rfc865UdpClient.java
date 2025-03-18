import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Rfc865UdpClient {
    public static void main(String[] argv) throws SocketException {
        // 1. Open UDP Socket
        DatagramSocket socket = new DatagramSocket();
        
        try {
            // Get client IP address
            String clientIP = socket.getLocalAddress().getHostAddress();
            
            // Define server address and port
            InetAddress address = InetAddress.getByName("10.96.181.175");
            int serverPort = 17;  // Confirm the server is listening on this port

            // 2. Send UDP Request to Server
            String message = "ok hello";
            byte[] buffer = message.getBytes();
            DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, serverPort);
            socket.send(request);

            // 3. Receive UDP Reply from Server
            buffer = new byte[65535]; // Maximum UDP packet size
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            socket.receive(reply);

            // Print received message
            String received = new String(reply.getData(), 0, reply.getLength());
            System.out.println("Server Response: " + received);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Closing Socket...");
            socket.close();
        }
    }
}