package LoaiSanPham;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Button.*;
import NhanVien.DateLabelFormatter;
import SanPham.SanPham;
import SanPham.SanPhamBUS;
import ThongTinDangNhap.ThongTin;
import ThongTinDangNhap.ThongTinBUS;
import net.sourceforge.jdatepicker.impl.*;

public class LoaiSanPhamGUI extends JPanel{
	private BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
	private JTable lspTable = new JTable();
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane sp ;
	
	private Border border= BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	
	private JTextField malsp = new JTextField(15);
	private JTextField tenlsp = new JTextField(15);
	
	private LoaiSanPhamBUS lsp = new LoaiSanPhamBUS();
	private ThongTinBUS ttbus = new ThongTinBUS();
	private String quyen;
	
	
public LoaiSanPhamGUI(){
	setLayout(layout);
	setBorder(border);
	JPanel panel = CreateInfo();
	lspTable.setModel(model);
	model.addColumn("Mã hóa đơn");
	model.addColumn("Tên loại sản phẩm");
	
	ShowLSP();
	
	lspTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent evt) {
			// TODO Auto-generated method stub
		
			Click(evt);
		
		}
	});
	
	sp = new JScrollPane(lspTable);
	add(panel);
	add(sp);
}
	private JPanel CreateInfo() {
		
		JButton them = new ButtonAdd();
		JButton sua = new ButtonChange();
		JButton xoa = new ButtonRemove();
		try {
			ThongTin thongtin = ttbus.DocFile();
			quyen = thongtin.getQuyen();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JPanel panel = new JPanel();
		
		JPanel  tim= new JPanel();
		JTextField timkiem = new JTextField(20);
		timkiem.setBorder(BorderFactory.createTitledBorder(border,"Tìm kiếm"));
		addDocumentListener(timkiem);
		
		tim.add(timkiem);
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center,BoxLayout.Y_AXIS));
		
		malsp.setBorder(BorderFactory.createTitledBorder(border,"Mã loại sản phẩm"));
		tenlsp.setBorder(BorderFactory.createTitledBorder(border,"Tên loại sản phẩm"));
		
	
		
		JPanel lef = new JPanel();
		lef.add(malsp);
		lef.add(tenlsp);
	
	
			
		center.add(lef);
		
		//quyền
				if(quyen.equals("Q3")) {
					them.setEnabled(false);
					sua.setEnabled(false);
					xoa.setEnabled(false);
				}
				else {
				them.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent me) {
						Them(me);
					}
				});
				
				
				sua.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent me) {
						 
						Sua(me);
						
					}
				});
				
				
				xoa.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent me) {
						 
						Xoa(me);
						
					}
				});
				}
		
		
		JPanel right = new JPanel();
		right.setLayout(new FlowLayout());
		right.add(them);
		right.add(sua);
		right.add(xoa);
		center.add(right);
		
	
		add(center);
		add(tim);
		return panel;
	}
		private void ShowLSP() {
			
			for(LoaiSanPham sph : lsp.getDssp()) {
			Object[] obj = {sph.getMalsp(),sph.getTenlsp()};
			model.addRow(obj);
			}
			
		}
		private void Click(ListSelectionEvent evt) {
			int i = lspTable.getSelectedRow();
			if(i>=0) {
			
				malsp.setText((String)model.getValueAt(i, 0));
				tenlsp.setText((String)model.getValueAt(i, 1));
				
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
		private void Search(String value) {
			ArrayList<LoaiSanPham> ds = lsp.Search(value);
			DefaultTableModel newModel = new DefaultTableModel();
			lspTable.setModel(newModel);
			newModel.addColumn("Mã SP");
			newModel.addColumn("Tên SP");
	
			for(LoaiSanPham sph : ds) {
				Object[] obj = {sph.getMalsp(),sph.getTenlsp()};
				newModel.addRow(obj);
				}
			
		}
		private void Them(MouseEvent me) {
			if(malsp.getText().equals("") || tenlsp.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Chưa điền đủ thông tin");
				return;
			}
			String MaSP = malsp.getText();
			String TenSP = tenlsp.getText();
			
			LoaiSanPham sanpham= new LoaiSanPham(MaSP, TenSP);
			
						
			if(lsp.Add(sanpham)){
				model.addRow(new Object[] {MaSP, TenSP});
				JOptionPane.showMessageDialog(this, "Thêm thành công");
				
			}
		}
		private void Sua(MouseEvent me) {
			int i =lspTable.getSelectedRow();
			if(i>=0) {		
				String MaSP = malsp.getText();
				String TenSP = tenlsp.getText();
				
				if(malsp.getText().equals("") || tenlsp.getText().equals("") ) {
					JOptionPane.showMessageDialog(this, "Chưa điền đủ thông tin");
					return;
				}
				if(lsp.update(MaSP, TenSP)) {
					//todo
					
					//ShowSP();
					
					model.setValueAt(MaSP,i,0);
					model.setValueAt(TenSP, i, 1);
					
					JOptionPane.showMessageDialog(this, "Sửa thành công");
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm để sửa");
			}
			 
		}
		
		private void Xoa(MouseEvent me) {
			int i = lspTable.getSelectedRow();
			if(i>=0) {
				
				if(JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa?", "Chú ý",JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION) {
					String maSP =String.valueOf(model.getValueAt(i, 0)); 
					System.out.println(maSP);
					if(lsp.Delete(maSP)){
						model.removeRow(i);
						JOptionPane.showMessageDialog(this, "Xóa thành công");
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm ");
			}
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		JPanel p= new LoaiSanPhamGUI();
		
		f.add(p);
		f.pack();
		f.setVisible(true);
	}

}
