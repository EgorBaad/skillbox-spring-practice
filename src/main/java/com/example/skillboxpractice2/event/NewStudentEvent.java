package com.example.skillboxpractice2.event;

import com.example.skillboxpractice2.dto.Student;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class NewStudentEvent extends ApplicationEvent {
    private Student student;

    public NewStudentEvent(Object source, Student student) {
        super(source);
        this.student = student;
    }
}
