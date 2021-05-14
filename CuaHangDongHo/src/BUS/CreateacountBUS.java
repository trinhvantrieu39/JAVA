package BUS;
import DAO.*;
public class CreateacountBUS {
    CreateacountBUS CreateAcountDAL=new CreateacountBUS();
    public boolean insertUser(String TenTK, String MatKhau, String MaNV, String MaQuyen){
        if (CreateAcountDAL.insertUser(TenTK,MatKhau,MaNV,MaQuyen)==true){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean checkTenTaiKhoan(String Tentaikhoan){
        if (CreateAcountDAL.checkTenTaiKhoan(Tentaikhoan)==false){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkMaNV(String MaNV) {
        if (CreateAcountDAL.checkMaNV(MaNV)==false){
            return true;
        }
        else{
            return false;
        }

    }
}
