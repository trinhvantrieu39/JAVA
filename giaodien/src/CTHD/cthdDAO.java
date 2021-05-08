package CTHD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDatabase.getMySQLConnection;
import SanPham.SanPham;

public class cthdDAO {
	getMySQLConnection spConnect;
	public ArrayList<cthd> readDB(String mahd){
		ArrayList<cthd> dssp = new ArrayList<>();
		spConnect = new getMySQLConnection();
		try {
			String query = "SELECT * FROM `chitiethd` WHERE `MaHD` LIKE '"+mahd+"'";
			ResultSet r = spConnect.sqlQuery(query);
			if(r!= null) {
				while (r.next()) {
					String masp = r.getString("MaSP");
					float dongia = r.getFloat("DonGia");
					int soluong = r.getInt("SoLuong");
					float thanhtien = r.getFloat("ThanhTien");
					dssp.add(new cthd(mahd, masp, dongia, soluong, thanhtien));
				}
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu DB!");
		}
		
		spConnect.closeConnect();
		return dssp;
	}
	public boolean AddDB(cthd ct) {
		for(cthd sanp : readDB()) {
			if(sanp.masp.equals(ct.masp)) {	//trùng mã sản phẩm thì cộng thêm số lượng
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
}
