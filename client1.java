import java.io.*;
import java.net.*;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
public class client1{
    Socket socketClient = new Socket();
    static String id = "client1";
    static int [][] matrice = new int [3][3];
    static OutputStream outputStream;
    static ObjectOutputStream objectOutputStream;

    int port = 6969;
    BufferedReader keyboard;
    public Socket connetti()
    {
        try
        {
            System.out.println(id+":provo a connettermi..");

            Socket socketClient = new Socket(InetAddress.getLocalHost(),port);
            System.out.println(id+":connessione riuscita");

            outputStream = socketClient.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);


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
        objectOutputStream.writeObject(matrice);
        System.out.println("matrice inviata..");

        /* 
        OutputStream output = socketClient.getOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(output);
        riempila();
        out.writeObject(matrice);
        out.close();
        socketClient.close();
        System.out.println("matrice inviata..");
         out.write(convertIntMatrixToByteArray(in.read(null)));
        System.out.print*/


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
    public void riempila()
    {
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                matrice[i][j]=1;
            }
        }
        System.out.println("matrice riempita..");
    }
    public static void main (String[] args) throws IOException
    { 
        client1 client = new client1();
        client.connetti();
       // interfaccia a = new interfaccia();
       client.comunica();
       
    }
}

