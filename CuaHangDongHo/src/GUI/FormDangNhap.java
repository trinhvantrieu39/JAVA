package GUI;



import keeptoo.KGradientPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.Timer;
import java.sql.*;
import DAO.*;
import BUS.*;
import DTO.*;
public class FormDangNhap extends JFrame implements ActionListener {
    private KGradientPanel panel;
    private JLabel lb_tk,lb_mk,lb_TIeude;
    private JButton bt_DN;
    private JTextField txt_tenDN,Checktxt_tenDN,Checktxt_password;
    private JPasswordField txt_password;
    private String TenNguoiDung;
    public static void main(String[] args) {
        new FormDangNhap();

    }
    public FormDangNhap() {

        panel=new KGradientPanel();
        lb_TIeude = new JLabel("Đăng Nhập");
        lb_TIeude.setFont(new Font("Arial", Font.BOLD, 30));
        lb_TIeude.setHorizontalAlignment(SwingConstants.CENTER);
        lb_TIeude.setBounds(127, 45, 234, 30);
        lb_TIeude.setForeground(Color.WHITE);
        panel.add(lb_TIeude);


        JLabel lb_Tentk = new JLabel("Tên tài khoản :");
        lb_Tentk.setHorizontalAlignment(SwingConstants.RIGHT);
        lb_Tentk.setFont(new Font("Arial", Font.BOLD, 15));
        lb_Tentk.setBounds(23, 109, 111, 22);
        lb_Tentk.setForeground(Color.WHITE);
        panel.add(lb_Tentk);

        JLabel lb_mk = new JLabel("Mật khẩu :");
        lb_mk.setHorizontalAlignment(SwingConstants.RIGHT);
        lb_mk.setFont(new Font("Arial", Font.BOLD, 15));
        lb_mk.setBounds(23, 180, 111, 22);
        lb_mk.setForeground(Color.WHITE);
        panel.add(lb_mk);

        txt_tenDN = new JTextField();
        txt_tenDN.setBounds(144, 106, 234, 30);
        txt_tenDN.setFont(new Font("Arial",Font.BOLD,12));
        panel.add(txt_tenDN);
        txt_tenDN.setColumns(10);

        txt_password = new JPasswordField();
        txt_password.setBounds(144, 177, 234, 30);
        txt_password.setFont(new Font("Arial",Font.BOLD,12));
        panel.add(txt_password);

        setContentPane(panel);




        bt_DN = new JButton("Đăng Nhập");
        bt_DN.setBackground(new Color(255,0,0));
        bt_DN.setForeground(Color.WHITE);
        bt_DN.setFont(new Font("Arial", Font.BOLD, 20));
        bt_DN.setBounds(168, 248, 153, 36);
        bt_DN.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.add(bt_DN);



        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txt_tenDN.addActionListener(this);
        txt_password.addActionListener(this);
        bt_DN.addActionListener(this);


        setBounds(380, 100, 477, 407);
        panel.kStartColor=new Color(63,94,251);
        panel.kEndColor=new Color(252,70,107);
        setTitle("Đăng nhập");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DangnhapDAO dt=new DangnhapDAO();
        String tendangnhap=txt_tenDN.getText();
        System.out.println(tendangnhap);
        String matkhau=String.valueOf(txt_password.getPassword());
        System.out.println(matkhau);


        if (e.getSource()==bt_DN){
            if (tendangnhap.equals("")){
                //    JOptionPane.showMessageDialog(null,"khong duoccbor trong ten dang nhap");
                Checktxt_tenDN=new JTextField();
                Checktxt_tenDN.setBounds(144, 106, 234, 30);
                Checktxt_tenDN.setFont(new Font("Arial",Font.BOLD,14));
                Checktxt_tenDN.setText("* Không được bỏ trống");

                Checktxt_tenDN.setBorder(new EmptyBorder(5,5,5,5));
                Checktxt_tenDN.setHorizontalAlignment(SwingConstants.RIGHT);
                Checktxt_tenDN.setForeground(Color.RED);

                panel.add(Checktxt_tenDN);

                //txt_MaNV.setText("");


                ActionListener taskPerformer = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {

                        Checktxt_tenDN.setVisible(false);




                    }
                };
                new Timer(7000, taskPerformer).start();



                return;

            }



            if (matkhau.equals("")){
                //    JOptionPane.showMessageDialog(null,"khong duoccbor trong ten dang nhap");
                Checktxt_password=new JTextField();
                Checktxt_password.setBounds(144, 177, 234, 30);
                Checktxt_password.setFont(new Font("Arial",Font.BOLD,14));
                Checktxt_password.setText("* Không được bỏ trống");

                Checktxt_password.setBorder(new EmptyBorder(5,5,5,5));
                Checktxt_password.setHorizontalAlignment(SwingConstants.RIGHT);
                Checktxt_password.setForeground(Color.RED);

                panel.add(Checktxt_password);




                ActionListener taskPerformer = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {

                        Checktxt_password.setVisible(false);




                    }
                };
                new Timer(7000, taskPerformer).start();



                return;

            }

            if (dt.checkLogin(tendangnhap,matkhau)==true){
                try {
                    dt.GetInfor(tendangnhap);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                new giaodienchinh();
                this.setVisible(false);

            }
            else {
                JOptionPane.showMessageDialog(this,"Tên đăng nhập hoặc mật khẩu không đúng");
            }

        }
    }

}




//
//class database {
//    connect kn = new connect();
//
////    public boolean checkLogin(String tentaikhoan, String matkhau) {
//
//        try {
//
//            if (kn.connect() == true) {
//
//
//
//                String sql = "SELECT MaTK,MatKhau,MaNV,MaQuyen FROM taikhoan WHERE MaTK=? and MatKhau=? ";
//                PreparedStatement ps = kn.con.prepareStatement(sql);
//                ps.setString(1, tentaikhoan);
//                ps.setString(2, matkhau);
//
//                ResultSet rs = ps.executeQuery();
//                if (rs.next()) {
//                     System.out.println(tentaikhoan + " " + matkhau);
//                     
//                    return true;
//
//                } else {
//
//                    return false;
//                }
//
//            }
//        }
//        catch (Exception e) {
//            System.out.println(e);
//        }
//        kn.closeDB();
//        return false;
//    }
//    public void GetInfor(String tendangnhap) throws IOException {
//    	try {
//    		if(kn.connect()) {
//    			String sql = "SELECT * FROM taikhoan WHERE `taikhoan`.`MaTK` = '"+tendangnhap+"'";
//    			 Statement stmt = kn.con.createStatement();
//    			 ResultSet r = stmt.executeQuery(sql);
//    			 while(r.next()) {
//	    			 String matk = r.getString("MaTK");
//	    			 String mk = r.getString("MatKhau");
//	    			 String nv = r.getString("MaNV");
//	    			 String quyen = r.getString("MaQuyen");
//	    			 GhiFile(matk, mk, nv, quyen);
//	    			 
//    			 }
//    		}kn.closeDB();
//    	}
//    	catch(SQLException e) {
//    		
//    	}
//    }
//    public void GhiFile(String matk, String matkhau, String manv, String maquyen) throws IOException {
//		File FILE = new File("taikhoan.txt");
//		FILE.delete();
//		FILE.createNewFile();
//		try{
//			FileOutputStream file = new FileOutputStream("taikhoan.txt");
//			DataOutputStream data = new DataOutputStream(file);
//			file = new FileOutputStream("taikhoan.txt");
//			data = new DataOutputStream(file);
//				data.writeUTF(matk);
//				data.writeUTF(matkhau);
//				data.writeUTF(manv);
//				data.writeUTF(maquyen);
//				
//			System.out.println("SAVED");
//
//			file.close();
//			data.close();
//		}
//		catch(FileNotFoundException e) {
//
//		}
//	}
//    
//}
