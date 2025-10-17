package pe.pedroavila.adapter.repository;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.persistence.Table;

@Component
public class EntityDatabaseHelper {

    private final JdbcTemplate jdbcTemplate;

    public EntityDatabaseHelper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int generateCode(Class<?> entityClass) {
        String tableName = getTableName(entityClass);
        String sql = String.format("SELECT COALESCE(MAX(code), 0) + 1 FROM %s", tableName);
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
        return result != null ? result : 1;
    }

    public boolean existsByName(Class<?> entityClass, String name) {
        String tableName = getTableName(entityClass);
        String sql;
        Integer count;

        sql = String.format("SELECT COUNT(*) FROM %s WHERE name = ?", tableName);
        count = jdbcTemplate.queryForObject(sql, Integer.class, name);

        return count != null && count > 0;
    }

    public int update(Class<?> entityClass, Long id, Map<String, Object> fieldsToUpdate) {

        String tableName = getTableName(entityClass);

        // Generamos dinámicamente la parte SET del SQL
        String setClause = fieldsToUpdate.keySet().stream()
                .map(key -> key + " = ?")
                .collect(Collectors.joining(", "));

        String sql = String.format("UPDATE %s SET %s WHERE id = ?", tableName, setClause);

        // Construimos los parámetros (valores de los campos + id al final)
        Object[] params = Stream.concat(fieldsToUpdate.values().stream(), Stream.of(id))
                .toArray();

        // Ejecutamos el UPDATE
        return jdbcTemplate.update(sql, params);
    }

    private String getTableName(Class<?> entityClass) {
        Table tableAnnotation = entityClass.getAnnotation(Table.class);
        if (tableAnnotation != null && !tableAnnotation.name().isEmpty()) {
            return tableAnnotation.name();
        }

        String simpleName = entityClass.getSimpleName().toLowerCase();
        return simpleName.endsWith("s") ? simpleName : simpleName + "s";
    }
}
