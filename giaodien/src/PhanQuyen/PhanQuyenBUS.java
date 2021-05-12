package PhanQuyen;

import java.util.ArrayList;

public class PhanQuyenBUS {
	ArrayList<PhanQuyen> dsq = new ArrayList<>();
	PhanQuyenDAO DAO = new PhanQuyenDAO();
	public PhanQuyenBUS() {
		dsq= DAO.readDB();
	}
	public ArrayList<PhanQuyen> getdsq(){
		return dsq;
	}
}
