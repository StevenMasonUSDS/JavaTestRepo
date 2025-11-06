# Java Test Repository

A simple Java project with Maven Wrapper for easy development and building.

## Project Structure

```
JavaTestRepo/
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── mvnw                    # Maven wrapper script (executable)
├── mvnw.cmd               # Maven wrapper script for Windows
├── pom.xml                # Maven project configuration
├── src/
│   ├── main/java/         # Source code directory
│   │   └── App.java       # Sample Java class
│   └── test/java/         # Test code directory
│       └── AppTest.java   # Sample test class
└── target/                # Build output directory
```

## Prerequisites

- Java 11 or higher
- No Maven installation required (Maven Wrapper included)

## How to Use

The Maven Wrapper ensures that anyone can build your project without having Maven installed locally - it will automatically download and use the correct Maven version specified in the wrapper properties.

### Build the project:
```bash
./mvnw clean compile
```

### Run tests:
```bash
./mvnw test
```

### Package the application:
```bash
./mvnw package
```

### Run the application:
```bash
./mvnw exec:java -Dexec.mainClass="com.example.App"
```

### Clean build artifacts:
```bash
./mvnw clean
```

### View project information:
```bash
./mvnw --version
```

## Key Features

- **Maven 3.9.9** - Latest stable Maven version
- **Java 11** - Target Java version (configurable in pom.xml)
- **JUnit 5** - Modern testing framework included
- **Standard Maven Structure** - Follows Maven directory conventions
- **Maven Wrapper** - No local Maven installation required

## Development

### Adding Dependencies

Edit the `pom.xml` file to add new dependencies in the `<dependencies>` section:

```xml
<dependency>
    <groupId>group.id</groupId>
    <artifactId>artifact-id</artifactId>
    <version>version</version>
</dependency>
```

### Writing Tests

Add test files in the `src/test/java/` directory. Tests should follow JUnit 5 conventions:

```java
@Test
public void testSomething() {
    // Your test code here
    assertTrue(true);
}
```

### Changing Java Version

To change the target Java version, update the properties in `pom.xml`:

```xml
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```

## Getting Started

1. Clone or download this repository
2. Navigate to the project directory
3. Run `./mvnw clean compile` to build the project
4. Run `./mvnw test` to execute tests
5. Start coding in `src/main/java/`

## License

This project is open source and available under the [MIT License](LICENSE).