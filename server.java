import java.io.*;
import java.net.*;
public class server {
    ServerSocket server = null;
    Socket socketClient = null;
    Socket socketClient2 = null;
    int [][] matrice = new int [3][3];
    int port = 6969;
    public Socket attendi() throws IOException
    {
        System.out.println("inizializzo  server..");
        server = new ServerSocket(port); 
        System.out.println("server inizializzato su porta "+port);
        socketClient = server.accept();//mi metto in ascolto sulla porta
        System.out.println("connessione stabilita con client1");
        
   
        return socketClient;
    }
    public Socket attendi2() throws IOException
    {
        socketClient2 = server.accept();
        System.out.println("connessione stabilita con client2");
        return socketClient;
   
    }
    public void comunica() throws IOException, ClassNotFoundException
    {
        System.out.println("ci provo almeno");

        InputStream in = socketClient.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(in);
        OutputStream out = socketClient.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        matrice= (int[][]) ois.readObject();
        System.out.println("matrice ricevuta..");
        print();
        
    
    }
    public void print()
{
    for (int[] row : matrice) {
        for (int value : row) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
    
    public static void main (String[] args) throws ClassNotFoundException, IOException
    {
        server s = new server();
        s.attendi();
        s.comunica();
    }

}
