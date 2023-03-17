package ru.hogwarts.school.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;
@Data
public class FacultyDTO {

    private Long facultyId;
    private String name;
    private String color;
    @JsonIgnore
    private List<StudentDTO> students;
    public static FacultyDTO fromFaculty(Faculty faculty) {
    FacultyDTO dto = new FacultyDTO();
    dto.setFacultyId(faculty.getFacultyId());
    dto.setName(faculty.getName());
    dto.setColor(faculty.getColor());
    return dto;
}
    public Faculty toFaculty() {
        Faculty faculty = new Faculty();
        faculty.setFacultyId(this.getFacultyId());
        faculty.setName(this.getName());
        faculty.setColor(this.getColor());
        return faculty;
    }

}
