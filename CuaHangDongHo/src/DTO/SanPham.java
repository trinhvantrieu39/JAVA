package DTO;

public class SanPham {
	public String masp;
	public String tensp;
	public String maloai;
	public int soluong;
	public float dongia;
	public String hinhanh;
	public SanPham() {
		this.masp= null;
		this.tensp= null;
		this.maloai= null;
		this.soluong= 0;
		this.dongia= 0;
		this.hinhanh= null;
	}
	public SanPham(String masp, String tensp, String maloai, int soluong, float dongia, String hinhanh) {
		this.masp= masp;
		this.tensp= tensp;
		this.maloai= maloai;
		this.soluong= soluong;
		this.dongia= dongia;
		this.hinhanh= hinhanh;
	}
	public String getMasp() {
		return masp;
	}
	public void setMasp(String masp) {
		this.masp = masp;
	}
	public String getTensp() {
		return tensp;
	}
	public void setTensp(String tensp) {
		this.tensp = tensp;
	}
	public String getMaloai() {
		return maloai;
	}
	public void setMaloai(String maloai) {
		this.maloai = maloai;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public float getDongia() {
		return dongia;
	}
	public void setDongia(float dongia) {
		this.dongia = dongia;
	}
	public String getHinhanh() {
		return hinhanh;
	}
	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}
	
	
	
}

