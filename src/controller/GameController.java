package controller;

import model.ThuatToanCatTiaAB;
import model.ThuatToanMinimax;
import model.Node;
import view.Main;

public class GameController {
    Main mainCoVua;
    private int chieuSau;
    private ThuatToanCatTiaAB alphaBetaPruning;
    private ThuatToanMinimax minimax;

    public GameController(Main mainCoVua) {
        this.mainCoVua = mainCoVua;
        this.alphaBetaPruning = new ThuatToanCatTiaAB();
        this.minimax = new ThuatToanMinimax();
    }

    public int getChieuSau() {
        return chieuSau;
    }

    public void setChieuSau(int chieuSau) {
        this.chieuSau = chieuSau;
    }

    public Node nuocDiTotNhatMinimax(int player, Node viTri, int depth) {
        return minimax.nuocDiTotNhat(player, viTri, depth);
    }

    public Node nuocDiTotNhatAlphaBeta(int player, Node viTri, int alpha, int beta, int depth) {
        return alphaBetaPruning.nuocDiTotNhat(player, viTri, alpha, beta, depth);
    }
}
