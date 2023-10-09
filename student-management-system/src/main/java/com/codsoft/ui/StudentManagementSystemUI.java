package com.codsoft.ui;

import com.codsoft.model.Student;
import com.codsoft.model.StudentTableModel;
import com.codsoft.util.DataStorageUtil;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StudentManagementSystemUI extends JPanel
                             implements ActionListener {
    DataStorageUtil storageUtil = new DataStorageUtil();
    static JFrame frame = new JFrame("Student Management System");

    JTable studentList;
    AbstractTableModel tableModel;

    private final JTextField nameField;
    private final JTextField rollNoField;
    private final JTextField gradeField;

    private JTextField filterTextField;
    protected static final String textFieldName = "Name";
    protected static final String textFieldRollNo = "Roll No";
    protected static final String textFieldGrade = "Grade";

    protected JLabel actionStatus;
    protected JButton saveButton;
    protected JButton deleteButton;
    protected JButton editButton;

    private int editId = -1;

    public StudentManagementSystemUI() {
        setLayout(new BorderLayout());

        nameField = new JTextField(10);
        nameField.setActionCommand(textFieldName);
        nameField.addActionListener(this);

        rollNoField = new JTextField(10);
        rollNoField.setActionCommand(textFieldRollNo);
        rollNoField.addActionListener(this);

        gradeField = new JTextField(10);
        gradeField.setActionCommand(textFieldName);
        gradeField.addActionListener(this);

        JLabel nameFieldLabel = new JLabel(textFieldName + ": ");
        nameFieldLabel.setLabelFor(nameField);
        JLabel rollNoFieldLabel = new JLabel(textFieldRollNo + ": ");
        rollNoFieldLabel.setLabelFor(rollNoField);
        JLabel gradeFieldLabel = new JLabel(textFieldGrade + ": ");
        gradeFieldLabel.setLabelFor(gradeField);

        saveButton = new JButton("Save");

        JPanel textControlsPane = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        textControlsPane.setLayout(gridBagLayout);

        JLabel[] labels = {nameFieldLabel, rollNoFieldLabel, gradeFieldLabel};
        JTextField[] textFields = {nameField, rollNoField, gradeField};
        addLabelTextRows(labels, textFields, textControlsPane);

        c.gridwidth = GridBagConstraints.REMAINDER; //last
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;
//        textControlsPane.add(addStudentLabel, c);
        this.saveButton.addActionListener(this);
        textControlsPane.add(saveButton, c);
        textControlsPane.setBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Add/Edit Student"),
                                BorderFactory.createEmptyBorder(5,5,5,5)));

        //Create a text area.
        JScrollPane areaScrollPane = makeInstructionTextComponent();
        areaScrollPane.setPreferredSize(new Dimension(250, 250));
        areaScrollPane.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Instructions"),
                                BorderFactory.createEmptyBorder(5,5,5,5)),
                areaScrollPane.getBorder()));

        //Create a text pane.
//        JComponent listPanel = makeList();
        makeList();
        JScrollPane paneScrollPane = new JScrollPane();
        paneScrollPane.setViewportView(this.studentList);
        paneScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        paneScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneScrollPane.setPreferredSize(new Dimension(600, 400));
        paneScrollPane.setMinimumSize(new Dimension(50, 60));

        this.filterTextField = new JTextField();
        this.filterTextField.setVisible(true);
        JPanel rightPane = new JPanel();
        rightPane.add(paneScrollPane);

        this.editButton = new JButton("Edit");
        this.editButton.addActionListener(this);
        rightPane.add(this.editButton);
        this.deleteButton = new JButton("Delete");
//        deleteButton.setSize(new Dimension(4,5));
        this.deleteButton.addActionListener(this);
        this.deleteButton.setAlignmentY(50.0f);
        rightPane.add(deleteButton);

        rightPane.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Student List"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));


        //Put everything together.
        JPanel leftPane = new JPanel(new BorderLayout());
        leftPane.add(textControlsPane, 
                     BorderLayout.PAGE_START);
        leftPane.add(areaScrollPane,
                     BorderLayout.CENTER);
        actionStatus = new JLabel("No activities.");
        actionStatus.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        JPanel bottomPane = new JPanel(new BorderLayout());
        bottomPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Status"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        bottomPane.add(actionStatus);
        add(leftPane, BorderLayout.LINE_START);
        add(rightPane, BorderLayout.LINE_END);
        add(bottomPane, BorderLayout.SOUTH);
    }

    private static JScrollPane makeInstructionTextComponent() {
        JTextArea textArea = new JTextArea(
                """
                        1. Enter details and click on save button to save the details.
                        2. Select a row and click on edit button to update details and save.\s
                        3. Select one or multiple rows and click on Delete button to remove from database."""
        );
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setEnabled(false);
        return new JScrollPane(textArea);
    }

    private void addLabelTextRows(JLabel[] labels,
                                  JTextField[] textFields,
                                  Container container) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        int numLabels = labels.length;

        for (int i = 0; i < numLabels; i++) {
            c.gridwidth = GridBagConstraints.RELATIVE;
            c.fill = GridBagConstraints.NONE;
            c.weightx = 0.0;
            container.add(labels[i], c);

            c.gridwidth = GridBagConstraints.REMAINDER;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            container.add(textFields[i], c);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.saveButton) {
            String name = this.nameField.getText();
            String rollNo = this.rollNoField.getText();
            String grade = this.gradeField.getText();
            if (editId != -1) {
                storageUtil.getStudentByRollNo(String
                        .valueOf(this.rollNoField.getText())).ifPresentOrElse(student -> {
                            if (editId != student.getId()){
                                JOptionPane.showMessageDialog(frame
                                        , String.format("Roll no already assigned to %s, with student Id %s"
                                                , student.getName(), student.getId()));
                            } else {
                                this.updateStudentAndPersist();
                            }
                }, this::updateStudentAndPersist);
            } else {
                int lastId = 0;
                if (!storageUtil.dataList.isEmpty()) {
                    lastId = storageUtil.dataList.get(storageUtil.dataList.size() - 1).getId();
                }
               if( storageUtil.saveData(new Student( lastId + 1,
                       name, rollNo, grade))) {
                   JOptionPane.showMessageDialog(frame, "Student Details Added.");
                   this.clearInputFields();
               } else {
                   JOptionPane.showMessageDialog(frame, "Failed to Save. Roll No Already assigned to Other Student");
               }
            }
            if(storageUtil.persist()) {
                this.actionStatus.setText("Data saved...");
            }
            this.reloadTable();
        } else if (e.getSource() == this.deleteButton) {
            int[] selectedRows = this.studentList.getSelectedRows();
            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(frame, "Please select at least one row to Delete.");
            } else {
                for (int row : selectedRows) {
                    if (row >= 0) {
                        int id = (int) this.studentList.getValueAt(row, 0);
                        storageUtil.deleteData(id);
                    }
                }
                String row = "Row";
                if (selectedRows.length > 1) {
                    row = "Rows";
                }
                this.actionStatus.setText(String.format("%s %s deleted...", selectedRows.length, row));
                storageUtil.persist();
                reloadTable();
            }
        } else if (e.getSource() == this.editButton) {
            int[] selectedRows = this.studentList.getSelectedRows();
            if (selectedRows.length != 1) {
                JOptionPane.showMessageDialog(frame, "Please select one row to update.");
            } else {
                int row = selectedRows[0];
                if(row > -1) {
                    int id = (int) this.studentList.getValueAt(row, 0);
                    storageUtil.getStudentById(id).ifPresentOrElse(student -> {
                                this.editId = student.getId();
                                this.nameField.setText(student.getName());
                                this.rollNoField.setText(student.getRollNo());
                                this.gradeField.setText(student.getGrade());
                            }
                            , () -> JOptionPane.showMessageDialog(frame
                                    , "Student details not present."));
                }
            }
        }
    }

    private void updateStudentAndPersist() {
        storageUtil.getStudentById(editId).ifPresentOrElse(student -> {
            student.setName(this.nameField.getText());
            student.setRollNo(this.rollNoField.getText());
            student.setGrade(this.gradeField.getText());
            if (storageUtil.persist()) {
                this.actionStatus.setText("Data Updated...");
            }
            this.editId = -1;
            this.clearInputFields();
        }, () -> {
            JOptionPane.showMessageDialog(frame
                    , "Student details not present.");
            this.editId = -1;
            this.clearInputFields();
        });
    }

    protected void makeList() {
        this.tableModel = new StudentTableModel(storageUtil.dataList);
        this.studentList = new JTable(tableModel) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        this.studentList.setEnabled(true);
    }

    protected void reloadTable() {
        var tableModel = (StudentTableModel) this.studentList.getModel();
        tableModel.refreshTable(storageUtil.dataList);
    }

    private void clearInputFields() {
        this.nameField.setText("");
        this.rollNoField.setText("");
        this.gradeField.setText("");
        this.editId = -1;
    }

    public static void createAndShowGUI() {
        //Create and set up the window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new StudentManagementSystemUI(), BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
