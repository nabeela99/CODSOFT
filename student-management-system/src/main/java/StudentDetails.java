public class StudentDetails {
    private int id;
    private String name;
    private String rollNo;
    private int grade;

    public StudentDetails(int id, String name, String rollNo,int grade) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

}
