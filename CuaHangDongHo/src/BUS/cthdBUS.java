package BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

//import HoaDon.HoaDon;
import DAO.*;
import DTO.*;

public class cthdBUS {
	private ArrayList<cthd> dssp = new ArrayList<>();
	private cthdDAO spDAO = new cthdDAO();
	public cthdBUS(String mahd) {
		dssp = spDAO.readDB(mahd);
	}
	public cthdBUS() {
		dssp = spDAO.readDB();
	}
	public ArrayList<cthd> getDssp() {
		return dssp;
	}
	public boolean Add(cthd sp,String mahd) {
		boolean check = spDAO.AddDB(sp,mahd);
		if(check) {
			for(cthd ct : dssp) {
				if(ct.getMaSP().equals(sp.getMaSP())) {
					ct.setSoLuong(ct.getSoLuong()+sp.getSoLuong());
					return true;
				}
			}
			dssp.add(sp);
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Thêm không thành công!!!");
			return false;
		}
	}
	public ArrayList<cthd> Search(String value){
		ArrayList<cthd> result = new ArrayList<>();
		for(cthd sanpham : dssp) {
			if( sanpham.getMaHD().toLowerCase().contains(value) ||
				sanpham.getMaSP().toLowerCase().contains(value) ||
				String.valueOf(sanpham.getSoLuong()).toLowerCase().contains(value) ||
				String.valueOf(sanpham.getThanhTien()).toLowerCase().contains(value)  ) {
				result.add(sanpham);
			}
				
		}
		return result;
	}

}
