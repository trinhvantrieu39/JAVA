package TaiKhoan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDatabase.getMySQLConnection;

public class TaiKhoanDAO {
	getMySQLConnection Connect ;
	
	public ArrayList<TaiKhoan> readDB(){
		ArrayList<TaiKhoan> dstk = new ArrayList<>();
		Connect = new getMySQLConnection();
		try {
			String query = "SELECT * FROM taikhoan";
			ResultSet r = Connect.sqlQuery(query);
			if(r!=null) {
				while (r.next()) {
					//MaTK MatKhau	MaQuyen	MaNV
					String matk =r.getString("MaTK");
					String matkhau = r.getString("MatKhau");
					String quyen = r.getString("MaQuyen");
					String manv = r.getString("MaNV");
					dstk.add(new TaiKhoan(matk,matkhau,quyen,manv));
				}
			}
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu DB!");
		}
		Connect.closeConnect();
		return dstk;
	}
	public Boolean ChangDB (String tk, String mk, String quyen, String nv) {
		Connect = new getMySQLConnection();
		String query = "UPDATE `taikhoan` SET"
				+"MatKhau='"+ mk
				+"',MaQuyen='"+quyen
				+"',MaNV='"+nv
				+"' WHERE `taikhoan`.`MaTK`='" 
				+tk+"'";
		String qr ="UPDATE `taikhoan` SET `MatKhau` = '"+mk+"', `MaQuyen` = '"+quyen+"', `MaNV` = '"+nv+"' WHERE `taikhoan`.`MaTK` = '"+tk+"';";
		boolean check = Connect.sqlUpdate(qr);
		Connect.closeConnect();
		return check;
	}
	public Boolean AddDb(TaiKhoan tk) {
		Connect = new getMySQLConnection();
		for(TaiKhoan taikhoan : readDB()) {
			if(taikhoan.getMatk().equals(tk.getMatk())){
				JOptionPane.showMessageDialog(null, "Mã tài khoản đã tồn tại ");
				return false;
			}
		}
		String query = "INSERT INTO `taikhoan` (`MaTK` ,`MatKhau`, ``MaQuyen`, `MaNV` ) VALUES (' "+tk.getMatk()+"','"+tk.getMatkhau()+"','"+tk.getMaquyen()+"','"+tk.getManv()+"');";
		boolean check = Connect.sqlInsert(query);
		Connect.closeConnect();
		return check;
	}
	public Boolean DeleteDB(String tk) {
		Connect = new getMySQLConnection();
		String query="DELETE FROM `taikhoan` WHERE `taikhoan`.`MaTK` = '"+tk+"'";
		boolean check =Connect.sqlDelete(query);
		return check;
	}
}
