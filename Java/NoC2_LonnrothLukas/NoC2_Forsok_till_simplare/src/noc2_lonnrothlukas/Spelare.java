/**
 * Klassen 'Spelare'
 * klassen spelare där objekten spelare 
 * skapas samt metoder för att få
 * eller ändra specifik info om spelaren
 * finns('get/set'- metoderna).
 *
 */



package noc2_lonnrothlukas;

/**
 *
 * @author Lukas
 */
public class Spelare {
    private String namn;
    private String forening;
    private int insats;
    
    public Spelare(){
        
    }
    
    public Spelare(String namn, String forening, int insats){
        this.namn = namn;
        this.forening = forening;
        this.insats = insats;
    }

    /**
     * @return the namn
     */
    public String getNamn() {
        return namn;
    }

    /**
     * @param namn the namn to set
     */
    public void setNamn(String namn) {
        this.namn = namn;
    }

    /**
     * @return the forening
     */
    public String getForening() {
        return forening;
    }

    /**
     * @param forening the forening to set
     */
    public void setForening(String forening) {
        this.forening = forening;
    }

    /**
     * @return the insats
     */
    public int getInsats() {
        return insats;
    }

    /**
     * @param insats the insats to set
     */
    public void setInsats(int insats) {
        this.insats = insats;
    }
    
    
    //gör att man printar det som finn i objektet istället för "hash"-koden.
    @Override
    public String toString(){
        return "[Namn: "+ namn + ", Förening: "+ forening + ", Insats: " + insats + " ]";
    }
}
