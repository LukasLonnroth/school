/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doaprojekt1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lonnroka
 */
public class WordList {

    WordList() {

    }

    //Hämtar orden från ordlista.txt och lägger dem som enskilda strängar i en ArrayList
    public ArrayList getWordList() {
        FileInputStream fileIn = null;
        BufferedReader reader = null;
        ArrayList<String> wordList = new ArrayList();
        try {
            fileIn = new FileInputStream("ordlista.txt");
            reader = new BufferedReader(new InputStreamReader(fileIn));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                wordList.add(line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WordList.class.getName()).log(Level.SEVERE, null, ex);
        }

        return wordList;
    }
}
