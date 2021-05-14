package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

//import ConnectDatabase.getMySQLConnection;
import BUS.*;
import DTO.*;
public class PhanQuyenDAO {
	getMySQLConnection connect ;
	public ArrayList<PhanQuyen> readDB() {
		ArrayList<PhanQuyen> dsq = new ArrayList<>();
		connect =new getMySQLConnection();
		try {
			String qr= "SELECT * FROM `phanquyen`";
			ResultSet r= connect.sqlQuery(qr);
			if(r!= null) {
				while (r.next()) {
					//`MaQuyen`, `TenQuyen`, `ChiTietQuyen`
					String ma = r.getString("MaQuyen");
					String ten = r.getString("TenQuyen");
					String chitiet = r.getString("ChiTietQuyen");
					dsq.add(new PhanQuyen(ma,ten,chitiet));
				}
			}
			
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu DB!");
		}
		return dsq;
	}
}
