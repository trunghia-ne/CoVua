
package view;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import model.Data;
import model.QuanCo;

public class ThangChucChoTOTPanel extends JDialog implements ActionListener {
    int index;
    int location;
    JPanel main_pane;
    Main coVua;

    public ThangChucChoTOTPanel(Main coVua) {
        setTitle("New Piece");
        this.coVua = coVua;
        main_pane = new JPanel(new GridLayout(1, 4, 10, 0));
        ThuVienAnh thuVienAnh = new ThuVienAnh();

        int[] cmdActions = {
                QuanCo.HAU, QuanCo.XE, QuanCo.TUONG, QuanCo.MA
        };
        for (int i = 0; i < cmdActions.length; i++) {
            JButton button = new JButton();
            button.addActionListener(this);
            button.setActionCommand(cmdActions[i] + "");
            main_pane.add(button);
        }
        setContentPane(main_pane);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                resumeGame(QuanCo.HAU);
            }
        });
    }

    public void setIcons(boolean white) {
        Component[] components = main_pane.getComponents();
        ThuVienAnh thuVienAnh = new ThuVienAnh();
        String[] resourceStrings = {"q", "r", "b", "n"};
        for (int i = 0; i < components.length; i++) {
            JButton button = (JButton) components[i];
            button.setIcon(new ImageIcon(
                    thuVienAnh.getResource((white ? "trang" : "den") + resourceStrings[i])));
        }
        pack();
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        int loaiquancochondethangchuc = Integer.parseInt(e.getActionCommand());
        setVisible(false);
        resumeGame(loaiquancochondethangchuc);
    }

    public void resumeGame(int loaiquancochondethangchuc) {
        coVua.viTriTrenMaTrix.cacLoaiQuanCoNguoi[index] = new QuanCo(loaiquancochondethangchuc, location);
        coVua.banCoPanel.repaint();
        coVua.trangThai = Data.MAY_DI;
    }
}
