/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NhanVien;

import NhanVien.NhanvienDAO;
import NhanVien.NhanvienDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NhanvienBUS {
        public static ArrayList<NhanvienDTO> dsnv;
        public NhanvienBUS(){}
        public void docDSNV(){
                NhanvienDAO data=new NhanvienDAO();
                if(dsnv==null) dsnv=new ArrayList<NhanvienDTO>();
                dsnv=data.docDSNV();
            }
    public void them(NhanvienDTO nv){
                //Kiem tra du lieu hop le
                //Kiem tra ma sinh vien duy nhat
                NhanvienDAO data=new NhanvienDAO();
                data.them(nv);
                dsnv.add(nv);
             }
    public void sua(int i,NhanvienDTO nv){
            NhanvienDAO data=new NhanvienDAO();
            data.sua(nv);
            dsnv.set(i,nv);
    }
    public void xoa(int i,String ma){
        NhanvienDAO data=new NhanvienDAO();
        data.xoa(ma);
        dsnv.remove(i);
    }
    
   boolean checkTimKiem(String s,NhanvienDTO nv){
        if(nv.getMaNV().toLowerCase().contains(s.toLowerCase()) ||
           nv.getTenNV().toLowerCase().contains(s.toLowerCase()) ||
           new SimpleDateFormat("d-M-yyyy").format(nv.getNgaySinh()).toLowerCase().contains(s.toLowerCase()) ||
           new SimpleDateFormat("d/M/yyyy").format(nv.getNgaySinh()).toLowerCase().contains(s.toLowerCase()) ||
           nv.getDiaChi().toLowerCase().contains(s.toLowerCase()) ||
           nv.getSDT().toLowerCase().contains(s.toLowerCase())||
           nv.getGioitinh().toLowerCase().contains(s.toLowerCase())){
            return true;
        }
        return false;
    }
   void duyetNV(ArrayList nvnhom,NhanvienDTO nv){
        NhanvienDTO nv1=new NhanvienDTO();
        nv1.setMaNV(nv.getMaNV());
        nv1.setTenNV(nv.getTenNV());
        nv1.setNgaySinh(nv.getNgaySinh());
        nv1.setDiaChi(nv.getDiaChi());
        nv1.setSDT(nv.getSDT());
        nv1.setGioitinh(nv.getGioitinh());
        nvnhom.add(nv1);
    }
     public ArrayList timKiem(String s){
        ArrayList<NhanvienDTO> nvnhom=new ArrayList<>();
        for(NhanvienDTO nv:dsnv){
            if(checkTimKiem(s,nv)==true){
                duyetNV(nvnhom,nv);
            }
        }
        return nvnhom;
    }
     
     
}