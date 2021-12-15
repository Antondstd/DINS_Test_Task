package ru.dins.test_task.Configurations;


import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomPhysicalNamingStrategy extends SpringPhysicalNamingStrategy {

    @Value("${table.custom.one}")
    private String customTableOne;

    @Value("${table.custom.two}")
    private String customTableTwo;

    @Override
    public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        switch (identifier.getText()) {
            case "CustomTableOne":
                return new Identifier(customTableOne, identifier.isQuoted());
            case "CustomTableTwo":
                return new Identifier(customTableTwo, identifier.isQuoted());
            default:
                return super.toPhysicalTableName(identifier, jdbcEnv);
        }
    }
}
