import java.io.*;
import java.net.*;
public class client1{
    Socket socketClient = new Socket();
    static String id = "client1";
    static int [][] matrice = new int [3][3];

    int port = 6969;
    public Socket connetti() throws UnknownHostException, IOException
    {
            System.out.println(id + ": provo a connettermi..");
            socketClient = new Socket(InetAddress.getLocalHost(), port);
            System.out.println(id + ": connessione riuscita");
    
        return socketClient;
    }
    public void comunica() throws IOException
    {
       
        OutputStream out = socketClient.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(matrice);
        System.out.println(id + ": matrice inviata");

       
    }
   
    public int[][] ciuccia(int[][] ma)
    {
        for (int a=0; a<3;a++) {
            for (int b=0; b<3; b++) {
            matrice[a][b]= ma [a][b];
            }
        }
        System.out.println("matrice ciucciata");
        return matrice;
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
}

