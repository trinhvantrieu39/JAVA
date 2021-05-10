package HoaDon;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import LoaiSanPham.LoaiSanPham;
import SanPham.SanPham;

public class HoaDonBUS {
	private ArrayList<HoaDon> dshd = new ArrayList<>();
	
	private HoaDonDAO hdDAO = new HoaDonDAO();
	
	public HoaDonBUS (){
		dshd = hdDAO.readDB();
	}
	
	/////////////
	public ArrayList<HoaDon> getDshd() {
		return dshd;
	}
	public ArrayList<HoaDon> Search(String value){
		ArrayList<HoaDon> result = new ArrayList<>();
		for(HoaDon sanpham : dshd) {
			if( sanpham.getMaHD().toLowerCase().contains(value) ||
				sanpham.getMaKH().toLowerCase().contains(value) ||
				sanpham.getMaNV().toLowerCase().contains(value) ||
				sanpham.getNgayLap().toLowerCase().contains(value)  ) {
				result.add(sanpham);
			}
				
		}
		return result;
	}
	public boolean Add(HoaDon sp) {
		boolean check = hdDAO.AddDB(sp);
		if(check) {
			dshd.add(sp);
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Thêm không thành công!!!");
			return false;
		}
	}
}
