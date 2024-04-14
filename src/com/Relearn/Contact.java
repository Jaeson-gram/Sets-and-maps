package com.Relearn;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Contact {

    private String name;
    private Set<String> emails = new HashSet<>();
    private Set<String> phones = new HashSet<>();

    public Contact(String name) {
        this(name, null);
    }

    public Contact(String name, String email) {
        this(name, email, 0);
    }

    public Contact(String name, long phone) {
        this(name, null, phone);
    }

    public Contact(String name, String email, long phone) {
        this.name = name;
        if (email != null) {
            emails.add(email);
        }
        if (phone > 0) {
            String p = String.valueOf(phone);
            p = "(%s) %s-%s".formatted(p.substring(0, 3), p.substring(3, 6),
                    p.substring(6));
            phones.add(p);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "%s: %s %s".formatted(name, emails, phones);
    }

    public Contact mergeContactData(Contact contact) {

        Contact newContact = new Contact(name);
        newContact.emails = new HashSet<>(this.emails);
        newContact.phones = new HashSet<>(this.phones);
        newContact.emails.addAll(contact.emails);
        newContact.phones.addAll(contact.phones);
        return newContact;
    }

// Java.util.object

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Contact contact = (Contact) o;
//        return getName().equals(contact.getName()) && Objects.equals(emails, contact.emails) && Objects.equals(phones, contact.phones);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getName(), emails, phones);
//    }


    //Intelli J Default


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return getName().equals(contact.getName());
    }

    @Override
    public int hashCode() {
        return 33 * getName().hashCode();
    }

    public void addEmail(String companyName){
        String[] names = name.split(" ");
        String email = "%c%s@%s.com".formatted(name.charAt(0), names[names.length - 1], companyName.replaceAll(" ", "").toLowerCase());
        if(!emails.add(email)){
            System.out.println(name + " already has email: " + email + "!");
        }
        else {
            System.out.println("email " + "'" + email + "' for " + name + " added!");
        }
    }

    public void replaceExtistingEmail(String oldMail, String newMail){
        //Set does not have an implementation for replace() or replaceAll(), so we do it manually
        if (emails.contains(oldMail)){
            emails.remove(oldMail);
            emails.add(newMail);
        }
    }
}
