### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues

#### Your experience in Java

Please let us know more about your Java experience in a few sentences. For example:

- I have 3 years experience in Java and I started to use Spring Boot from last year
- I'm a beginner and just recently learned Spring Boot
- I know Spring Boot very well and have been using it for many years


#### Work done
- I replaced all setter injection with constructor injection to make writing test easier.
- Instead of printing to stdout I replaced them with call of slf4j to print to logging instance. slf4j is used because it is a interface so implemtation can be changed if needed.
- Used dto to pass param to controller and return from controller
- Refactor crud flow so appropriate status code is returned based on operation success or not

#### Further improvement
- /employees endpoint could be a problem if return too many employee. Ideally it should use pagination to reduce size of response.
- use optimistic lock in entity model to avoid possible lost write when multiple requests write to same employee record.
- enable cache on api /employees/{employeeId} to reduce db hit when retrieving employee 
- use spring security to add rbac access control to api endpoint
- use Bucket4j to rate limit to frequent called api like get employee

#### My experience in Java
I have 5 years experience in Java and I started to use Spring Boot from 5 years ago.