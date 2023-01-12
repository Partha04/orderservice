package com.pdn.orderservice.testutills;


import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class PostgresTestContainer {
    static final PostgreSQLContainer postgreSQLContainer;

    static {
        postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres:14.1-alpine")
                .withDatabaseName("test")
                .withUsername("postgres")
                .withPassword("mysecretpassword")
                .withExposedPorts(5432)
                .withReuse(true);

        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void datasourceConfig(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }
}