/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TugasKeempat;

import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Throw {
    public void cobaTrows() throws ArithmeticException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan angka: ");
        
        String input = scanner.nextLine();
        int angka = Integer.parseInt(input);
        System.out.println("Angka yang dimasukkan: " + angka);
    }

    public static void main(String[] args) {
        Throw contoh = new Throw();
        
        try {
            contoh.cobaTrows(); // Memanggil metode yang melempar pengecualian
        } catch (NumberFormatException e) {
            // Menangani pengecualian dari metode dengan throws
            System.out.println("Error, penanganan Throws: Anda memasukkan selain angka!!");
        }
    }
    
}
