package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Button.*;

import DAO.*;
import BUS.*;
import DTO.*;

public class CuaHangGUI extends JPanel{
	private JTable cuahangTable = new JTable();
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane scr ;
	private JPanel text;
	private JPanel image;
	private JLabel ImageSP = new JLabel();
	private JTextField masp = new JTextField(20);
	private JTextField tensp = new JTextField(20);
	private JTextField loaisp = new JTextField(20);
	private JTextField dongia = new JTextField(20);
	private JTextField timkiem = new JTextField(66);
	private JTextField hinhanh = new JTextField(20);
	private SpinnerNumberModel soluong = new SpinnerNumberModel();	//cài số lượng tối đa -> get bên sản phẩm
	//private int soluongsp;
	private SanPhamBUS sp = new SanPhamBUS();
	private ArrayList<SanPham> dssp = new ArrayList<>();
	private JPanel panelgiohang = new GioHangGUI(dssp);
	private JFrame fgiohang = new JFrame();
	
	public CuaHangGUI(){
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JPanel info = CreateInput();
		add(info);
		cuahangTable.setModel(model);
		model.addColumn("Mã SP");
		model.addColumn("Tên SP");
		model.addColumn("Loại SP");
		model.addColumn("Đơn Giá");
		model.addColumn("Số lượng");
		model.addColumn("Hình ảnh");
		ShowSP();	
		cuahangTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent evt) {
				// TODO Auto-generated method stub
			
				Click(evt);
			
			}
		});
		scr = new JScrollPane(cuahangTable);
		add(scr);
	}
	private JPanel CreateInput(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		setBorder(border);
				
		image = new JPanel();
		image.setBorder(border);
		image.setPreferredSize(new Dimension(250, 250));
		image.add(ImageSP);
		
		text = new JPanel();
		text.setPreferredSize(new Dimension(500,250));
		text.setLayout(new BoxLayout(text,BoxLayout.Y_AXIS));
		
		JPanel p0 = new JPanel();
		timkiem.setBorder(BorderFactory.createTitledBorder(border,"Tìm kiếm"));
		addDocumentListener(timkiem);
		p0.add(timkiem);
		
		JPanel p1 = new JPanel();
		masp.setBorder(BorderFactory.createTitledBorder(border,"Mã sản phẩm"));
		masp.setEditable(false);
		p1.add(masp);
		
		loaisp.setBorder(BorderFactory.createTitledBorder(border,"Loại sản phẩm"));
		loaisp.setEditable(false);
		p1.add(loaisp);
		
		tensp.setBorder(BorderFactory.createTitledBorder(border,"Tên sản phẩm"));
		tensp.setEditable(false);
		p1.add(tensp);
		
		JPanel p2 = new JPanel();
		dongia.setBorder(BorderFactory.createTitledBorder(border,"Đơn giá"));
		dongia.setEditable(false);
		
		
		hinhanh.setBorder(BorderFactory.createTitledBorder(border,"Hình ảnh"));
		hinhanh.setEditable(false);
		
		p2.add(dongia);
		p2.add(hinhanh);
		
		
		soluong = new SpinnerNumberModel(0,0,0,1);	//get soluong san pham
		addModel("Số lượng",soluong,p2);
		
		JButton them =new ButtonAdd();
		them.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				 
				them(me);
				
			}
		});

		//gio hang
		JButton giohang = new ButtonGioHang();
		giohang.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				showgiohang(panelgiohang);
				
				
			}
		});
		
		JPanel p3 = new JPanel();
		p3.add(them);
		p3.add(giohang);
		
		text.add(p0);
		text.add(p1);
		text.add(p2);
		text.add(p3);
		
		panel.add(text,BorderLayout.CENTER);
		panel.add(image,BorderLayout.WEST);
		
		text.setBorder(new EmptyBorder(20, 0, 0, 0));
		return panel;
	}
	private void addModel(String title, SpinnerNumberModel model, JPanel ptext) {
		
		//soluongsp = soluong.getNumber().intValue();
		ptext.add(new JLabel(title));
		JSpinner spinner = new JSpinner(model);
		spinner.setPreferredSize(new Dimension(80,30));
		
		ptext.add(spinner);
	}
	private void ShowSP() {
		
		for(SanPham sph : sp.getDssp()) {
		Object[] obj = {sph.masp,sph.tensp,sph.maloai, sph.dongia,sph.soluong, sph.hinhanh};
		model.addRow(obj);
		}
		
	}

	private void Click(ListSelectionEvent evt) {
		int i = cuahangTable.getSelectedRow();
		if(i>=0) {
			
			ImageIcon ima =  new ImageIcon(new ImageIcon(getClass().getResource("/images/ImageSP/"+(String)model.getValueAt(i, 5))).getImage().getScaledInstance(170, 250, Image.SCALE_AREA_AVERAGING));
			ImageSP.setIcon(ima);
			
			masp.setText((String)model.getValueAt(i, 0));
			tensp.setText((String)model.getValueAt(i, 1));
			loaisp.setText((String)model.getValueAt(i, 2));
			dongia.setText(String.valueOf(model.getValueAt(i, 3)));
			//soluong.setValue(model.getValueAt(i, 4));
			int sl =(int) model.getValueAt(i, 4);	// số lượng sản phẩm hiện có
			//soluong = new SpinnerNumberModel(0, 0, sl, 1);
			soluong.setMaximum(sl);
			
			hinhanh.setText((String)model.getValueAt(i, 5));
			
			//soluongsp = (int) model.getValueAt(i, 4);
			}
			
		}
	private void them(MouseEvent me) {
		int i = cuahangTable.getSelectedRow();
		if(i>=0) {
			String masp = (String)model.getValueAt(i, 0);
			String tensp = (String)model.getValueAt(i, 1);
			String maloai = (String)model.getValueAt(i, 2);
			int sluong	= (int)soluong.getValue();
			float dongia = (float)model.getValueAt(i, 3);
			String hinhanh = (String)model.getValueAt(i, 5);
			if(sluong == 0) {
				JOptionPane.showInternalMessageDialog(null, "Xin chọn số lượng");
				return;
			}
			for(SanPham sp : dssp) {
				if(sp.getMasp().equals(masp)) {
					sp.setSoluong(sp.getSoluong() + sluong);
					//hiện cửa sổ giỏ hàng
					panelgiohang = new GioHangGUI(dssp);					
					fgiohang.setVisible(false);					
					showgiohang(panelgiohang);
					//
					
					return;
				}
			}
			SanPham sanpham= new SanPham(masp,tensp,maloai,sluong,dongia,hinhanh);
			
			dssp.add(sanpham);
			//hiện cửa sổ giỏ hàng
			panelgiohang = new GioHangGUI(dssp);					
			fgiohang.setVisible(false);					
			showgiohang(panelgiohang);
			//
			
		}
	}
	private void showgiohang(JPanel panelgiohang) {
		fgiohang = new JFrame();
		//set vị trí giữa màn hình
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() ) / 4);
	    int y = (int) ((dimension.getHeight() )/14);
	    fgiohang.setLocation(x, y);
	    //
		fgiohang.setSize(700,400);
		fgiohang.add(panelgiohang);
		fgiohang.setVisible(true);
	}
	private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                Search(txField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	Search(txField.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
            	Search(txField.getText());
            }
        });
    }
	private void Search(String value) {
		ArrayList<SanPham> ds = sp.Search(value);
		DefaultTableModel nmodel = new DefaultTableModel();
		cuahangTable.setModel(nmodel);
		nmodel.addColumn("Mã SP");
		nmodel.addColumn("Tên SP");
		nmodel.addColumn("Loại SP");
		nmodel.addColumn("Đơn Giá");
		nmodel.addColumn("Số lượng");
		nmodel.addColumn("Hình ảnh");


		for(SanPham sph : ds) {
			Object[] obj = {sph.getMasp(),sph.getTensp(), sph.getMaloai(), sph.getDongia(), sph.getSoluong(), sph.getHinhanh()};
			nmodel.addRow(obj);
			}
		
	}
	public static void main(String[] args) {
		JFrame f = new JFrame();
		JPanel p = new CuaHangGUI();
		f.add(p);
		f.setVisible(true);
		f.pack();
	}
}
