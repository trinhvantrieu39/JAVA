package PhanQuyen;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import TaiKhoan.TaiKhoanGUI;

public class PhanQuyenGUI extends JPanel{
	private JTable quyenTable = new JTable();
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane sp ;
	private BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
	private Border border= BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	PhanQuyenBUS pq = new PhanQuyenBUS();
	
	public PhanQuyenGUI() {
		setLayout(layout);
		setBorder(border);
		
		JPanel  tim= new JPanel();
		JTextField timkiem = new JTextField(20);
		timkiem.setBorder(BorderFactory.createTitledBorder(border,"Tìm kiếm"));
		tim.add(timkiem);
		quyenTable.setModel(model);
		model.addColumn("Mã quyền");
		model.addColumn("Tên quyền");
		model.addColumn("Chi tiết quyền");
		ShowQuyen();
		sp =new JScrollPane(quyenTable);
		add(tim);
		add(sp);
	}
	private void ShowQuyen() {
		for(PhanQuyen quyen : pq.getdsq()) {
			Object[] obj = {quyen.getMaquyen(),quyen.getTenquyen(),quyen.getChitiet()};
			model.addRow(obj);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		JPanel p= new PhanQuyenGUI();
		
		f.add(p);
		f.pack();
		f.setVisible(true);	}

}
