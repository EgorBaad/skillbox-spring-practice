package com.example.skillboxpractice2.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentEventListener {

    @EventListener
    public void onNewStudent(NewStudentEvent newStudentEvent) {
        System.out.println("New student added: " + newStudentEvent.getStudent());
    }

    @EventListener
    public void onDeleteStudent(DeleteStudentEvent deleteStudentEvent) {
        System.out.println("Student deleted: " + deleteStudentEvent.getId());
    }
}
