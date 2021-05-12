package PhieuNhap;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Button.ButtonShow;
import CTPN.ctpnGUI;


public class PhieuNhapGUI extends JPanel{

	private BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
	private JTable phieunhapTable = new JTable();
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane sp ;
	
	private Border border= BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	
	private JTextField mapn = new JTextField(15);
	private JTextField manv = new JTextField(15);
	

	private JTextField makh = new JTextField(15);
	
	private JTextField tongtien = new JTextField(15);
	private JTextField date = new JTextField(15);
	private PhieuNhapBUS hd = new PhieuNhapBUS();
	private JButton see = new ButtonShow();
	
public PhieuNhapGUI(){
	setLayout(layout);
	setBorder(border);
	JPanel panel = CreateInfo();
	phieunhapTable.setModel(model);
	model.addColumn("Mã phiếu nhập");
	model.addColumn("Mã NV");
	model.addColumn("Mã NCC");
	model.addColumn("Ngày lập");
	model.addColumn("Tổng tiền");
	
	
	sp = new JScrollPane(phieunhapTable);
	ShowHD();
	phieunhapTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		
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
	mapn.setBorder(BorderFactory.createTitledBorder(border,"Mã phiếu nhập"));
	
	JPanel ns = new JPanel();
	
	//ngaysinh.setEditable(false);
	
	date.setBorder(BorderFactory.createTitledBorder(border,"Ngày lập"));
	 
	makh.setBorder(BorderFactory.createTitledBorder(border, "Mã khách hàng"));
	
	//lấy loại sản phẩm ở database vào
	
	
	JPanel lef = new JPanel();
	lef.add(mapn);
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
		
		for(PhieuNhap hdh : hd.getDshd()) {
		Object[] obj = {hdh.getMaPN(),hdh.getMaNV(),hdh.getMaNCC(), hdh.getNgayLap(), hdh.getTongTien()};
		model.addRow(obj);
		}
		
	}
	private void Click(ListSelectionEvent evt) {
		int i = phieunhapTable.getSelectedRow();
		if(i>=0) {
			see.setEnabled(true);
			mapn.setText((String)model.getValueAt(i, 0));
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
		ArrayList<PhieuNhap> ds = hd.Search(value);
		DefaultTableModel newModel = new DefaultTableModel();
		phieunhapTable.setModel(newModel);
		newModel.addColumn("Mã phiếu nhập");
		newModel.addColumn("Mã NV");
		newModel.addColumn("Mã KH");
		newModel.addColumn("Ngày lập");
		newModel.addColumn("Tổng tiền");

		for(PhieuNhap sph : ds) {
			Object[] obj = {sph.getMaPN(),sph.getMaNV(), sph.getMaNCC(), sph.getNgayLap(), sph.getTongTien()};
			newModel.addRow(obj);
			}
		
	}
	private void xemchitiet(MouseEvent me) {
		int i = phieunhapTable.getSelectedRow();
		if(i>=0) {
			
			new ctpnGUI((String)model.getValueAt(i, 0));
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		JPanel p= new PhieuNhapGUI();
		
		f.add(p);
		f.pack();
		f.setVisible(true);
	}

}
