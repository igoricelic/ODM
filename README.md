# Indigo Framework - ODM

This is the open-source ODM (Object Document Mapper) library for MongoDB written in JAVA and part of <b>Indigo Project Framework</b>

Version: 1.0.0
Licence: MIT
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
## Architecture

![alt text](https://github.com/igoricelic/ODM/blob/master/IndigoArchitekture.png)

## Use

Follow this short guide and learn how to use this library.

### Configuration

```java
// This is the first way, it is necessary to add all classes that represent the entities as list
IndigoConfigurator configurator = new IndigoConfiguratorImpl(-db_host-, -db_port-, -db-, List<Class> entities);

// In the secound way, we can add only path to package with entity classes (for example: com.myproject.entities)
IndigoConfigurator configurator = new IndigoConfiguratorImpl(-db_host-, -db_port-, -db-, -path_to_package_with_entities-);

// After configuration, we can create repository for our entity
CrudRepository<Entity> crudRepositoryDAO = configurator.provideCrudRepository(Entity.class)
// Or create proxy for our custom repository bean
CustomRepositoryInterface myCustomRepoDAO = configurator.provideProxy(CustomRepository.class)
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
### Annotations

#### @Id - This annotation specifies the property, use for identity (primary key of a document) of the class.
```
expose - (OPTIONAL) says whether want to read value of field from database, default value is true
```

#### @Document - This annotation specifies to declare the class as document in database
```
collection - (OPTIONAL) name of collection in out database, default value is name of class

embedded - (OPTIONAL) says whether the document is embedded in other document, default falue is false
```

#### @Field - This annotation is used to specify field or attribute for persistence property.
```
name - (OPTIONAL) name of field in database, default value is name of field in entity class

type - (OPTIONAL) java type of field as Class object (for example: type = String.class)
```

#### @Embedded - This annotation is used to specify other embedded document or list of document in out persistence property.
```
name - (OPTIONAL) name of field in database, default value is name of field in entity class

targetEntity - (OPTIONAL) type of embedded entity (for example: targetEntity = Customer.class)
```

#### @Transient - This annotation specifies the property which in not persistent i.e. the value is never stored into database.

### Repositories

The central interface and Indigo ODM repository abstraction is Repository. It takes the domain class to manage with type of the domain class. This interface acts primarily as a marker interface to capture the types to work with and help you discover interfaces that extend this one. The [CrudRepository (see all methods)](https://github.com/igoricelic/ODM/blob/master/src/main/java/com/orm/v_1/SimpleDocumentMapper/repositories/CrudRepository.java) provides sophisticated CRUD functionality for the entity class that is being managed.

``` java
public List<T> readAll (); // returns all entities

public Optional<T> readById (Object id); // returns the entity identified by the given id

public T createOne (T t); // saves the given entity

public T updateOne (T t); // update the given entity

public T deleteOne (T t); // delete the given entity

// ... and more :)
```
On top of the CrudRepository there is a [PagingAndSortingRepository (see all methods)](https://github.com/igoricelic/ODM/blob/master/src/main/java/com/orm/v_1/SimpleDocumentMapper/repositories/PagingAndSortingRepository.java) abstraction that adds additional methods to ease paginated access to entities:

``` java
public Page<T> readAll (PageRequest pageRequest); // new PageRequest(page, size);
	
public List<T> readAll (SortRequest sortRequest);
	
public Page<T> readAll (PageRequest pageRequest, SortRequest sortRequest);

// Page<T>
public interface Page<T> {
  public List<T> getContent();
  public int getPage();
  public int getSize();
  public int numberOfElements();
  public int totalPages();
  public int totalElements();
  public boolean getFirstPage();
  public boolean getLastPage();
}


// ... and more :)
```
Standard CRUD functionality repositories usually have queries on the underlying datastore. Indigo provides specification interface for queries on database:
[SpecificationRepository (see all methods)](https://github.com/igoricelic/ODM/blob/master/src/main/java/com/orm/v_1/SimpleDocumentMapper/repositories/SpecificationRepository.java)

``` java
Specification<Customer> specification = new SpecificationBuilder<Customer>()
	.addCriterion("firstName","Michael",Comparator.StartsWith).build();
			
Specification<Customer> specification = new SpecificationBuilder<Customer>()
	.addCriterion("customerType", CustomerType.ADMIN, Comparator.Equality).build();
			
/**
* For embedded documents, we can use -field_name_in_base_class-.-field_name_in_embedded_class-
**/
Specification<Customer> specification = new SpecificationBuilder<Customer>()
	.addCriterion("firstName", "Michael", Comparator.Equality)
	.addCriterion("packages.title", "END", Comparator.EndWith)
	.addCriterion("packages.channel", "Channel_1", Comparator.In)
	.operator(Operator.Or).build();

// In SpecificationRepository, we can use methods:
public List<T> readBy (Specification<T> specification);
	
public T readOneBy (Specification<T> specification);
	
public long countBy (Specification<T> specification);
	
public boolean existBy (Specification<T> specification);
	
public boolean deleteBy (Specification<T> specification);
```

Also, we can create out custom repository and declare methods of query on the interface:

``` java
// First, we have to inherit one of the repositories
public interface CustomerDao extends PagingAndSortingRepository<Customer> {
	
	public boolean existsByEmailEq (String email);
	
	public long countByFirstNameEq (String firstName);
	
	public Optional<Customer> readByEmailEqAndPasswordEq (String email, String password);
	
	public Page<Customer> readByPackages_TitleStartsWith (String packageTitlePrefix, PageRequest pageRequest);
	
	public List<Customer> readByLastNameEndWithAndPackages_CreatedAtEq (String lastName, Date createdAt);
	
	public Optional<List<Customer>> readByCustomerTypeEqOrCustomerTypeEq (CustomerType customerType1, CustomerType customerType2);
}
``` 
For search in the embedded document we have to use _ , after each field from the model we have to write a type of comparator.
The name of the method must start with READ, COUNT od EXISTS.

### Comparators

| Name for specification  | Name for method-query |
| ------------- | ------------- |
| Equality  | Eq  |
| LessThan  | Lt |
| LessThanEquality  | Lte  |
| GreaterThan  | Gt  |
| GreaterThanEquality  | Gtl  |
| NotEquals  | Ne  |
| In  | In  |
| StartsWith  | StartsWith  |
| EndsWith  | EndsWith  |
| Contains  | Contains  |
| Before  | Before  |
| After  | After  |
