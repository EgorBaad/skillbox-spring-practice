package com.example.skillboxpractice2;

import com.example.skillboxpractice2.dto.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@ConditionalOnExpression("${app.students.init-default:true} and " +
//        "!'${app.students.default-list}'.isEmpty()")
@Component
public class InitStudentsLoader implements StudentsLoader{
    @Value("${app.students.default-list}")
    private String filepath;
    @Override
    public List<Student> init() {
        List<Student> studentList = new ArrayList<>();
        InputStream inputStream = getClass().getResourceAsStream(filepath);

        try {
            if (inputStream == null) {
                throw new NullPointerException("Could not find the file");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = br.readLine()) != null) {
                String[] studentFields = line.split(" ");
                if (studentFields.length == 3) {
                    studentList.add(new Student(
                            UUID.randomUUID(),
                            studentFields[0].trim(),
                            studentFields[1].trim(),
                            Integer.parseInt(studentFields[2].trim())
                    ));
                } else {
                    System.out.println("Invalid line in file: " + line + "\n" +
                            "Make sure it complies with pattern \"NAME LAST_NAME AGE\"");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
        return studentList;
    }
}
