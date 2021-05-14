package BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import DAO.*;
import DTO.*;
public class ctpnBUS {
	private ArrayList<ctpn> dssp = new ArrayList<>();
	private ctpnDAO spDAO = new ctpnDAO();
	public ctpnBUS(String mahd) {
		dssp = spDAO.readDB(mahd);
	}
	public ctpnBUS() {
		dssp = spDAO.readDB();
	}
	public ArrayList<ctpn> getDssp() {
		return dssp;
	}
	public boolean Add(ctpn sp,String mahd) {
		boolean check = spDAO.AddDB(sp,mahd);
		if(check) {
			for(ctpn ct : dssp) {
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
	public ArrayList<ctpn> Search(String value){
		ArrayList<ctpn> result = new ArrayList<>();
		for(ctpn sanpham : dssp) {
			if( sanpham.getMaPN().toLowerCase().contains(value) ||
				sanpham.getMaSP().toLowerCase().contains(value) ||
				String.valueOf(sanpham.getSoLuong()).toLowerCase().contains(value) ||
				String.valueOf(sanpham.getThanhTien()).toLowerCase().contains(value)  ) {
				result.add(sanpham);
			}
				
		}
		return result;
	}
}
