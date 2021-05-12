package NhaCungCap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDatabase.getMySQLConnection;
import SanPham.SanPham;



public class NhaCungCapDAO {
    getMySQLConnection Connect;
    public ArrayList<NhaCungCap> readDB(){
        ArrayList<NhaCungCap> dsncc = new ArrayList<>();
        Connect = new getMySQLConnection();
        try {
            String query = "SELECT * FROM nhacungcap";
            ResultSet r = Connect.sqlQuery(query);
            if(r!= null) {
                while (r.next()) {
                    String mancc=r.getString("MaNCC");
                    String tenncc= r.getString("TenNCC");
                    String dcncc=r.getString("DiaChi");
                    String sdtncc=r.getString("SDT");

                    dsncc.add(new NhaCungCap(mancc,tenncc,dcncc,sdtncc));
                }
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu DB!");
        }

        Connect.closeConnect();
        return dsncc;
    }
    
    public boolean ChangeDB(String mancc, String tenncc,String dcncc,String sdtncc) {
        Connect = new getMySQLConnection();
        /*
        String query = "UPDATE nhacungcap SET "

                +"TenNCC='" +tenncc
                +"DiaChi='"+dcncc
                +"SDT='"+sdtncc

                +"' WHERE MaNCC='"
                +mancc+"'";
        */
        String query = "UPDATE `nhacungcap` SET `TenNCC`='"+tenncc+"',`DiaChi`='"+dcncc+"',`SDT`='"+sdtncc+"' WHERE `nhacungcap`.`MaNCC` = '"+mancc+"'";
        boolean check = Connect.sqlUpdate(query);
        Connect.closeConnect();
        return check;
    }

    public boolean AddDB(NhaCungCap ncc) {
        for(NhaCungCap nhacungcap : readDB()) {
            if(nhacungcap.getMancc().equals(ncc.getMancc())) {
                JOptionPane.showMessageDialog(null, "Mã nhà cung cấp đã tồn tại");
                return false;
            }
        }
        Connect = new getMySQLConnection();


        String query = "INSERT INTO `nhacungcap`(`MaNCC`, `TenNCC`,`DiaChi`,`SDT`) VALUES ('"+ncc.getMancc()+"', '"+ncc.getTenncc()+"','"+ncc.getDcncc()+"','"+ncc.getSdtncc()+"');";
        boolean check = Connect.sqlInsert(query);
        Connect.closeConnect();
        return check;
    }

    public boolean DeleteDB(String mancc) {
        Connect = new getMySQLConnection();


        String query = "DELETE FROM `nhacungcap` WHERE `nhacungcap`.`MaNCC` = '"+mancc+"'";
        boolean check = Connect.sqlDelete(query);
        return check;
    }
}

