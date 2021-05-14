package BUS;

import java.util.ArrayList;
import DAO.*;
import DTO.*;

public class PhanQuyenBUS {
	ArrayList<PhanQuyen> dsq = new ArrayList<>();
	PhanQuyenDAO DAO = new PhanQuyenDAO();
	public PhanQuyenBUS() {
		dsq= DAO.readDB();
	}
	public ArrayList<PhanQuyen> getdsq(){
		return dsq;
	}
	public ArrayList<PhanQuyen> Search(String value){
		ArrayList<PhanQuyen> result = new ArrayList<>();
		for(PhanQuyen sanpham : dsq) {
			if( sanpham.getTenquyen().toLowerCase().contains(value) ||
				sanpham.getMaquyen().toLowerCase().contains(value) ||
				sanpham.getChitiet().toLowerCase().contains(value)  ) {
				result.add(sanpham);
			}
				
		}
		return result;
	}
}
