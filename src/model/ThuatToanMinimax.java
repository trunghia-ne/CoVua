package model;

import controller.MoRongNuocDi;

public class ThuatToanMinimax {
    HamDanhGiaTinhDiem hamDanhGiaDiem;

    public ThuatToanMinimax() {
        hamDanhGiaDiem = new HamDanhGiaTinhDiem();
    }

    public Node nuocDiTotNhat(int player, Node state, int depth) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long startTime = System.nanoTime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Bo nho truoc khi chay: " + usedMemoryBefore / 1024 + " KB");

        if (depth == 0) {
            return state;
        }
        Node bestMove = null;
        MoRongNuocDi moRongNuocDi = new MoRongNuocDi(state, player);
        moRongNuocDi.moRongNuocDiMoi();
        Node[] dsNuocCoTheDi = moRongNuocDi.getDanhSachMoRong();
        if (dsNuocCoTheDi.length == 0) {
            return state;
        }
        for (Node move : dsNuocCoTheDi) {
            if (bestMove == null) {
                bestMove = move;
            }
            int score;
            if (player == Data.NGUOI) {
                score = minimax(false, move, depth - 1);
                if (score > hamDanhGiaDiem.tinhDiem(bestMove)) {
                    bestMove = move;
                }
            } else {
                score = minimax(true, move, depth - 1);
                if (score < hamDanhGiaDiem.tinhDiem(bestMove)) {
                    bestMove = move;
                }
            }
        }
        long endTime = System.nanoTime();
        long thoiGianChay = (endTime - startTime) / 1000000;
        System.out.println("Minimax - Thoi gian chay: " + thoiGianChay + " milliseconds");

        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Bo nho sau khi chay: " + usedMemoryAfter / 1024 + "KB");
        System.out.println("Bo nho su dung nuoc di: " + (usedMemoryAfter - usedMemoryBefore) / 1024 + " KB");
        return bestMove;
    }

    private int minimax(boolean maxmin, Node viTri, int depth) {
        if (depth == 0) {
            return hamDanhGiaDiem.tinhDiem(viTri);
        }
        if (maxmin) {
            int maxPlayer = Integer.MIN_VALUE;
            MoRongNuocDi moRongNuocDi = new MoRongNuocDi(viTri, Data.NGUOI);
            moRongNuocDi.moRongNuocDiMoi();
            Node[] dsNuocCoTheDi = moRongNuocDi.getDanhSachMoRong();
            for (Node move : dsNuocCoTheDi) {
                int value = minimax(false, move, depth - 1);
                maxPlayer = Math.max(maxPlayer, value);
            }
            return maxPlayer;

        } else {
            int minPlayer = Integer.MAX_VALUE;
            MoRongNuocDi moRongNuocDi = new MoRongNuocDi(viTri, Data.MAY);
            moRongNuocDi.moRongNuocDiMoi();
            Node[] dsNuocCoTheDi = moRongNuocDi.getDanhSachMoRong();

            for (Node move : dsNuocCoTheDi) {
                int value = minimax(true, move, depth - 1);
                minPlayer = Math.min(minPlayer, value);
            }
            return minPlayer;
        }
    }
    public static void main(String[] args) {
        Node viTriBanDau = new Node();
        viTriBanDau.taoBanCo(true);
        System.out.println("ban co ban dau:");
        viTriBanDau.displayBoard();

        int depth = 3;

        ThuatToanMinimax demo = new ThuatToanMinimax();
        int diemTotNhat = demo.minimax(true, viTriBanDau, depth);

        System.out.println("diem tot nhat: "+diemTotNhat);
    }
}
