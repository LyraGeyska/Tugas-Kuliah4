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
public class CbException {

    public int pembagian(int a, int b) throws ArithmeticException {
        return a / b;
    }

    public static void main(String[] args) {
        CbException menghitung = new CbException();

        try {
            int hasil = menghitung.pembagian(10, 0);
            System.out.println("Hasil : " + hasil);
        } catch (ArithmeticException e) {
            System.out.println("Error : Tidak boleh ada pembagian oleh 0");
        }
    }
}
