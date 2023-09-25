public class ObjectsClasses {
    public static void main(String[] args) {

        // Creating the first object from Person class
        Person firstPerson = new Person();
        firstPerson.name = "Felix";
        firstPerson.gender = 'm';

        // Creating the second object from Person class
        Person secondPerson = new Person();
        secondPerson.name = "Dan";
        secondPerson.gender = 'F';

        System.out.println("His name is " + firstPerson.name);
        System.out.println("His age is " + firstPerson.age);
        System.out.println("His gender is " + firstPerson.gender);

        System.out.println("Her name is " + secondPerson.name);
        System.out.println("Her age is " + secondPerson.age);
        System.out.println("Her gender is " + secondPerson.gender);

        // Calling the class method
        secondPerson.running();

        // Creating new object from Cat class
        Cat orange = new Cat("Orange");
        orange.catColor("Orange");
        orange.catAge(1);
        orange.printCat();
    }
}