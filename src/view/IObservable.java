package view;

import java.awt.event.MouseEvent;

public interface IObservable {
    public void choiGame();

    public boolean ketThucGame(int player);

    public void hienThongBaothoat(String message);

    public void hienThongBao();

    public void mouseClicked(MouseEvent e);

    public boolean choPhepDiChuyen(int huongDi);

    public boolean choPhepDoiViTriVuaVaXe(int viTriDich);

    public void diChuyenAnh();

    public void capNhatViTriQuanCo();

    public void thangChucQuanTot();

    public void luuLichSuDi();

    public void loadAnhQuanCo();

    public void thoat();
}
