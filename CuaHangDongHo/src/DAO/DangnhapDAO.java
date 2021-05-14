package DAO;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import BUS.*;
import DTO.*;
public class DangnhapDAO {
	getMySQLConnection kn;
	 public boolean checkLogin(String tentaikhoan, String matkhau) {

	        try {

	          
	        	kn=new getMySQLConnection();
	        	kn.Connect();

	                String sql = "SELECT MaTK,MatKhau,MaNV,MaQuyen FROM taikhoan WHERE MaTK=? and MatKhau=? ";
	                PreparedStatement ps = kn.conn.prepareStatement(sql);
	                ps.setString(1, tentaikhoan);
	                ps.setString(2, matkhau);

	                ResultSet rs = ps.executeQuery();
	                if (rs.next()) {
	                     System.out.println(tentaikhoan + " " + matkhau);
	                     
	                    return true;

	                } else {

	                    return false;
	                }

	            
	        }
	        catch (Exception e) {
	            System.out.println(e);
	        }
	        kn.closeConnect();;
	        return false;
	    }
	    public void GetInfor(String tendangnhap) throws IOException {
	    	try {
	    		kn=new getMySQLConnection();
	    		kn.Connect();
	    			String sql = "SELECT * FROM taikhoan WHERE `taikhoan`.`MaTK` = '"+tendangnhap+"'";
	    			 Statement stmt = kn.conn.createStatement();
	    			 ResultSet r = stmt.executeQuery(sql);
	    			 while(r.next()) {
		    			 String matk = r.getString("MaTK");
		    			 String mk = r.getString("MatKhau");
		    			 String nv = r.getString("MaNV");
		    			 String quyen = r.getString("MaQuyen");
		    			 GhiFile(matk, mk, nv, quyen);
		    			 
	    			 }
	    		kn.closeConnect();;
	    	}
	    	catch(SQLException e) {
	    		System.out.print(e);
	    	}
	    }
	    public void GhiFile(String matk, String matkhau, String manv, String maquyen) throws IOException {
			File FILE = new File("taikhoan.txt");
			FILE.delete();
			FILE.createNewFile();
			try{
				FileOutputStream file = new FileOutputStream("taikhoan.txt");
				DataOutputStream data = new DataOutputStream(file);
				file = new FileOutputStream("taikhoan.txt");
				data = new DataOutputStream(file);
					data.writeUTF(matk);
					data.writeUTF(matkhau);
					data.writeUTF(manv);
					data.writeUTF(maquyen);
					
				System.out.println("SAVED");

				file.close();
				data.close();
			}
			catch(FileNotFoundException e) {

			}
		}
	    
}
