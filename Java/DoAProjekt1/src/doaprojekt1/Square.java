/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doaprojekt1;

/**
 *
 * @author lonnroka
 */
public class Square {
    Letter letter;
    
    Square(Letter l){
        letter = l;
    }
    
    //Returnerar en läslig version av det som finns i rutan
    public String getOutput(){
        String output = letter.letter+letter.points;
        return output;
    }
}
