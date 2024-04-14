package com.Relearn;

import java.util.*;

public class TreeSetMain {

    public static void main(String[] args) {

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        Contact bob = new Contact("Emanuel Bob", 234889898);

//        NavigableSet<Contact> sorted = new TreeSet<>(phones);
        Comparator<Contact> mySort = Comparator.comparing(Contact::getName);
        NavigableSet<Contact> sorted = new TreeSet<>(mySort);
        sorted.addAll(phones); //use addAll when adding a collection, use add when adding an item
        sorted.add(bob);
        sorted.forEach(System.out::println);

        System.out.println();
        //noargs constructor can work here because String implements Comparable and has a default sort mechanism
        NavigableSet<String> justNames = new TreeSet<>();
//        phones.forEach(jn -> justNames.add(jn.getName())); Emanuel Bob
        sorted.forEach(jn -> justNames.add(jn.getName()));
        System.out.println(justNames);

        System.out.println();
        //passing an already sorted list also works fine. The sort of the already sorted list is used
        NavigableSet<Contact> fullSet = new TreeSet<>(sorted);
        fullSet.addAll(emails);
        fullSet.forEach(System.out::println);

        System.out.println("----------------------------------------------");
        //method to get the comparator used
        List<Contact> anotherFullList = new ArrayList<>(phones);
        anotherFullList.addAll(emails);
        anotherFullList.add(bob);
        anotherFullList.sort(sorted.comparator());
        anotherFullList.forEach(System.out::println);
//        System.out.println(sorted.comparator());

        //min and max
        System.out.println("----------------------------------------------");
        Contact min =Collections.min(fullSet, fullSet.comparator());
        Contact max = Collections.max(fullSet, fullSet.comparator());
        Contact first = fullSet.first();
        Contact last = fullSet.last();

        System.out.printf(" min: %s.    max: %s %n first: %s.    last: %s %n", min.getName(), max.getName(), first.getName(), last.getName());
        System.out.println("----------------------------------------------");

        //pollFirst() and pollLast()   ->    elements are first removed
        NavigableSet<Contact> copiedSet = new TreeSet<>(fullSet);
        System.out.println("first element: " + copiedSet.pollFirst().getName());
        System.out.println("last element: " + Objects.requireNonNull(copiedSet.pollLast()).getName());
        System.out.println("-----------------------------------------------");
        copiedSet.forEach(System.out::println);
        System.out.println("-----------------------------------------------");

        //methods unique only to the TreeSet

        Contact daffy = new Contact("Daffy Duck");
        Contact daisy = new Contact("Daisy Duck");
        Contact snoopy = new Contact("Snoopy");
        Contact archie = new Contact("Archie");

        for (Contact contact : List.of(daffy, daisy, last, snoopy)){
            System.out.printf("ceiling (%s) = %s %n", contact.getName(), fullSet.ceiling(contact));
            System.out.printf("higher (%s) = %s %n", contact.getName(), fullSet.higher(contact));
        }
        System.out.println("--------------------------------------------------------");

        for (Contact contact : List.of(daffy, daisy, first, archie)){
            System.out.printf("floor (%s) = %s %n", contact.getName(), fullSet.floor(contact));
            System.out.printf("lower (%s) = %s %n", contact.getName(), fullSet.lower(contact));
        }
        System.out.println("--------------------------------------------------------");

        //descendingSet();
        NavigableSet<Contact> setInDescendingOrder = fullSet.descendingSet();
        setInDescendingOrder.forEach(System.out::println);
        System.out.println("--------------------------------------------------------");

        //any changes made to the child set affects the parent set it is backed by
        Contact lastContactInDescendingSet = setInDescendingOrder.pollLast();
        assert lastContactInDescendingSet != null;
        System.out.println("removed " + lastContactInDescendingSet.getName());

        System.out.println("--------------------------------------------------------");
        System.out.println("setInDescendingOrder");
        setInDescendingOrder.forEach(System.out::println);

        System.out.println("--------------------------------------------------------");
        System.out.println("fullSet");
        fullSet.forEach(System.out::println);

        //headSet and tailSet - returning objects before and after the given object, respectively
        Contact marion = new Contact("Maid Marion");
        var headSet = fullSet.headSet(marion, true); //exclusive by default (excludes the passed obj) boolean statement to specifies if inclusive
        headSet.forEach(System.out::println);
        System.out.println("--------------------------------------------------------");

        var tailSet = fullSet.tailSet(marion, true); //inclusive by default (includes the passed element) boolean statement specifies if inclusive
        tailSet.forEach(System.out::println);
        System.out.println("--------------------------------------------------------");

        Contact linus = new Contact("Lin Van Pelt");
        var subSet = fullSet.subSet(linus, true, marion, true); //-> (elementfrom, bool fromInclusive, elementTo, bool toInclusive)
        subSet.forEach(System.out::println);
//        headSet.stream().findFirst(); -> read about spliterator and wanted to keep this code, and the one below, here.
//        var innn = 564;
//        for (; 99 < 90; innn += 2){
//            tailSet.removeIf(tailSet.contains(marion)? marion.getName() : null);
//        }


        /* The TreeSet does offer many advantages, in terms of built-in functionality, over the other two Set implementations,
            but it does come at a higher cost.

            If the number of elements is not large, or the desired collection has to be sorted, and continuously resorted
            as elements are added and removed, and shouldn't contain duplicates elements, the TreeSet is a good alternative
            to the ArrayList.
         */

    }
}
