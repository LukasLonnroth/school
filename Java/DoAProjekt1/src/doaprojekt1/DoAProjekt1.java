/*
 * Main startar wordgame() som sköter spelets gång
 * 
 */
package doaprojekt1;

import java.awt.FlowLayout;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author lonnroka
 */
public class DoAProjekt1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        wordgame();
    }

    public static void wordgame() {
        //Tar in ordlistan och påsen med bokstäver
        WordList wl = new WordList();
        ArrayList<String> wordList = wl.getWordList();
        LetterBag lb = new LetterBag();
        ArrayList<Letter> letterbag = lb.readFile();
        Scanner in = new Scanner(System.in);

        //Tar in spelarens namn
        System.out.println("Enter your name:");
        String playerName = in.next();

        //Hämtar en rad på 7 bokstäver åt spelaren
        ArrayList<Letter> row = lb.getRow(letterbag);
        
        //JFrame fönster
        JFrame window = new JFrame("Wordgame!");
        window.setVisible(true);
        window.setSize(500, 320);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new FlowLayout());
        JLabel windowpf = new JLabel();
        window.add(windowpf);
        JLabel windowrow = new JLabel();
        window.add(windowrow);
        /**
        JButton endTurn = new JButton("End turn");
        JButton exitGame = new JButton("Exit game");
        window.add(endTurn);
        window.add(exitGame);
        */

        //Printar spelplanen och låter spelaren göra sitt drag bokstav för bokstav
        boolean newTurn = true;
        PlayingField pf = new PlayingField();
        while (newTurn) {
            String rowString = "";
            System.out.println("\n" + playerName + ", here is your row:");
            for (int i = 0; i < row.size(); i++) {
                System.out.print(row.get(i).letter + row.get(i).points + " ");
                rowString += row.get(i).letter + row.get(i).points + " ";
            }
            System.out.println("\n\nMake your move:");
            
            //printar spelplanen till terminalen
            pf.printPlayingfield(pf.squares);
            
            //printar spelplanen och raden till fönstret
            windowpf.setText("<html>"+pf.stringPlayingfield(pf.squares)+"</html>");
            windowrow.setText(rowString);

            
            boolean turnfinished = false;

            if (letterbag.isEmpty()) {
                newTurn = false;
            }

            while (!turnfinished) {
                System.out.println("\nWich letter would you like to place? "
                        + "Type 'end' to end your turn. Type 'exit' to end the game");
                String letterIn = in.next().toUpperCase();
                boolean letterExists = false;
                int index = 0;

                for (int i = 0; i < row.size(); i++) {
                    String checkLetter = row.get(i).letter;
                    if (letterIn.equals(checkLetter)) {
                        letterExists = true; //bokstaven finns i raden
                        index = i;
                    }
                }

                if (letterExists) {
                    //om bokstaven finns låter man spelaren placera den och
                    //tar bort den från raden.
                    System.out.println("\nWhere would you like to place " + letterIn + "?");
                    System.out.println("Enter x coordinates (1-15)\u2192:");
                    int xIn = in.nextInt() - 1;
                    System.out.println("Enter y coordinates (1-15)\u2193:");
                    int yIn = in.nextInt() - 1;
                    if (xIn < 15 && xIn > -1 | yIn < 15 && yIn > -1) {
                        pf.squares[yIn][xIn] = new Square(row.get(index));
                        row.remove(index);
                    } else {
                        System.out.println("1-15");
                    }
                    System.out.println("\n" + playerName + ", here is your row:");
                    String newRow="";
                    for (int i = 0; i < row.size(); i++) {
                        System.out.print(row.get(i).letter + row.get(i).points + " ");
                        newRow += row.get(i).letter + row.get(i).points + " ";
                    }
                    System.out.println("\n");
                    pf.printPlayingfield(pf.squares);
                    windowpf.setText("<html>"+pf.stringPlayingfield(pf.squares)+"</html>");
                    windowrow.setText(newRow);
                } else if ("END".equals(letterIn)) {
                    turnfinished = true; //spelaren är klar med sitt drag
                } else if ("EXIT".equals(letterIn)) {
                    turnfinished = true;
                    newTurn = false;
                } else {
                    System.out.println("\nCan not find the given letter in "
                            + "your row. Are you sure you typed the right one?");
                    //Fel bokstav angiven = loopen börjar om
                }
            }

            //Kolla giltigheten på draget (lodrätt/vågrätt)
            //Jämför lagda ord med ordlistan
            String pfWords = "#";
            for (int i = 0; i < pf.squares.length; i++) {
                for (int j = 0; j < pf.squares.length; j++) {
                    pfWords = pfWords + pf.squares[i][j].getOutput();
                }
                pfWords = pfWords + ",";
            }
            for (int i = 0; i < pf.squares.length; i++) {
                for (int j = 0; j < pf.squares.length; j++) {
                    pfWords = pfWords + pf.squares[j][i].getOutput();
                }
                pfWords = pfWords + ",";
            }
            String replaceAll = pfWords.replaceAll("#", "");
            replaceAll = replaceAll.replaceAll("\\d", "");
            String[] words = replaceAll.replaceAll("\\s", null).split(",");
            ArrayList<String> realWords = new ArrayList();
            for (int i = 0; i < words.length; i++) {
                if (words[i].length() > 1) {
                    realWords.add(words[i]);
                }
            }
            for (int i = 0; i < realWords.size(); i++) {
                if (!wordList.contains(realWords.get(i))) {
                    System.out.println(realWords.get(i) + " is not a real word!!!");
                }
            }
            //Spelaren får nya brickor så att den har 7
            while (row.size() < 7) {
                row.add(lb.giveLetter(letterbag));
            }
        }

        //Beräkna totala poäng
        int totPoints = 0;
        for (int i = 0; i < pf.squares.length; i++) {
            for (int j = 0; j < pf.squares.length; j++) {
                totPoints += pf.squares[i][j].letter.points;
            }
        }
        System.out.println("\nCongratulations " + playerName + " your total points: " + totPoints);

        
        
        //Skriver poängen till 'scores.txt'
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("scores.txt", true);
            bw = new BufferedWriter(fw);
            bw.write(playerName + "  " + totPoints);
            bw.newLine();
        } catch (IOException e) {
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();

            }

        }
        
        JOptionPane.showMessageDialog(null ,"Congratulations " + playerName + " your total points: " + totPoints );
        System.exit(0);
    }

}
