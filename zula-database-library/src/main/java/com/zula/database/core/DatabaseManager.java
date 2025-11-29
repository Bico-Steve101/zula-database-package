package com.zula.database.core;

import com.zula.database.config.DatabaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public class DatabaseManager {

    private final DatabaseProperties properties;

    @Value("${spring.application.name:unknown-service}")
    private String serviceName;

    @Autowired
    public DatabaseManager(DatabaseProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void init() {
        System.out.println("Zula Database Manager initialized");
        System.out.println("Auto-create schema: " + properties.isAutoCreateSchema());
        System.out.println("Service schema: " + generateSchemaName());
    }

    public String generateSchemaName() {
        String prefix = properties.getSchemaPrefix();
        return (prefix.isEmpty() ? "" : prefix + "_") + serviceName.toLowerCase();
    }

    public String generateTableName(String tableName) {
        return generateSchemaName() + "." + tableName.toLowerCase();
    }
}