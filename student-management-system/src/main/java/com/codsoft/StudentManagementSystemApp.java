package com.codsoft;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import static com.codsoft.ui.StudentManagementSystemUI.createAndShowGUI;

public class StudentManagementSystemApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            createAndShowGUI();
        });
    }
}
