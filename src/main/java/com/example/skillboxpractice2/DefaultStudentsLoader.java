package com.example.skillboxpractice2;

import com.example.skillboxpractice2.dto.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnExpression("!${app.students.init-default} or " +
        "'${app.students.default-list}'.isEmpty()")
//Не очень понятно, почему такой вариант не работает. В этом случае бин инициализируется вместе с InitStudentsLoader,
//несмотря на Order.
//@Order(Ordered.LOWEST_PRECEDENCE)
//@ConditionalOnMissingBean(type="StudentsLoader")
public class DefaultStudentsLoader implements StudentsLoader{

    @Override
    public List<Student> init() {
        return new ArrayList<>();
    }
}
