package BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import DAO.*;
import DTO.*;
public class TaiKhoanBUS {
	ArrayList<TaiKhoan> dstk = new ArrayList<>();
	private TaiKhoanDAO DAO = new TaiKhoanDAO();
	
	public TaiKhoanBUS() {
		dstk =DAO.readDB();
	}
	public ArrayList<TaiKhoan> getDstk(){
		return dstk;
	}
	public Boolean Add(TaiKhoan tk) {
		boolean check = DAO.AddDb(tk);
		if(check) {
			dstk.add(tk);
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Thêm không thành công ");
			return false;
		}
	}
	public Boolean Update(String tk, String matkhau, String quyen, String nv) {
		boolean check = DAO.ChangDB(tk, matkhau, quyen, nv);
		if(check) {
			for(TaiKhoan taikhoan : dstk) {
				if(tk.equals(taikhoan.getMatk())) {
					taikhoan.setMatk(tk);
					taikhoan.setMatkhau(matkhau);
					taikhoan.setMaquyen(quyen);
					taikhoan.setManv(nv);
					return true;
				}
			}
		}
		return false;
	}
	public Boolean Delete(String tk) {
		boolean check = DAO.DeleteDB(tk);
		if(check) {
			for(TaiKhoan taikhoan : dstk) {
				if(taikhoan.getMatk().equals(tk)) {
					dstk.remove(taikhoan);
					return true;
				}
			}
		}
		return false;
	}
	public ArrayList<TaiKhoan> Search (String value ){
		ArrayList<TaiKhoan> result = new ArrayList<>();
		for(TaiKhoan tk : dstk) {
			if(tk.getMatk().toLowerCase().contains(value) || tk.getMaquyen().toLowerCase().contains(value) || tk.getMatkhau().toLowerCase().contains(value) || tk.getManv().toLowerCase().contains(value)){
				result.add(tk);
			}
		}
		return result;
	}
}
