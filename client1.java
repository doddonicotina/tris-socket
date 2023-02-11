import java.io.*;
import java.net.*;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
public class client1{
    Socket socketClient = new Socket();
    static String id = "client1";
    static int [][] matrice;
    ObjectOutputStream out = null;

    DataInputStream in;
    String mess;

    int port = 6969;
    BufferedReader keyboard;
    public Socket connetti()
    {
        try
        {
            System.out.println(id+":provo a connettermi..");

            Socket socketClient = new Socket(InetAddress.getLocalHost(),port);
            System.out.println(id+":connessione riuscita");


            in = new DataInputStream(socketClient.getInputStream());
            out = new ObjectOutputStream(socketClient.getOutputStream());

        }
        catch (UnknownHostException e)
        {
            System.out.println(id+":host sconosciuto");
        }
        catch(IOException e)
        {
            System.err.println(id+":aiutoooooooo");
        }
        return socketClient;
    }
    public void comunica() throws IOException
    {
        OutputStream out = socketClient.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(matrice);
        oos.close();
        socketClient.close();
        System.out.println("invio byte al server..");
        /* out.write(convertIntMatrixToByteArray(in.read(null)));
        System.out.println("invio riuscito");*/



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
        client1 client = new client1();
        client.connetti();
        interfaccia a = new interfaccia();
        matrice = a.game(id);
        client.comunica();
        
    }
}

