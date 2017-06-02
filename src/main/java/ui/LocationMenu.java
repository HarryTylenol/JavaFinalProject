package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by juny on 2017. 6. 2..
 */
public class LocationMenu extends JFrame implements ActionListener {

    String[] locations = {"Seoul", "Busan", "Daegu", "Gwangju", "Incheon", "Daejeon", "Ulsan",
            "Gyeonggi-do", "Gangwon-do", "Chungcheongbuk-do", "Chungcheongnam-do", "Jeollabuk-do",
            "Jeollanam-do", "Gyeongsangbuk-do", "Gyeongsangnam-do", "Jeju-do"};

    public void actionPerformed(ActionEvent e) {
        setDefaultLookAndFeelDecorated(true);
        setTitle("GridLayout Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 8));
        for (String location: locations) {
            add(new JButton(location));
        }
        pack();
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                setVisible(false);
                dispose();
            }
        });

    }
}
