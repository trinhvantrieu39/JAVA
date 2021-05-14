/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import BUS.*;
import DTO.*;
public class NhanVienDAO {
            String dbUrl="jdbc:mysql://localhost:3306/cuahangdongho";
            String username="root"; String password="";
            Connection con=null;
            Statement stmt=null;
            ResultSet rs=null;
            public NhanVienDAO(){
                if(con==null)
                {
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        con=DriverManager.getConnection(dbUrl, username, password);
                    }
                    catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null,"Lỗi không tìm thấy class.");
                    }
                    catch(SQLException e){
                        JOptionPane.showMessageDialog(null, "Lỗi không tìm thấy SQL");
                    }
                }    
            }
            public ArrayList docDSNV()
            {   
                ArrayList dsnv=new ArrayList<NhanVienDTO>();
                try{
                String qry="select *from NhanVien";
                stmt=con.createStatement();
                rs=stmt.executeQuery(qry);
                while(rs.next()){
                    NhanVienDTO nv=new NhanVienDTO();
                    nv.MaNV=rs.getString(1);
                    nv.TenNV=rs.getString(2);
                    nv.NgaySinh=rs.getDate(3);
                    nv.DiaChi=rs.getString(4);
                    nv.SDT=rs.getString(5);
                    nv.Gioitinh=rs.getString(6);
                    dsnv.add(nv);
                    }
                }
                catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "không đọc được dữ liệu SQL");
                }    
                return dsnv;            }   
            public void them(NhanVienDTO nv){
                try{
                    String qry="insert into NhanVien VALUES(";
                    qry=qry+"'"+nv.MaNV+"'";
                    qry=qry+","+"'"+nv.TenNV+"'";
                    qry=qry+","+"'"+(new SimpleDateFormat("yyyy-MM-dd").format(nv.NgaySinh))+"'";
                    qry=qry+","+"'"+nv.DiaChi+"'";
                    qry=qry+","+"'"+nv.SDT+"'";
                    qry=qry+","+"'"+nv.Gioitinh+"'";
                    qry=qry+")";                    
                    stmt=con.createStatement();
                    if(stmt==null)
                    {
                        JOptionPane.showMessageDialog(null, "ket noi that bai");
                    }
                    stmt.executeUpdate(qry);
                }
                catch(SQLException ex){
                    JOptionPane.showMessageDialog(null,"Lỗi ghi thông tin sinh viên");
                }
    }
    public boolean xoa(String MaNV){
        try{
            String qry="Delete from NhanVien where MaNV='"+MaNV+"'";
            stmt=con.createStatement();
            stmt.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Lỗi xóa sinh viên!");
            return false;
        }
        return true;
    }
    public void sua(NhanVienDTO nv){
        try{
            String qry="Update NhanVien Set ";
            qry=qry+" TenNV='"+nv.TenNV+"'";
            qry=qry+",NgaySinh='"+(new SimpleDateFormat("yyyy-MM-dd").format(nv.NgaySinh))+"'";
            qry=qry+",DiaChi='"+nv.DiaChi+"'";
            qry=qry+",SDT='"+nv.SDT+"'";
            qry=qry+",Gioitinh='"+nv.Gioitinh+"'";
            qry=qry+"  where MaNV='"+nv.MaNV+"'";
            stmt=con.createStatement();
            stmt.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Lỗi cập nhật sửa sinh viên!");
        }
    }
}
