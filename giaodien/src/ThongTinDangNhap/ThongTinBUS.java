package ThongTinDangNhap;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

public class ThongTinBUS {
	private ThongTin thongtin;
	ThongTinDAO dao =new ThongTinDAO();
	public void ThongTinBUS() {
		try {
			thongtin = DocFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void GhiFile(ThongTin thongtin) throws IOException {
		File FILE = new File("taikhoan.txt");
		FILE.delete();
		FILE.createNewFile();
		try{
			FileOutputStream file = new FileOutputStream("taikhoan.txt");
			DataOutputStream data = new DataOutputStream(file);
			file = new FileOutputStream("taikhoan.txt");
			data = new DataOutputStream(file);
				data.writeUTF(thongtin.getMatk());
				data.writeUTF(thongtin.getMatkhau());
				data.writeUTF(thongtin.getManv());
				data.writeUTF(thongtin.getQuyen());
				
			System.out.println("SAVED");

			file.close();
			data.close();
		}
		catch(FileNotFoundException e) {

		}
	}
	public ThongTin DocFile() throws IOException {
		ThongTin thongtin2 = null;
		FileInputStream file = null;
		DataInputStream data = null;
		try {
			file = new FileInputStream("taikhoan.txt");
			data = new DataInputStream(file);
			
			String tk= data.readUTF();
			String mk=data.readUTF();
			String nv = data.readUTF();
			String quyen = data.readUTF();
			thongtin2 =new ThongTin(tk, mk, nv, quyen);
			file.close();
			data.close();
		//	for( int i=0;i<SoLuongSP;i++)
		//		sp[i].XuatSP();
		}
		catch (EOFException e) {
		}
		return thongtin2;
	}
	public String tennguoidung () {
		try {
			thongtin =DocFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String manv = thongtin.getManv();
		String ten="";
		try {
			ten = dao.TenNguoiDung(manv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ten;
	}
}
