package SanPham;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDatabase.*;

public class SanPhamDAO {
	getMySQLConnection spConnect;
	
	public ArrayList<SanPham> readDB(){
		ArrayList<SanPham> dssp = new ArrayList<>();
		spConnect = new getMySQLConnection();
		try {
			String query = "SELECT * FROM sanpham";
			ResultSet r = spConnect.sqlQuery(query);
			if(r!= null) {
				while (r.next()) {
					String masp = r.getString("MaSP");
					String tensp = r.getString("TenSP");
					String maloai = r.getString("MaLoai");
					float dongia = r.getFloat("DonGia");
					int soluong = r.getInt("SoLuong");
					String hinhanh = r.getString("HinhAnh");
					dssp.add(new SanPham(masp, tensp, maloai, soluong, dongia, hinhanh));
				}
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu DB!");
		}
		
		spConnect.closeConnect();
		return dssp;
	}
	public boolean ChangeDB(String masp, String tensp, String maloai, Float dongia, int soluong, String hinhanh) {
		spConnect = new getMySQLConnection();	
		String query = "UPDATE sanpham SET "  
				
				+"TenSP='" +tensp
				+"',MaLoai='" +maloai
				+"',DonGia='" +dongia
				+"',SoLuong='" +soluong
				+"',HinhAnh='" +hinhanh
				+"' WHERE MaSP='"
				+masp+"'";
			boolean check = spConnect.sqlUpdate(query);
			spConnect.closeConnect();
		return check;
	}
	public boolean AddDB(SanPham sp) {
		for(SanPham sanp : readDB()) {
			if(sanp.masp.equals(sp.masp)) {
				JOptionPane.showMessageDialog(null, "Mã sản phẩm đã tồn tại");
				return false;
			}
		}
		spConnect = new getMySQLConnection();
		
		String query = 	"INSERT INTO `sanpham` (`MaSP`, `TenSP`, `MaLoai`, `DonGia`, `SoLuong`, `HinhAnh`) VALUES ('"+sp.masp+"', '"+sp.tensp+"', '"+sp.maloai+"', '"+String.valueOf(sp.dongia)+"', '"+String.valueOf(sp.soluong)+"', '"+sp.hinhanh+"');";
		boolean check = spConnect.sqlInsert(query);
		spConnect.closeConnect();
		return check;		
	}
	public boolean DeleteDB(String masp) {
		spConnect = new getMySQLConnection();
		
		String query = "DELETE FROM `sanpham` WHERE `sanpham`.`MaSP` = '"+masp+"'";
		boolean check = spConnect.sqlDelete(query);
		return check;
	}
	
}
