import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Student {
    private String studentId;
    private String fullName;
    private Date dateOfBirth;
    private String major;
    private float gpa;

    // Phuong thuc nhap thong tin sinh vien
    public void enterStudentInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ID sinh vien: ");
        studentId = scanner.nextLine();
        System.out.print("Nhap ten sinh vien: ");
        fullName = scanner.nextLine();
        System.out.print("Nhap ngay sinh (YYYY-MM-DD): ");
        dateOfBirth = new Date(scanner.nextLine()); // Chuyen doi chuoi thanh Date (can xu ly them neu dung thuc te)
        System.out.print("Nhap nganh hoc: ");
        major = scanner.nextLine();
        System.out.print("Nhap GPA: ");
        gpa = scanner.nextFloat();
        scanner.nextLine(); // Doc bo dong trong
    }

    // Phuong thuc hien thi thong tin sinh vien
    public void displayStudentInfo() {
        System.out.println("ID: " + studentId);
        System.out.println("Ten: " + fullName);
        System.out.println("Ngay sinh: " + dateOfBirth);
        System.out.println("Nganh: " + major);
        System.out.println("GPA: " + gpa);
    }

    public String getStudentId() {
        return studentId;
    }
}

class StudentList {
    private ArrayList<Student> studentList = new ArrayList<>();

    // Nhap danh sach sinh vien
    public void enterStudentList(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap thong tin cho sinh vien thu " + (i + 1));
            Student student = new Student();
            student.enterStudentInfo();
            studentList.add(student);
        }
    }

    // Hien thi tat ca sinh vien
    public void displayAllStudents() {
        for (Student student : studentList) {
            student.displayStudentInfo();
            System.out.println("-------------------");
        }
    }

    // Tim sinh vien theo ID
    public Student findStudentById(String idToFind) {
        for (Student student : studentList) {
            if (student.getStudentId().equals(idToFind)) {
                return student;
            }
        }
        return null;
    }

    // Xoa sinh vien theo ID
    public boolean deleteStudentById(String idToDelete) {
        Student student = findStudentById(idToDelete);
        if (student != null) {
            studentList.remove(student);
            return true;
        }
        return false;
    }

    // Chinh sua sinh vien theo ID
    public boolean editStudentById(String idToEdit) {
        Student student = findStudentById(idToEdit);
        if (student != null) {
            System.out.println("Nhap lai thong tin cho sinh vien co ID " + idToEdit);
            student.enterStudentInfo();
            return true;
        }
        return false;
    }
}

public class Processor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentList studentList = new StudentList();
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Nhap vao thong tin cua n sinh vien");
            System.out.println("2. Hien thi thong tin tat ca sinh vien");
            System.out.println("3. Tim kiem sinh vien bang ID");
            System.out.println("4. Xoa sinh vien bang ID");
            System.out.println("5. Chinh sua sinh vien bang ID");
            System.out.println("0. Thoat");
            System.out.print("Chon mot lua chon: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Doc bo dong trong

            switch (choice) {
                case 1:
                    System.out.print("Nhap so luong sinh vien: ");
                    int n = scanner.nextInt();
                    scanner.nextLine(); // Doc bo dong trong
                    studentList.enterStudentList(n);
                    break;
                case 2:
                    studentList.displayAllStudents();
                    break;
                case 3:
                    System.out.print("Nhap ID sinh vien can tim: ");
                    String idToFind = scanner.nextLine();
                    Student foundStudent = studentList.findStudentById(idToFind);
                    if (foundStudent != null) {
                        foundStudent.displayStudentInfo();
                    } else {
                        System.out.println("Khong tim thay sinh vien");
                    }
                    break;
                case 4:
                    System.out.print("Nhap ID sinh vien can xoa: ");
                    String idToDelete = scanner.nextLine();
                    if (studentList.deleteStudentById(idToDelete)) {
                        System.out.println("Xoa thanh cong");
                    } else {
                        System.out.println("Khong tim thay sinh vien");
                    }
                    break;
                case 5:
                    System.out.print("Nhap ID sinh vien can chinh sua: ");
                    String idToEdit = scanner.nextLine();
                    if (studentList.editStudentById(idToEdit)) {
                        System.out.println("Chinh sua thanh cong");
                    } else {
                        System.out.println("Khong tim thay sinh vien");
                    }
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh");
                    break;
                default:
                    System.out.println("Lua chon khong hop le");
            }
        } while (choice != 0);

        scanner.close();
    }
}
