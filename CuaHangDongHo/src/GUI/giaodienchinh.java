package GUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


//import HoaDon.HoaDonGUI;
//import KhachHang.KhachHangGUI;
//import LoaiSanPham.LoaiSanPhamGUI;
//import NhaCungCap.NhaCungCapGUI;
//import NhanVien.NhanVienGUI;
//import NhapHang.NhapHangGUI;
//import PhieuNhap.PhieuNhapGUI;
//
//import SanPham.SanPhamGUI;
//import TaiKhoan.TaiKhoanGUI;
//
//import PhanQuyen.PhanQuyenGUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.lang.StackWalker.Option;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import DAO.*;
import BUS.*;
import DTO.*;


public class giaodienchinh extends JFrame {
private int height = 700;
private int width = 1300;
private JList<LeftMenuItem> list;
private BorderLayout mainLayout = new BorderLayout(0,0);

private JPanel content;
private JPanel top;
private JPanel left;

private JLabel luachon;
private JLabel donhangOut=new JLabel();
private JLabel donhangIn =new JLabel();

private JButton logout;

private JPanel cuahang;
private JPanel nhaphang;
private JPanel nhanvien;
private JPanel sanpham;
private JPanel loaisanpham;
private JPanel nhacungcap;
private JPanel khachhang;
private JPanel hoadon;
private JPanel phieunhap;
private JPanel taikhoan;
private JPanel phanquyen;

private String TenNguoiDung ;
private String tentk;
private ThongTin tt =null;
private String quyennguoidung="";
//public giaodienchinh(String TenNguoiDung) {
public giaodienchinh() {
	ThongTinBUS thongtin = new ThongTinBUS();
	TenNguoiDung = thongtin.tennguoidung();
	
	
	try {
		tt = thongtin.DocFile();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	tentk= tt.getMatk();
	quyennguoidung = tt.getQuyen();
	//setLayout(mainLayout);
	setSize(width,height);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	top =CreateTopPanel(TenNguoiDung);
	
	left = CreateLeftPanel();
	
	content = new JPanel();
	content.setLayout(new BorderLayout());
	
	
	
	JPanel container = new JPanel();
	container.setLayout(mainLayout);
	container.add(top,BorderLayout.NORTH);
	container.add(left,BorderLayout.WEST);
	container.add(content,BorderLayout.CENTER);
	container.setBorder(new EmptyBorder(0,10,10,10));
	//khoa thay doi kich thuoc
	setResizable(false);
	add(container);
	setVisible(true);
	
	/*
	//thiet lap giua man hin
	//setLocationRelativeTo(null);
	setVisible(true);
	
	
	//full man hinh
	//setExtendedState(JFrame.MAXIMIZED_BOTH);
	*/
}
	

	public JPanel CreateTopPanel(String Ten) {
		JPanel panel = new JPanel(); //panel chinh	
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 18);
		panel.setLayout(new BorderLayout());
		
		ImageIcon iconOut = new ImageIcon();// icon dangxuat
		
		

		iconOut=new ImageIcon(getClass().getResource("/images/logout.png"));
		logout = new JButton(Ten);
		logout.setIcon(iconOut);
		logout.setBackground(new Color(220,220,220));
		logout.setFont(font);
		
		//tennguoidung = new JLabel("")
		//dangxuat.add(logout);
		//dangxuat.add(tennguoidung);
		
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
                
                if(JOptionPane.showConfirmDialog(content, "Bạn có muốn đăng xuất?","Chú ý",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                	setVisible(false);
                	new FormDangNhap();
                }
            }
		});
		
		luachon = new JLabel("", SwingConstants.CENTER);
		
		luachon.setFont(font);
		
		ImageIcon pass = new ImageIcon();// icon dangxuat
		
		

		pass=new ImageIcon(getClass().getResource("/images/secure.png"));
		
		JButton doimk = new JButton("ChangePassword");
		doimk.setIcon(pass);
		doimk.setBackground(new Color(220,220,220));
		doimk.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				JFrame f= new JFrame();
				JPanel p =new DoiMKGUI(tentk,tt);
				f.setSize(400, 300);;
				f.add(p);
				f.setVisible(true);
				
			}
		});
		panel.add(logout,BorderLayout.WEST);
		panel.add(luachon,BorderLayout.CENTER);
		panel.add(doimk,BorderLayout.EAST);
		
		
		panel.setBorder(new EmptyBorder(10, 10, 10, 30));
		return panel;
	}
	
	

	public JPanel CreateLeftPanel() {
		
		JPanel panel = new JPanel(new BorderLayout());
		JScrollPane sp = new JScrollPane(list = createList());
		panel.add(sp,BorderLayout.CENTER);
		return panel;
	}
	
	private JList<LeftMenuItem> createList() {
		DefaultListModel<LeftMenuItem> model = new DefaultListModel<>();
		model.addElement(new LeftMenuItem("Cửa hàng","shop"));
		model.addElement(new LeftMenuItem("Nhập hàng","checklist"));
		model.addElement(new LeftMenuItem("Sản phẩm","watch"));
		model.addElement(new LeftMenuItem("Loại sản phẩm","phanloai"));
		model.addElement(new LeftMenuItem("Hóa đơn","bill"));
		model.addElement(new LeftMenuItem("Phiếu nhập","phieunhap"));
		
		model.addElement(new LeftMenuItem("Nhân viên","employee"));
		
		model.addElement(new LeftMenuItem("Khách hàng","user"));
		model.addElement(new LeftMenuItem("Nhà cung cấp","company"));
		model.addElement(new LeftMenuItem("Thống kê","thongke"));
		model.addElement(new LeftMenuItem("Phân quyền","shield"));
		model.addElement(new LeftMenuItem("Tài khoản","account"));
		
		
		list = new JList<LeftMenuItem>(model);
		list.setCellRenderer(new LeftMenuItemRenderer());
		
		
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				LeftSelec();
			}
		});
		return list;
	}
	public JPanel ImagePanel(){
	    //Create game panel and attributes
	    JPanel gamePanel = new JPanel();
	    ImageIcon ima = new ImageIcon(new ImageIcon(getClass().getResource("/images/sorry.png")).getImage().getScaledInstance(1000, 600, Image.SCALE_AREA_AVERAGING));
	    JLabel lb = new JLabel(ima);
	    gamePanel.add(lb);
	    //Set Return
	    return gamePanel;
	}
	private void LeftSelec() {
		String nameAction=String.valueOf(list.getSelectedValue());
		//JOptionPane.showConfirmDialog(this, nameAction);
		content.removeAll();
		top.remove(donhangIn);
		top.remove(donhangOut);
		JPanel imag = ImagePanel();
		switch(nameAction){
			case "Cửa hàng":{
				luachon.setText("CỬA HÀNG");
				
				
				cuahang = new CuaHangGUI();
				
				content.add(cuahang);
				break;
			}
			case "Hóa đơn":{
				luachon.setText("HÓA ĐƠN");
				
				hoadon = new HoaDonGUI();
				
				content.add(hoadon);
				break;
			}
			case"Phiếu nhập":{
				luachon.setText("PHIẾU NHẬP");
				
				phieunhap = new PhieuNhapGUI();
				
				content.add(phieunhap);
				break;
			}
			case "Sản phẩm":{
				luachon.setText("SẢN PHẨM");
				if(sanpham == null) {
					sanpham= new SanPhamGUI();
					
				}
				content.add(sanpham);
				
				break;
			}
			case "Loại sản phẩm":{
				luachon.setText("LOẠI SẢN PHẨM");
				if(loaisanpham == null) {
					loaisanpham= new LoaiSanPhamGUI();
					
				}
				content.add(loaisanpham);
				
				break;
			}
			case "Nhập hàng":{
				
				
				luachon.setText("NHẬP HÀNG");
				if(nhaphang ==null) {
				nhaphang = new NhapHangGUI();
				}
				content.add(nhaphang);
				break;
			}
			case "Nhân viên":{
				luachon.setText("NHÂN VIÊN");
				if(quyennguoidung.equals("Q2") || quyennguoidung.equals("Q1")) {
					
					if(nhanvien == null) {
					nhanvien = new NhanVienGUI();
					}
					content.add(nhanvien);
					break;
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Bạn không đủ quyền truy cập!!!");
					content.add(imag);
					break;
				}
				
			}
			case "Thống kê":{
				luachon.setText("THỐNG KÊ");
				if(quyennguoidung.equals("Q2") || quyennguoidung.equals("Q1")) {
					
					
					break;
				}
				else {
					JOptionPane.showMessageDialog(null, "Bạn không đủ quyền truy cập!!!");
					content.add(imag);
					break;
				}
				
			}
			case "Khách hàng":{
				luachon.setText("KHÁCH HÀNG");
			
				khachhang = new KhachHangGUI();
				
				content.add(khachhang);
				break;
			}
			case "Nhà cung cấp":{
				luachon.setText("NHÀ CUNG CẤP");
				if(nhacungcap == null) {
				nhacungcap = new NhaCungCapGUI();
				}
				content.add(nhacungcap);
				break;
			}
			case "Tài khoản":{
				luachon.setText("TÀI KHOẢN");
				if(quyennguoidung.equals("Q1")) {
					
					if(taikhoan ==null) {
					taikhoan = new TaiKhoanGUI();
					}
					content.add(taikhoan);
				}
				else {
					JOptionPane.showMessageDialog(null, "Bạn không đủ quyền truy cập!!!");
					content.add(imag);
				}
				break;
			}
			case "Phân quyền":{
				luachon.setText("PHÂN QUYỀN");
				if(quyennguoidung.equals("Q1")) {
					
					if(phanquyen ==null) {
					phanquyen = new PhanQuyenGUI();
					}
					content.add(phanquyen);
				}
				else {
					JOptionPane.showMessageDialog(null, "Bạn không đủ quyền truy cập!!!");
					content.add(imag);
				}
				break;
			}
		}
		
		revalidate();
		repaint();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 new giaodienchinh() ;
		 
		
	}

}
