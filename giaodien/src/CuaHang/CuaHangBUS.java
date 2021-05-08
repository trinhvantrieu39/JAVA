package CuaHang;

import java.util.ArrayList;

import SanPham.SanPham;
import SanPham.SanPhamDAO;

public class CuaHangBUS {
	private ArrayList<SanPham> dssp = new ArrayList<>();
	private CuaHangDAO chDAO = new CuaHangDAO();
	public CuaHangBUS (){
		dssp = chDAO.readDB();
	}
	
	public ArrayList<SanPham> getDssp() {
		return dssp;
	}
}
