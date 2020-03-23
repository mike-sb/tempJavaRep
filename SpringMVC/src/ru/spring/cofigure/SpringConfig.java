package ru.spring.cofigure;

import org.springframework.context.annotation.Bean;
import ru.spring.service.TestBean;

public class SpringConfig {
    @Bean
    public TestBean getTestBean()
    {
        return new TestBean("hello!");
    }
}
