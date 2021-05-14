package DTO;

public class HoaDon {
	//MaHD	MaNV	MaKH	NgayLap	TongTien
	private String MaHD;
	private String MaNV;
	private String MaKH;
	private String NgayLap;
	private Float TongTien;
	public HoaDon(String mahd, String manv, String makh, String ngaylap, float tongtien) {
		MaHD = mahd;
		MaNV = manv;
		MaKH = makh;
		NgayLap = ngaylap;
		TongTien = tongtien;
	}
	public String getMaHD() {
		return MaHD;
	}
	public void setMaHD(String maHD) {
		MaHD = maHD;
	}
	public String getMaNV() {
		return MaNV;
	}
	public void setMaNV(String maNV) {
		MaNV = maNV;
	}
	public String getMaKH() {
		return MaKH;
	}
	public void setMaKH(String maKH) {
		MaKH = maKH;
	}
	public String getNgayLap() {
		return NgayLap;
	}
	public void setNgayLap(String ngayLap) {
		NgayLap = ngayLap;
	}
	public Float getTongTien() {
		return TongTien;
	}
	public void setTongTien(Float tongTien) {
		TongTien = tongTien;
	}
	
	
}
