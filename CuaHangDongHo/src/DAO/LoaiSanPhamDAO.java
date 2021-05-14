package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
//
//import ConnectDatabase.getMySQLConnection;
//import SanPham.SanPham;
import BUS.*;
import DTO.*;


public class LoaiSanPhamDAO {
	getMySQLConnection Connect;
	public ArrayList<LoaiSanPham> readDB(){
		ArrayList<LoaiSanPham> dslsp = new ArrayList<>();
		Connect = new getMySQLConnection();
		try {
			String query = "SELECT * FROM loaisp";
			ResultSet r = Connect.sqlQuery(query);
			if(r!= null) {
				while (r.next()) {
					String ma=r.getString("MaLSP");
					String ten= r.getString("TenLSP");
					
					dslsp.add(new LoaiSanPham(ma,ten));
				}
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu DB!");
		}
		
		Connect.closeConnect();
		return dslsp;
	}
	public boolean ChangeDB(String malsp, String tensp) {
		Connect = new getMySQLConnection();	
		String query = "UPDATE loaisp SET "  
				
				+"TenlSP='" +tensp
				
				+"' WHERE MaLSP='"
				+malsp+"'";
			boolean check = Connect.sqlUpdate(query);
			Connect.closeConnect();
		return check;
	}
	
	public boolean AddDB(LoaiSanPham lsp) {
		for(LoaiSanPham sanp : readDB()) {
			if(sanp.getMalsp().equals(lsp.getMalsp())) {
				JOptionPane.showMessageDialog(null, "Mã loại sản phẩm đã tồn tại");
				return false;
			}
		}
		Connect = new getMySQLConnection();
		
		
		String query = "INSERT INTO `loaisp`(`MaLSP`, `TenLSP`) VALUES ('"+lsp.getMalsp()+"', '"+lsp.getTenlsp()+"');";
		boolean check = Connect.sqlInsert(query);
		Connect.closeConnect();
		return check;		
	}
	
	public boolean DeleteDB(String malsp) {
		Connect = new getMySQLConnection();
		
		
		String query = "DELETE FROM `loaisp` WHERE `loaisp`.`MaLSP` = '"+malsp+"'";
		boolean check = Connect.sqlDelete(query);
		return check;
	}
}
