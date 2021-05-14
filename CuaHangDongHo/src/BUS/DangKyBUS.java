package BUS;
import DAO.*;
import DTO.*;
public class DangKyBUS {
	DangKyDAO dn=new DangKyDAO();
	public boolean insertUser( String TenTK, String MatKhau, String MaNV, String MaQuyen) {
		if(dn.insertUser(TenTK, MatKhau, MaNV, MaQuyen)==true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	 public boolean checkTenTaiKhoan(String Tentaikhoan){
		 if(dn.checkTenTaiKhoan(Tentaikhoan)==false) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }
	 
	  public boolean checkMaNV(String MaNV){
		  if (checkMaNV(MaNV)==false) {
			  return true;
		  }
		  else {
			  return false;
		  }
	  }
}
