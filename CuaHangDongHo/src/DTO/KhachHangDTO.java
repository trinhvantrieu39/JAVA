package DTO;

public class KhachHangDTO {
    public String MaKH;
    public String TenKH;
    public String  DiaCHi;
    public String SDT;
    public String NgaySinh;
    public String Gioitinh;

    public KhachHangDTO(){
        this.MaKH="";
        this.TenKH="";
        this.DiaCHi="";
        this.SDT="";
        this.NgaySinh="";
        this.Gioitinh="";
    }
    public KhachHangDTO(String MaKH,String TenKH,String DiaCHi,String SDT,String NgaySinh,String Gioitinh){
        this.MaKH=MaKH;
        this.TenKH=TenKH;
        this.DiaCHi=DiaCHi;
        this.SDT=SDT;
        this.NgaySinh=NgaySinh;
        this.Gioitinh=Gioitinh;
    }

    public String getMaKH() {
        return MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public String getDiaCHi() {
        return DiaCHi;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public String getSDT() {
        return SDT;
    }

    public String getGioitinh() {
        return Gioitinh;
    }

    public void setMaKH(String maKH) {
        this.MaKH = maKH;
    }

    public void setTenKH(String tenKH) {
        this.TenKH = tenKH;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setDiaCHi(String diaCHi) {
        this.DiaCHi = diaCHi;
    }

    public void setGioitinh(String gioitinh) {
        this.Gioitinh = gioitinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.NgaySinh = ngaySinh;
    }
}
