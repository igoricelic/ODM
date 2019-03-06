# Indigo - ODM

This is the open-source ODM (Object Document Mapper) library written in JAVA and part of <b>IndigoProject</b>
.

## Install

Maven dependency
```maven
<repositories>
 <repository>
  <id>ODM</id>
  <url>https://github.com/igoricelic/ODM.git</url>
  <snapshots>
   <enabled>true</enabled>
   <updatePolicy>always</updatePolicy>
  </snapshots>
 </repository>
</repositories>

<dependency>
 <groupId>com.orm.v_1</groupId>
 <artifactId>SimpleDocumentMapper</artifactId>
 <version>0.0.1-SNAPSHOT</version>
</dependency>
```

## Use

Follow this short guide and learn how to use this library.

### Configuration

```java
// This is the first way, it is necessary to add all classes that represent the entities as list
IndigoConfigurator configurator = new IndigoConfiguratorImpl(-db_host-, -db_port-, -db-, List<Class> entities);

// In the secound way, we can add only path to package with entity classes (for example: com.myproject.entities)
IndigoConfigurator configurator = new IndigoConfiguratorImpl(-db_host-, -db_port-, -db-, -path_to_package_with_entities-);

// After configuration, we can create repository for our entity
configurator.provideCrudRepository(Entity.class)
// Or create proxy for our custom repository bean
configurator.provideProxy(CustomRepository.class)
```

### Mapping

```java
@Document(collection = "customer")
public class Customer {
  @Id
  private String id;
	
  @Field(name = "first_name")
  private String firstName;
	
  @Field(name = "last_name")
  private String lastName;
	
  private String email;
	
  private String password;
	
  @Field(name = "customer_type")
  private CustomerType customerType;
	
  @Embedded
  private List<Package> packages;
	
  private List<Date> dates;
}

@Document(embedded = true)
public class Package {
	
  private String title;
	
  @Field(name = "created_at")
  private Date createdAt;
	
  private List<String> channels;
}
```
