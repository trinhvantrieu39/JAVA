package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//import ConnectDatabase.*;
import DTO.*;
public class DangKyDAO {
	 getMySQLConnection kn;
	    public boolean insertUser( String TenTK, String MatKhau, String MaNV, String MaQuyen){
	        try {
	        	 kn=new getMySQLConnection();
	        	 kn.Connect();
	                String sql1 = "INSERT INTO  `taikhoan` (`MaTK`,`MatKhau`,`MaNV`,`MaQuyen`) VALUES (?,?,?,?)";


	                PreparedStatement pst=kn.conn.prepareStatement(sql1);



	                pst.setString(1,TenTK);
	                pst.setString(2,MatKhau);
	                pst.setString(3,MaNV);

	                pst.setString(4,MaQuyen);



	                pst.executeUpdate();
	                kn.closeConnect();;
	                return true;
	         
	        }catch(Exception e){
	            System.out.println(e);
	        }
	        return  false;
	    }

	    public boolean checkTenTaiKhoan(String Tentaikhoan){
	        try{
	        	 kn=new getMySQLConnection();
	        	 kn.Connect();
	                String sql="SELECT MaTK,MaNV FROM taikhoan WHERE MaTK='"+Tentaikhoan+"'";
	                Statement st=kn.conn.createStatement();


	                ResultSet rs= st.executeQuery(sql);

	                while(rs.next()){
	                    String ten_tk=rs.getString("MaTK");

	                    if (ten_tk.equals(Tentaikhoan)){

	                        return false;
	                    }

	                }
	            
	        }
	        catch (Exception e){
	            System.out.println(e);
	        }
	        return true;
	    }

	    public boolean checkMaNV(String MaNV){
	        try{
	        	 kn=new getMySQLConnection();
	        	 kn.Connect();
	                String sql="SELECT MaNV FROM taikhoan WHERE  MaNV='"+MaNV+"'";
	                Statement st=kn.conn.createStatement();


	                ResultSet rs= st.executeQuery(sql);

	                while(rs.next()){

	                    String Ma_nv=rs.getString("MaNV");
	                    if (Ma_nv.equals(MaNV)){

	                        return false;
	                    }

	                }
	            
	        }
	        catch (Exception e){
	            System.out.println(e);
	        }
	        return true;
	    }

		

}
