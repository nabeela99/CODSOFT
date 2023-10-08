package com.codsoft.util;

import com.codsoft.model.Student;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DataStorageUtil {

    private static final String DATA_FILE_NAME = "student-management-system/src/main/resources/student_data";
    public List<Student> dataList = new LinkedList<>();

    public DataStorageUtil() {
        loadData();
    }


    public boolean saveData(Student student) {
        if (!this.dataList.contains(student)) {
            return this.dataList.add(student);
        }
        return false;
    }

    public void deleteData(int studentId) {
        Student student = new Student(studentId);
        if (this.dataList.contains(student)) {
            this.dataList.remove(student);
        }
    }


    public boolean persist()  {
        try(FileOutputStream fos = new FileOutputStream(DATA_FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(dataList);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public Optional<Student> getStudentById(int id) {
        Student studentToSearch = new Student(id);
        if (dataList.contains(studentToSearch)) {
            return dataList.stream().filter(student -> student.equals(studentToSearch)).findFirst();
        }
        return Optional.empty();
    }

    public Optional<Student> getStudentByRollNo(String rollNo) {
        Student studentToSearch = new Student(rollNo);
        if (dataList.contains(studentToSearch)) {
            return dataList.stream().filter(student -> student.equals(studentToSearch)).findFirst();
        }
        return Optional.empty();
    }
    public void loadData() {
        try(FileInputStream fos = new FileInputStream(DATA_FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fos)) {
            List<Student> students = (List<Student>) ois.readObject();
            this.dataList.addAll(students);
        } catch (IOException | ClassNotFoundException ignored) {
        }
    }
}
