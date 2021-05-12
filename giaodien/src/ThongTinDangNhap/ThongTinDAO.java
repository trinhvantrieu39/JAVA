package ThongTinDangNhap;

import java.sql.ResultSet;
import java.sql.SQLException;

import ConnectDatabase.getMySQLConnection;

public class ThongTinDAO {
	public String TenNguoiDung(String manv) throws SQLException {
		String TenNguoiDung =null;
		getMySQLConnection con = new getMySQLConnection();
		String query ="SELECT `TenNV` FROM `nhanvien` WHERE `MaNV` LIKE '"+manv+"'";
		ResultSet r = con.sqlQuery(query);
		while(r.next()) {
			TenNguoiDung = r.getString("TenNV");
		}
		con.closeConnect();
		return TenNguoiDung;
	}
	public ThongTin ThongTinDangNhap(String matk) {
		ThongTin thongtin =null;
		getMySQLConnection con = new getMySQLConnection();
		try {
			
			String query = "SELECT * FROM taikhoan WHERE  `taikhoan`.`MaTK` = '"+matk+"'";
			ResultSet r  = con.sqlQuery(query);
			while (r.next()) {
				String taikhoan = r.getString("MaTK");
				String mk = r.getString("MatKhau");
				String quyen = r.getString("MaQuyen");
				String nv = r.getString("MaNV");
				thongtin = new ThongTin(taikhoan,mk,nv,quyen);
			}
		}
		catch(SQLException e) {
			
		}
		con.closeConnect();
		return thongtin;
	}
}
