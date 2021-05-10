package HoaDon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectDatabase.getMySQLConnection;




public class HoaDonDAO {
	getMySQLConnection Connect;
	public ArrayList<HoaDon> readDB(){
		ArrayList<HoaDon> dshd = new ArrayList<>();
		Connect = new getMySQLConnection();
		try {
			String query = "SELECT * FROM hoadon";
			ResultSet r = Connect.sqlQuery(query);
			if(r!= null) {
				while (r.next()) {
					String mahd = r.getString("MaHD");
					String manv = r.getString("MaNV");
					String makh = r.getString("MaKH");
					float tongtien = r.getFloat("TongTien");
					
					String ngaylap = r.getString("NgayLap");
					dshd.add(new HoaDon(mahd, manv, makh, ngaylap, tongtien));
				}
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu DB!");
		}
		
		Connect.closeConnect();
		return dshd;
	}
	public boolean AddDB(HoaDon sp) {
		for(HoaDon sanp : readDB()) {
			if(sanp.getMaHD().equals(sp.getMaHD())) {
				JOptionPane.showMessageDialog(null, "Mã hóa đơn đã tồn tại");
				return false;
			}
		}
		Connect = new getMySQLConnection();
		
		String query = 	"INSERT INTO `hoadon` (`MaHD`, `MaNV`, `MaKH`, `NgayLap`, `TongTien`) VALUES ('"+sp.getMaHD()+"', '"+sp.getMaNV()+"', '"+sp.getMaKH()+"', '"+sp.getNgayLap()+"', '"+sp.getTongTien()+"');";
		boolean check = Connect.sqlInsert(query);
		Connect.closeConnect();
		return check;		
	}
	
}
