package BUS;

import java.util.ArrayList;


import DAO.*;
import DTO.*;
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
