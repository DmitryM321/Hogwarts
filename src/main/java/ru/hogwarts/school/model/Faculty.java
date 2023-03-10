package ru.hogwarts.school.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long facultyId;
        private String name;
        String color;
        @OneToMany(mappedBy = "faculty")
        private List<Student> students;

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long id_faculty) {
        this.facultyId = id_faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Faculty() {
    }
    public Faculty(Long id, String name, String color) {
        this.facultyId = facultyId;
        this.name = name;
        this.color = color;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(facultyId, faculty.facultyId) && Objects.equals(name, faculty.name) && Objects.equals(color, faculty.color);
    }
    @Override
    public int hashCode() {
        return Objects.hash(facultyId, name, color);
    }
    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + facultyId +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
