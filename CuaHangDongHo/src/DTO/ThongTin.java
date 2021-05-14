package DTO;

public class ThongTin {
	private String matk;
	private String matkhau;
	private String manv;
	private String quyen;
	public ThongTin(String tk, String mk, String nv, String quyen) {
		matk = tk;
		matkhau = mk;
		manv =nv;
		this.quyen = quyen;
	}
	public String getMatk() {
		return matk;
	}
	public void setMatk(String matk) {
		this.matk = matk;
	}
	public String getMatkhau() {
		return matkhau;
	}
	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
	public String getManv() {
		return manv;
	}
	public void setManv(String manv) {
		this.manv = manv;
	}
	public String getQuyen() {
		return quyen;
	}
	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}
	
}
