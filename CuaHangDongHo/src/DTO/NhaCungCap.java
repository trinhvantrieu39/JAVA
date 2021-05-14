package DTO;
public class NhaCungCap {
    private String mancc;
    private String tenncc;
    private String dcncc;
    private String sdtncc;

    public NhaCungCap(String ma, String ten, String dc, String sdt) {
        this.mancc = ma;
        this.tenncc = ten;
        this.dcncc = dc;
        this.sdtncc = sdt;
    }

    public String getMancc() {
        return mancc;
    }

    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public String getTenncc() {
        return tenncc;
    }

    public void setTenncc(String tenncc) {
        this.tenncc = tenncc;
    }

    public String getDcncc() {
        return dcncc;
    }

    public void setDcncc(String dcncc) {
        this.dcncc = dcncc;
    }

    public String getSdtncc() {
        return sdtncc;
    }

    public void setSdtncc(String sdtncc) {
        this.sdtncc = sdtncc;
    }
}


