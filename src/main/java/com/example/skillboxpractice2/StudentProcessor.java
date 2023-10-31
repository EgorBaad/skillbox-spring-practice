package com.example.skillboxpractice2;

import com.example.skillboxpractice2.dto.Student;
import com.example.skillboxpractice2.event.DeleteStudentEvent;
import com.example.skillboxpractice2.event.NewStudentEvent;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ShellComponent
@RequiredArgsConstructor
public class StudentProcessor {
    private final StudentsLoader studentsLoader;
    private final ApplicationEventPublisher publisher;
    private List<Student> studentList = new ArrayList<>();

//    не работает, т.к. Spring Shell никогда не доходит до события ApplicationReady
//    @EventListener(ApplicationReadyEvent.class)
//    @ConditionalOnExpression("${app.students.init-default:true} and " +
//            "!'${app.students.default-list}'.isEmpty()")
    @PostConstruct
    public void init() {
        studentList = studentsLoader.init();
    }

    @ShellMethod(key="add")
    public String addStudent(String firstName, String lastName, int age) {
        UUID id = UUID.randomUUID();
        Student newStudent = new Student(id, firstName, lastName, age);
        studentList.add(newStudent);
        publisher.publishEvent(new NewStudentEvent(this, newStudent));
        return "Successfully added";
    }

    @ShellMethod(key="show")
    public String showStudents() {
        return studentList.toString();
    }

    @ShellMethod(key="delete")
    public String deleteStudent(String id) {
        if (studentList.removeIf(s -> s.getId().toString().equals(id))) {
            publisher.publishEvent(new DeleteStudentEvent(this, id));
            return "Successfully removed";
        } else {
            return "No such student found";
        }
    }

    @ShellMethod(key="cl")
    public String clearStudentsList() {
        studentList.clear();
        return "The list of students was cleared";
    }
}
