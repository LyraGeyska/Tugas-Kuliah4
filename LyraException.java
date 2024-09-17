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
public class LyraException extends ArithmeticException {

    public LyraException(String message) {
        super(message);
    }

    public static void cekAngka(int angka) throws LyraException {
        if (angka < 0) {
            // Jika angka negatif, lemparkan custom exception
            throw new LyraException("Anda memasukkan bilangan negatif!");
        }
        System.out.println("Angka valid: " + angka);
    }

    public static void main(String[] args) {
        // TODO code application logic here

        int angka = 10; // Angka negatif
        try {
            cekAngka(angka);
        } catch (LyraException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}
