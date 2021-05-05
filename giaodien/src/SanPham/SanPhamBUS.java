package SanPham;
import java.util.*;

import javax.swing.JOptionPane;
public class SanPhamBUS {
	private ArrayList<SanPham> dssp = new ArrayList<>();
	private SanPhamDAO spDAO = new SanPhamDAO();
	public SanPhamBUS (){
		dssp = spDAO.readDB();
	}
	
	public ArrayList<SanPham> getDssp() {
		return dssp;
	}
	public boolean update (String masp, String tensp, String maloai, float dongia, int soluong, String hinhanh) {
		boolean check = spDAO.ChangeDB(masp, tensp, maloai, dongia, soluong, hinhanh);
		if(check) {
			for(SanPham sp : dssp) {
				if(masp.equals(sp.masp)) {
					sp.setTensp(tensp);
					sp.setMaloai(maloai);
					sp.setDongia(dongia);
					sp.setSoluong(soluong);
					sp.setHinhanh(hinhanh);
					return true;
				}
			}
		}
		return false;
	}
	public boolean Add(SanPham sp) {
		boolean check = spDAO.AddDB(sp);
		if(check) {
			dssp.add(sp);
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Thêm không thành công!!!");
			return false;
		}
	}
	public boolean Delete(String masp) {
		boolean check = spDAO.DeleteDB(masp);
		if(check) {
			for(SanPham sp : dssp) {
				if(masp.equals(sp.masp)) {
					dssp.remove(sp);
					return true;
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Thêm không thành công!!!");
			return false;
		}
		return check;
		
	}
	
	public ArrayList<SanPham> Search(String value){
		ArrayList<SanPham> result = new ArrayList<>();
		for(SanPham sanpham : dssp) {
			if( sanpham.getMasp().toLowerCase().contains(value) ||
				sanpham.getTensp().toLowerCase().contains(value) ||
				sanpham.getMaloai().toLowerCase().contains(value) ||
				String.valueOf(sanpham.getDongia()).contains(value) ||
				String.valueOf(sanpham.getSoluong()).contains(value) ) {
				result.add(sanpham);
			}
				
		}
		return result;
	}
}
