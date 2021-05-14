package GUI;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JPanel;

public class ThongKeGUI {
	private JPanel nhaphang= new TKNhapHangGUI();
	private JPanel banhang= new TKBanHangGUI(); 
	private float doanhthu =0;
	private float chiphi=0;
	private float loinhuan = 0;
	public ThongKeGUI() {
		try {
			doanhthu = DocFileBanHang();
			System.out.println(doanhthu);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private float DocFileBanHang() throws IOException {
		FileInputStream file = null;
		DataInputStream data = null;
		float tien=0;
		try {
			file = new FileInputStream("banhang.txt");
			data = new DataInputStream(file);
			
			tien= data.readFloat();
			
			
			file.close();
			data.close();
		//	for( int i=0;i<SoLuongSP;i++)
		//		sp[i].XuatSP();
		}
		catch (EOFException e) {
		}
		return tien;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new ThongKeGUI();
	}

}
