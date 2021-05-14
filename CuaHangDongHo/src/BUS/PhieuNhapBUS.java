package BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import DAO.*;
import DTO.*;
public class PhieuNhapBUS {
private ArrayList<PhieuNhap> dshd = new ArrayList<>();
	
	private PhieuNhapDAO hdDAO = new PhieuNhapDAO();
	
	public PhieuNhapBUS (){
		dshd = hdDAO.readDB();
	}
	
	/////////////
	public ArrayList<PhieuNhap> getDshd() {
		return dshd;
	}
	public ArrayList<PhieuNhap> Search(String value){
		ArrayList<PhieuNhap> result = new ArrayList<>();
		for(PhieuNhap sanpham : dshd) {
			if( sanpham.getMaPN().toLowerCase().contains(value) ||
				sanpham.getMaNCC().toLowerCase().contains(value) ||
				sanpham.getMaNV().toLowerCase().contains(value) ||
				sanpham.getNgayLap().toLowerCase().contains(value)  ) {
				result.add(sanpham);
			}
				
		}
		return result;
	}
	public boolean Add(PhieuNhap sp) {
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
