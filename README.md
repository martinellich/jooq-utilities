# jOOQ Utilities

**jooq-utilities** is a small open source library that enhances [jOOQ](https://www.jooq.org)

## How To

### Dependency 

Add a dependency to the current version:

```xml
<dependency>
    <groupId>io.seventytwo.oss</groupId>
    <artifactId>jooq-utilities</artifactId>
    <version>1.1.0</version>
</dependency>
```

### EqualsAndHashCodeJavaGenerator

By default jOOQ generates equals() and hashCode() on all fields. But sometimes it's useful to have only the primary key fields. 

`EqualsAndHashCodeJavaGenerator` is a `JavaGenerator` that adds `equals()` and `hashCode()` to the generated Records 
based on the PrimaryKey.

Add `EqualsAndHashCodeJavaGenerator` to `jooq-codegen-maven` like this: 

```xml
<plugin>
    <groupId>org.jooq</groupId>
    <artifactId>jooq-codegen-maven</artifactId>
    <executions>
        <execution>
            <goals>
                <goal>generate</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <generator>
            <name>io.seventytwo.jooq.EqualsAndHashCodeJavaGenerator</name>
            ... 
        </generator>
    </configuration>
</plugin>
```

## License
**jooq-utilities** is open and free software under Apache License, Version 2: http://www.apache.org/licenses/LICENSE-2.0.html
