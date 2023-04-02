package ru.hogwarts.school;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.hogwarts.school.config.ConfigDocker;


class HogwartsApplicationTests extends ConfigDocker {
    @Test
    void contextLoads() {
    }

}
