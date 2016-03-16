### Meanwhile in Java-land...


Spring eliminated the need for most XML (2008), and eventually all (2012)

```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
    .inMemoryAuthentication()
    .withUser("user").password("password").roles("USER");
  }
}
```


Annotations and improvements made JPA and Hibernate easily readable
```java
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;

    protected Customer() {}
};
```


Single class-style coding lets you write code just as unorganized as NodeJS

```java
STILL NEED EXAMPLE HERE

```


Java got better

```java

List<String> organizers =
    Arrays.asList("Kevin", "Carlus", "Galen");

organizers
    .stream()
    .filter(s -> s.endsWith("n"))
    .map(String::toLowerCase)
    .sorted()
    .forEach(System.out::println);
```

Note:
This is easily parallelizable

This is lazy


No more excuses

![XKCD](images/xkcd-compiling.png)

Note:
DevTools are analogous to rerun in Ruby

Spring Loaded allows you to hotload code


Deploying got easier

![uberjar](images/java-jar.png)

Note:
Uberjars make deploying significantly easier. Spring Boot now embeds a Tomcat container, making everything just work with `java -jar`


## Profiles

```
@Configuration
@Profile("dev")
public class StandaloneDataConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("classpath:com/bank/config/sql/schema.sql")
            .addScript("classpath:com/bank/config/sql/test-data.sql")
            .build();
    }
};
@Configuration
@Profile("production")
public class JndiDataConfig {

    @Bean(destroyMethod="")
    public DataSource dataSource() throws Exception {
        Context ctx = new InitialContext();
        return (DataSource) ctx.lookup("java:comp/env/jdbc/datasource");
    }
}```


Some great companies threw their weight behind Spring

![Netflix](images/netflix.png)


And if you hate Java still...


Groovy

![Groovy](images/groovy.png)

Note:
Great language

Speed comparable to Java

What to use if you like what Ruby brings to the table


Kotlin

![Kotlin](images/kotlin.png)

Note:
Great language

Speed comparable to Java

What to use if you like what Scala brings to the table

