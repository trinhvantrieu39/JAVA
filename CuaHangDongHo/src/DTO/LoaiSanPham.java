package DTO;

public class LoaiSanPham {
	private String malsp;
	private String tenlsp;
	public LoaiSanPham(String ma, String ten) {
		malsp= ma;
		tenlsp = ten;
	}
	public String getMalsp() {
		return malsp;
	}
	public void setMalsp(String malsp) {
		this.malsp = malsp;
	}
	public String getTenlsp() {
		return tenlsp;
	}
	public void setTenlsp(String tenlsp) {
		this.tenlsp = tenlsp;
	}
	
}
