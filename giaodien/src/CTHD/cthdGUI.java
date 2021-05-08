package CTHD;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
public class cthdGUI extends JFrame{
	private JTable cthdTable = new JTable();
	private JScrollPane scr = new JScrollPane();
	private DefaultTableModel model = new DefaultTableModel();
	private JTextField timkiem = new JTextField(30);
	public cthdGUI() {
		
		JPanel contai = create();
		add(contai);
		setVisible(true);
		
		setSize(700,500);
	}
	private JPanel create() {
		JPanel contai = new JPanel();
		contai.setLayout(new BoxLayout(contai,BoxLayout.Y_AXIS));
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		
		timkiem.setBorder(BorderFactory.createTitledBorder(border,"Tìm kiếm"));
		//addDocumentListener(timkiem);
		cthdTable.setModel(model);
		model.addColumn("Mã hóa đơn");
		model.addColumn("Mã sản phẩm");
		model.addColumn("Đơn giá");
		model.addColumn("Số lượng");
		model.addColumn("Thành tiền");
		
		scr = new JScrollPane(cthdTable);
		contai.add(timkiem);
		contai.add(scr);
		return contai;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new cthdGUI();
	}

}
