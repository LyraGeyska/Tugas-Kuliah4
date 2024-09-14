/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TugasKeempat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class Utama {

    java.sql.Connection conn;
    Statement stmt;
    PreparedStatement pstmt;

    String driver = "org.postgresql.Driver";
    String koneksi = "jdbc:postgresql://localhost:5432/mhs";
    String user = "postgres";
    String password = "lyra123";
    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    BufferedReader input = new BufferedReader(inputStreamReader);

    /**
     * @param args the command line arguments
     */
    public void menu() {
        System.out.println("\n========= MENU UTAMA =========");
        System.out.println("1. Lihat Data");
        System.out.println("2. Tambah Data");
        System.out.println("3. Update Data");
        System.out.println("4. Hapus Data");
        System.out.println("0. Exit");
        System.out.println(" ");
        System.out.print("PILIHAN> ");

        try {
            int pilihan = Integer.parseInt(input.readLine());
            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    tampil();
                    break;
                case 2:
                    tambah();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                default:
                    System.out.println("Nomor Tidak Terdaftar!");

            }
        } catch (IOException ex) {
            Logger.getLogger(Utama.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Tidak ada aktifitas pada nomor tersebut!");
        }

    }

    //READ
    public void tampil() {
        try {
            // TODO code application logi
            Class.forName(driver);
            String sql = "SELECT * FROM mahasiswa";
            conn = DriverManager.getConnection(koneksi, user, password);
            stmt = conn.createStatement();

            while (!conn.isClosed()) {
                ResultSet rs;
                rs = stmt.executeQuery(sql);
                System.out.println("+---------------------------------------------+");
                System.out.println("|            SELURUH DATA MAHASISWA           |");
                System.out.println("+---------------------------------------------+");
                while (rs.next()) {
                    System.out.println(String.valueOf(rs.getObject(1)) + " "
                            + String.valueOf(rs.getObject(2)) + " "
                            + String.valueOf(rs.getObject(3)) + " " + String.valueOf(rs.getObject(4)));
                }
                System.out.println("+---------------------------------------------+");
                conn.close();
            }

            stmt.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Utama.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Utama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //CREATE
    public void tambah() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(koneksi, user, password);
            conn.setAutoCommit(false);
            int k = 0;

            String sql = "INSERT INTO mahasiswa VALUES(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            boolean selesai = false;
            while (!selesai) {
                System.out.println(" ");

                System.out.println("+---------------------------------------------+");
                System.out.println("|           SELURUH DATA MAHASISWA            |");
                System.out.println("+---------------------------------------------+");

                System.out.print("\nMasukkan Data Anda Dibawah!");
                System.out.println(" ");
                System.out.print("\nNIM Mahasiswa: ");
                String nim = input.readLine().trim();
                System.out.print("Nama Mahasiswa: ");
                String nama = input.readLine().trim();
                System.out.print("Alamat: ");
                String alamat = input.readLine().trim();
                System.out.print("Nomor Telepon: ");
                String no_telepon = input.readLine().trim();

                pstmt.setLong(1, Long.parseLong(nim));
                pstmt.setString(2, nama);
                pstmt.setString(3, alamat);
                pstmt.setLong(4, Long.parseLong(no_telepon));
                int n = pstmt.executeUpdate();
                k = k + n;

                System.out.println(" ");
                System.out.print("=====================================================");
                System.out.print("\nApakah Anda Ingin Menambah Data Mahasiswa Baru? (YA/TIDAK): ");
                String pilihan = input.readLine().trim();
                if (!pilihan.equalsIgnoreCase("YA")) {
                    selesai = true;
                }
            }
            //MENAMBAHKAN ALERT, BERAPA KALI TRANSAKSI BERHASIL DIEKSEKUSI (BERHASIL MENAMBAHKAN 4 DATA)
            conn.commit();
            System.out.println(k + " Data Berhasil Ditambahkan!");
            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            System.out.println("Terjadi kesalahan saat memasukkan data.");
            ex.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback(); // Batalkan transaksi jika terjadi kesalahan
                }
            } catch (SQLException e) {
                System.out.println("Gagal melakukan rollback data.");
                e.printStackTrace();
            }
        }
    }

    //UPDATE
    public void update() {
        try {
            Class.forName(driver);
            String sql = "UPDATE mahasiswa SET nama = ?, alamat= ?, no_telepon= ? WHERE NIM= ?";
            conn = DriverManager.getConnection(koneksi, user, password);
            pstmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            int k = 0;

            new Utama().tampil();

            boolean selesai = false;
            while (!selesai) {
                System.out.println("___________________________________");
                System.out.println("________INPUT DATA MAHASISWA_______");
                System.out.println(" ");
                System.out.print("MASUKKAN NIM MAHASISWA YANG AKAN DIUPDATE!");
                System.out.print("\n------------------------------------------");
                System.out.print("\nMasukkan Nomor Induk Mahasiswa : ");
                String nim = input.readLine().trim();
                System.out.print("Nama baru : ");
                String nama = input.readLine().trim();
                System.out.print("Alamat Baru: ");
                String alamat = input.readLine().trim();
                System.out.print("No Telepon Baru : ");
                String no_telepon = input.readLine().trim();

                pstmt.setString(1, nama);
                pstmt.setString(2, alamat);
                pstmt.setLong(3, Long.parseLong(no_telepon));
                pstmt.setLong(4, Long.parseLong(nim));
                System.out.println(" ");
                int n = pstmt.executeUpdate();
                k = k + n;

                System.out.print("============================================================");
                System.out.print("\nApakah Anda Ingin Update Data Yang Lain? (YA/TIDAK): ");
                System.out.println(" ");
                String pilihan = input.readLine().trim();

                if (!pilihan.equalsIgnoreCase("YA")) {
                    selesai = true;
                }
            }
            conn.commit();
            System.out.println(k + " Data Berhasil Diupdate!");
            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            System.out.println("Terjadi kesalahan saat memasukkan data.");
            ex.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback(); // Batalkan transaksi jika terjadi kesalahan
                }
            } catch (SQLException e) {
                System.out.println("Gagal melakukan rollback data.");
                e.printStackTrace();
            }
        }
    }

    //DELETE
    public void delete() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(koneksi, user, password);
            conn.setAutoCommit(false);
            int k = 0;
            String sql = "DELETE FROM mahasiswa WHERE nim= ?";
            pstmt = conn.prepareStatement(sql);

            new Utama().tampil();

            boolean selesai = false;
            while (!selesai) {
                System.out.println("___________________________________");
                System.out.println("________INPUT DATA MAHASISWA_______");
                System.out.println(" ");
                System.out.print("Masukkan Nomor Induk Mahasiswa yang akan dihapus : ");
                String dogtagToDelete = input.readLine().trim();
                // Delete the record from the database
                String deleteSql = "DELETE FROM mahasiswa WHERE nim = ?";
                pstmt = conn.prepareStatement(deleteSql);
                pstmt.setLong(1, Long.parseLong(dogtagToDelete));
                int n = pstmt.executeUpdate();
                k = k + n;

                System.out.println("============================================================");
                System.out.print("Apakah Anda Ingin Menghapus Data Mahasiswa Yang Lain? (IYA/TIDAK): ");
                String pilihan = input.readLine().trim();
                if (!pilihan.equalsIgnoreCase("IYA")) {
                    selesai = true;
                }
            }
            conn.commit();
            System.out.println(k + " Data Mahasiswa Berhasil Dihapus!");

            pstmt.close();
            conn.close();
//            System.out.println("Sukses dalam satu transaksi.");
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            System.out.println("Terjadi kesalahan saat melakukan insert data dalam loop.");
            ex.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                System.out.println("Gagal melakukan rollback transaksi.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here

        new Utama().menu();

    }

}
