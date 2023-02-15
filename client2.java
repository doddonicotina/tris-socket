import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.io.ByteArrayOutputStream;
import javax.imageio.stream.IIOByteBuffer;
import java.io.IOException;
import java.io.ObjectOutputStream;
public class client2 {
    Socket socketClient = null;
    static String id = "client2";
    static int[][] matrice;
    ObjectOutputStream out = null;

    diiocane oporco

    DataInputStream in;
    String mess;

    int port = 6969;
    BufferedReader keyboard;

    public Socket connetti()
    {
        try
        {
            System.out.println(id+": provo a connettermi..");

            Socket socketClient = new Socket(InetAddress.getLocalHost(),port);
            System.out.println(id+":connessione riuscita");

            in = new DataInputStream(socketClient.getInputStream());
            out = new ObjectOutputStream(socketClient.getOutputStream());


        }
        catch (UnknownHostException e)
        {
            System.out.println(id+": host sconosciuto");
        }
        catch(IOException e)
        {
            System.err.println(id+":aiutoooooooo");
        }
        return socketClient;
    }

    public void comunica() throws IOException
    {
        System.out.println("invio byte al server..");
        out.write(convertIntMatrixToByteArray());
    }
    public static byte[] convertIntMatrixToByteArray() {
        int rows = matrice.length;
        int cols = matrice[0].length;
        ByteBuffer buffer = ByteBuffer.allocate(rows * cols * 4);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buffer.putInt(matrice[i][j]);
            }
        }
        return buffer.array();
    }
    public static void main (String[] args) throws IOException
    {
        client2 client = new client2();
        client.connetti();
        interfaccia a = new interfaccia();
        matrice = a.game(id);
        client.comunica();
        
    }
}
