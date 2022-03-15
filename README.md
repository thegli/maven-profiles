# Maven Profiles

Demonstrate the configuration and use of a **Maven profile**.

The Java code in this project is simply food for the compiler, and contains nothing valuable.

## Standard build

`mvn clean package` compiles the sources, executes the unit tests, and creates the jar file.

## Extended build

`mvn clean package jar:test-jar` additionally creates a second jar with the test classes.

```shell
$ mvn clean package jar:test-jar
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------------< ch.thegli.example.maven:maven-profiles >---------------
[INFO] Building maven-profiles 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ maven-profiles ---
[INFO] Deleting /home/thegli/maven-profiles/target
[INFO] 
[INFO] --- maven-resources-plugin:3.2.0:resources (default-resources) @ maven-profiles ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] skip non existing resourceDirectory /home/thegli/maven-profiles/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.10.1:compile (default-compile) @ maven-profiles ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /home/thegli/maven-profiles/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:3.2.0:testResources (default-testResources) @ maven-profiles ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] skip non existing resourceDirectory /home/thegli/maven-profiles/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.10.1:testCompile (default-testCompile) @ maven-profiles ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /home/thegli/maven-profiles/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:3.0.0-M5:test (default-test) @ maven-profiles ---
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running ch.thegli.example.maven.RandomValuesTest
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.123 s - in ch.thegli.example.maven.RandomValuesTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- maven-jar-plugin:3.2.2:jar (default-jar) @ maven-profiles ---
[INFO] Building jar: /home/thegli/maven-profiles/target/maven-profiles-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- maven-jar-plugin:3.2.2:test-jar (default-cli) @ maven-profiles ---
[INFO] Building jar: /home/thegli/maven-profiles/target/maven-profiles-0.0.1-SNAPSHOT-tests.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.171 s
[INFO] Finished at: 2022-03-15T21:03:24+01:00
[INFO] ------------------------------------------------------------------------
```

## Use profile for extended build

We configure a Maven profile **withtestjar** that:

* is disabled by default
* runs the default goals *install* and *package* when active
* declares the *maven-jar-plugin* with the execution of the goal *test-jar*

```xml
<profiles>
    <profile>
        <id>withtestjar</id>
        <activation>
            <activeByDefault>false</activeByDefault>
        </activation>
        <build>
            <defaultGoal>clean package</defaultGoal>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-jar-plugin</artifactId>
                    <executions>
                        <execution>
                            <goals>
                                <goal>test-jar</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>
            </plugins>
        </build>
    </profile>
</profiles>
```

`mvn -P withtestjar` does the same as *mvn clean package jar:test-jar*.

```shell
$ mvn -P withtestjar
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------------< ch.thegli.example.maven:maven-profiles >---------------
[INFO] Building maven-profiles 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ maven-profiles ---
[INFO] Deleting /home/thegli/maven-profiles/target
[INFO] 
[INFO] --- maven-resources-plugin:3.2.0:resources (default-resources) @ maven-profiles ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] skip non existing resourceDirectory /home/thegli/maven-profiles/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.10.1:compile (default-compile) @ maven-profiles ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /home/thegli/maven-profiles/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:3.2.0:testResources (default-testResources) @ maven-profiles ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] skip non existing resourceDirectory /home/thegli/maven-profiles/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.10.1:testCompile (default-testCompile) @ maven-profiles ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /home/thegli/maven-profiles/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:3.0.0-M5:test (default-test) @ maven-profiles ---
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running ch.thegli.example.maven.RandomValuesTest
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.104 s - in ch.thegli.example.maven.RandomValuesTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- maven-jar-plugin:3.2.2:jar (default-jar) @ maven-profiles ---
[INFO] Building jar: /home/thegli/maven-profiles/target/maven-profiles-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- maven-jar-plugin:3.2.2:test-jar (default) @ maven-profiles ---
[INFO] Building jar: /home/thegli/maven-profiles/target/maven-profiles-0.0.1-SNAPSHOT-tests.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.064 s
[INFO] Finished at: 2022-03-15T21:01:36+01:00
[INFO] ------------------------------------------------------------------------
```
