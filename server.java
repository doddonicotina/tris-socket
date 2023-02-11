import java.io.*;
import java.net.*;
public class server {
    ServerSocket server = null;
    Socket socketClient = null;
    Socket socketClient2 = null;
    DataOutputStream out;
 
    int [][] matrice;
    ObjectInputStream in;
    ObjectInputStream in1;
    int port = 6969;
    int i = 0;

    public Socket attendi()
    {
        try
        {
        System.out.println("inizializzo  server..");
        server = new ServerSocket(port); 
        System.out.println("server inizializzato su porta "+port);
        socketClient = server.accept();//mi metto in ascolto sulla porta
        System.out.println("connessione stabilita con client1");
        socketClient2 = server.accept();
        System.out.println("connessione stabilita con client2");
        
        i=+1;
        


        in = new ObjectInputStream(socketClient.getInputStream());
        in1 = new ObjectInputStream(socketClient2.getInputStream());

        
        out = new DataOutputStream(socketClient.getOutputStream());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return socketClient;
    }
    public void comunica() throws IOException, ClassNotFoundException
    {
        InputStream in = socketClient.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(in);
        matrice= (int[][]) ois.readObject();
        System.out.println("matrice ricevuta..");

        ois.close();
    }
    public static int[][] convertiByteInMatrice(byte[] dati) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(dati);
        ObjectInputStream ois = new ObjectInputStream(bis);
        int[][] matrice = (int[][]) ois.readObject();
        ois.close();
        return matrice;
    }
    
    public static void main (String[] args) throws ClassNotFoundException, IOException
    {
        server s = new server();
        s.attendi();
        s.comunica();
    }

}
