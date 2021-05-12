package NhaCungCap;
import java.util.*;

import javax.swing.JOptionPane;

import SanPham.SanPham;
public class NhaCungCapBUS {
    private ArrayList<NhaCungCap> dsncc = new ArrayList<>();
    private NhaCungCapDAO nccDAO = new NhaCungCapDAO();
    public NhaCungCapBUS (){
        dsncc = nccDAO.readDB();
    }

    public ArrayList<NhaCungCap> getDsncc() {
        return dsncc;
    }

    public boolean update (String mancc, String tenncc, String dcncc, String sdtncc) {
        boolean check = nccDAO.ChangeDB(mancc, tenncc,dcncc,sdtncc);
        if(check) {
            for(NhaCungCap ncc : dsncc) {
                if(mancc.equals(ncc.getMancc())) {
                    ncc.setTenncc(tenncc);
                    ncc.setDcncc((dcncc));
                    ncc.setSdtncc(sdtncc);

                    return true;
                }
            }
        }
        return false;
    }
    public boolean Add(NhaCungCap ncc) {
        boolean check = nccDAO.AddDB(ncc);
        if(check) {
            dsncc.add(ncc);
            return true;
        }
        else {
            JOptionPane.showMessageDialog(null, "Thêm không thành công!!!");
            return false;
        }
    }
    public boolean Delete(String mancc) {
        boolean check = nccDAO.DeleteDB(mancc);
        if(check) {
            for(NhaCungCap ncc : dsncc) {
                if(mancc.equals(ncc.getMancc())) {
                    dsncc.remove(ncc);
                    return true;
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Xóa không thành công!!!");
            return false;
        }
        return check;

    }

    public ArrayList<NhaCungCap> Search(String value){
        ArrayList<NhaCungCap> result = new ArrayList<>();
        for(NhaCungCap nhacungcap : dsncc) {
            if( nhacungcap.getMancc().toLowerCase().contains(value) ||
                    nhacungcap.getTenncc().toLowerCase().contains(value) ||
                    nhacungcap.getDcncc().toLowerCase().contains(value)||
                    nhacungcap.getSdtncc().toLowerCase().contains(value)) {
                result.add(nhacungcap);
            }

        }
        return result;
    }

}
