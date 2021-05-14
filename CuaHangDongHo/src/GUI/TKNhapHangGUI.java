package GUI;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.SanPhamBUS;
import BUS.ctpnBUS;
import DTO.SanPham;
import DTO.ctpn;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
public class TKNhapHangGUI extends JPanel{
	private JTable banhangTable = new JTable();
	
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane scr = new JScrollPane();
	//private JTextField timkiem = new JTextField(20);
	private float tongtien;
	SanPhamBUS spBUS =new SanPhamBUS();
	ctpnBUS ctBUS = new ctpnBUS();
	public TKNhapHangGUI() {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		
		JPanel top = new JPanel();
		
		
		/*
		timkiem.setBorder(BorderFactory.createTitledBorder(border,"Tìm kiếm"));
		top.add(timkiem);
		top.setBorder(new EmptyBorder(20,0,0,0));
		*/
		banhangTable.setModel(model);
		model.addColumn("Mã SP");
		model.addColumn("Tên SP");
		model.addColumn("Loại SP");
		model.addColumn("Số lượng");
		model.addColumn("Đơn giá nhập");
		model.addColumn("Tiền nhập");
		
		ShowTable();
		
		scr = new JScrollPane(banhangTable);
		//mid.add(scr);
		
		
		add(top);
		add(scr);
	}

	private void ShowTable() {
		
		ArrayList<SanPham> dssp = spBUS.getDssp();
		ArrayList<ctpn> dsct = ctBUS.getDssp();
		int soluong=0;
		tongtien =0;
		for(SanPham sp : dssp) {	//thiết lập listsp với mỗi sp có số lượng bằng 0
			sp.setSoluong(0);
		}
		for(SanPham sp :dssp) {
			for(ctpn ct : dsct) {
				if(sp.getMasp().equals(ct.getMaSP())) {	//nếu cùng mã thì cộng dồn số lượng bán vào
					sp.setSoluong(sp.getSoluong()+ct.getSoLuong());
					sp.setDongia(ct.getDonGia()); 	//set lại giá nhập sản phẩm
				}
			}
			if(sp.getSoluong()!=0) {	//k lấy sản phẩm chưa bán
				soluong += sp.getSoluong();
				tongtien += sp.getSoluong()*sp.getDongia();
				Object[] obj= {sp.getMasp(),sp.getTensp(), sp.getMaloai(), sp.getSoluong(), sp.getDongia(), sp.getSoluong()*sp.getDongia()};
				model.addRow(obj);
			}
		}
		Object[] obj1 = {};
		model.addRow(obj1);
		Object[] obj2 = {"","","TỔNG:",soluong,"",tongtien};
		model.addRow(obj2);
		try {
			GhiFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void GhiFile() throws IOException{
		File FILE = new File("nhaphang.txt");
		FILE.delete();
		FILE.createNewFile();
		try {
			FileOutputStream file = new FileOutputStream("nhaphang.txt");
			DataOutputStream data = new DataOutputStream(file);
			data.writeFloat(tongtien);
			System.out.println("SAVED");
			file.close();
			data.close();
		}
		catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f= new JFrame();
		JPanel banhang = new TKNhapHangGUI();
		f.add(banhang);
		f.pack();
		f.setVisible(true);
		
	}

}
