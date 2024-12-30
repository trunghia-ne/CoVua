package model;

public class HamDanhGiaTinhDiem {

    public int tinhDiem(Node viTri) {
        int diemNguoi = 0;
        int diemMay = 0;

        for (QuanCo quanCo : viTri.cacLoaiQuanCoNguoi) {
            if (quanCo != null) {
                diemNguoi += diemQuanCo(quanCo);
            }
        }
        for (QuanCo quanCo : viTri.cacLoaiQuanCoMay) {
            if (quanCo != null) {
                diemMay += diemQuanCo(quanCo);
            }
        }
        return diemNguoi - diemMay;
    }

    private int diemQuanCo(QuanCo quanCo) {
        int diem = quanCo.diem;

        if (isViTriChienLuoc(quanCo.viTriQuanCo)) {
            diem += 10;
        }

        return diem;
    }

    private boolean isViTriChienLuoc(int viTri) {
        int[] viTriTrungTam = { 44, 45, 54, 55 };
        for (int vt : viTriTrungTam) {
            if (viTri == vt) {
                return true;
            }
        }
        return false;
    }
}
