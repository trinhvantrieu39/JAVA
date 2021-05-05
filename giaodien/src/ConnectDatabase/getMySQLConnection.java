package ConnectDatabase;
import java.sql.*;

import javax.swing.JOptionPane;

public class getMySQLConnection {
	
	Connection conn = null;
	Statement stmt = null;
	public getMySQLConnection() {
		checkDriver();
		Connect();
	}
	public void Connect(){
		final String host = "localhost";
		final String user = "root";
		final String pass = "";
		final String dbName = "cuahangdongho";
		try {
		
		String connectionURL = "jdbc:mysql://" + host+ ":3306/" + dbName;
		Connection conn =DriverManager.getConnection(connectionURL, user, pass);
		stmt = conn.createStatement();
		System.out.println("Đã kết nối tới database");
		}
		catch(SQLException e) {
			System.err.println("Không thể kết nối!");
			JOptionPane.showMessageDialog(null, "Không thể kết nối tới database!!!");
			
		}
		
	}
	public ResultSet sqlQuery(String r) {
		try {
			ResultSet result = stmt.executeQuery(r);
			return result;
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể lấy dữ liệu từ database!");
		}
		return null;
	}
	public boolean sqlUpdate(String r) {
		
		try{
			stmt.executeUpdate(r);
			return true;
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"Không thể sửa dữ liệu!!!");
			return false;
		}
	}
	public boolean sqlInsert(String r) {
		try {
			stmt.executeUpdate(r);
			return true;
			
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"Không thể thêm dữ liệu!!!");
			return false;
		}
	}
	public boolean sqlDelete(String r) {
		try {
			stmt.executeUpdate(r);
			return true;
			
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"Không thể xóa dữ liệu!!!");
			return false;
		}
	}
	public void checkDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			JOptionPane.showMessageDialog(null, "Không tìm thấy driver");
		}
	}
	public void closeConnect() {
		try {
			if(conn!= null) {
				conn.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể đóng DB!!!");
		}
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new getMySQLConnection();
	}
}
