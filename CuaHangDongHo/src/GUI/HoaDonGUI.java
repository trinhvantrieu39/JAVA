package GUI;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import DAO.*;
import BUS.*;
import DTO.*;
import net.sourceforge.jdatepicker.impl.*;

public class HoaDonGUI extends JPanel{
	private BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
	private JTable hoadonTable = new JTable();
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane sp ;
	
	private Border border= BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	
	private JTextField mahd = new JTextField(15);
	private JTextField manv = new JTextField(15);
	

	private JTextField makh = new JTextField(15);
	
	private JTextField tongtien = new JTextField(15);
	private JTextField date = new JTextField(15);
	private HoaDonBUS hd = new HoaDonBUS();
	private JButton see = new ButtonShow();
	
public HoaDonGUI(){
	setLayout(layout);
	setBorder(border);
	JPanel panel = CreateInfo();
	hoadonTable.setModel(model);
	model.addColumn("Mã hóa đơn");
	model.addColumn("Mã NV");
	model.addColumn("Mã KH");
	model.addColumn("Ngày lập");
	model.addColumn("Tổng tiền");
	
	
	sp = new JScrollPane(hoadonTable);
	ShowHD();
	hoadonTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent evt) {
			// TODO Auto-generated method stub
		
			Click(evt);
		
		}
	});
	add(panel);
	add(sp);
}
private JPanel CreateInfo() {
	JPanel panel = new JPanel();
	
	JPanel  tim= new JPanel();
	JTextField timkiem = new JTextField(20);
	timkiem.setBorder(BorderFactory.createTitledBorder(border,"Tìm kiếm"));
	addDocumentListener(timkiem);
	
	tim.add(timkiem);
	see.addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent me) {
			 
			xemchitiet(me);
			
		}
	});
	see.setEnabled(false);
	tim.add(see);
	JPanel center = new JPanel();
	center.setLayout(new BoxLayout(center,BoxLayout.Y_AXIS));
	tongtien.setBorder(BorderFactory.createTitledBorder(border,"Tổng tiền"));
	manv.setBorder(BorderFactory.createTitledBorder(border,"Mã nhân viên"));
	mahd.setBorder(BorderFactory.createTitledBorder(border,"Mã hóa đơn"));
	
	JPanel ns = new JPanel();
	
	//ngaysinh.setEditable(false);
	
	date.setBorder(BorderFactory.createTitledBorder(border,"Ngày lập"));
	 
	makh.setBorder(BorderFactory.createTitledBorder(border, "Mã khách hàng"));
	
	//lấy loại sản phẩm ở database vào
	
	
	JPanel lef = new JPanel();
	lef.add(mahd);
	lef.add(manv);
	lef.add(makh);
	
	lef.add(ns);
		
	center.add(lef);
	lef.add(tongtien);
	
	JPanel right = new JPanel();
	right.setLayout(new FlowLayout());

	center.add(right);
	
	ns.add(date);
	add(center);
	add(tim);
	return panel;
}
//hàm ngày sinh

	private void ShowHD() {
		
		for(HoaDon hdh : hd.getDshd()) {
		Object[] obj = {hdh.getMaHD(),hdh.getMaNV(),hdh.getMaKH(), hdh.getNgayLap(), hdh.getTongTien()};
		model.addRow(obj);
		}
		
	}
	private void Click(ListSelectionEvent evt) {
		int i = hoadonTable.getSelectedRow();
		if(i>=0) {
			see.setEnabled(true);
			mahd.setText((String)model.getValueAt(i, 0));
			manv.setText((String)model.getValueAt(i, 1));
			makh.setText((String)model.getValueAt(i, 2));
			date.setText(String.valueOf(model.getValueAt(i, 3)));
			tongtien.setText(String.valueOf(model.getValueAt(i, 4)));
			
			
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
		ArrayList<HoaDon> ds = hd.Search(value);
		DefaultTableModel newModel = new DefaultTableModel();
		hoadonTable.setModel(newModel);
		newModel.addColumn("Mã hóa đơn");
		newModel.addColumn("Mã NV");
		newModel.addColumn("Mã KH");
		newModel.addColumn("Ngày lập");
		newModel.addColumn("Tổng tiền");

		for(HoaDon sph : ds) {
			Object[] obj = {sph.getMaHD(),sph.getMaNV(), sph.getMaKH(), sph.getNgayLap(), sph.getTongTien()};
			newModel.addRow(obj);
			}
		
	}
	private void xemchitiet(MouseEvent me) {
		int i = hoadonTable.getSelectedRow();
		if(i>=0) {
			
			new cthdGUI((String)model.getValueAt(i, 0));
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		JPanel p= new HoaDonGUI();
		
		f.add(p);
		f.pack();
		f.setVisible(true);
	}

}
