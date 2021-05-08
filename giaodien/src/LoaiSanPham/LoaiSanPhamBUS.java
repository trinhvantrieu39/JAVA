package LoaiSanPham;
import java.util.*;

import javax.swing.JOptionPane;

import SanPham.SanPham;
public class LoaiSanPhamBUS {
	private ArrayList<LoaiSanPham> dssp = new ArrayList<>();
	private LoaiSanPhamDAO lspDAO = new LoaiSanPhamDAO();
	public LoaiSanPhamBUS (){
		dssp = lspDAO.readDB();
	}
	
	public ArrayList<LoaiSanPham> getDssp() {
		return dssp;
	}
	
	public boolean update (String masp, String tensp) {
		boolean check = lspDAO.ChangeDB(masp, tensp);
		if(check) {
			for(LoaiSanPham sp : dssp) {
				if(masp.equals(sp.getMalsp())) {
					sp.setTenlsp(tensp);
					
					return true;
				}
			}
		}
		return false;
	}
	public boolean Add(LoaiSanPham sp) {
		boolean check = lspDAO.AddDB(sp);
		if(check) {
			dssp.add(sp);
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Thêm không thành công!!!");
			return false;
		}
	}
	public boolean Delete(String malsp) {
		boolean check = lspDAO.DeleteDB(malsp);
		if(check) {
			for(LoaiSanPham sp : dssp) {
				if(malsp.equals(sp.getMalsp())) {
					dssp.remove(sp);
					return true;
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Xóa không thành công!!!");
			return false;
		}
		return check;
		
	}
	
	public ArrayList<LoaiSanPham> Search(String value){
		ArrayList<LoaiSanPham> result = new ArrayList<>();
		for(LoaiSanPham sanpham : dssp) {
			if( sanpham.getMalsp().toLowerCase().contains(value) ||
				sanpham.getTenlsp().toLowerCase().contains(value) ) {
				result.add(sanpham);
			}
				
		}
		return result;
	}
	
}
