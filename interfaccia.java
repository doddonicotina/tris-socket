import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.plaf.InsetsUIResource;  

public class interfaccia extends JFrame{
    static ImageIcon croce = new ImageIcon ("croce.png");
    static ImageIcon cerchio = new ImageIcon ("cerchio.png");
    static ImageIcon doddo = new ImageIcon ("doddo.png");
    static int [][] matrice = new int [3][3];
    static JButton[][] matriceb = new JButton[3][3];
    client1 c = new client1();

    static int[] coordinates ;



    public interfaccia() {
        Insets buttonmargin = new InsetsUIResource(0, 0, 0, 0);
        JFrame frame = new JFrame ("Tris");
                for (int a=0; a<3;a++) {
                    for (int b=0; b<3; b++) {
                        matrice[a][b]=-1;
                        matriceb[a][b]= new JButton();
                        matriceb[a][b].setBackground(Color.white);
                        matriceb[a][b].setMargin(buttonmargin);
                        frame.add(matriceb[a][b]);
                    }
                }
                for (int x=0; x<3;x++) {
                    for (int y=0; y<3; y++) {
                        int a = x;
                        int b = y;
                    matriceb[x][y].addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {  
                            if (true)
                            {
                                matriceb[a][b].setIcon(croce);
                                matriceb[a][b].setText("");
                                matrice[a][b]=1;
                                client1 client = new client1();
                                try{
                                    client.connetti();
                                    client.comunica();
                                } catch (IOException ex){
                                    System.out.println("Errore durante l'invio della matrice");
                                }
        
        
                            }
                            else if (true)
                            {
                                matriceb[a][b].setText("");
                                matriceb[a][b].setIcon(cerchio);
                                matrice[a][b]=0;
                            }           
                            if (isThereaTris(matrice))
                            {
                                coordinates = whereIstheTris(matrice);
                                matriceb[coordinates[0]][coordinates[1]].setIcon(doddo);
                                matriceb[coordinates[2]][coordinates[3]].setIcon(doddo);
                                matriceb[coordinates[4]][coordinates[5]].setIcon(doddo);
                            }               
                        } 
                    });
                    
                    }
        frame.setVisible(true);
        frame.setSize(900,900); 
        frame.setLayout(new GridLayout(3,3));  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    

    }
}
    public void createClient(String id)
    {
        if (id=="Client1")
        {
        c.connetti();
        
        }
    }/* 
    /*public int[][] game(String id)
    public void game(String id)
    {
       
        for (int x=0; x<3;x++) {
        	for (int y=0; y<3; y++) {
                int a = x;
                int b = y;
            matriceb[x][y].addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {  
                    if (id=="client1")
                    {
                        matriceb[a][b].setIcon(croce);
                        matriceb[a][b].setText("");
                        matrice[a][b]=1;
                        client1 client = new client1();
                        try{
                            client.connetti();
                            client.comunica();
                        } catch (IOException ex){
                            System.out.println("Errore durante l'invio della matrice");
                        }


                    }
                    else if (id=="client2")
                    {
                        matriceb[a][b].setText("");
                        matriceb[a][b].setIcon(cerchio);
                        matrice[a][b]=0;
                    }           
                    if (isThereaTris(matrice))
                    {
                        coordinates = whereIstheTris(matrice);
                        matriceb[coordinates[0]][coordinates[1]].setIcon(doddo);
                        matriceb[coordinates[2]][coordinates[3]].setIcon(doddo);
                        matriceb[coordinates[4]][coordinates[5]].setIcon(doddo);
                    }               
                } 
            });
            
            }

        }/* 
        return matrice;
        
    }*/

    

    public void syncro ()
    {
        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if (matrice[i][j]==1)
                {
                    matriceb[i][j].setText("");
                    matriceb[i][j].setIcon(croce);
                }
                else
                {
                    matriceb[i][j].setText("");
                    matriceb[i][j].setIcon(cerchio);
                }
            }
        }
    }
    public static Boolean isThereaTris(int[][] matrice) {
        //controllo righe
        for(int i = 0; i < 3; i++) {
            if(matrice[i][0] == matrice[i][1] && matrice[i][1] == matrice[i][2] && matrice[i][0] !=-1 && matrice[i][1]  !=-1 && matrice[i][2] != -1 )
                return true;
        }
        //controllo colonne
        for(int i = 0; i < 3; i++) {
            if(matrice[0][i] == matrice[1][i] && matrice[1][i] == matrice[2][i] && matrice[i][0] !=-1 && matrice[i][1]  !=-1 && matrice[i][2] != -1 )
                return true;
        }
        //controllo diagonali
        if(matrice[0][0] == matrice[1][1] && matrice[1][1] == matrice[2][2] && matrice[0][0] !=-1 && matrice[1][1]  !=-1 && matrice[2][2] != -1 )
            return true;
        if(matrice[0][2] == matrice[1][1] && matrice[1][1] == matrice[2][0] && matrice[0][2] !=-1 && matrice[1][1]  !=-1 && matrice[2][0] != -1 )
            return true;
        return false;
    }

    public static int[] whereIstheTris(int[][] matrice) {

        int[] coordinates = new int[6];
        // check rows
        for (int i = 0; i < 3; i++) {
            if (matrice[i][0] == matrice[i][1] && matrice[i][1] == matrice[i][2] && matrice[i][0] != -1) {
                coordinates[0] = i;
                coordinates[1] = 0;
                coordinates[2] = i;
                coordinates[3] = 1;
                coordinates[4] = i;
                coordinates[5] = 2;
                return coordinates;
            }
        }
    
        // check columns
        for (int i = 0; i < 3; i++) {
            if (matrice[0][i] == matrice[1][i] && matrice[1][i] == matrice[2][i] && matrice[0][i] != -1) {
                coordinates[0] = 0;
                coordinates[1] = i;
                coordinates[2] = 1;
                coordinates[3] = i;
                coordinates[4] = 2;
                coordinates[5] = i;
                return coordinates;
            }
        }
    
        // check diagonals
        if (matrice[0][0] == matrice[1][1] && matrice[1][1] == matrice[2][2] && matrice[0][0] != -1) {
            coordinates[0] = 0;
            coordinates[1] = 0;
            coordinates[2] = 1;
            coordinates[3] = 1;
            coordinates[4] = 2;
            coordinates[5] = 2;
            return coordinates;
        }
        if (matrice[0][2] == matrice[1][1] && matrice[1][1] == matrice[2][0] && matrice[0][2] != -1) {
            coordinates[0] = 0;
            coordinates[1] = 2;
            coordinates[2] = 1;
            coordinates[3] = 1;
            coordinates[4] = 2;
            coordinates[5] = 0;
            return coordinates;
        }
        return null;
    

}
public static void main (String[] args) throws IOException
    {
        interfaccia a = new interfaccia();
    }
}