# DbUnit/Guice/MyBatis integration for testing of guicified DAO classes

This library greatly simplifies writing integration tests for projects employing MyBatis ORM framework and Guice dependency injection framework. It also makes DbUnit compatible with JUnit 4.

## Usage

### Add Maven dependency to your pom.xml:

```xml
<dependency>
    <groupId>org.plukh</groupId>
    <artifactId>dbunit-guice-mybatis</artifactId>
    <version>1.0.0</version>
    <scope>test</scope>
</dependency>
```

### Create your `TestMyBatisGuiceModule` (or extend an existing one), along the lines of:
 
```java
  public class MyBatisDAOModule extends XMLMyBatisModule { 
     public MyBatisDAOModule() {
     }
 
     @Override
     protected void initialize() {
         //Set environment
         setEnvironmentId("test");
 
         //Bind DAO implementations
         bind(TopicDAO.class).to(MyBatisTopicDAOImpl.class);
     }
  }
```

### Create your test class:
* Extend [`DbUnitTest`](main/java/org/plukh/dbunitguice/dbunit/DbUnitTest.java) class.
* Add `@GuiceModules(<your module classes here>)` - note that all modules have to have public no-args constructors.
* Optionally, add `@DataSets` annotation (either on the class itself or on individual test methods); set `override` to `true` for method datasets to completely replace class datasets (by default, they're combined together). `DbUnitTest` expects standard [`XmlDataSet`](http://dbunit.sourceforge.net/components.html) dataset files - see [library tests](test/resources/org/plukh/dbunitguice/dbunit) for some examples.
* See [`DbUnitTestTest`](test/java/org/plukh/dbunitguice/dbunit/DbUnitTestTest.java) for examples on how to assert database state after update; [`TestUtils`](/main/java/org/plukh/dbunitguice/util/TestUtils.java) contains helpful methods to assert correctness of ordered and unordered collections returned by your DAO methods.  

## Note

This specific implementation of `DbUnitTest` is targeted towards MySQL. To make it work with other databases, override `getSetUpOperation()` and `getTearDownOperation()`, adding specific operations required by your database (like, you may want to implement `ALTER TABLE ... DISABLE TRIGGER` for disabling foreign key checks in PostgreSQL, as it doesn't support global check on/off flag).