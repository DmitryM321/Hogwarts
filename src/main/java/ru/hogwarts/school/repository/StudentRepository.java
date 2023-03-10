package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findAllByAge(Integer age);
    Collection<Student> findByAgeBetween(Integer min, int Integer);


//    Faculty findStudentByFacultyId(Long id);
//    List<Student> findStudentByFacultyFacultyId(Long id);

}
