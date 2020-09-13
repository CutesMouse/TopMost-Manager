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
import java.util.ArrayList;
import java.util.stream.Collectors;
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

    public void update() {
        Update(null);
    }

    private void Cancel(ActionEvent e) {
        Object select = Windows.getSelectedValue();
        if (select == null) return;
        ListData<WinDef.HWND> data = (ListData<WinDef.HWND>) select;
        User32.INSTANCE.SetWindowPos(data.getItem(),new WinDef.HWND(Pointer.createConstant(-2)),0,0,0,0, WinUser.SWP_NOMOVE | WinUser.SWP_NOSIZE);
    }
    private ArrayList<ListData<WinDef.HWND>> getNowHWNDS() {
        User32 api = User32.INSTANCE;
        ArrayList<ListData<WinDef.HWND>> datas = new ArrayList<>();
        api.EnumWindows((hwnd, pointer) -> {
            char WinName[];
            int nameLength;
            nameLength = api.GetWindowTextLength(hwnd);
            WinName = new char[nameLength + 1];
            api.GetWindowText(hwnd, WinName, nameLength + 1);
            String Name = new String(WinName);
            Name = Name.substring(0, Name.length() - 1);
            if (Name.isEmpty()) return true;
            if (Name.equals("Default IME")) return true;
            if (Name.equals("MSCTFIME UI")) return true;
            datas.add(new ListData<>(hwnd, Name));
            return true;
        }, null);
        return datas;
    }
    private ArrayList<ListData<WinDef.HWND>> last = new ArrayList<>();
    private void Update(ActionEvent e) {
        ArrayList<ListData<WinDef.HWND>> now = getNowHWNDS();
        last = now;
        this.getWindowsList().setListData(now.toArray());
    }

    private void checkAndUpdate(ActionEvent e) {
        ArrayList<ListData<WinDef.HWND>> now = getNowHWNDS();
        getWindowsList().setListData(new ArrayList<>(now.stream().filter(p -> !last.contains(p)).collect(Collectors.toList())).toArray());
        last = now;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        Windows = new JList();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("TopMost \u8996\u7a97\u7f6e\u9802\u795e\u5668");
        setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
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
        button2.addActionListener(e -> Cancel(e));
        contentPane.add(button2);
        button2.setBounds(585, 75, 160, 45);

        //---- button3 ----
        button3.setText("\u66f4\u65b0");
        button3.addActionListener(e -> Update(e));
        contentPane.add(button3);
        button3.setBounds(585, 125, 160, 45);

        //---- button4 ----
        button4.setText("\u66f4\u65b0\u4e26\u7be9\u9078");
        button4.addActionListener(e -> checkAndUpdate(e));
        contentPane.add(button4);
        button4.setBounds(585, 180, 160, 45);

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
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
