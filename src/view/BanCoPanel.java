package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import model.Data;

public class BanCoPanel extends JPanel implements MouseListener {
    Image anh_quanCo;
    Main main;
    int movingX, movingY, desX, desY, deltaX, deltaY;

    public BanCoPanel(Main main) {
        setPreferredSize(new Dimension(450, 495));
        setBackground(main.mauNen);
        addMouseListener(this);
        this.main = main;
    }

    @Override
    public void paintComponent(Graphics g) {
        if (main.viTriTrenMaTrix.banCo == null)
            return;
        super.paintComponent(g);
        g.drawImage(main.images.get(Data.GAME_CO_VUA), 20, 36, this);
        g.drawImage(main.images.get(Data.BOARD_IMAGE), 20, 65, this);
        for (int i = 0; i < main.viTriTrenMaTrix.banCo.length - 11; i++) {
            if (main.viTriTrenMaTrix.banCo[i] == Data.KHUNG)
                continue;
            int x = i % 10;
            int y = (i - x) / 10;

            if (main.quanCoDangChon && i == main.diChuyen.viTriDau) {
                g.drawImage(main.images.get(Data.GLOW), x * 45, y * 45, this);
            } else if (!main.quanCoDangChon && main.diChuyen.viTriDich == i
                    && (main.viTriTrenMaTrix.banCo[i] == Data.OTRONG || main.viTriTrenMaTrix.banCo[i] < 0)) {
                g.drawImage(main.images.get(Data.GLOW2), x * 45, y * 45, this);
            }

            if (main.viTriTrenMaTrix.banCo[i] == Data.OTRONG)
                continue;

            if (main.trangThai == Data.CAP_NHAT && i == main.diChuyen.viTriDau)
                continue;
            if (main.viTriTrenMaTrix.banCo[i] > 0) {
                int piece = main.viTriTrenMaTrix.cacLoaiQuanCoNguoi[main.viTriTrenMaTrix.banCo[i]].diem;
                g.drawImage(main.images.get(piece), x * 45, y * 45, this);
            } else {
                int piece = main.viTriTrenMaTrix.cacLoaiQuanCoMay[-main.viTriTrenMaTrix.banCo[i]].diem;
                g.drawImage(main.images.get(-piece), x * 45, y * 45, this);
            }
        }
        if (main.trangThai == Data.CAP_NHAT) {
            g.drawImage(anh_quanCo, movingX, movingY, this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (main.trangThai != Data.NGUOI_DI)
            return;
        int viTriQuanCo = main.mapGiaTriBanCo(e.getY()) * 10 + main.mapGiaTriBanCo(e.getX());
        if (main.viTriTrenMaTrix.banCo[viTriQuanCo] == Data.KHUNG)
            return;
        if ((!main.quanCoDangChon || main.viTriTrenMaTrix.banCo[viTriQuanCo] > 0)
                && main.viTriTrenMaTrix.banCo[viTriQuanCo] != Data.OTRONG) {
            if (main.viTriTrenMaTrix.banCo[viTriQuanCo] > 0) {
                main.quanCoDangChon = true;
                main.diChuyen.viTriDau = viTriQuanCo;

            }
        } else if (main.quanCoDangChon && main.choPhepDiChuyen(viTriQuanCo)) {
            main.quanCoDangChon = false;

            main.diChuyen.viTriDich = viTriQuanCo;
            main.trangThai = Data.DICHUYEN_ANH;

        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}