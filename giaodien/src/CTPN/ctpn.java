package CTPN;

public class ctpn {
	private String MaPN;
	private String MaSP;
	private float DonGia;
	private int SoLuong;
	private float ThanhTien;
	public ctpn (String mapn, String masp, float dongia, int soluong, float thanhtien) {
		MaPN = mapn;
		MaSP = masp;
		DonGia = dongia;
		SoLuong = soluong;
		ThanhTien = thanhtien;
	}
	public String getMaPN() {
		return MaPN;
	}
	public void setMaPN(String maPN) {
		MaPN = maPN;
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
		return ThanhTien;
	}
	public void setThanhTien(float thanhTien) {
		ThanhTien = thanhTien;
	}
}
