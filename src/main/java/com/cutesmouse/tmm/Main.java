package com.cutesmouse.tmm;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Controller con = new Controller();
        User32 api = User32.INSTANCE;
        ArrayList<ListData<WinDef.HWND>> datas = new ArrayList<>();
        api.EnumWindows((hwnd,pointer) -> {
            char WinName[];
            int nameLength;
            nameLength = api.GetWindowTextLength(hwnd);
            WinName = new char[nameLength+1];
            api.GetWindowText(hwnd,WinName,nameLength+1);
            String Name = new String(WinName);
            Name = Name.substring(0,Name.length()-1);
            if (Name.isEmpty()) return true;
            datas.add(new ListData<>(hwnd,Name));
            return true;
        },null);
        con.getWindowsList().setListData(datas.toArray());
        con.setVisible(true);
    }
}
