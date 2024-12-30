package controller;

import model.QuanCo;
import model.Node;

public interface IGameCoVua {

    public int trangThai(int nguoiChoi);

    public boolean vuaAnToan(int player, int viTriDau, int viTriCuoi);

    public boolean kiemTraChieuTuong(int player);

    public boolean totChieu(QuanCo vua);

    public boolean maChieu(QuanCo vua);

    public boolean vuaChieu(QuanCo vua);

    public boolean tuongChieu(QuanCo vua);

    public boolean xeChieu(QuanCo vua);

    public boolean hauChieu(QuanCo vua);

    public Node getViTri();

    public void setViTri(Node viTri);

    public QuanCo getVuaNguoi();

    public void setVuaNguoi(QuanCo vuaNguoi);

    public QuanCo getVuaMay();

    public void setVuaMay(QuanCo vuaMay);
}
