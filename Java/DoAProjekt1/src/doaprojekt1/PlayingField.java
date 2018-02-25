/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doaprojekt1;

/**
 *
 * @author lukas
 */
public class PlayingField {
    Square[][] squares = new Square[15][15];
    
    //Konstruerar en spelplan på 15x15 rutor och fyller tomma rutor med # och 0 poäng
    PlayingField(){
        Letter emptySquare = new Letter("#", 0);
        for(int i = 0; i < squares.length; i++){
            for(int j = 0; j < squares.length; j++){
                squares[i][j] = new Square(emptySquare);
            }
        }
    }
    
    //printar spelplanen till en läslig matris
    public void printPlayingfield(Square[][] pf) {
        try {
            int rows = pf.length;
            int columns = pf[0].length;
            String str = "| ";

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    str += pf[i][j].getOutput() + " ";
                }

                System.out.println(str + " |");
                str = "| ";
            }

        } catch (Exception e) {
            System.out.println("Matrix is empty!!");
        }
    }
    
    //printar spelplanen till en läslig matris
    public String stringPlayingfield(Square[][] pf) {
        String playingFieldString = "";
        try {
            int rows = pf.length;
            int columns = pf[0].length;
            String str = "| ";
            

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    str += pf[i][j].getOutput() + " | ";
                }

                playingFieldString += str + "<br>";
                str ="| ";
            }

        } catch (Exception e) {
            System.out.println("Matrix is empty!!");
        }
        return playingFieldString;
    }
}
