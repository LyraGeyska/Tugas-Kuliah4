/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TugasKeempat;

/**
 *
 * @author HP
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            throw new LyraException("Ini Dari Lyra Exception");
        } catch (LyraException ex) {
            System.out.println("Ditangkap : " + String.valueOf(ex));
        }
    }
}
