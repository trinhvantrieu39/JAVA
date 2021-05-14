package DAO;
import BUS.*;
import DTO.*;
import java.sql.*;
import java.util.Vector;

public class khachHangDAL {
    getMySQLConnection connect;

    public Vector<KhachHangDTO> getAllKhachHang(){
            connect=new getMySQLConnection();
        Vector <KhachHangDTO> arr=new Vector<KhachHangDTO>();
        try{
            String  sql="SELECT *FROM khachhang ";
            Statement stm=connect.conn.createStatement();
            ResultSet rs=stm.executeQuery(sql);
            while(rs.next()){
                KhachHangDTO kh=new KhachHangDTO();
                kh.setMaKH(rs.getString("MaKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setDiaCHi(rs.getString("DiaChi"));
                kh.setSDT(rs.getString("SDT"));
                kh.setNgaySinh(rs.getString("NgaySinh"));
                kh.setGioitinh(rs.getString("GioiTinh"));
                arr.add(kh);
            }

        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            connect.closeConnect();
        }
        return arr;
    }

    public  boolean addKhachHang(KhachHangDTO khachhang){
        connect=new getMySQLConnection();
        boolean result=false;
        try {
                String sql="INSERT INTO khachhang values(?,?,?,?,?,?)";
            PreparedStatement pst=connect.conn.prepareStatement(sql);
            pst.setString(1,khachhang.getMaKH());
            pst.setString(2,khachhang.getTenKH());
            pst.setString(3,khachhang.getDiaCHi());
            pst.setString(4,khachhang.getSDT());
            pst.setString(5,khachhang.getNgaySinh());
            pst.setString(6,khachhang.getGioitinh());
            if (pst.executeUpdate()>=1){
                result=true;
            }

        }catch(Exception e){
            System.out.println(e);
        }
        finally {
            connect.closeConnect();
        }
        return result;
    }

    public boolean checkMaKH(String MaKH){
        connect=new getMySQLConnection();
        boolean result=false;
        try {
            String sql="SELECT MaKH FROM khachhang WHERE MaKH='"+MaKH+"'";
            Statement st=connect.conn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            result=rs.next();

        }catch(Exception ex){
            System.out.println(ex);
        }finally {
            connect.closeConnect();
        }
        return result;
    }

    public boolean checkTenKhachHang(String TenKh){
        connect=new getMySQLConnection();
        try{
            connect=new getMySQLConnection();
            String sql="SELECT * FROM  khachhang WHERE TenKH='"+TenKh+"'";
            Statement st=connect.conn.createStatement();


            ResultSet rs= st.executeQuery(sql);

            while(rs.next()){
                String ten_kh=rs.getString("TenKH");

                if (ten_kh.equals(TenKh)){

                    return false;
                }

            }


        }
        catch (Exception e){
            System.out.println(e);
        }finally {
            connect.closeConnect();
        }
        return true;
    }

    public boolean checksdtkh(String SDTKh){
        connect=new getMySQLConnection();
        try{
            connect=new getMySQLConnection();
            String sql="SELECT * FROM khachhang WHERE SDT='"+SDTKh+"'";
            Statement st=connect.conn.createStatement();


            ResultSet rs= st.executeQuery(sql);

            while(rs.next()){
                String SDT=rs.getString("SDT");

                if (SDT.equals(SDTKh)){

                    return false;
                }

            }


        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            connect.closeConnect();
        }
        return true;
    }

    public boolean ChangeDB(String makh, String tenkh, String diachi,String sdt,String ngaysinh, String gioitinh) {
        connect = new getMySQLConnection();
        String query = "UPDATE khachhang SET "

                +"MaKH='" +makh
                +"',TenKH='" +tenkh
                +"',DiaChi='" +diachi
                +"',SDT='" +sdt
                +"',NgaySinh='" +ngaysinh
                +"',GioiTinh='" +gioitinh
                +"' WHERE MaKH='"
                +makh+"'";
        boolean check = connect.sqlUpdate(query);
        connect.closeConnect();
        System.out.println("check"+check);
        return check;
    }

    public boolean DeleteDB(String makh) {
        connect = new getMySQLConnection();

        String query = "DELETE FROM khachhang WHERE khachhang.MaKH = '"+makh+"'" ;
        boolean check = connect.sqlDelete(query);
        return check;
    }


    public boolean search(String makh) {
        getMySQLConnection connect=new getMySQLConnection();
        try {


            Statement st = connect.conn.createStatement();

            String sql1 = " SELECT * FROM khachhang WHERE MaKH='" + makh + "'";

            ResultSet rs = st.executeQuery(sql1);

            // System.out.println(rs);


            while (rs.next()) {
                if (rs.getString("MaKH").equals(makh)){
                    return true;
                }


//                System.out.println("\n\nSan pham vua tim duoc la: ");
//                System.out.println("Ma san pham: " + rs.getInt("MaSP"));
//                System.out.println("Ten san pham: " + rs.getString("TenSP"));
//                System.out.println("Gia san pham: " + rs.getDouble("Gia"));
//                System.out.println("Ten loai san pham: " + rs.getString("TenLoaiSP"));
            }


        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

}
