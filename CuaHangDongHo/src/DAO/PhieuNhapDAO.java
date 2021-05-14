package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

//import ConnectDatabase.getMySQLConnection;
import BUS.*;
import DTO.*;

public class PhieuNhapDAO {
	getMySQLConnection Connect;
	public ArrayList<PhieuNhap> readDB(){
		ArrayList<PhieuNhap> dshd = new ArrayList<>();
		Connect = new getMySQLConnection();
		try {
			String query = "SELECT * FROM phieunhap";
			ResultSet r = Connect.sqlQuery(query);
			if(r!= null) {
				while (r.next()) {
					String mapn = r.getString("MaPN");
					String manv = r.getString("MaNV");
					String mancc = r.getString("MaNCC");
					float tongtien = r.getFloat("TongTien");
					
					String ngaylap = r.getString("NgayLap");
					dshd.add(new PhieuNhap(mapn, manv, mancc, ngaylap, tongtien));
				}
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu DB!");
		}
		
		Connect.closeConnect();
		return dshd;
	}
	public boolean AddDB(PhieuNhap sp) {
		for(PhieuNhap sanp : readDB()) {
			if(sanp.getMaPN().equals(sp.getMaPN())) {
				JOptionPane.showMessageDialog(null, "Mã hóa đơn đã tồn tại");
				return false;
			}
		}
		Connect = new getMySQLConnection();
		
		String query = 	"INSERT INTO `phieunhap` (`MaPN`, `MaNV`, `MaNCC`, `NgayLap`, `TongTien`) VALUES ('"+sp.getMaPN()+"', '"+sp.getMaNV()+"', '"+sp.getMaNCC()+"', '"+sp.getNgayLap()+"', '"+sp.getTongTien()+"');";
		boolean check = Connect.sqlInsert(query);
		Connect.closeConnect();
		return check;		
	}
}
