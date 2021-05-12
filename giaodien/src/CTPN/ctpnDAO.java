package CTPN;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import ConnectDatabase.getMySQLConnection;

public class ctpnDAO {
	getMySQLConnection spConnect;
	public ArrayList<ctpn> readDB(String mahd){
		ArrayList<ctpn> dssp = new ArrayList<>();
		spConnect = new getMySQLConnection();
		try {
			String query = "SELECT * FROM `chitietpn` WHERE `MaPN` LIKE '"+mahd+"'";
			ResultSet r = spConnect.sqlQuery(query);
			if(r!= null) {
				while (r.next()) {
					String masp = r.getString("MaSP");
					float dongia = r.getFloat("DonGia");
					int soluong = r.getInt("SoLuong");
					float thanhtien = r.getFloat("ThanhTien");
					dssp.add(new ctpn(mahd, masp, dongia, soluong, thanhtien));
				}
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu DB!");
		}
		
		spConnect.closeConnect();
		return dssp;
	}
	public boolean AddDB(ctpn ct, String mahd) {
		for(ctpn sanp : readDB(mahd)) {
			if(sanp.getMaSP().equals(ct.getMaSP())) {	//trùng mã sản phẩm thì cộng thêm số lượng
				sanp.setSoLuong( sanp.getSoLuong() + ct.getSoLuong());
				
			}
		}
		spConnect = new getMySQLConnection();
		
		String query = "INSERT INTO `chitietpn` (`MaPN`, `MaSP`, `DonGia`, `SoLuong`, `ThanhTien`) VALUES ('"+ct.getMaPN()+"', '"+ct.getMaSP()+"', '"+ct.getDonGia()+"', '"+ct.getSoLuong()+"', '"+ct.getThanhTien()+"');";
		boolean check = spConnect.sqlInsert(query);
		spConnect.closeConnect();
		return check;		
	}
}
