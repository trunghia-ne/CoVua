package model;

public class Data {

    public final static int OTRONG = 44;
    public final static int KHUNG = 88;
    public final static int NGUOI = 1;
    public final static int MAY = 0;
    public final static int BOARD_IMAGE = 1000;
    public final static int GLOW = 1001;
    public final static int GLOW2 = 1002;
    public final static int DICHUYEN_ANH = 1003;
    public final static int CAP_NHAT = 1004;
    public final static int NGUOI_DI = 1005;
    public final static int MAY_DI = 1006;
    public final static int DAKETTHUC = 1007;
    public final static int THUA = 0;
    public final static int DACHIEUTUONG = 1;
    public final static int TAOMOIGAME_BUTTON = 10078;
    public final static int LOGO = 10077;
    public final static int NEW_BUTTON2 = 10079;
    public final static int THOAT_BUTTON = 10080;
    public final static int THOAT_BUTTON2 = 10081;
    public final static int THANG_CHUC = 10086;
    public final static int BOARD_IMAGE2 = 10087;
    public final static int GAME_CO_VUA = 10095;
    public static final int QUAYLUI_BUTTON = 10084;
    public static final int QUAYLUI_BUTTON2 = 10094;

    public static int doiNguyenTac(int player) {
        return (player == NGUOI) ? MAY : NGUOI;
    }

    public final static int[] BANG_DIEM_TOT_NGUOI = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 50, 50, 50, 50, 50, 50, 50, 0, 0, 10, 10, 20, 30, 30, 20, 10, 10, 0, 0, 5, 5,
            10, 25, 25, 10, 5, 5, 0, 0, 0, 0, 0, 20, 20, 0, 0, 0, 0, 0, 5, -5, -10, 0, 0, -10, -5, 5, 0, 0, 5, 10, 10,
            -20, -20, 10, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0 };
    public final static int[] BANG_DIEM_TOT_MAY = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 5, 10, 10, -20, -20, 10, 10, 5, 0, 0, 5, -5, -10, 0, 0, -10, -5, 5, 0, 0, 0, 0, 0,
            20, 20, 0, 0, 0, 0, 0, 5, 5, 10, 25, 25, 10, 5, 5, 0, 0, 10, 10, 20, 30, 30, 20, 10, 10, 0, 0, 50, 50, 50,
            50, 50, 50, 50, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0 };
    public final static int[] BANG_DIEM_MA_NGUOI = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -50,
            -40, -30, -30, -30, -30, -40, -50, 0, 0, -40, -20, 0, 0, 0, 0, -20, -40, 0, 0, -30, 0, 10, 15, 15, 10, 0,
            -30, 0, 0, -30, 5, 15, 20, 20, 15, 5, -30, 0, 0, -30, 0, 15, 20, 20, 15, 0, -30, 0, 0, -30, 5, 10, 15, 15,
            10, 5, -30, 0, 0, -40, -20, 0, 5, 5, 0, -20, -40, 0, 0, -50, -40, -30, -30, -30, -30, -40, -50, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    public final static int[] BANG_DIEM_MA_MAY = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -50,
            -40, -30, -30, -30, -30, -40, -50, 0, 0, -40, -20, 0, 5, 5, 0, -20, -40, 0, 0, -30, 5, 10, 15, 15, 10, 5,
            -30, 0, 0, -30, 0, 15, 20, 20, 15, 0, -30, 0, 0, -30, 5, 15, 20, 20, 15, 5, -30, 0, 0, -30, 0, 10, 15, 15,
            10, 0, -30, 0, 0, -40, -20, 0, 0, 0, 0, -20, -40, 0, 0, -50, -40, -30, -30, -30, -30, -40, -50, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    public final static int[] BANG_DIEM_TUONG_NGUOI = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            -20, -10, -10, -10, -10, -10, -10, -20, 0, 0, -10, 0, 0, 0, 0, 0, 0, -10, 0, 0, -10, 0, 5, 10, 10, 5, 0,
            -10, 0, 0, -10, 5, 5, 10, 10, 5, 5, -10, 0, 0, -10, 0, 10, 10, 10, 10, 0, -10, 0, 0, -10, 10, 10, 10, 10,
            10, 10, -10, 0, 0, -10, 5, 0, 0, 0, 0, 5, -10, 0, 0, -20, -10, -10, -10, -10, -10, -10, -20, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    public final static int[] BANG_DIEM_TUONG_MAY = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            -20, -10, -10, -10, -10, -10, -10, -20, 0, 0, -10, 5, 0, 0, 0, 0, 5, -10, 0, 0, -10, 10, 10, 10, 10, 10, 10,
            -10, 0, 0, -10, 0, 10, 10, 10, 10, 0, -10, 0, 0, -10, 5, 5, 10, 10, 5, 5, -10, 0, 0, -10, 0, 5, 10, 10, 5,
            0, -10, 0, 0, -10, 0, 0, 0, 0, 0, 0, -10, 0, 0, -20, -10, -10, -10, -10, -10, -10, -20, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };

    public final static int[] BANG_DIEM_XE_NGUOI = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5,
            10, 10, 10, 10, 10, 10, 5, 0, 0, -5, 0, 0, 0, 0, 0, 0, -5, 0, 0, -5, 0, 0, 0, 0, 0, 0, -5, 0, 0, -5, 0, 0,
            0, 0, 0, 0, -5, 0, 0, -5, 0, 0, 0, 0, 0, 0, -5, 0, 0, -5, 0, 0, 0, 0, 0, 0, -5, 0, 0, 0, 0, 0, 5, 5, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    public final static int[] BANG_DIEM_XE_MAY = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, -5, 0, 0, 0, 0, 0, 0, -5, 0, 0, -5, 0, 0, 0, 0, 0, 0,
            -5, 0, 0, -5, 0, 0, 0, 0, 0, 0, -5, 0, 0, -5, 0, 0, 0, 0, 0, 0, -5, 0, 0, -5, 0, 0, 0, 0, 0, 0, -5, 0, 0, 5,
            10, 10, 10, 10, 10, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    public final static int[] BANG_DIEM_HAU_NGUOI = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            -20, -10, -10, -5, -5, -10, -10, -20, 0, 0, -10, 0, 0, 0, 0, 0, 0, -10, 0, 0, -10, 0, 5, 5, 5, 5, 0, -10, 0,
            0, -5, 0, 5, 5, 5, 5, 0, -5, 0, 0, 0, 0, 5, 5, 5, 5, 0, -5, 0, 0, -10, 5, 5, 5, 5, 5, 0, -10, 0, 0, -10, 0,
            5, 0, 0, 0, 0, -10, 0, 0, -20, -10, -10, -5, -5, -10, -10, -20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0 };
    public final static int[] BANG_DIEM_HAU_MAY = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -20,
            -10, -10, -5, -5, -10, -10, -20, 0, 0, -10, 0, 0, 0, 0, 5, 0, -10, 0, 0, -10, 0, 5, 5, 5, 5, 5, -10, 0, 0,
            -5, 0, 5, 5, 5, 5, 0, 0, 0, 0, -5, 0, 5, 5, 5, 5, 0, -5, 0, 0, -10, 0, 5, 5, 5, 5, 0, -10, 0, 0, -10, 0, 0,
            0, 0, 0, 0, -10, 0, 0, -20, -10, -10, -5, -5, -10, -10, -20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0 };
    public final static int[] BANG_DIEM_VUA_NGUOI = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            -30, -40, -40, -50, -50, -40, -40, -30, 0, 0, -30, -40, -40, -50, -50, -40, -40, -30, 0, 0, -30, -40, -40,
            -50, -50, -40, -40, -30, 0, 0, -30, -40, -40, -50, -50, -40, -40, -30, 0, 0, -20, -30, -30, -40, -40, -30,
            -30, -20, 0, 0, -10, -20, -20, -20, -20, -20, -20, -10, 0, 0, 20, 20, 0, 0, 0, 0, 20, 20, 0, 0, 20, 30, 10,
            0, 0, 10, 30, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    public final static int[] BANG_DIEM_VUA_MAY = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20,
            30, 10, 0, 0, 10, 30, 20, 0, 0, 20, 20, 0, 0, 0, 0, 20, 20, 0, 0, -10, -20, -20, -20, -20, -20, -20, -10, 0,
            0, -20, -30, -30, -40, -40, -30, -30, -20, 0, 0, -30, -40, -40, -50, -50, -40, -40, -30, 0, 0, -30, -40,
            -40, -50, -50, -40, -40, -30, 0, 0, -30, -40, -40, -50, -50, -40, -40, -30, 0, 0, -30, -40, -40, -50, -50,
            -40, -40, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

}
