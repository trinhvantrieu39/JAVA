package GioHang;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import Button.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class GioHangGUI extends JPanel{
	private JTextField tfmahd = new JTextField(20);
	private JTextField tfmanv = new JTextField(20);
	private JTextField tfmakh = new JTextField(20);
	private JTextField tfngaylap = new JTextField(20);
	private JTextField tongtien = new JTextField(30); 
	JButton sua = new ButtonChange();
	JButton xoa = new ButtonRemove();
	JButton thanhtoan = new ButtonThanhToan();
	
	private JTable giohangTable = new JTable();
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane scr = new JScrollPane();
	public GioHangGUI() {
		JPanel ThongTin = CreateTop();
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		//table
		giohangTable.setModel(model);
		model.addColumn("Mã sản phẩm");
		model.addColumn("Tên sản phẩm");
		model.addColumn("Số lượng");
		model.addColumn("Đơn giá (vnd)");
		model.addColumn("Thành tiền (vnd)");
		giohangTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		giohangTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		giohangTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		giohangTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		giohangTable.getColumnModel().getColumn(4).setPreferredWidth(150);
		scr = new JScrollPane(giohangTable);
		
		
		JPanel bot = new JPanel();
		JPanel botbot = new JPanel();
		
		
		
		botbot.add(thanhtoan);
		JLabel label = new JLabel("Tổng tiền (vnd): ");
		tongtien.setEditable(false);
		bot.add(label);
		bot.add(tongtien);
		add(ThongTin);
		add(scr);
		add(bot);
		add(botbot);
		
	}
	private JPanel CreateTop() {
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top,BoxLayout.Y_AXIS));
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		tfmahd.setBorder(BorderFactory.createTitledBorder(border,"Mã hóa đơn"));
		tfmanv.setBorder(BorderFactory.createTitledBorder(border,"Mã nhân viên"));
		tfmakh.setBorder(BorderFactory.createTitledBorder(border,"Mã khách hàng"));
		tfngaylap.setBorder(BorderFactory.createTitledBorder(border,"Ngày lập"));
		tfngaylap.setEditable(false);
		tfngaylap.setText(String.valueOf(java.time.LocalDate.now()));
		
		
		JPanel on = new JPanel();
		JPanel below = new JPanel();
		JPanel bot = new JPanel();
		on.add(tfmahd);
		on.add(tfmanv);
		below.add(tfmakh);
		below.add(tfngaylap);
				
		
		xoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				Xoa(me);
			}
		});
		bot.add(xoa);
		
		top.add(on);
		top.add(below);
		top.add(bot);
		return top;
	}
	private void Thanhtoan(MouseEvent me) {
		
	}
	
	private void Xoa(MouseEvent me) {
		
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		JPanel p = new GioHangGUI();
		f.setSize(700,700);
		f.add(p);
		f.setVisible(true);
		//f.pack();
		
	}

}
