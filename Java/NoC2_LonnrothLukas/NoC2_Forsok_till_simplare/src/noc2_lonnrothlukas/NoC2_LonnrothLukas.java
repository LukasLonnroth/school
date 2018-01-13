//Main där nästan alla moment körs



package noc2_lonnrothlukas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Lukas
 */
public class NoC2_LonnrothLukas {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String[] alter = {"Nytt Spel", "Fortsätt på gammalt spel", "Avsluta"};
        int svar = JOptionPane.showOptionDialog(null, "Välj ett alternativ", null, 1, 3, null, alter, alter[0]);//Fråga om man vill starta ett nytt spel eller fortsätta
        boolean felinput = true;
        boolean mellanraportbool = true;
        boolean spela = true;

        if (svar == 0) {
            try {
                int deltagare = Integer.parseInt(JOptionPane.showInputDialog("Ange antal deltagare i heltal"));
                Spelare ref = new Spelare();

                Spelare[] spelarLista = new Spelare[deltagare];

                for (int i = 0; i < deltagare; i++) {

                    int spelarnr = i + 1;
                    String namn = JOptionPane.showInputDialog("Ange namn på spelare " + spelarnr + ":");
                    String forening = JOptionPane.showInputDialog("Ange förening:");
                    int insats = Integer.parseInt(JOptionPane.showInputDialog("Ange insats:"));

                    spelarLista[i] = new Spelare(namn, forening, insats);
                }

                int totsumma = (deltagare) * 100;
                System.out.println("Den totala summan är " + totsumma);
                

                while (mellanraportbool = true) {
                    String[] alternativ = {" Ny runda ", " Mellanraport ", " Avsluta eller spara "};
                    int svarsalternativ = JOptionPane.showOptionDialog(null, "Välj ett alternativ", null, 1, 3, null, alternativ, alternativ[0]);//Fråga om man vill starta ett nytt spel eller fortsätta

                    //tar in resultat från en runda
                    if (svarsalternativ == 0) {
                        felinput = true;
                        //whileloopen gör att nyrunda börjar om så man inte måste börja från början
                        while (felinput == true) {
                            //kolla för fel input
                            try {
                                int totalresultat = 0;

                                // tar in rundans resultat från varje spelare
                                for (int i = 0; i < deltagare; i++) {
                                    String spelarnamn = spelarLista[i].getNamn();
                                    String spelarforening = spelarLista[i].getForening();

                                    String rundresultat = JOptionPane.showInputDialog("Ange rundans resultat för " + spelarnamn + " som representerar " + "'" + spelarforening + "':");
                                    int rundresultatint = Integer.parseInt(rundresultat);
                                    spelarLista[i].setInsats(rundresultatint);
                                    totalresultat = totalresultat + rundresultatint;
                                }

                                if (totalresultat != totsumma) {
                                    JOptionPane.showMessageDialog(null, "Någon måste ha angett fel resultat!" + "\n" + " (för mycket eller för lite spelmarker i spel enligt första input!)");
                                }
                                felinput = false;
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Fel input");
                                felinput = true;
                            }
                        }
                    } else if (svarsalternativ == 1) {
                        for (int i = 0; i < spelarLista.length; i++) {
                            JOptionPane.showMessageDialog(null, spelarLista[i] + "\n");

                        }
                    } else {
                        String[] sparaavsluta = {" Spara ", " Avsluta "};
                        int sparaavslutaval = JOptionPane.showOptionDialog(null, "Välj ett alternativ", null, 1, 2, null, sparaavsluta, sparaavsluta[0]);

                        if (sparaavslutaval == 0) {
                            //här skrivs till fil
                            String namnforeninginsats = "";
                            for (int i = 0; i < deltagare; i++) {
                                namnforeninginsats = namnforeninginsats + spelarLista[i].getNamn() + "," + spelarLista[i].getForening() + "," + Integer.toString(spelarLista[i].getInsats()) + ":";

                            }
                            try {
                                FileWriter fw = new FileWriter("Spel.txt");
                                PrintWriter pw = new PrintWriter(fw);
                                pw.println(namnforeninginsats);

                                pw.close();

                            } catch (IOException e) {
                                System.out.println("ERROR!");
                            }
                            JOptionPane.showMessageDialog(null, "Ditt spel har sparats!");
                            System.exit(0);
                        } else {
                            System.exit(0);
                        }
                    }
                }
            
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Fel input");
            }
        
        } 

        //om man väljer gammalt spel
        else if (svar == 1) {
            
            //läser från fil
            FileReader file = new FileReader("Spel.txt");
            BufferedReader reader = new BufferedReader(file);
            
            String text = "";
            String line = reader.readLine();
            while (line != null){
                text += line;
                line = reader.readLine();
            }
            
            String[] inData = text.split(":");
            Spelare[] spelarLista = new Spelare[inData.length];
            
            for (int i = 0; i < inData.length; i++){
                String[] inlastobj = inData[i].split(",");
                
                spelarLista[i] = new Spelare(inlastobj[0], inlastobj[1], Integer.parseInt(inlastobj[2]));
            }
            for (int i = 0; i < spelarLista.length; i++) {
                JOptionPane.showMessageDialog(null, spelarLista[i] + "\n");
            }
            
            int totsumma = inData.length * 100;
            
            
            //Samma som ovanför (skulle kunna göras till metod)
            while (mellanraportbool = true) {
                String[] alternativ = {" Ny runda ", " Mellanraport ", " Avsluta eller spara "};
                int svarsalternativ = JOptionPane.showOptionDialog(null, "Välj ett alternativ", null, 1, 3, null, alternativ, alternativ[0]);//Fråga om man vill starta ett nytt spel eller fortsätta

                //tar in resultat från en runda
                if (svarsalternativ == 0) {
                    felinput = true;
                    //whileloopen gör att nyrunda börjar om så man inte måste börja från början
                    while (felinput == true) {
                        //kolla för fel input
                        try {
                            int totalresultat = 0;

                            // tar in rundans resultat från varje spelare
                            for (int i = 0; i < inData.length; i++) {
                                String spelarnamn = spelarLista[i].getNamn();
                                String spelarforening = spelarLista[i].getForening();

                                String rundresultat = JOptionPane.showInputDialog("Ange rundans resultat för " + spelarnamn + " som representerar " + "'" + spelarforening + "':");
                                int rundresultatint = Integer.parseInt(rundresultat);
                                spelarLista[i].setInsats(rundresultatint);
                                totalresultat = totalresultat + rundresultatint;
                            }

                            if (totalresultat != totsumma) {
                                JOptionPane.showMessageDialog(null, "Någon måste ha angett fel resultat!" + "\n" + " (för mycket eller för lite spelmarker i spel enligt första input!)");
                            }
                            felinput = false;
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Fel input");
                            felinput = true;
                        }
                    }
                } else if (svarsalternativ == 1) {
                    for (int i = 0; i < spelarLista.length; i++) {
                        JOptionPane.showMessageDialog(null, spelarLista[i] + "\n");

                    }
                } else {
                    String[] sparaavsluta = {" Spara ", " Avsluta "};
                    int sparaavslutaval = JOptionPane.showOptionDialog(null, "Välj ett alternativ", null, 1, 2, null, sparaavsluta, sparaavsluta[0]);

                    if (sparaavslutaval == 0) {
                        //här skrivs till fil
                        String namnforeninginsats = "";
                        for (int i = 0; i < inData.length; i++) {
                            namnforeninginsats = namnforeninginsats + spelarLista[i].getNamn() + "," + spelarLista[i].getForening() + "," + Integer.toString(spelarLista[i].getInsats()) + ":";

                        }
                        try {
                            FileWriter fw = new FileWriter("Spel.txt");
                            PrintWriter pw = new PrintWriter(fw);
                            pw.println(namnforeninginsats);

                            pw.close();

                        } catch (IOException e) {
                            System.out.println("Kan inte hitta filen du söker. \n Är du säker på att du har sparat ett spel?");
                        }
                        JOptionPane.showMessageDialog(null, "Ditt spel har sparats!");
                        System.exit(0);
                    } else {
                        System.exit(0);
                    }
                    }
                }

        } 
        
        //om man trycker på 'x' eller avsluta stängs programmet
        else {
            System.exit(0);
        }
    }

}
