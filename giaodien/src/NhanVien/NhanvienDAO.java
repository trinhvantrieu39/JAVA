/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NhanVien;
import NhanVien.NhanvienDTO;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane; 
public class NhanvienDAO {
            String dbUrl="jdbc:mysql://localhost:3306/cuahangdongho";
            String username="root"; String password="";
            Connection con=null;
            Statement stmt=null;
            ResultSet rs=null;
            public NhanvienDAO(){
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
                ArrayList dsnv=new ArrayList<NhanvienDTO>();
                try{
                String qry="select *from nhanvien";
                stmt=con.createStatement();
                rs=stmt.executeQuery(qry);
                while(rs.next()){
                    NhanvienDTO nv=new NhanvienDTO();
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
                    JOptionPane.showMessageDialog(null, "không tìm thấy SQL");
                }    
                return dsnv;            }   
            public void them(NhanvienDTO nv){
                try{
                    String qry="insert into nhanvien VALUES(";
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
    public void xoa(String MaNV){
        try{
            String qry="Delete from nhanvien where MaNV='"+MaNV+"'";
            stmt=con.createStatement();
            stmt.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Lỗi xóa sinh viên!");
        }
    }
    public void sua(NhanvienDTO nv){
        try{
            String qry="Update nhanvien Set ";
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
