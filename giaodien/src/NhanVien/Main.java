/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NhanVien;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    public static void main(String[] args) {
        JFrame f=new JFrame();
        JPanel panel=new NhanVienGUI();
        f.add(panel);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.pack();
            
    }
}
