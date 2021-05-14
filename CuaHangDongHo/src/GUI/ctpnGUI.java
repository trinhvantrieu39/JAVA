package GUI;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import DAO.*;
import BUS.*;
import DTO.*;

public class ctpnGUI extends JFrame{
	private JTable ctpnTable = new JTable();
	private JScrollPane scr = new JScrollPane();
	private DefaultTableModel model = new DefaultTableModel();
	private JTextField timkiem = new JTextField(30);
	private ctpnBUS ct;
	
	public ctpnGUI( String mahd) {
		
		JPanel contai = create(mahd);
		add(contai);
		setVisible(true);
		setSize(700,500);
	}
	private JPanel create(String mahd) {
		JPanel contai = new JPanel();
		contai.setLayout(new BoxLayout(contai,BoxLayout.Y_AXIS));
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		
		timkiem.setBorder(BorderFactory.createTitledBorder(border,"Tìm kiếm"));
		addDocumentListener(timkiem);
		ctpnTable.setModel(model);
		model.addColumn("Mã sản phẩm");
		model.addColumn("Đơn giá");
		model.addColumn("Số lượng");
		model.addColumn("Thành tiền");
		ShowHD(mahd);
		
		scr = new JScrollPane(ctpnTable);
		contai.add(timkiem);
		contai.add(scr);
		return contai;
	}
	private void ShowHD(String mahd) {
		ct = new ctpnBUS(mahd);
		for(ctpn hdh : ct.getDssp()) {
		Object[] obj = {hdh.getMaSP(),hdh.getDonGia(), hdh.getSoLuong(), hdh.getThanhTien()};
		setTitle("CHI TIẾT HÓA ĐƠN: "+ mahd);
		model.addRow(obj);
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
		ArrayList<ctpn> ds = ct.Search(value);
		DefaultTableModel newModel = new DefaultTableModel();
		ctpnTable.setModel(newModel);
		newModel.addColumn("Mã sản phẩm");
		newModel.addColumn("Đơn giá");
		newModel.addColumn("Số lượng");
		newModel.addColumn("Thành tiền");

		for(ctpn sph : ds) {
			Object[] obj = {sph.getMaSP(), sph.getDonGia(), sph.getSoLuong(), sph.getThanhTien()};
			newModel.addRow(obj);
			}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ctpnGUI("hd1");
	}
}
