package model;

import controller.MoRongNuocDi;

public class ThuatToanCatTiaAB {
    HamDanhGiaTinhDiem hamDanhGiaDiem;

    public ThuatToanCatTiaAB() {
        this.hamDanhGiaDiem = new HamDanhGiaTinhDiem();
    }

    public Node nuocDiTotNhat(int player, Node viTri, int alpha, int beta, int depth) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long startTime = System.nanoTime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Bo nho truoc khi chay: " + usedMemoryBefore / 1024 + " KB");

        if (depth == 0) {
            return viTri;
        }
        Node bestMove = null;
        MoRongNuocDi moRongNuocDi = new MoRongNuocDi(viTri, player);
        moRongNuocDi.moRongNuocDiMoi();
        Node[] dsNuocCoTheDi = moRongNuocDi.getDanhSachMoRong();
        if (dsNuocCoTheDi.length == 0) {
            return viTri;
        }

        for (Node move : dsNuocCoTheDi) {
            if (bestMove == null) {
                bestMove = move;
            }
            int score;
            if (player == Data.NGUOI) {
                score = alphaBeta(false, move, alpha, beta, depth - 1);
                if (score > alpha) {
                    bestMove = move;
                    alpha = score;
                }
            } else {
                score = alphaBeta(true, move, alpha, beta, depth - 1);
                if (score < beta) {
                    bestMove = move;
                    beta = score;
                }
            }
            if (beta <= alpha) {
                break;
            }

        }
        long endTime = System.nanoTime();
        long thoiGianChay = (endTime - startTime) / 1000000;
        System.out.println("Alpha-Beta - Thoi gian chay: " + thoiGianChay + " milliseconds");

        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Bo nho sau khi chay: " + usedMemoryAfter / 1024 + "KB");
        System.out.println("Bo nho su dung nuoc di: " + (usedMemoryAfter - usedMemoryBefore) / 1024 + " KB");
        return bestMove;
    }

    private int alphaBeta(boolean maximizingPlayer, Node viTri, int alpha, int beta, int depth) {
        if (depth == 0) {
            return hamDanhGiaDiem.tinhDiem(viTri);
        }
        if (maximizingPlayer) {
            int maxValue = Integer.MIN_VALUE;
            MoRongNuocDi moRongNuocDi = new MoRongNuocDi(viTri, Data.NGUOI);
            moRongNuocDi.moRongNuocDiMoi();
            Node[] dsNuocCoTheDi = moRongNuocDi.getDanhSachMoRong();
            for (Node move : dsNuocCoTheDi) {
                int value = alphaBeta(false, move, alpha, beta, depth - 1);
                maxValue = Math.max(maxValue, value);
                alpha = Math.max(alpha, value);
                if (beta <= alpha) {
                    break;
                }
            }
            return maxValue;
        } else {
            int minValue = Integer.MAX_VALUE;
            MoRongNuocDi moRongNuocDi = new MoRongNuocDi(viTri, Data.MAY);
            moRongNuocDi.moRongNuocDiMoi();
            Node[] dsNuocCoTheDi = moRongNuocDi.getDanhSachMoRong();
            for (Node move : dsNuocCoTheDi) {
                int value = alphaBeta(true, move, alpha, beta, depth - 1);
                minValue = Math.min(minValue, value);
                beta = Math.min(beta, value);
                if (beta <= alpha) {
                    break;
                }
            }
            return minValue;
        }
    }
}
