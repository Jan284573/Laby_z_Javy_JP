package L2_Kolekcje;

import java.util.ArrayList;
import java.util.Comparator;

public class L2Z5_List {
    public static void main(String[] args) {
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(new Person("Ania", 20));
        personList.add(new Person("Andrzej", 25));
        personList.add(new Person("Fiona", 38));
        personList.add(new Person("Jędrzej", 20));
        personList.add(new Person("Kajetan", 19));
        personList.add(new Person("Henryk", 70));
        personList.add(new Person("Mokebe", 23));

        System.out.println("Lista: ");
        for(Person p : personList) {
            p.printPerson();
        }

        personList.sort(new AgeComparator());

        System.out.println("\nLista po sortowaniu: ");
        for(Person p : personList) {
            p.printPerson();
        }

    }

}

class Person{
    public String name;
    public int age;

    Person(String name, int age){
        this.name=name;
        this.age=age;
    }

    public void printPerson(){
        System.out.println(this.name+" "+this.age);
    }
}

class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2){
        return Integer.compare(p1.age,p2.age);
    }

}