package ru.hogwarts.school.DTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hogwarts.school.model.Student;
@Data
public class StudentDTO {
    private Long studentId;
    private String name;
    private int age;
    private Long facultyId; // FacultyDTO faculty;  //facultyId

     public static StudentDTO fromStudent(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setStudentId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        dto.setFacultyId(student.getFaculty().getFacultyId());
        return dto;
    }
     public Student toStudent() {
        Student student = new Student();
        student.setId(this.getStudentId());
        student.setName(this.getName());
        student.setAge(this.getAge());
 //       student.setFaculty(this.getFaculty().toFaculty());
        return student;
    }
  }
