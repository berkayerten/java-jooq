package com.techthrivecatalyst.javajooq;

import com.techthrivecatalyst.javajooq.generated.tables.pojos.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;


class UserRepositoryIntegrationTest extends BaseIntegrationTest {

    @Test
    void connectionEstablished() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    void shouldInsertAndFetchUser() {
        userRepository.insert("John Doe", "j.doe@example.com");

        var users = userRepository.findAll();
        assertThat(users).hasSize(1);
        Employee user = users.getFirst();
        assertThat(user.getName()).isEqualTo("John Doe");
        assertThat(user.getEmail()).isEqualTo("j.doe@example.com");
    }
}
