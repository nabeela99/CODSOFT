package com.codsoft.model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {
    String[] columnNames = {"ID", "Name", "Roll No", "Grade"};
    private Object[][] data;

    public StudentTableModel(List<Student> students) {
        data = getData(students);
    }


    private Object[][] getData(List<Student> students) {
        Object[][] rows = new Object[students.size()][columnNames.length];
        int i = 0;
        for (Student student : students) {
            Object[] row = new Object[columnNames.length];
            row[0] = student.getId();
            row[1] = student.getName();
            row[2] = student.getRollNo();
            row[3] = student.getGrade();
            rows[i++] = row;
        }
        return rows;
    }


    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void refreshTable(List<Student> students) {
        data = getData(students);
        fireTableDataChanged();
    }
}