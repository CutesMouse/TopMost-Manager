/*
 * Created by JFormDesigner on Mon Sep 07 21:44:26 CST 2020
 */

package com.cutesmouse.tmm;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author CutesMouse
 */
public class Controller extends JFrame {
    public Controller() {
        initComponents();
    }

    private void topMost(ActionEvent e) {
        Object select = Windows.getSelectedValue();
        if (select == null) return;
        ListData<WinDef.HWND> data = (ListData<WinDef.HWND>) select;
        User32.INSTANCE.SetWindowPos(data.getItem(),new WinDef.HWND(Pointer.createConstant(-1)),0,0,0,0, WinUser.SWP_NOMOVE | WinUser.SWP_NOSIZE);
    }

    public JList getWindowsList() {
        return Windows;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        Windows = new JList();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("TopMost \u8996\u7a97\u7f6e\u9802\u795e\u5668");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {

            //---- Windows ----
            Windows.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 18));
            scrollPane1.setViewportView(Windows);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(15, 20, 555, 400);

        //---- button1 ----
        button1.setText("\u7f6e\u9802");
        button1.addActionListener(e -> topMost(e));
        contentPane.add(button1);
        button1.setBounds(585, 20, 160, 45);

        //---- button2 ----
        button2.setText("\u53d6\u6d88");
        contentPane.add(button2);
        button2.setBounds(585, 75, 160, 45);

        //---- button3 ----
        button3.setText("\u66f4\u65b0");
        contentPane.add(button3);
        button3.setBounds(585, 125, 160, 45);

        contentPane.setPreferredSize(new Dimension(765, 475));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JList Windows;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
