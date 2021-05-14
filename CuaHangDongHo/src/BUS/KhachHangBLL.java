package BUS;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;
import DAO.*;
import DTO.*;
public class KhachHangBLL {
    khachHangDAL kh=new khachHangDAL();
   Vector <KhachHangDTO> dskh=new Vector<>();

    public Vector<KhachHangDTO> getAllKhachang(){
        return kh.getAllKhachHang();
    }

    public String addkhachang(KhachHangDTO khDTO){
        if (kh.checkMaKH(khDTO.getMaKH())){
            return "Mã khách hàng đã tồn tại";
        }
        if(!kh.checkTenKhachHang(khDTO.getTenKH())){
            return "Tên Khách hàng đã tồn tại";
        }
        if (!kh.checksdtkh(khDTO.getSDT())){
            return "Số diện thoại đã được sử dụng";
        }
        if (kh.addKhachHang(khDTO)){
            return "Thêm thành công";
        }
        return "Thêm thất bại ";
    }

    public boolean update (String makh, String tenkh, String diachi, String sdt, String ngaysinh, String gioitinh) {
        boolean check = kh.ChangeDB(makh, tenkh, diachi, sdt, ngaysinh, gioitinh);
        System.out.println("chek2="+check);
        if(check) {
            dskh=getAllKhachang();
            for(int i=0;i<dskh.size() ;i++) {
                KhachHangDTO kh=dskh.get(i);

                if(makh.equals(kh.getMaKH())) {
                    System.out.println("makh ne= "+kh.getMaKH());
                    kh.setMaKH(makh);
                    kh.setTenKH(tenkh);
                    kh.setDiaCHi(diachi);
                    kh.setSDT(sdt);
                    kh.setNgaySinh(ngaysinh);
                    kh.setGioitinh(gioitinh);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean Delete(String makh){
        boolean check = kh.DeleteDB(makh);
        dskh=getAllKhachang();
        if (check){
            for (int i=0;i<dskh.size();i++){
                KhachHangDTO khDTO=dskh.get(i);
                if (makh.equals(khDTO.getMaKH())){
                    System.out.println("makh ne= "+khDTO.getMaKH());
                    dskh.remove(khDTO);
                    return true;
                }
            }
        }else {

            return false;
        }
        return check;

    }
    public Vector<KhachHangDTO> Search(String value){
        Vector<KhachHangDTO> result = new Vector<KhachHangDTO>();
        dskh=getAllKhachang();
        for (int i = 0; i <dskh.size() ; i++)
        {
            KhachHangDTO kh=dskh.get(i);
            System.out.println(kh.getTenKH().toLowerCase().contains(value));
            if( kh.getMaKH().toUpperCase().contains(value) ||kh.getMaKH().toLowerCase().contains(value)||
                    kh.getTenKH().toUpperCase().contains(value) ||kh.getTenKH().toLowerCase().contains(value)||
                    kh.getSDT().contains(value)
            ) {
                result.add(kh);
            }

        }
        return result;
    }
}
