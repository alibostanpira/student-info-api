package org.abpira.studentapi.repository;

import org.abpira.studentapi.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    public void setUp() {
        // Clear the repository to ensure a clean state for each test
        studentRepository.deleteAll();

        student = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@doe.com")
                .age(12)
                .gradeLevel("Third grade")
                .createdAt(LocalDateTime.now())
                .createdBy("Anonymous")
                .build();
    }

    @Test
    public void studentRepository_SaveStudent_ReturnSavedStudent() {

        // Act
        Student savedStudent = studentRepository.save(student);

        // Assert - re-fetch the student from the repository to verify actual DB persistence
        Optional<Student> retrievedStudent = studentRepository.findById(savedStudent.getStudentId());
        Assertions.assertThat(retrievedStudent.isPresent()).isTrue();
        Student actualStudent = retrievedStudent.get();

        Assertions.assertThat(actualStudent).isNotNull();
        Assertions.assertThat(actualStudent.getFirstName()).isEqualTo("John");
        Assertions.assertThat(actualStudent.getLastName()).isEqualTo("Doe");
        Assertions.assertThat(actualStudent.getEmail()).isEqualTo("john@doe.com");
        Assertions.assertThat(actualStudent.getAge()).isEqualTo(12);
        Assertions.assertThat(actualStudent.getGradeLevel()).isEqualTo("Third grade");
        Assertions.assertThat(actualStudent.getCreatedAt()).isNotNull();
        Assertions.assertThat(actualStudent.getCreatedBy()).isEqualTo("Anonymous");
    }

    @Test
    public void studentRepository_FindByEmail_ReturnStudent() {

        // Arrange
        studentRepository.save(student);

        // Act
        Optional<Student> retrievedStudent = studentRepository.findByEmail("john@doe.com");

        // Assert
        Assertions.assertThat(retrievedStudent.isPresent()).isTrue();
        Student actualStudent = retrievedStudent.get();

        Assertions.assertThat(actualStudent.getEmail()).isEqualTo("john@doe.com");
        Assertions.assertThat(actualStudent.getAge()).isEqualTo(12);
        Assertions.assertThat(actualStudent.getGradeLevel()).isEqualTo("Third grade");
        Assertions.assertThat(actualStudent.getFirstName()).isEqualTo("John");
        Assertions.assertThat(actualStudent.getLastName()).isEqualTo("Doe");
        Assertions.assertThat(actualStudent.getCreatedAt()).isNotNull();
        Assertions.assertThat(actualStudent.getCreatedBy()).isEqualTo("Anonymous");
    }

    @Test
    public void studentRepository_FindByNonExistingEmail_ReturnEmpty() {
        // Act
        Optional<Student> retrievedStudent = studentRepository.findByEmail("nonexistent@doe.com");

        // Assert
        Assertions.assertThat(retrievedStudent.isPresent()).isFalse();
    }

    @Test
    public void studentRepository_DeleteStudent_RemovesStudent() {
        // Arrange
        Student savedStudent = studentRepository.save(student);

        // Act
        studentRepository.deleteById(savedStudent.getStudentId());

        // Assert
        Optional<Student> deletedStudent = studentRepository.findById(savedStudent.getStudentId());
        Assertions.assertThat(deletedStudent.isEmpty()).isTrue();
    }

    @Test
    public void studentRepository_FindAll_ReturnsMultipleStudents() {
        // Arrange
        Student student2 = Student.builder()
                .firstName("Jane")
                .lastName("Smith")
                .email("jane@smith.com")
                .age(14)
                .gradeLevel("Fourth grade")
                .createdAt(LocalDateTime.now())
                .createdBy("Admin")
                .build();

        studentRepository.save(student);
        studentRepository.save(student2);

        // Act
        Iterable<Student> students = studentRepository.findAll();

        // Assert
        Assertions.assertThat(students).hasSize(2);
    }
}
