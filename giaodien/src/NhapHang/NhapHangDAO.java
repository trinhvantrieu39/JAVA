package NhapHang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDatabase.getMySQLConnection;
import SanPham.SanPham;

public class NhapHangDAO {
	getMySQLConnection chConnect;
	public ArrayList<SanPham> readDB(){
		ArrayList<SanPham> dssp = new ArrayList<>();
		chConnect = new getMySQLConnection();
		try {
			String query = "SELECT * FROM sanpham";
			ResultSet r = chConnect.sqlQuery(query);
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
		
		chConnect.closeConnect();
		return dssp;
	}
}
