package com.example.skillboxpractice2.event;

import com.example.skillboxpractice2.dto.Student;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DeleteStudentEvent extends ApplicationEvent {
    private String id;
    public DeleteStudentEvent(Object source, String id) {
        super(source);
        this.id = id;
    }
}
