package SanPham;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class SanPhamGUI extends JPanel{
	private JTable sanphamTable = new JTable();
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane scr ;
	private JPanel text;
	private JPanel image;
	private JLabel ImageSP = new JLabel();
	private JTextField masp = new JTextField(20);
	private JTextField tensp = new JTextField(20);
	private JTextField loaisp = new JTextField(20);
	private JTextField dongia = new JTextField(20);
	private JTextField hinhanh = new JTextField(20);
	private JTextField timkiem = new JTextField(66);
	
	private SpinnerNumberModel soluong = new SpinnerNumberModel();	//cài số lượng tối đa -> get bên sản phẩm
	
	private SanPhamBUS sp = new SanPhamBUS();
	
	public SanPhamGUI(){
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JPanel info = CreateInput();
		add(info);
		
		sanphamTable.setModel(model);
		model.addColumn("Mã SP");
		model.addColumn("Tên SP");
		model.addColumn("Loại SP");
		model.addColumn("Đơn Giá");
		model.addColumn("Số lượng");
		model.addColumn("Hình ảnh");
		
				
		//model.addRow(new Object[] {"masp", "tensp", "loaisp", "200", "asd.png", "20"});
		ShowSP();
		
		sanphamTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent evt) {
				// TODO Auto-generated method stub
			Click(evt);
			}
		});
		
		
		scr = new JScrollPane(sanphamTable);
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
		
		p1.add(masp);
		
		loaisp.setBorder(BorderFactory.createTitledBorder(border,"Loại sản phẩm"));
		
		p1.add(loaisp);
		
		tensp.setBorder(BorderFactory.createTitledBorder(border,"Tên sản phẩm"));
		
		p1.add(tensp);
		
		JPanel p2 = new JPanel();
		dongia.setBorder(BorderFactory.createTitledBorder(border,"Đơn giá"));
		hinhanh.setBorder(BorderFactory.createTitledBorder(border,"Hình ảnh"));
		
		p2.add(dongia);
		p2.add(hinhanh);
		
		soluong = new SpinnerNumberModel(0, 0, 1000, 1);	//get soluong san pham
		addModel("Số lượng",soluong,p2);
		
		JButton them =new ButtonAdd();
		them.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				Them(me);
			}
		});
		JButton sua = new ButtonChange();
		sua.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				 
				Sua(me);
				
			}
		});
		JButton xoa = new ButtonRemove();
		xoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				 
				Xoa(me);
				
			}
		});
		
		JPanel p3 = new JPanel();
		p3.add(them);
		p3.add(sua);
		p3.add(xoa);
		
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
	//show textfield khi nhap chon model
	private void Click(ListSelectionEvent evt) {
		int i = sanphamTable.getSelectedRow();
		if(i>=0) {
			masp.setText((String)model.getValueAt(i, 0));
			tensp.setText((String)model.getValueAt(i, 1));
			loaisp.setText((String)model.getValueAt(i, 2));
			dongia.setText(String.valueOf(model.getValueAt(i, 3)));
			soluong.setValue(model.getValueAt(i, 4));
			
			hinhanh.setText((String)model.getValueAt(i, 5));
			//soluongsp = (int) model.getValueAt(i, 4);
		}
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
	private void Sua(MouseEvent me) {
		int i =sanphamTable.getSelectedRow();
		if(i>=0) {		
			String MaSP = masp.getText();
			String TenSP = tensp.getText();
			String MaLoai = loaisp.getText();
			Float DonGia =Float.parseFloat(dongia.getText());
			int SoLuong = (int) soluong.getNumber();
			String HinhAnh = hinhanh.getText();
			if(masp.getText().equals("") || tensp.getText().equals("") || dongia.getText().equals("") || hinhanh.getText().equals("") || loaisp.getText().equals("") ) {
				JOptionPane.showMessageDialog(this, "Chưa điền đủ thông tin");
				return;
			}
			if(sp.update(MaSP, TenSP, MaLoai, DonGia,SoLuong,HinhAnh)) {
				//todo
				
				//ShowSP();
				
				model.setValueAt(MaSP,i,0);
				model.setValueAt(TenSP, i, 1);
				model.setValueAt(MaLoai, i, 2);
				model.setValueAt(DonGia, i, 3);
				model.setValueAt(HinhAnh,i,4);
				model.setValueAt(SoLuong, i, 5);
				
				JOptionPane.showMessageDialog(this, "Sửa thành công");
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm để sửa");
		}
		 
	}
	private void Them(MouseEvent me) {
		if(masp.getText().equals("") || tensp.getText().equals("") || dongia.getText().equals("") || hinhanh.getText().equals("") || loaisp.getText().equals("") ) {
			JOptionPane.showMessageDialog(this, "Chưa điền đủ thông tin");
			return;
		}
		String MaSP = masp.getText();
		String TenSP = tensp.getText();
		String MaLoai = loaisp.getText();
		Float DonGia =Float.parseFloat(dongia.getText());
		int SoLuong = (int) soluong.getNumber();
		String HinhAnh = hinhanh.getText();
		SanPham sanpham= new SanPham(MaSP, TenSP, MaLoai, SoLuong, DonGia, HinhAnh);
		
					
		if(sp.Add(sanpham)){
			model.addRow(new Object[] {MaSP, TenSP, MaLoai, DonGia,SoLuong, HinhAnh});
			JOptionPane.showMessageDialog(this, "Thêm thành công");
			ShowSP();
		}
	}
	private void Xoa(MouseEvent me) {
		int i = sanphamTable.getSelectedRow();
		if(i>=0) {
			
			if(JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa?", "Chú ý",JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION) {
				String maSP =String.valueOf(model.getValueAt(i, 0)); 
				System.out.println(maSP);
				if(sp.Delete(maSP)){
					model.removeRow(i);
					JOptionPane.showMessageDialog(this, "Xóa thành công");
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm ");
		}
	}
	private void Search(String value) {
		ArrayList<SanPham> ds = sp.Search(value);
		DefaultTableModel newModel = new DefaultTableModel();
		sanphamTable.setModel(newModel);
		newModel.addColumn("Mã SP");
		newModel.addColumn("Tên SP");
		newModel.addColumn("Loại SP");
		newModel.addColumn("Đơn Giá");
		newModel.addColumn("Số lượng");
		newModel.addColumn("Hình ảnh");
		for(SanPham sph : ds) {
			Object[] obj = {sph.masp,sph.tensp,sph.maloai, sph.dongia,sph.soluong, sph.hinhanh};
			newModel.addRow(obj);
			}
		
	}
	public static void main(String[] args) {
		JFrame f = new JFrame();
		JPanel p = new SanPhamGUI();
		f.add(p);
		f.setVisible(true);
		f.pack();
	}
}
