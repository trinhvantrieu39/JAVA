package CTHD;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;



import java.awt.*;
import java.util.ArrayList;
public class cthdGUI extends JFrame{
	private JTable cthdTable = new JTable();
	private JScrollPane scr = new JScrollPane();
	private DefaultTableModel model = new DefaultTableModel();
	private JTextField timkiem = new JTextField(30);
	private cthdBUS ct;
	
	public cthdGUI( String mahd) {
		
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
		cthdTable.setModel(model);
		model.addColumn("Mã sản phẩm");
		model.addColumn("Đơn giá");
		model.addColumn("Số lượng");
		model.addColumn("Thành tiền");
		ShowHD(mahd);
		
		scr = new JScrollPane(cthdTable);
		contai.add(timkiem);
		contai.add(scr);
		return contai;
	}
	private void ShowHD(String mahd) {
		ct = new cthdBUS(mahd);
		for(cthd hdh : ct.getDssp()) {
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
		ArrayList<cthd> ds = ct.Search(value);
		DefaultTableModel newModel = new DefaultTableModel();
		cthdTable.setModel(newModel);
		newModel.addColumn("Mã sản phẩm");
		newModel.addColumn("Đơn giá");
		newModel.addColumn("Số lượng");
		newModel.addColumn("Thành tiền");

		for(cthd sph : ds) {
			Object[] obj = {sph.getMaSP(), sph.getDonGia(), sph.getSoLuong(), sph.getThanhTien()};
			newModel.addRow(obj);
			}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new cthdGUI("hd1");
	}

}
