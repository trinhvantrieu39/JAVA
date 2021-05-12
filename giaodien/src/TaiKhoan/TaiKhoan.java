package TaiKhoan;

public class TaiKhoan {
	private String matk;
	private String matkhau;
	private String maquyen ;
	private String manv;
	public TaiKhoan(String matk, String matkhau,String maquyen, String manv) {
		this.matk = matk;
		this.matkhau = matkhau;
		this.manv = manv;
		this.maquyen = maquyen;
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
	public String getMaquyen() {
		return maquyen;
	}
	public void setMaquyen(String maquyen) {
		this.maquyen = maquyen;
	}
	public String getManv() {
		return manv;
	}
	public void setManv(String manv) {
		this.manv = manv;
	}
	
}
