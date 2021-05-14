package DTO;

public class PhieuNhap {
	//MaPN	MaNV MaNCC	NgayLap	TongTien

	private String MaPN;
	private String MaNV;
	private String MaNCC;
	private String NgayLap;
	private float TongTien;
	public PhieuNhap(String mapn, String manv, String mancc, String ngaylap, float tien) {
		this.MaPN = mapn;
		this.MaNV = manv;
		this.MaNCC = mancc;
		this.NgayLap = ngaylap;
		this.TongTien = tien;
	}
	public String getMaPN() {
		return MaPN;
	}
	public void setMaPN(String maPN) {
		MaPN = maPN;
	}
	public String getMaNV() {
		return MaNV;
	}
	public void setMaNV(String maNV) {
		MaNV = maNV;
	}
	public String getMaNCC() {
		return MaNCC;
	}
	public void setMaNCC(String maNCC) {
		MaNCC = maNCC;
	}
	public String getNgayLap() {
		return NgayLap;
	}
	public void setNgayLap(String ngayLap) {
		NgayLap = ngayLap;
	}
	public float getTongTien() {
		return TongTien;
	}
	public void setTongTien(float tongTien) {
		TongTien = tongTien;
	}
	
}
