package DTO;

public class PhanQuyen {
	private String maquyen;
	private String tenquyen;
	private String chitiet;
	public PhanQuyen(String ma, String ten, String chitiet) {
		maquyen = ma;
		this.tenquyen = ten;
		this.chitiet=chitiet;
	}
	public String getMaquyen() {
		return maquyen;
	}
	public void setMaquyen(String maquyen) {
		this.maquyen = maquyen;
	}
	public String getTenquyen() {
		return tenquyen;
	}
	public void setTenquyen(String tenquyen) {
		this.tenquyen = tenquyen;
	}
	public String getChitiet() {
		return chitiet;
	}
	public void setChitiet(String chitiet) {
		this.chitiet = chitiet;
	}
	
}
