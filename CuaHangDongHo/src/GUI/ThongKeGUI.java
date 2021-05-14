package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;




public class ThongKeGUI extends JPanel{
	private JPanel nhaphang= new TKNhapHangGUI();
	private JPanel banhang= new TKBanHangGUI(); 
	private JPanel kinhdoanh = new JPanel();
	private float doanhthu =0;
	private float chiphi=0;
	private float loinhuan = 0;
	public ThongKeGUI() {
		 JPanel jp = new JPanel(); 
	     jp.setLayout(new BorderLayout()); 
		setLayout(new BorderLayout());
		JTabbedPane tabbed = new JTabbedPane();
		banhang = new TKBanHangGUI();
		nhaphang = new TKNhapHangGUI();
		kinhdoanh = KinhDoanh();
		tabbed.addTab("Kinh Doanh", kinhdoanh);
		tabbed.addTab("Doanh Thu", banhang);
		tabbed.addTab("Chi Phí", nhaphang);

		jp.add(tabbed, BorderLayout.CENTER); 
	     add(jp, BorderLayout.CENTER); 
	}
	private JPanel KinhDoanh() {
		JPanel panel = new JPanel();
		JPanel top = new JPanel();
		JPanel mid = new JPanel();
		JPanel bot = new JPanel();
		
		try {
			doanhthu = DocFileBanHang();
			chiphi = DocFileNhapHang();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loinhuan = doanhthu - chiphi;
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		ImageIcon dticon= new ImageIcon(new ImageIcon(getClass().getResource("/images/doanhthu.png")).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING));
		JLabel dt= new JLabel("Doanh thu: ");
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 50);
		dt.setFont(font);
		dt.setIcon(dticon);
		
		JLabel cp = new JLabel("Chi phí:    ");
		ImageIcon cpicon= new ImageIcon(new ImageIcon(getClass().getResource("/images/chiphi.png")).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING));
		cp.setIcon(cpicon);
		cp.setFont(font);
		
		JLabel ln = new JLabel("Lợi nhuận: ");
		ImageIcon lnicon= new ImageIcon(new ImageIcon(getClass().getResource("/images/loinhuan.png")).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING));
		ln.setIcon(lnicon);
		ln.setFont(font);
		//so tien
		JLabel tien1 = new JLabel(""+doanhthu+"  VND");
		JLabel tien2 = new JLabel("   "+chiphi+"  VND");
		JLabel tien3 = new JLabel(""+loinhuan+"  VND");
		tien1.setFont(font);
		tien2.setFont(font);
		tien3.setFont(font);

		top.add(dt);
		top.add(tien1);
		top.setBorder(new EmptyBorder(40,0,0,0));
		
		mid.add(cp);
		mid.add(tien2);
		
		bot.add(ln);
		bot.add(tien3);
		
		panel.add(top);
		panel.add(mid);
		panel.add(bot);
		return panel;
	}
	private float DocFileBanHang() throws IOException {
		FileInputStream file = null;
		DataInputStream data = null;
		float tien=0;
		try {
			file = new FileInputStream("banhang.txt");
			data = new DataInputStream(file);
			
			tien= data.readFloat();
			
			
			file.close();
			data.close();
			
		}
		catch (EOFException e) {
		}
		return tien;
	}
	private float DocFileNhapHang() throws IOException {
		FileInputStream file = null;
		DataInputStream data = null;
		float tien=0;
		try {
			file = new FileInputStream("nhaphang.txt");
			data = new DataInputStream(file);
			
			tien= data.readFloat();
			
			
			file.close();
			data.close();
		//	for( int i=0;i<SoLuongSP;i++)
		//		sp[i].XuatSP();
		}
		catch (EOFException e) {
		}
		return tien;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			JPanel p = new ThongKeGUI();
			JFrame f = new JFrame();
			f.add(p);
			f.pack();
			f.setVisible(true);
	}

}
