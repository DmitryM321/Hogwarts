package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findAllByAge(Integer age);
    Collection<Student> findByAgeBetween(Integer min, Integer max);


//    @Query(value = "SELECT COUNT(*) AS count FROM student", nativeQuery = true)
//     Long getCountStudent();
    @Query(value = "SELECT COUNT(student) AS count FROM student AS student", nativeQuery = true)
    Long getCountStudent();
    @Query(value = "SELECT AVG(student.age) FROM student AS student", nativeQuery = true)
    Long getAverageAgeStudents();
    @Query(value = "SELECT * FROM student ORDER BY age ASC LIMIT 5", nativeQuery = true)
    Collection<Student> getYoungestStudents();
}
