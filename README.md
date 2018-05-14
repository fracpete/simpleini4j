# simpleini4j
Wrapper for the [Apache Hierarchical Configuration](https://commons.apache.org/proper/commons-configuration/) 
classes for handling INI files, to make handling easier.

## Usage

### Read/write
Here is how to read and write INI files:

```java
// read
INIFile ini = INIFile.read("/some/where/config.ini");
System.out.println(ini);
```

```java
// write
INIFile ini = ...
ini.write("/some/where/else/config.ini");
```

### Operations

```java
INIFile ini = ...

// setting
ini.set("section1", "intVal", 134);
ini.set("section1", "boolVal", false);
ini.set("section1", "doubleVal", 3.14);
System.out.println(ini);

// retrieving
System.out.println(ini.getInt("section1", "intVal"));
System.out.println(ini.getBoolean("section1", "boolVal"));
System.out.println(ini.getBoolean("section1", "doubleVal"));

// check for presence
if (ini.has("section1", "key1"))
  System.out.println("section1/key1: " + ini.get("section1", "key1"));

// remove value
ini.remove("section1", "doubleVal");
System.out.println(ini);

// remove section
ini.remove("section1");
System.out.println(ini);
```

## Maven
Add the following dependency to your `pom.xml`:
```xml
  <dependency>
    <groupId>com.github.fracpete</groupId>
    <artifactId>simpleini4j</artifactId>
    <version>0.0.1</version>
  </dependency>
```
