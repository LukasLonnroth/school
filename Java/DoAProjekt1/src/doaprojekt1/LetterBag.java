/**
 * readFile()
 * Tar in brickor.txt, läser och lägger till brickorna som letter objekt med
 * bokstav och poäng till letters arrayen och returnerar den.
 *
 * (måst ännu fixas: addLetter() och giveLetter() )
 *
 */
package doaprojekt1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lonnroka
 */
public class LetterBag {

    //public ArrayList<Letter> letterList = new ArrayList();
    
    public ArrayList readFile() {
        FileInputStream fileIn = null;
        BufferedReader reader = null;
        ArrayList<Letter> letterList = new ArrayList();
        try {
            fileIn = new FileInputStream("brickor.txt");
            reader = new BufferedReader(new InputStreamReader(fileIn));
            String lineIn = reader.readLine();

            while (lineIn != null) {
                String[] lineInSplit = lineIn.split("\t", 3);
                int n = Integer.parseInt(lineInSplit[2]);
                for(int i = 0; i < n; i++){
                    String l = lineInSplit[0];
                    int p = Integer.parseInt(lineInSplit[1]);
                    Letter letter = new Letter(l, p);
                    letterList.add(letter);
                }
                lineIn = reader.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WordList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return letterList;
    }
    
    //Tar 7 Letters från LetterBag och returnerar dom som en ArrayList tar bort
    //dom från letterbag
    public ArrayList getRow(ArrayList letterbag){
        ArrayList<Letter> row = new ArrayList();
        Random randomGenerator = new Random();
        for(int i = 0; i < 7; i++){
            int index = randomGenerator.nextInt(letterbag.size());
            Letter letter = (Letter) letterbag.get(index);
            row.add(letter);
            letterbag.remove(index);
        }
        return row;
    }

/**
 * public Letter addLetter() {
        Letter l;
        return l;
    }
*/
    
    public Letter giveLetter(ArrayList letterbag) {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(letterbag.size());
        Letter l = (Letter) letterbag.get(index);
        letterbag.remove(index);
        return l;
    }
}
