package GUI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import DAO.*;
import BUS.*;
import DTO.*;

public class DoiMKGUI extends JPanel{
	private JTextField tentk,mk,mkmoi;
	private JButton ThayDoi = new JButton();
	TaiKhoanBUS tkbus = new TaiKhoanBUS();
	ThongTinBUS ttbus =new ThongTinBUS();
	
	
	public DoiMKGUI(String ten,ThongTin tk) {
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		tentk = new JTextField(20);
		tentk.setBorder(BorderFactory.createTitledBorder(border,"Tên tài khoản"));
		tentk.setEditable(false);
		tentk.setText(ten);
		mk = new JTextField(20);
		mk.setBorder(BorderFactory.createTitledBorder(border,"Mật khẩu hiện tại"));
		mkmoi = new JTextField(20);
		mkmoi.setBorder(BorderFactory.createTitledBorder(border,"Mật khẩu mới"));
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		ThayDoi.setText("Xác nhận");
		ThayDoi.setBackground(new Color(220,220,220));
		ThayDoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				thaydoi(me, tk);
				
			}
		});
		JPanel on =new JPanel();
		JPanel bot =new JPanel();
		on.add(tentk);
		on.add(mk);
		on.add(mkmoi);
		bot.add(ThayDoi);
		add(on);
		add(bot);
	}
	private void thaydoi(MouseEvent me, ThongTin tt) {
		
		if(mk.getText().equals("") || mkmoi.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Không được để trống mật khẩu");return;
		}
		else
			if( tt.getMatkhau().equals(mk.getText())) {
				
				String taik = tt.getMatk();
				String matk= mkmoi.getText();
				String tenq = tt.getQuyen();
				String nhanv = tt.getManv();
				tt.setMatkhau(matk);
				
				System.out.println(taik+" "+matk+" "+tenq+" "+nhanv);
				if (tkbus.Update(taik,matk,tenq,nhanv)) {
					try {
						ttbus.GhiFile(tt);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					JOptionPane.showMessageDialog(this, "Sửa thành công");
				}
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Mật khẩu không đúng");
				return;
			}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThongTinBUS ttbus =new ThongTinBUS();
		ThongTin thongtin = null;
		
		
		try {
			thongtin = ttbus.DocFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFrame f= new JFrame();
		JPanel p =new DoiMKGUI("quanly1",thongtin);
		f.setSize(400, 300);;
		f.add(p);
		f.setVisible(true);
	}

}
