public class Cat {
    String name;
    String color;
    int age;

    Cat(String name) {
        this.name = name;
    }

    public void catColor(String catColor) {
        color = catColor;
    }

    public void catAge(int catAge) {
        age = catAge;
    }

    public void printCat() {
        System.out.println("Cat Name:" + name);
        System.out.println("Cat Color:" + color);
        System.out.println("Cat Age:" + age);
    }
}
