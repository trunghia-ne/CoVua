package controller;

import java.util.ArrayList;
import java.util.List;

import model.Data;
import model.DiChuyen;
import model.QuanCo;
import model.Node;

public class MoRongNuocDi {
    Node vitTri;
    int nguoiChoi;
    List<Node> danhSachVitri = new ArrayList<Node>();
    GameCoVua gs;

    public MoRongNuocDi(Node viTri, int nguoiChoi){
        this.vitTri = viTri;
        this.nguoiChoi = nguoiChoi;
        this.gs = new GameCoVua(viTri);
    }
    public Node[] getDanhSachMoRong(){
        return danhSachVitri.toArray(new Node[danhSachVitri.size()]);
    }
    public void moRongNuocDiMoi(){
        if(nguoiChoi == Data.NGUOI){
            for(int i=1; i<vitTri.cacLoaiQuanCoNguoi.length; i++){
                QuanCo quanCo = vitTri.cacLoaiQuanCoNguoi[i];
                if(quanCo == null) continue;
                switch(quanCo.diem){
                    case QuanCo.TOT:
                        nuocDiMoiQuanTot_nguoi(quanCo);
                        break;
                    case QuanCo.MA:
                        nuocDiMoiQuanMa_Nguoi(quanCo);
                        break;
                    case QuanCo.VUA:
                        nuocDiMoiQuanVua_Nguoi(quanCo);
                        break;
                    case QuanCo.TUONG:
                        nuocDiMoiQuanTuong_Nguoi(quanCo);
                        break;
                    case QuanCo.XE:
                        nuocDiMoiQuanXe_Nguoi(quanCo);
                        break;
                    case QuanCo.HAU:
                        nuocDiMoiQuanHau_Nguoi(quanCo);
                }
            }
        }else{
            for(int i=1; i<vitTri.cacLoaiQuanCoMay.length; i++){
                QuanCo quanCo = vitTri.cacLoaiQuanCoMay[i];
                if(quanCo == null) continue;
                switch(quanCo.diem){
                    case QuanCo.TOT:
                        nuocDiMoiQuanTot_May(quanCo);
                        break;
                    case QuanCo.MA:
                        nuocDiMoiQuanMa_May(quanCo);
                        break;
                    case QuanCo.VUA:
                        nuocDiMoiQuanVua_May(quanCo);
                        break;
                    case QuanCo.TUONG:
                        nuocDiMoiQuanTuong_May(quanCo);
                        break;
                    case QuanCo.XE:
                        nuocDiMoiQuanXe_May(quanCo);
                        break;
                    case QuanCo.HAU:
                        nuocDiMoiQuanHau_May(quanCo);
                }
            }
        }
    }
    public void nuocDiMoiQuanTot_nguoi(QuanCo tot){
        int viTri = tot.viTriQuanCo;
        // thử đi lên 1 ô
        int vitriMoi = vitTri.banCo[viTri-10];
        if(vitriMoi != Data.KHUNG){
            //chưa đụng tường
            if(vitriMoi == Data.OTRONG && gs.vuaAnToan(Data.NGUOI,viTri,viTri-10)) {
                // đi lên nếu trống và an toàn thì có thể lên 1 ô
                danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,viTri-10)));

            }
        }

        if(viTri > 80 && vitriMoi == Data.OTRONG &&
                vitTri.banCo[viTri-20] == Data.OTRONG && gs.vuaAnToan(Data.NGUOI,viTri,viTri-20)) {
            //có thể lên 2 ô
            danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,viTri-20)));
        }

        int viTriCheoPhai = vitTri.banCo[viTri-9];
        if(viTriCheoPhai != Data.KHUNG) {
            if(viTriCheoPhai<0 && gs.vuaAnToan(Data.NGUOI,viTri,viTri-9))
                //có thể đi chéo phải
                danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,viTri-9)));
        }
        int viTriCheoTrai = vitTri.banCo[viTri-11];
        if(viTriCheoTrai != Data.KHUNG) {
            if(viTriCheoTrai<0 && gs.vuaAnToan(Data.NGUOI,viTri,viTri-11))
                //có thể di chéo trái
                danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,viTri-11)));
        }
    }
    public void nuocDiMoiQuanTot_May(QuanCo tot){
        int viTri = tot.viTriQuanCo;
        int viTriMoi = vitTri.banCo[viTri+10];
        if(viTriMoi != Data.KHUNG){
            if(viTriMoi == Data.OTRONG && gs.vuaAnToan(Data.MAY,viTri,viTri+10)){
//            	có thể lên 1 ô
                danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,viTri+10)));
            }
        }

        if(viTri < 39 && viTriMoi == Data.OTRONG &&
                vitTri.banCo[viTri+20] == Data.OTRONG && gs.vuaAnToan(Data.MAY,viTri,viTri+20)) {
            //có thể lên 2 ô
            danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,viTri+20)));
        }

        int viTriCheoPhai = vitTri.banCo[viTri+11];
        if(viTriCheoPhai != Data.KHUNG) {
            if(viTriCheoPhai>0 && viTriCheoPhai != Data.OTRONG &&
                    gs.vuaAnToan(Data.MAY,viTri,viTri+11))
                //có thể đi chéo phải
                danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,viTri+11)));
        }
        int viTriCheoTrai = vitTri.banCo[viTri+9];
        if(viTriCheoTrai != Data.KHUNG) {
            if(viTriCheoTrai>0 && viTriCheoTrai != Data.OTRONG &&
                    gs.vuaAnToan(Data.MAY,viTri,viTri+9))
                //có thể di chéo trái
                danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,viTri+9)));
        }
    }
    public void nuocDiMoiQuanMa_Nguoi(QuanCo ma){
        int viTri = ma.viTriQuanCo;
        int[] huongDiMoi = {viTri-21,viTri+21,viTri+19,viTri-19,
                viTri-12,viTri+12,viTri-8,viTri+8};
        for(int i=0; i<huongDiMoi.length; i++){
            int viTriMoi = vitTri.banCo[huongDiMoi[i]];
            if(viTriMoi != Data.KHUNG && (viTriMoi == Data.OTRONG || viTriMoi<0)
                    && gs.vuaAnToan(Data.NGUOI,viTri,huongDiMoi[i]))
                danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,huongDiMoi[i])));
        }
    }
    public void nuocDiMoiQuanMa_May(QuanCo ma){
        int viTri = ma.viTriQuanCo;
        int[] huongDiMoi = {viTri-21,viTri+21,viTri+19,viTri-19,
                viTri-12,viTri+12,viTri-8,viTri+8};
        for(int i=0; i<huongDiMoi.length; i++){
            int viTriMoi = vitTri.banCo[huongDiMoi[i]];
            if(viTriMoi != Data.KHUNG && (viTriMoi == Data.OTRONG || viTriMoi>0) &&
                    gs.vuaAnToan(Data.MAY,viTri,huongDiMoi[i])){
                danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,huongDiMoi[i])));
            }
        }
    }
    public void nuocDiMoiQuanVua_Nguoi(QuanCo vua){
        int viTri = vua.viTriQuanCo;
        int[] huongDiMoi = {viTri+1,viTri-1,viTri+10,viTri-10,
                viTri+11,viTri-11,viTri+9,viTri-9};
        for(int i=0; i<huongDiMoi.length; i++){
            int viTriMoi = vitTri.banCo[huongDiMoi[i]];
            if(viTriMoi != Data.KHUNG && (viTriMoi == Data.OTRONG || viTriMoi<0)
                    && gs.vuaAnToan(Data.NGUOI,viTri,huongDiMoi[i])){
                danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,huongDiMoi[i])));
            }
        }
    }
    public void nuocDiMoiQuanVua_May(QuanCo vua){
        int viTri = vua.viTriQuanCo;
        int[] huongDiMoi = {viTri+1,viTri-1,viTri+10,viTri-10,
                viTri+11,viTri-11,viTri+9,viTri-9};
        for(int i=0; i<huongDiMoi.length; i++){
            int viTriMoi = vitTri.banCo[huongDiMoi[i]];
            if(viTriMoi != Data.KHUNG && (viTriMoi == Data.OTRONG || viTriMoi>0) &&
                    gs.vuaAnToan(Data.MAY,viTri,huongDiMoi[i])){
                danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,huongDiMoi[i])));
            }
        }
    }
    public void nuocDiMoiQuanTuong_Nguoi(QuanCo tuong){
        int viTri = tuong.viTriQuanCo;
        int[] huongDi = {11,-11,9,-9};
        for (int i = 0; i < huongDi.length; i++) {
            int huongDiMoi = viTri + huongDi[i];
            while (true) {
                int viTriMoi = vitTri.banCo[huongDiMoi];
                if (viTriMoi == Data.KHUNG) {
                    break;
                }
                boolean vuaAnToan = gs.vuaAnToan(Data.NGUOI,viTri,huongDiMoi);
                if (viTriMoi == Data.OTRONG || viTriMoi < 0){
                    if(vuaAnToan){
                        danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,huongDiMoi)));
                        if (viTriMoi != Data.OTRONG || !vuaAnToan) {
                            break;
                        }
                    }else if(viTriMoi != Data.OTRONG) break;
                } else if(viTriMoi>0 && viTriMoi != Data.OTRONG){
                    break;
                }
                huongDiMoi += huongDi[i];
            }
        }
    }
    public void nuocDiMoiQuanTuong_May(QuanCo tuong){
        int viTri = tuong.viTriQuanCo;
        int[] huongDi = {11,-11,9,-9};
        for (int i = 0; i < huongDi.length; i++) {
            int huongDiMoi = viTri + huongDi[i];
            while (true) {
                int viTriMoi = vitTri.banCo[huongDiMoi];
                if (viTriMoi == Data.KHUNG) {
                    break;
                }
                boolean vuaAnToan = gs.vuaAnToan(Data.MAY,viTri,huongDiMoi);
                if (viTriMoi == Data.OTRONG || viTriMoi > 0) {
                    if(vuaAnToan){
                        danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,huongDiMoi)));
                        if (viTriMoi != Data.OTRONG || !vuaAnToan) {
                            break;
                        }
                    }else if(viTriMoi != Data.OTRONG) break;
                } else if(viTriMoi<0){
                    break;
                }
                huongDiMoi += huongDi[i];
            }
        }
    }
    public void nuocDiMoiQuanXe_Nguoi(QuanCo xe){
        int viTri = xe.viTriQuanCo;
        int[] huongDi = {1,-1,10,-10};
        for (int i = 0; i < huongDi.length; i++) {
            int huongDiMoi = viTri + huongDi[i];
            while (true) {
                int viTriMoi = vitTri.banCo[huongDiMoi];
                if (viTriMoi == Data.KHUNG) {
                    break;
                }
                boolean vuaAnToan = gs.vuaAnToan(Data.NGUOI,viTri,huongDiMoi);
                if (viTriMoi == Data.OTRONG || viTriMoi < 0) {
                    if(vuaAnToan){
                        danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,huongDiMoi)));
                        if (viTriMoi != Data.OTRONG) {
                            break;
                        }
                    }else if(viTriMoi != Data.OTRONG) break;
                } else if(viTriMoi>0 && viTriMoi != Data.OTRONG){
                    break;
                }
                huongDiMoi += huongDi[i];
            }
        }
    }
    public void nuocDiMoiQuanXe_May(QuanCo xe){
        int viTri = xe.viTriQuanCo;
        int[] huongDi = {1,-1,10,-10};
        for (int i = 0; i < huongDi.length; i++) {
            int huongDiMoi = viTri + huongDi[i];
            while (true) {
                int viTriMoi = vitTri.banCo[huongDiMoi];
                if (viTriMoi == Data.KHUNG) {
                    break;
                }
                boolean vuaAnToan = gs.vuaAnToan(Data.MAY,viTri,huongDiMoi);
                if (viTriMoi == Data.OTRONG || viTriMoi > 0) {
                    if(vuaAnToan){
                        danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,huongDiMoi)));
                        if (viTriMoi != Data.OTRONG) {
                            break;
                        }
                    }else if(viTriMoi != Data.OTRONG) break;
                } else if(viTriMoi<0){
                    break;
                }
                huongDiMoi += huongDi[i];
            }
        }
    }
    public void nuocDiMoiQuanHau_Nguoi(QuanCo hau){
        int viTri = hau.viTriQuanCo;
        int[] huongDi = {1,-1,10,-10,11,-11,9,-9};
        for (int i = 0; i < huongDi.length; i++) {
            int huongDiMoi = viTri + huongDi[i];
            while (true) {
                int viTriMoi = vitTri.banCo[huongDiMoi];
                if (viTriMoi == Data.KHUNG) {
                    break;
                }
                boolean vuaAnToan = gs.vuaAnToan(Data.NGUOI,viTri,huongDiMoi);
                if (viTriMoi == Data.OTRONG || viTriMoi < 0) {
                    if(vuaAnToan){
                        danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,huongDiMoi)));
                        if (viTriMoi != Data.OTRONG) {
                            break;
                        }
                    }else if(viTriMoi != Data.OTRONG) break;
                } else if(viTriMoi>0 && viTriMoi != Data.OTRONG){
                    break;
                }
                huongDiMoi += huongDi[i];
            }
        }
    }
    public void nuocDiMoiQuanHau_May(QuanCo hau){
        int viTri = hau.viTriQuanCo;
        int[] huongDi = {1,-1,10,-10,11,-11,9,-9};
        for (int i = 0; i < huongDi.length; i++) {
            int huongDiMoi = viTri + huongDi[i];
            while (true) {
                int viTriMoi = vitTri.banCo[huongDiMoi];
                if (viTriMoi == Data.KHUNG) {
                    break;
                }
                boolean vuaAnToan = gs.vuaAnToan(Data.MAY,viTri,huongDiMoi);
                if (viTriMoi == Data.OTRONG || viTriMoi > 0) {
                    if(vuaAnToan){
                        danhSachVitri.add(new Node(vitTri,new DiChuyen(viTri,huongDiMoi)));
                        if (viTriMoi != Data.OTRONG) {
                            break;
                        }
                    } else if (viTriMoi != Data.OTRONG) break;
                } else if(viTriMoi<0){
                    break;
                }
                huongDiMoi += huongDi[i];
            }
        }
    }
}
