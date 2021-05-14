package GUI;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


import java.awt.*;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

import DAO.*;
import BUS.*;
import DTO.*;

public class KhachHangGUI extends JPanel implements ActionListener {
    KhachHangBLL khBLL=new KhachHangBLL();

    private JTextField txt_MaKH, txt_TenKH, txt_NgaySinh, txt_DiaCHi, txt_SDT, txt_TimKiem;
    private JLabel lb_tieude, lb_MAKH, lb_TenKH, lb_NgaySinh, lb_DiaCHi, lb_SDT, lb_TimKiem,lb_GioiTinh;
    private JTable khach_hang_table;
    private JButton bt_add, bt_Sua, bt_Xoa, bt_TimKiem;
    private JRadioButton R_GioiTinhNAm,R_GioiTinhNu;
    private JScrollPane scrollPane;
    private DefaultTableModel model = new DefaultTableModel();
    private ThongTinBUS ttbus = new ThongTinBUS();
	private String quyen;
    public KhachHangGUI() {
    	
    	try {
			ThongTin thongtin = ttbus.DocFile();
			quyen = thongtin.getQuyen();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
        setLayout(null);

        setBackground(new Color(240, 230, 140));


        lb_MAKH = new JLabel("Mã Khách Hàng :");
        lb_MAKH.setBounds(70, 78, 150, 50);
        lb_MAKH.setHorizontalAlignment(SwingConstants.RIGHT);
        lb_MAKH.setFont(new Font("tohoma", Font.BOLD, 16));
        add(lb_MAKH);

        lb_TenKH = new JLabel("Tên Khách Hàng :");
        lb_TenKH.setFont(new Font("tohoma", Font.BOLD, 16));
        lb_TenKH.setHorizontalAlignment(SwingConstants.RIGHT);
        lb_TenKH.setBounds(495, 78, 150, 50);
        add(lb_TenKH);

        lb_NgaySinh = new JLabel("Ngày Sinh :");
        lb_NgaySinh.setFont(new Font("tohoma", Font.BOLD, 16));
        lb_NgaySinh.setHorizontalAlignment(SwingConstants.RIGHT);
        lb_NgaySinh.setBounds(70, 143, 150, 50);
        add(lb_NgaySinh);

        lb_DiaCHi = new JLabel("Địa Chỉ :");
        lb_DiaCHi.setHorizontalAlignment(SwingConstants.RIGHT);
        lb_DiaCHi.setBounds(495, 143, 150, 50);
        lb_DiaCHi.setFont(new Font("tohoma", Font.BOLD, 16));
        add(lb_DiaCHi);

        lb_SDT = new JLabel("Số Điện Thoại :");
        lb_SDT.setFont(new Font("tohoma", Font.BOLD, 16));
        lb_SDT.setHorizontalAlignment(SwingConstants.RIGHT);
        lb_SDT.setBounds(495, 200, 150, 50);
        add(lb_SDT);

        lb_TimKiem = new JLabel("Tìm Kiếm :");
        lb_TimKiem.setHorizontalAlignment(SwingConstants.RIGHT);
        lb_TimKiem.setFont(new Font("tohoma", Font.BOLD, 16));
        lb_TimKiem.setBounds(0, 272, 150, 50);
        add(lb_TimKiem);

        lb_GioiTinh = new JLabel("Giới tính:");
        lb_GioiTinh.setHorizontalAlignment(SwingConstants.RIGHT);
        lb_GioiTinh.setFont(new Font("tohoma", Font.BOLD, 16));
        lb_GioiTinh.setBounds(70, 200, 150, 50);
        add(lb_GioiTinh);

        R_GioiTinhNAm = new JRadioButton("Nam");
        R_GioiTinhNAm.setBackground(new Color(240, 230, 140));
        R_GioiTinhNAm.setFont(new Font("Tahoma", Font.BOLD, 16));
        R_GioiTinhNAm.setBounds(240, 217, 70, 23);
        add(R_GioiTinhNAm);

        R_GioiTinhNu = new JRadioButton("Nữ");
        R_GioiTinhNu.setBackground(new Color(240, 230, 140));

        R_GioiTinhNu.setBounds(310, 217, 60, 23);

        R_GioiTinhNu.setFont(new Font("tohoma", Font.BOLD, 16));

        add(R_GioiTinhNu);

        txt_MaKH = new JTextField();
        txt_MaKH.setBounds(235, 86, 220, 40);
        txt_MaKH.setBorder(null);
        txt_MaKH.setFont(new Font("tohoma", Font.BOLD, 16));
        txt_MaKH.setColumns(12);
        add(txt_MaKH);


        txt_TenKH = new JTextField();
        txt_TenKH.setBounds(657, 86, 220, 40);
        txt_TenKH.setFont(new Font("tohoma", Font.BOLD, 16));
        txt_TenKH.setBorder(null);
        txt_TenKH.setColumns(12);
        add(txt_TenKH);

        txt_NgaySinh = new JTextField();
        txt_NgaySinh.setBounds(235, 148, 220, 40);
        txt_NgaySinh.setFont(new Font("tohoma", Font.BOLD, 16));
        txt_NgaySinh.setBorder(null);
        txt_NgaySinh.setColumns(12);
        add(txt_NgaySinh);

        txt_DiaCHi = new JTextField();
        txt_DiaCHi.setBounds(657, 148, 220, 40);
        txt_DiaCHi.setFont(new Font("tohoma", Font.BOLD, 16));
        txt_DiaCHi.setBorder(null);
        txt_DiaCHi.setColumns(12);
        add(txt_DiaCHi);


        txt_SDT = new JTextField();
        txt_SDT.setBounds(657, 208, 220, 40);
        txt_SDT.setFont(new Font("tohoma", Font.BOLD, 16));
        txt_SDT.setBorder(null);
        txt_SDT.setColumns(12);
        add(txt_SDT);

        txt_TimKiem = new JTextField();
        txt_TimKiem.setBounds(160, 278, 350, 40);
        txt_TimKiem.setFont(new Font("tohoma", Font.BOLD, 16));
        txt_TimKiem.setBorder(null);
        txt_TimKiem.setColumns(12);
        add(txt_TimKiem);
        addDocumentListener(txt_TimKiem);


        bt_add = new ButtonAdd();
        bt_add.setFont(new Font("tohoma", Font.BOLD, 16));
        bt_add.setBorder(null);
        bt_add.setForeground(new Color(255, 255, 255));
        bt_add.setBackground(new Color(255, 0, 0));
        bt_add.setBounds(949, 88, 100, 40);
        add(bt_add);


        bt_Sua = new ButtonChange();
        bt_Sua.setFont(new Font("tohoma", Font.BOLD, 14));
        bt_Sua.setBorder(null);
        bt_Sua.setForeground(new Color(255, 255, 255));
        bt_Sua.setBackground(new Color(255, 0, 0));
        bt_Sua.setBounds(949, 154, 100, 40);
        add(bt_Sua);


        bt_Xoa = new ButtonRemove();
        bt_Xoa.setFont(new Font("tohoma", Font.BOLD, 14));
        bt_Xoa.setBorder(null);
        bt_Xoa.setForeground(new Color(255, 255, 255));
        bt_Xoa.setBackground(new Color(255, 0, 0));
        bt_Xoa.setBounds(949, 214, 100, 40);
        
        add(bt_Xoa);

        bt_add.addActionListener(this);
        bt_Sua.addActionListener(this);
        bt_Xoa.addActionListener(this);
        R_GioiTinhNu.addActionListener(this);
        R_GioiTinhNAm.addActionListener(this);
        txt_TimKiem.addActionListener(this);

        khach_hang_table = new JTable();
        khach_hang_table.setModel(model);
        khach_hang_table.setBackground(Color.WHITE);
        scrollPane = new JScrollPane(khach_hang_table);
        model.addColumn("Mã Khách Hàng");
        model.addColumn("Tên Khách Hàng");
        model.addColumn("Địa Chỉ");
        model.addColumn("Số Điện Thoại");
        model.addColumn("Ngày Sinh");
        model.addColumn("Giới tính");





        // model.addRow( (new Object[]{"traicây01","ngo khang","08/9/2001","adsasđ","0563535355"}));
        scrollPane.setBounds(0, 350, 1095, 400);
        scrollPane.setViewportView(khach_hang_table);
        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setBounds(1065, 352, 17, 260);

        this.add(scrollBar);
        setLayout(null);
        this.add(scrollPane);

    ShowdanhsachKH();


        khach_hang_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent evt) {
                // TODO Auto-generated method stub

                Click(evt);

            }
        });

        if(quyen.equals("Q3")) {
    		bt_Xoa.setEnabled(false);
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

    public void ShowdanhsachKH(){
        String Makh,tenkh,diachi,sdt,ngaysinh,gioitinh;
        Vector<KhachHangDTO> dskh=new Vector<KhachHangDTO>();
        dskh=khBLL.getAllKhachang();
        for (int i=0;i<dskh.size();i++){
            KhachHangDTO kh=dskh.get(i);
            Makh=kh.getMaKH();
            tenkh=kh.getTenKH();
            diachi=kh.getDiaCHi();
            sdt=kh.getSDT();
            ngaysinh=kh.getNgaySinh();
            gioitinh=kh.getGioitinh();
            Object[]row={Makh,tenkh,diachi,sdt,ngaysinh,gioitinh};
            model.addRow(row);

        }


    }
    private void Click(ListSelectionEvent evt) {
        int i = khach_hang_table.getSelectedRow();
        if(i>=0) {



            txt_MaKH.setText((String)model.getValueAt(i, 0));
            txt_MaKH.setEditable(false);

            txt_TenKH.setText((String)model.getValueAt(i, 1));
            txt_DiaCHi.setText((String)model.getValueAt(i, 2));
            txt_SDT.setText(String.valueOf(model.getValueAt(i, 3)));
            txt_NgaySinh.setText((String)model.getValueAt(i, 4));
            String gt= (String) model.getValueAt(i,5);
            if (gt.equals("Nam")) {
                R_GioiTinhNAm.setSelected(true);
            }
            else{
                R_GioiTinhNu.setSelected(true);
            }
            //soluongsp = (int) model.getValueAt(i, 4);
        }

    }
    public void reset(){
        txt_MaKH.setText("");
        txt_TenKH.setText("");
        txt_DiaCHi.setText("");
        txt_SDT.setText("");
        txt_NgaySinh.setText("");

    }
    public void resettable(){
        int row= model.getRowCount();
        if (khach_hang_table.getRowCount()>0){
            for(int i=row-1;i>=0;i--){
                model.removeRow(i);
            }
            ShowdanhsachKH();
        }
        else{
            ShowdanhsachKH();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String txtmakh = txt_MaKH.getText();
        String txttenkh = txt_TenKH.getText();
        String txtSDT = txt_SDT.getText();
        String txtdiachi = txt_DiaCHi.getText();
        String txtNgaySinh = txt_NgaySinh.getText();
        String txtgt;
        if (R_GioiTinhNAm.isSelected()){
            txtgt = R_GioiTinhNAm.getText();
    }
        else {
             txtgt=R_GioiTinhNu.getText();
        }
      //  System.out.println(txtgt);
        try {
            if (e.getSource() == bt_add) {
                    reset();
                    if(R_GioiTinhNAm.isSelected()&&R_GioiTinhNu.isSelected()){
                        JOptionPane.showMessageDialog(this, "Giới tính chỉ chọn một trong hai!");
                        return;
                    }
                if (txtmakh.equals("") || txttenkh.equals("") || txtSDT.equals("") || txtdiachi.equals("") || txtNgaySinh.equals("") || txtgt.equals("")) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin");

                } else {
                    KhachHangDTO kh = new KhachHangDTO();
                    kh.setMaKH(txtmakh);
                    kh.setTenKH(txttenkh);
                    kh.setDiaCHi(txtdiachi);
                    kh.setSDT(txtSDT);
                    kh.setNgaySinh(txtNgaySinh);
                    kh.setGioitinh(txtgt);
                JOptionPane.showMessageDialog(this, khBLL.addkhachang(kh));





                }
                resettable();
               // ShowdanhsachKH();
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,"Thông tin không hợp lệ !");
            throw new RuntimeException(ex);
        }

        if(e.getSource()==bt_Sua) {

               if (txtmakh.equals("") || txttenkh.equals("") || txtSDT.equals("") || txtdiachi.equals("") || txtNgaySinh.equals("") || txtgt.equals("")) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin");


                } else {
                   if(R_GioiTinhNAm.isSelected()&&R_GioiTinhNu.isSelected()){
                       JOptionPane.showMessageDialog(this, "Giới tính chỉ chọn một trong hai!");
                            return;
                   }

                    if (khBLL.update(txtmakh,txttenkh,txtdiachi,txtSDT,txtNgaySinh,txtgt)){
                        resettable();
                        JOptionPane.showMessageDialog(this,"Sửa thành công");
                        reset();
                    }
                }
            }

        if (e.getSource()==bt_Xoa){
            reset();
            int i = khach_hang_table.getSelectedRow();
            if(i>=0) {

                if(JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa?", "Chú ý",JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION) {
                    String makh =String.valueOf(model.getValueAt(i, 0));
                    System.out.println("ma tron bang la :"+makh);
                    if(khBLL.Delete(makh)){
                        model.removeRow(i);
                        JOptionPane.showMessageDialog(this, "Xóa thành công");
                        resettable();
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "Không thể xóa khách hàng đã đặt đơn hàng");
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Bạn phải chọn dòng muốn xóa,để xóa ");
            }
        }


    }
    private void Search(String value) {
       Vector<KhachHangDTO> dskh1=new Vector<KhachHangDTO>();
        Vector<KhachHangDTO> ds = khBLL.Search(value);
        ds=khBLL.getAllKhachang();
        DefaultTableModel newModel = new DefaultTableModel();
        khach_hang_table.setModel(newModel);
        newModel.addColumn("Mã Khách Hàng");
        newModel.addColumn("Tên Khách Hàng");
        newModel.addColumn("Địa Chỉ");
        newModel.addColumn("Số Điện Thoại");
        newModel.addColumn("Ngày Sinh");
        newModel.addColumn("Giới tính");

        for (int i=0;i<ds.size();i++){
            KhachHangDTO kh=ds.get(i);
            System.out.println(kh.MaKH+kh.TenKH);
            Object[] obj = {kh.MaKH,kh.TenKH,kh.DiaCHi, kh.SDT,kh.NgaySinh, kh.Gioitinh};
           if (kh.MaKH.contains(txt_TimKiem.getText())||txt_TimKiem.getText().toUpperCase().contains(kh.MaKH)||
                   kh.TenKH.toLowerCase().contains(txt_TimKiem.getText())|| txt_TimKiem.getText().toLowerCase().contains(kh.TenKH)||
                   kh.TenKH.toUpperCase().contains(txt_TimKiem.getText())|| txt_TimKiem.getText().toUpperCase().contains(kh.TenKH)||
                   kh.SDT.contains(txt_TimKiem.getText())|| txt_TimKiem.getText().contains(kh.SDT)) {
                newModel.addRow(obj);
            }
        }

    }


}


