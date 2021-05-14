package BUS;
import DAO.*;
import DTO.*;
public class DangNhapBUS {
	DangnhapDAO dn=new DangnhapDAO();
	 public boolean checkLogin(String tentaikhoan, String matkhau) {
		 if(dn.checkLogin(tentaikhoan, matkhau)==true) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }

}
