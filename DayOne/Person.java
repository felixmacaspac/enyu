// Class
public class Person {
    // instance variables
    String name;
    int age;
    char gender;

     /* We can a default value of each variables, the code below we have the age = 10
     as its default value if the user didn't override the value of it. */
    Person() {
        age = 10;
    }

    // the running() is what we called a method
    void running() {
        // body of our method
        System.out.println(name + " is currently running");
    }
}