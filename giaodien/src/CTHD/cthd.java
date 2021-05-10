package CTHD;

public class cthd {
	//MaHD	MaSP	DonGia	SoLuong	ThanhTien
	private String MaHD;
	private String MaSP;
	private float DonGia;
	private int SoLuong;
	private float ThanhTien;
	public cthd (String mahd, String masp, float dongia, int soluong, float thanhtien) {
		MaHD = mahd;
		MaSP = masp;
		DonGia = dongia;
		SoLuong = soluong;
		ThanhTien = thanhtien;
	}
	public String getMaHD() {
		return MaHD;
	}
	public void setMaHD(String maHD) {
		MaHD = maHD;
	}
	public String getMaSP() {
		return MaSP;
	}
	public void setMaSP(String maSP) {
		MaSP = maSP;
	}
	public float getDonGia() {
		return DonGia;
	}
	public void setDonGia(float donGia) {
		DonGia = donGia;
	}
	public int getSoLuong() {
		return SoLuong;
	}
	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}
	public float getThanhTien() {
		return DonGia * SoLuong;
	}
	public void setThanhTien(float thanhTien) {
		ThanhTien = thanhTien;
	}
	

}
