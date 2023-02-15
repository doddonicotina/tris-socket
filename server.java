import java.io.*;
import java.net.*;
public class server {
    ServerSocket server = null;
    Socket socketClient = null;
    Socket socketClient2 = null;
    DataOutputStream out;
    InputStream inputStream;

    int [][] matrice = new int [3][3];
    ObjectInputStream objectInputStream;
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
       // socketClient2 = server.accept();
       // System.out.println("connessione stabilita con client2");
        
        i=+1;
        

        inputStream = socketClient.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
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
        matrice = (int[][]) objectInputStream.readObject();
        System.out.println("matrice ricevuta..");
        printamela();
        
        /* 
        InputStream in = socketClient.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(in);
        matrice= (int[][]) ois.readObject();
        System.out.println("matrice ricevuta..");

        ois.close();
        */
    }
    public static int[][] convertiByteInMatrice(byte[] dati) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(dati);
        ObjectInputStream ois = new ObjectInputStream(bis);
        int[][] matrice = (int[][]) ois.readObject();
        ois.close();
        return matrice;
    }
    public void printamela()
    {
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
        System.out.println(matrice[i][j]);


            }
        }

    }
    
    public static void main (String[] args) throws ClassNotFoundException, IOException
    {
        server s = new server();
        s.attendi();
        s.comunica();
    }

}
