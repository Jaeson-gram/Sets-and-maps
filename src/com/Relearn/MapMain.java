package com.Relearn;

import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMain {

    public static void main(String[] args) {
        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);

        fullList.forEach(System.out::println);
        System.out.println("---------------------------------------------");

        //MAP
        Map<String, Contact> contacts = new HashMap<>();

        for (Contact contact : fullList){
            contacts.put(contact.getName(), contact);
        }

        contacts.forEach((k, v) -> System.out.println("Key: " + k + "          Value: " + v));

        //Micky Mouse:
        // -> Mickey Mouse: [micky1@aws.com] [] will be added instead of -> Mickey Mouse: [] [(999) 888-7777]
        //because when elements are added, the key is compared with all the keys in the map, if it exists, the new will replace the old.
        // So even though we have the phone as the first instance coming from our List, we get the email added, replacing all previously added instances.
        System.out.println("----------------------------------------------");
        //getting data from a Map - the key is searched and result returned

        // searching with a key
        System.out.println(contacts.get("Charlie Brown")); // -> Charlie Brown: [] [(333) 444-5555]
        System.out.println(contacts.get("Sam Adeyemi")); // -> null

        //jdk intro'd getOrDefault in jdk 11 so when we pass a key, if it doesn't exist, we can give it a default value
        var defaultContact = new Contact("Sam Dede", "sammydede@something.som", 8045454545L);
        System.out.println(contacts.getOrDefault("Sam Dede", defaultContact));

        //It is important to note that the contact never gets added to the map, it's just there for convenience.

        System.out.println("--------------------------------------------------");

        //merging contacts
        Map<String, Contact> mergedContacts = new HashMap<>();

        for (Contact contact : fullList){
            Contact duplicate = mergedContacts.put(contact.getName(), contact);

            if (duplicate != null){
                //we can use this to track our duplicates
                System.out.println("duplicate = " + duplicate);
                System.out.println("current = " + contact);

                //adding all duplicates to our contact form so we have all the contacts of a contact...
                mergedContacts.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }
        System.out.println();
        mergedContacts.forEach((k, v) -> System.out.println("Key: " + k + "     Value: " + v));

        System.out.println("-----------------------------------------------------------");

        //keeping only the first instance it finds, and ignoring the rest...
        Map<String, Contact> initialContacts = new HashMap<>();

        for (Contact contact : fullList){
            //If the specified key is not already associated with a value (or is mapped to null) associates it with the given value and returns null,
            // else returns the current value.
            initialContacts.putIfAbsent(contact.getName(), contact);
        }
        initialContacts.forEach((k, v) -> System.out.println("Key: " + k + "          Value: " + v));

//      should we desire to merge, we can repeat the same code in the merged above, and pass the contacts to the new map we create to store the merged contacts
        System.out.println("-------------------------------------------------------------");


        //But there is an even easier way to do the merging - using the merge method on the Map.
        Map<String, Contact> contactMap = new HashMap<>();
        fullList.forEach(contact -> contactMap.merge(contact.getName(), contact, Contact::mergeContactData));

//        fullList.forEach(contact -> contactMap.merge(contact.getName(), contact, (prev, curr) -> prev.mergeContactData(curr)));

        contactMap.forEach((k, v) -> System.out.println("Key: " + k + "          Value: " + v));

        System.out.println("--------------------------------------------------------------------");
        //compute() and computeIf()
        /* compute()
        "Attempts to compute a mapping for the specified key and its current mapped value (or null if there is no current mapping). For example, to either create or append a String msg to a value mapping:

         map.compute(key, (k, v) -> (v == null) ? msg : v.concat(msg))
         (Method merge() is often simpler to use for such purposes.)
         If the remapping function returns null, the mapping is removed (or remains absent if initially absent). If the remapping function itself throws an (unchecked) exception, the exception is rethrown, and the current mapping is left unchanged.
         The remapping function should not modify this map during computation."
         */

        for (String contactName : new String[] {"Ferran Javis", "Ingli Maito", "Daffy Duck"}){
//            contacts.compute(contactName, (k,v) -> new Contact(k));
            //if any key already exists, as typical of the Map, it overwrites the data with the new data.


//            contacts.computeIfAbsent(contactName, Contact::new);    // -> contacts.computeIfAbsent(contactName, k -> new Contact(k));
            //the computeIfAbsent takes only a key and defaults the value, and it doesn't do anything with the object if it is already in the Map.
            //It only computes if the key is absent from the HashMap



            contacts.computeIfPresent(contactName, (k, v) -> {
                v.addEmail("Jera Inc");
                return v;
            });
            //the computeIfPresent() computes stuff if the object already exists in the Map.


            //I just wanted to add them here... not necessarily part of the lesson
            if (!contactMap.containsKey(contactName)) {
                contactMap.put(contactName, new Contact(contactName, "noemail", 0));
            }
        }

        contacts.forEach((k, v) -> System.out.println("Key: " + k + "          Value: " + v));

        System.out.println("-----------------------------------------------------------------------");
        contactMap.forEach((k, v) -> System.out.println("Key: " + k + "          Value: " + v));

    }

}
