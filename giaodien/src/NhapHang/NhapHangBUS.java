package NhapHang;

import java.util.ArrayList;
import SanPham.SanPham;

public class NhapHangBUS {
	private ArrayList<SanPham> dssp = new ArrayList<>();
	private NhapHangDAO chDAO = new NhapHangDAO();
	public NhapHangBUS (){
		dssp = chDAO.readDB();
	}
	
	public ArrayList<SanPham> getDssp() {
		return dssp;
	}
}
