package NhaCungCap;
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
import ThongTinDangNhap.ThongTin;
import ThongTinDangNhap.ThongTinBUS;
import net.sourceforge.jdatepicker.impl.*;

public class NhaCungCapGUI extends JPanel{
    private BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
    private JTable nccTable = new JTable();
    private DefaultTableModel model = new DefaultTableModel();
    private JScrollPane sp ;

    private Border border= BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

    private JTextField mancc = new JTextField(15);
    private JTextField tenncc = new JTextField(15);
    private JTextField dcncc = new JTextField(15);
    private JTextField sdtncc=new JTextField(15);

    private NhaCungCapBUS ncc = new NhaCungCapBUS();
    private ThongTinBUS ttbus = new ThongTinBUS();
	private String quyen;

    public NhaCungCapGUI(){
        setLayout(layout);

        JPanel panel = CreateInfo();
        nccTable.setModel(model);
        model.addColumn("Mã nhà cung cấp");
        model.addColumn("Tên nhà cung cấp");
        model.addColumn("Địa chỉ");
        model.addColumn("SĐT");

        ShowNCC();

        nccTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent evt) {
                // TODO Auto-generated method stub

                Click(evt);

            }
        });

        sp = new JScrollPane(nccTable);
        add(panel);
        add(sp);
    }
    private JPanel CreateInfo() {
    	
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

        mancc.setBorder(BorderFactory.createTitledBorder(border,"Mã nhà cung cấp"));
        tenncc.setBorder(BorderFactory.createTitledBorder(border,"Tên nhà cung cấp"));
        dcncc.setBorder(BorderFactory.createTitledBorder(border,"Địa chỉ"));
        sdtncc.setBorder(BorderFactory.createTitledBorder(border,"Số điện thoại"));


        JPanel lef = new JPanel();
        lef.add(mancc);
        lef.add(tenncc);
        lef.add(dcncc);
        lef.add(sdtncc);



        center.add(lef);

        JButton them = new ButtonAdd();
        JButton sua = new ButtonChange();
        JButton xoa = new ButtonRemove();
        if(quyen.equals("Q3")) {

    		xoa.setEnabled(false);
		}
        else {
        	xoa.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mousePressed(MouseEvent me) {
	
	                Xoa(me);
	
	            }
	        });
        }
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
    private void ShowNCC() {

        for(NhaCungCap ncc : ncc.getDsncc()) {
            Object[] obj = {ncc.getMancc(),ncc.getTenncc(),ncc.getDcncc(),ncc.getSdtncc()};
            model.addRow(obj);
        }

    }
    private void Click(ListSelectionEvent evt) {
        int i = nccTable.getSelectedRow();
        if(i>=0) {

            mancc.setText((String)model.getValueAt(i, 0));
            tenncc.setText((String)model.getValueAt(i, 1));
            dcncc.setText((String)model.getValueAt(i, 2));
            sdtncc.setText((String)model.getValueAt(i, 3));
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
        ArrayList<NhaCungCap> ds = ncc.Search(value);
        DefaultTableModel newModel = new DefaultTableModel();
        nccTable.setModel(newModel);
        newModel.addColumn("Mã NCC");
        newModel.addColumn("Tên NCC");
        newModel.addColumn("Địa chỉ NCC");
        newModel.addColumn("SĐT");

        for(NhaCungCap ncc : ds) {
            Object[] obj = {ncc.getMancc(),ncc.getTenncc(),ncc.getDcncc(),ncc.getSdtncc()};
            newModel.addRow(obj);
        }

    }
    private void Them(MouseEvent me) {
        if(mancc.getText().equals("") || tenncc.getText().equals("")||dcncc.getText().equals("")||sdtncc.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa điền đủ thông tin");
            return;
        }
        String MaNCC = mancc.getText();
        String TenNCC = tenncc.getText();
        String DiaChiNCC=dcncc.getText();
        String SDTNCC=sdtncc.getText();

        NhaCungCap nhacungcap= new NhaCungCap(MaNCC, TenNCC,DiaChiNCC,SDTNCC);


        if(ncc.Add(nhacungcap)){
            model.addRow(new Object[] {MaNCC, TenNCC,DiaChiNCC,SDTNCC});
            JOptionPane.showMessageDialog(this, "Thêm thành công");

        }
    }
    private void Sua(MouseEvent me) {
        int i =nccTable.getSelectedRow();
        if(i>=0) {
            String MaNCC = mancc.getText();
            String TenNCC = tenncc.getText();
            String DiaChi=dcncc.getText();
            String SDT=sdtncc.getText();

            if(mancc.getText().equals("") || tenncc.getText().equals("")||dcncc.getText().equals("")||sdtncc.getText().equals("") ) {
                JOptionPane.showMessageDialog(this, "Chưa điền đủ thông tin");
                return;
            }
            if(ncc.update(MaNCC, TenNCC,DiaChi,SDT)) {
                //todo


                model.setValueAt(MaNCC,i,0);
                model.setValueAt(TenNCC, i, 1);
                model.setValueAt(DiaChi,i,2);
                model.setValueAt(SDT,i,3);

                JOptionPane.showMessageDialog(this, "Sửa thành công");
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Chưa chọn nhà cung cấp để sửa");
        }

    }

    private void Xoa(MouseEvent me) {
        int i = nccTable.getSelectedRow();
        if(i>=0) {

            if(JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa?", "Chú ý",JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION) {
                String maNCC =String.valueOf(model.getValueAt(i, 0));
                System.out.println(maNCC);
                if(ncc.Delete(maNCC)){
                    model.removeRow(i);
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Chưa chọn nhà cung cấp ");
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JFrame f = new JFrame();
        JPanel p= new NhaCungCapGUI();

        f.add(p);
        f.pack();
        f.setVisible(true);
    }

}
