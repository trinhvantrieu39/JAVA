package TaiKhoan;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.html.HTMLModElement;

import Button.*;
import DangNhap_DangKy.FormDangKy;
import NhanVien.DateLabelFormatter;
import net.sourceforge.jdatepicker.impl.*;

	public class TaiKhoanGUI extends JPanel{
		private BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
		private JTable taikhoanTable = new JTable();
		private DefaultTableModel model = new DefaultTableModel();
		private JScrollPane sp ;
		
		private Border border= BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		
		private JTextField tentk = new JTextField(15);
		private JTextField matkhau = new JTextField(15);
		
		private JComboBox maquyen;
		private JTextField manv = new JTextField(15);
		TaiKhoanBUS bus = new TaiKhoanBUS();
		
	public TaiKhoanGUI(){
		setLayout(layout);
		setBorder(border);
		JPanel panel = CreateInfo();
		taikhoanTable.setModel(model);
		model.addColumn("Tên tài khoản");
		model.addColumn("Mật khẩu");
		model.addColumn("Mã NV");
		model.addColumn("Mã quyền");
		
		ShowTK();
		taikhoanTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				Click(me);
			}
		});
		sp = new JScrollPane(taikhoanTable);
		add(panel);
		add(sp);
	}
	private JPanel CreateInfo() {
		JPanel panel = new JPanel();
		
		JPanel  tim= new JPanel();
		JTextField timkiem = new JTextField(20);
		timkiem.setBorder(BorderFactory.createTitledBorder(border,"Tìm kiếm"));
		
		tim.add(timkiem);
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center,BoxLayout.Y_AXIS));
		
		manv.setBorder(BorderFactory.createTitledBorder(border,"Mã nhân viên"));
		tentk.setBorder(BorderFactory.createTitledBorder(border,"Mã tài khoản"));
		 
		matkhau.setBorder(BorderFactory.createTitledBorder(border, "Mật khẩu"));
		//lấy loại sản phẩm ở database vào
		String quyenlist[] = {"Admin", "Quản lý","Nhân viên"};
		JLabel quyenlb = new JLabel("Loại quyền: ");
		maquyen = new JComboBox(quyenlist);
		
		JPanel mqpanel = new JPanel();
		mqpanel.add(quyenlb);
		mqpanel.add(maquyen);
		
		JPanel lef = new JPanel();
		lef.add(tentk);
		lef.add(matkhau);
		lef.add(manv);
				
		center.add(lef);
		center.add(mqpanel);
		
		JButton them = new ButtonAdd();
		them.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				new FormDangKy();
			}
		});
		
		JButton sua = new ButtonChange();
		sua.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				 
				sua(me);
				
			}
		});
		JButton xoa = new ButtonRemove();
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
	private void ShowTK() {
		for(TaiKhoan tk : bus.getDstk()) {
			Object[] obj = {tk.getMatk(),tk.getMatkhau(),tk.getManv(),tk.getMaquyen()};
			model.addRow(obj);
		}
	}
	
	private void Click(MouseEvent me) {
		int i =taikhoanTable.getSelectedRow();
		if(i>=0) {
			tentk.setText(model.getValueAt(i, 0).toString());
			matkhau.setText(model.getValueAt(i, 1).toString());
			manv.setText(model.getValueAt(i, 2).toString());
			
			switch(model.getValueAt(i, 3).toString()) {
			case "Q1":{
				maquyen.setSelectedItem("Admin");
				break;
			}
			case"Q2":{
				maquyen.setSelectedItem("Quản lý");
				break;
			}
			case"Q3":{
				maquyen.setSelectedItem("Nhân viên");
				break;
			}
			}
		}
	}

	private void sua(MouseEvent me) {
		int i =taikhoanTable.getSelectedRow();
		if(i>=0) {		
			String tk = tentk.getText();
			String mk = matkhau.getText();
			String nv = manv.getText();
			String quyen = null;
			switch ((String)maquyen.getSelectedItem()) {
			case "Admin" :{
				quyen="Q1";
				break;
			}
			case "Quản lý":{
				quyen="Q2";
				break;
			}
			case "Nhân viên":{
				quyen="Q3";
				break;
			}
		}
			if(tk.equals("") || mk.equals("") || nv.equals("")) {
				JOptionPane.showMessageDialog(null, "Không được để trống thông tin");
				return;
			}
			else {
				
				
				if(bus.Update(tk, mk, quyen, nv)) {
					model.setValueAt(tk, i, 0);
					model.setValueAt(mk, i, 1);
					model.setValueAt(nv, i, 2);
					model.setValueAt(quyen, i, 3);
					JOptionPane.showMessageDialog(this, "Sửa thành công");
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Chưa chọn tài khoản để sửa");
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		JPanel p= new TaiKhoanGUI();
		
		f.add(p);
		f.pack();
		f.setVisible(true);
	}

}
