package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import controller.GameCoVua;
import controller.GameController;
import model.Data;
import model.DiChuyen;
import model.QuanCo;
import model.Node;

public class Main extends JFrame implements MouseListener, IObservable {
    boolean quanCoDangChon;
    private boolean chonCoTrang;
    private ThuVienAnh thuVienAnh = new ThuVienAnh();
    private GameController gameController;
    private GameCoVua gameCoVua;
    DiChuyen diChuyen = new DiChuyen();
    Map<Integer, Image> images = new HashMap<Integer, Image>();
    private Map<Integer, Icon> icon_images = new HashMap<Integer, Icon>();
    JLabel taomoi_game, thoat, logo, quaylui;
    JPanel main_panel = new JPanel(new BorderLayout());
    TuyChonPanel tuyChon;
    Node viTriTrenMaTrix;
    BanCoPanel banCoPanel;
    int trangThai;
    boolean hoanDoiVuaXe;
    ThangChucChoTOTPanel thangChucPanel;
    Color mauNen = Color.decode("#D1EEEE");
    List<Node> lichSudichuyen = new ArrayList<Node>();
    int vitriundo;
    JSlider thanhlevel;

    public Main() {
        super("GAME CỜ VUA ");
        setContentPane(main_panel);
        viTriTrenMaTrix = new Node();
        thangChucPanel = new ThangChucChoTOTPanel(this);
        loadAnhMenu();
        loadAnhBanCo();
        banCoPanel = new BanCoPanel(this);
        main_panel.add(taoMenu(), BorderLayout.EAST);
        thanhlevel = new JSlider(JSlider.HORIZONTAL, 1, 5, 3);
        JPanel levelPane = new JPanel();
        thanhlevel.setMajorTickSpacing(1);
        thanhlevel.setPaintTicks(true);
        thanhlevel.setPaintLabels(true);
        levelPane.add(thanhlevel);
        levelPane.setBackground(mauNen);
        levelPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createTitledBorder("Chọn Độ Khó")));
        main_panel.add(levelPane, BorderLayout.SOUTH);
        main_panel.add(banCoPanel, BorderLayout.CENTER);
        main_panel.setBackground(mauNen);
        pack();
        Dimension size = getSize();
        size.height = 650;
        setSize(size);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                thoat();
            }
        });
    }

    public JPanel taoMenu() {
        logo = new JLabel(icon_images.get(Data.LOGO));
        taomoi_game = new JLabel(icon_images.get(Data.TAOMOIGAME_BUTTON));
        quaylui = new JLabel(icon_images.get(Data.QUAYLUI_BUTTON));
        thoat = new JLabel(icon_images.get(Data.THOAT_BUTTON));
        taomoi_game.addMouseListener(this);
        quaylui.addMouseListener(this);
        thoat.addMouseListener(this);
        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(taomoi_game);
        panel.add(quaylui);
        panel.add(thoat);

        panel.setBackground(mauNen);
        JPanel menu_panel = new JPanel(new BorderLayout());
        menu_panel.add(logo);
        menu_panel.setBackground(mauNen);
        menu_panel.add(panel, BorderLayout.SOUTH);
        menu_panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 40));
        return menu_panel;
    }

    public void init() {
        chonCoTrang = tuyChon.btTrang.isSelected();
        diChuyen.viTriDau = -1;
        diChuyen.viTriDich = -1;
        viTriTrenMaTrix = new Node();
        viTriTrenMaTrix.taoBanCo(chonCoTrang);
        gameCoVua = new GameCoVua(viTriTrenMaTrix);
        loadAnhQuanCo();
        thangChucPanel.setIcons(chonCoTrang);
        banCoPanel.repaint();
        if (chonCoTrang)
            trangThai = Data.NGUOI_DI;
        else
            trangThai = Data.MAY_DI;
        hoanDoiVuaXe = false;

        lichSudichuyen.clear();

        vitriundo = 0;

        gameController.setChieuSau(thanhlevel.getValue());
        System.out.println(thanhlevel.getValue());
        choiGame();
    }

    public void choiGame() {
        Thread t = new Thread() {
            public void run() {
                while (true) {
                    switch (trangThai) {
                        case Data.NGUOI_DI:
                            if (ketThucGame(Data.NGUOI_DI)) {
                                trangThai = Data.DAKETTHUC;
                                break;
                            }
                            break;
                        case Data.MAY_DI:
                            if (ketThucGame(Data.MAY)) {
                                trangThai = Data.DAKETTHUC;
                                break;
                            }
                            //do tốc độ minimax để so sánh
//						DiChuyen diChuyen2 = gameController.nuocDiTotNhatMinimax(Data.MAY, viTriTrenMaTrix,gameController.getChieuSau()).nuocDi;
                            diChuyen = gameController.nuocDiTotNhatAlphaBeta(Data.MAY, viTriTrenMaTrix, Integer.MIN_VALUE,
                                    Integer.MAX_VALUE, gameController.getChieuSau()).nuocDi;
                            System.out.println();
                            System.out.println();
                            System.out.println("nuoc di tot nhat cho may: ");
                            System.out.println(diChuyen.toString());

                            trangThai = Data.DICHUYEN_ANH;

                            break;
                        case Data.DICHUYEN_ANH:
                            diChuyenAnh();
                            break;
                        case Data.CAP_NHAT:
                            capNhatViTriQuanCo();

                            break;
                        case Data.DAKETTHUC:
                            return;
                    }
                    try {
                        Thread.sleep(3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    public boolean ketThucGame(int player) {
        int trangThai = gameCoVua.trangThai(player);
        boolean ketThuc = false;
        String color = "";
        if (player == Data.MAY) {
            color = (chonCoTrang) ? "Trắng" : "Đen";
        } else
            color = (chonCoTrang) ? "Đen" : "Trắng";
        if (trangThai == Data.DACHIEUTUONG) {
            hienThongBaothoat(color + " đã chiếu tướng hết đường đi nhé ");
            ketThuc = true;
        } else if (trangThai == Data.THUA) {
            hienThongBaothoat("chơi hay quá ");
            ketThuc = true;
        }
        return ketThuc;
    }

    public void hienThongBaothoat(String message) {
        int option = JOptionPane.showOptionDialog(null, message, "Kết thúc game", 0, JOptionPane.PLAIN_MESSAGE, null,
                new Object[] { " Chơi lại", "thoát" }, " Chơi lại");
        if (option == 0) {
            tuyChon.setVisible(true);
        }
    }

    public void hienThongBao() {
        JOptionPane.showMessageDialog(null, "đợi tui đi đã\n", "Message", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == thoat) {
            thoat();
        } else if (source == taomoi_game) {
            if (trangThai == Data.MAY_DI) {
                hienThongBao();
                return;
            }
            if (tuyChon == null) {
                tuyChon = new TuyChonPanel(this);
                gameController = new GameController(this);
            }
            tuyChon.setVisible(true);
        } else if (source == quaylui) {
            System.out.println("hien tai dang luu " + lichSudichuyen.size() + "ban co");
            vitriundo = lichSudichuyen.size() - 2;
            System.out.println("quay lại ban cờ " + vitriundo);
            if (vitriundo > 0) {
                quaylui();
                xoa2thangcuoi();
                luuLichSuDi();
            } else {
                System.out.println("het roi");
                this.trangThai = Data.DAKETTHUC;
                this.init();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getSource();
        if (source == taomoi_game) {
            taomoi_game.setIcon(icon_images.get(Data.NEW_BUTTON2));
        } else if (source == thoat) {
            thoat.setIcon(icon_images.get(Data.THOAT_BUTTON2));
        } else if (source == quaylui) {
            quaylui.setIcon(icon_images.get(Data.QUAYLUI_BUTTON2));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object source = e.getSource();
        if (source == taomoi_game) {
            taomoi_game.setIcon(icon_images.get(Data.TAOMOIGAME_BUTTON));
        } else if (source == thoat) {
            thoat.setIcon(icon_images.get(Data.THOAT_BUTTON));
        } else if (source == quaylui) {
            quaylui.setIcon(icon_images.get(Data.QUAYLUI_BUTTON));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    public boolean choPhepDiChuyen(int huongDi) {
        int viTriDau = diChuyen.viTriDau;
        int viTriDich = viTriTrenMaTrix.banCo[huongDi];
        if (viTriDich == Data.KHUNG)
            return false;
        if (!gameCoVua.vuaAnToan(Data.NGUOI, viTriDau, huongDi))
            return false;
        boolean choPhepDi = false;
        int quanCo = viTriTrenMaTrix.cacLoaiQuanCoNguoi[viTriTrenMaTrix.banCo[viTriDau]].diem;
        switch (quanCo) {
            case QuanCo.TOT:
                if (huongDi == viTriDau - 10 && viTriDich == Data.OTRONG)
                    choPhepDi = true;
                if (huongDi == viTriDau - 20 && viTriTrenMaTrix.banCo[viTriDau - 10] == Data.OTRONG
                        && viTriDich == Data.OTRONG && viTriDau > 80)
                    choPhepDi = true;
                if (huongDi == viTriDau - 9 && viTriDich < 0)
                    choPhepDi = true;
                if (huongDi == viTriDau - 11 && viTriDich < 0)
                    choPhepDi = true;
                break;
            case QuanCo.MA:
            case QuanCo.VUA:
                if (quanCo == QuanCo.VUA)
                    choPhepDi = choPhepDoiViTriVuaVaXe(huongDi);
                int[] huongDiMoi = null;
                if (quanCo == QuanCo.MA)
                    huongDiMoi = new int[] { viTriDau - 21, viTriDau + 21, viTriDau + 19, viTriDau - 19, viTriDau - 12,
                            viTriDau + 12, viTriDau - 8, viTriDau + 8 };
                else
                    huongDiMoi = new int[] { viTriDau + 1, viTriDau - 1, viTriDau + 10, viTriDau - 10, viTriDau + 11,
                            viTriDau - 11, viTriDau + 9, viTriDau - 9 };
                for (int i = 0; i < huongDiMoi.length; i++) {
                    if (huongDiMoi[i] == huongDi) {
                        if (viTriDich == Data.OTRONG || viTriDich < 0) {
                            choPhepDi = true;
                            break;
                        }
                    }
                }
                break;
            case QuanCo.TUONG:
            case QuanCo.XE:
            case QuanCo.HAU:
                int[] huongDiChuyen = null;
                if (quanCo == QuanCo.TUONG)
                    huongDiChuyen = new int[] { 11, -11, 9, -9 };
                if (quanCo == QuanCo.XE)
                    huongDiChuyen = new int[] { 1, -1, 10, -10 };
                if (quanCo == QuanCo.HAU)
                    huongDiChuyen = new int[] { 1, -1, 10, -10, 11, -11, 9, -9 };
                for (int i = 0; i < huongDiChuyen.length; i++) {
                    int viTriMoi = viTriDau + huongDiChuyen[i];
                    choPhepDi = true;
                    while (huongDi != viTriMoi) {
                        viTriDich = viTriTrenMaTrix.banCo[viTriMoi];
                        if (viTriDich != Data.OTRONG) {
                            choPhepDi = false;
                            break;
                        }
                        viTriMoi += huongDiChuyen[i];
                    }
                    if (choPhepDi)
                        break;
                }
                break;
        }

        return choPhepDi;
    }

    public boolean choPhepDoiViTriVuaVaXe(int viTriDich) {
        QuanCo vua = viTriTrenMaTrix.cacLoaiQuanCoNguoi[8];
        QuanCo xe_phai = viTriTrenMaTrix.cacLoaiQuanCoNguoi[6];
        QuanCo xe_trai = viTriTrenMaTrix.cacLoaiQuanCoNguoi[5];

        if (vua.dcPhepDiChuyen)
            return false;
        int viTriDau = diChuyen.viTriDau;

        if (xe_phai == null && xe_trai == null)
            return false;
        if (xe_phai != null && xe_phai.dcPhepDiChuyen && xe_trai != null && xe_trai.dcPhepDiChuyen)
            return false;

        if (chonCoTrang) {
            if (viTriDau != 95)
                return false;
            if (viTriDich != 97 && viTriDich != 93)
                return false;
            if (viTriDich == 97) {
                if (viTriTrenMaTrix.banCo[96] != Data.OTRONG)
                    return false;
                if (viTriTrenMaTrix.banCo[97] != Data.OTRONG)
                    return false;
                if (!gameCoVua.vuaAnToan(Data.NGUOI, viTriDau, 96))
                    return false;
                if (!gameCoVua.vuaAnToan(Data.NGUOI, viTriDau, 97))
                    return false;
            } else if (viTriDich == 93) {
                if (viTriTrenMaTrix.banCo[94] != Data.OTRONG)
                    return false;
                if (viTriTrenMaTrix.banCo[93] != Data.OTRONG)
                    return false;
                if (!gameCoVua.vuaAnToan(Data.NGUOI, viTriDau, 94))
                    return false;
                if (!gameCoVua.vuaAnToan(Data.NGUOI, viTriDau, 93))
                    return false;
            }
        } else {
            if (viTriDau != 94)
                return false;
            if (viTriDich != 92 && viTriDich != 96)
                return false;
            if (viTriDich == 92) {
                if (viTriTrenMaTrix.banCo[93] != Data.OTRONG)
                    return false;
                if (viTriTrenMaTrix.banCo[92] != Data.OTRONG)
                    return false;
                if (!gameCoVua.vuaAnToan(Data.NGUOI, viTriDau, 93))
                    return false;
                if (!gameCoVua.vuaAnToan(Data.NGUOI, viTriDau, 92))
                    return false;
            } else if (viTriDich == 96) {
                if (viTriTrenMaTrix.banCo[95] != Data.OTRONG)
                    return false;
                if (viTriTrenMaTrix.banCo[96] != Data.OTRONG)
                    return false;
                if (!gameCoVua.vuaAnToan(Data.NGUOI, viTriDau, 95))
                    return false;
                if (!gameCoVua.vuaAnToan(Data.NGUOI, viTriDau, 96))
                    return false;
            }
        }

        return hoanDoiVuaXe = true;
    }

    // map giá trị ban cờ và giao diện
    public int mapGiaTriBanCo(int giaTri) {
        return giaTri / 45;
    }

    public void diChuyenAnh() {
        int anh_QuanCo = 0;
        if (viTriTrenMaTrix.banCo[diChuyen.viTriDau] > 0) {
            anh_QuanCo = viTriTrenMaTrix.cacLoaiQuanCoNguoi[viTriTrenMaTrix.banCo[diChuyen.viTriDau]].diem;
        } else {
            anh_QuanCo = -viTriTrenMaTrix.cacLoaiQuanCoMay[-viTriTrenMaTrix.banCo[diChuyen.viTriDau]].diem;
        }
        banCoPanel.anh_quanCo = images.get(anh_QuanCo);
        int x = diChuyen.viTriDau % 10;
        int y = (diChuyen.viTriDau - x) / 10;
        banCoPanel.desX = diChuyen.viTriDich % 10;
        banCoPanel.desY = (diChuyen.viTriDich - banCoPanel.desX) / 10;
        int dX = banCoPanel.desX - x;
        int dY = banCoPanel.desY - y;
        banCoPanel.movingX = x * 45;
        banCoPanel.movingY = y * 45;
        if (Math.abs(dX) > Math.abs(dY)) {
            if (dY == 0) {
                banCoPanel.deltaX = (dX > 0) ? 1 : -1;
                banCoPanel.deltaY = 0;
            } else {
                banCoPanel.deltaX = (dX > 0) ? Math.abs(dX / dY) : -(Math.abs(dX / dY));
                banCoPanel.deltaY = (dY > 0) ? 1 : -1;
            }
        } else {
            if (dX == 0) {
                banCoPanel.deltaY = (dY > 0) ? 1 : -1;
                banCoPanel.deltaX = 0;
            } else {
                banCoPanel.deltaX = (dX > 0) ? 1 : -1;
                banCoPanel.deltaY = (dY > 0) ? Math.abs(dY / dX) : -(Math.abs(dY / dX));
            }
        }
        trangThai = Data.CAP_NHAT;
    }

    public void capNhatViTriQuanCo() {
        if (banCoPanel.movingX == banCoPanel.desX * 45 && banCoPanel.movingY == banCoPanel.desY * 45) {
            banCoPanel.repaint();
            int viTriDau = viTriTrenMaTrix.banCo[diChuyen.viTriDau];
            if (viTriDau > 0) {
                trangThai = Data.MAY_DI;
            } else {
                if (diChuyen.viTriDich > 90 && diChuyen.viTriDich < 98
                        && viTriTrenMaTrix.cacLoaiQuanCoMay[-viTriDau].diem == QuanCo.TOT)
                    thangChucTOT_may();
                if (ketThucGame(Data.NGUOI))
                    trangThai = Data.DAKETTHUC;
                trangThai = Data.NGUOI_DI;
            }
            viTriTrenMaTrix.update(diChuyen);

            luuLichSuDi();

            if (viTriDau > 0) {
                if (hoanDoiVuaXe) {
                    System.out.println("doi vua xe");
                    hoanDoiVuaXe();

                    trangThai = Data.DICHUYEN_ANH;
                    // trangThai =Data.NGUOI_DI;

                } else if (diChuyen.viTriDich > 20 && diChuyen.viTriDich < 29
                        && viTriTrenMaTrix.cacLoaiQuanCoNguoi[viTriDau].diem == QuanCo.TOT) {
                    thangChucQuanTot();
                }
            } else {
                if (ketThucGame(Data.NGUOI)) {
                    trangThai = Data.DAKETTHUC;
                    return;
                }
            }
            if (!hoanDoiVuaXe && trangThai != Data.THANG_CHUC)
                lichSudichuyen.remove(lichSudichuyen.size() - 1);
            luuLichSuDi();
            if (hoanDoiVuaXe)
                hoanDoiVuaXe = false;
            if (trangThai != Data.DICHUYEN_ANH) {
            }

        }

        banCoPanel.movingX += banCoPanel.deltaX;
        banCoPanel.movingY += banCoPanel.deltaY;
        banCoPanel.repaint();

    }

    public void thangChucQuanTot() {
        // hiện panel chọn thăng chức
        thangChucPanel.location = diChuyen.viTriDich;
        thangChucPanel.index = viTriTrenMaTrix.banCo[diChuyen.viTriDich];
        thangChucPanel.setVisible(true);
        trangThai = Data.THANG_CHUC;
    }

    public void thangChucTOT_may() {
        // tự động đổi tốt thành hậu cho máy khi ở trạng thái thăng chức
        int viTriThangChuc = viTriTrenMaTrix.banCo[diChuyen.viTriDau];
        viTriTrenMaTrix.cacLoaiQuanCoMay[-viTriThangChuc] = new QuanCo(QuanCo.HAU, diChuyen.viTriDich);
    }

    public void hoanDoiVuaXe() {

        if (diChuyen.viTriDich == 97 || diChuyen.viTriDich == 96) {
            diChuyen.viTriDau = 98;
            diChuyen.viTriDich -= 1;
        } else if (diChuyen.viTriDich == 92 || diChuyen.viTriDich == 93) {
            diChuyen.viTriDau = 91;
            diChuyen.viTriDich += 1;
        }

    }

    public void luuLichSuDi() {
        viTriTrenMaTrix.nuocDi = null;
        lichSudichuyen.add(new Node(viTriTrenMaTrix));

        System.out.println("da luu " + lichSudichuyen.size() + " nuoc di");
        System.out.println("ban co moi \n==============================");
        viTriTrenMaTrix.displayBoard();
        System.out.println("==============================");
    }

    public void quaylui() {
        if (lichSudichuyen.size() >= 1) {
            vitriundo--;
            Node vt = lichSudichuyen.get(vitriundo);
            this.viTriTrenMaTrix = vt;
            this.gameCoVua.setViTri(vt);
            trangThai = Data.NGUOI_DI;
            banCoPanel.repaint();

            System.out.println("so lan lui lai " + lichSudichuyen.size() / 2);
        }

    }

    public void xoa2thangcuoi() {
        int s = lichSudichuyen.size() - 1;
        System.out.println("da xoa ban co nay");
        lichSudichuyen.get(s).displayBoard();
        lichSudichuyen.remove(s);
        System.out.println("da xoa ban co nay");
        lichSudichuyen.get(s - 1).displayBoard();
        lichSudichuyen.remove(s - 1);
        System.out.println("da xoa ban co nay");
        lichSudichuyen.get(s - 2).displayBoard();
        lichSudichuyen.remove(s - 2);

    }

    public void loadAnhQuanCo() {
        char[] resource_keys = { 'p', 'n', 'b', 'r', 'q', 'k' };
        int[] images_keys = { QuanCo.TOT, QuanCo.MA, QuanCo.TUONG, QuanCo.XE, QuanCo.HAU, QuanCo.VUA };
        try {
            for (int i = 0; i < resource_keys.length; i++) {
                images.put(images_keys[i],
                        ImageIO.read(thuVienAnh.getResource((chonCoTrang ? "trang" : "den") + resource_keys[i])));
                images.put(-images_keys[i],
                        ImageIO.read(thuVienAnh.getResource((chonCoTrang ? "den" : "trang") + resource_keys[i])));

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadAnhBanCo() {
        try {
            images.put(Data.BOARD_IMAGE, ImageIO.read(thuVienAnh.getResource("banco")));
            images.put(Data.GLOW, ImageIO.read(thuVienAnh.getResource("glow")));
            images.put(Data.GLOW2, ImageIO.read(thuVienAnh.getResource("glow2")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadAnhMenu() {
//		icon_images.put(Data.LOGO, new ImageIcon(thuVienAnh.getResource("logo")));
        icon_images.put(Data.TAOMOIGAME_BUTTON, new ImageIcon(thuVienAnh.getResource("new_game")));
        icon_images.put(Data.NEW_BUTTON2, new ImageIcon(thuVienAnh.getResource("new_game_hover")));
        icon_images.put(Data.THOAT_BUTTON, new ImageIcon(thuVienAnh.getResource("thoat")));
        icon_images.put(Data.THOAT_BUTTON2, new ImageIcon(thuVienAnh.getResource("thoat_hover")));
        icon_images.put(Data.QUAYLUI_BUTTON, new ImageIcon(thuVienAnh.getResource("quaylui")));
        icon_images.put(Data.QUAYLUI_BUTTON2, new ImageIcon(thuVienAnh.getResource("quaylui_hover")));
    }

    public void thoat() {
        int option = JOptionPane.showConfirmDialog(null, "Cậu non quá đánh không lại nên thoát hả?", " Cờ Vua ",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION)
            System.exit(0);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    boolean nimbusFound = false;
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if (info.getName().equals("Nimbus")) {
                            UIManager.setLookAndFeel(info.getClassName());
                            nimbusFound = true;
                            break;
                        }
                    }
                    if (!nimbusFound) {
                        int option = JOptionPane.showConfirmDialog(null,
                                " không hỗ trợ UI này\n" + "ban muốn tiếp tục không?", "cảnh báo",
                                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (option == JOptionPane.NO_OPTION) {
                            System.exit(0);
                        }
                    }
                    Main mcg = new Main();
                    mcg.setLocationRelativeTo(null);
                    mcg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    mcg.setResizable(false);
                    mcg.setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getStackTrace());
                    e.printStackTrace();
                }
            }
        });
    }
}