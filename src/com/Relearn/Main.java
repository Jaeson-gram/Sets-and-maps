package com.Relearn;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        List<Contact> emails =  ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");
        printData("Phone List", phones);
        printData("Email List", emails);

        Set<Contact> emailContacts = new HashSet<>(emails);
        Set<Contact> phoneContacts = new HashSet<>(phones);
        printData("Phones Repository", phoneContacts);
        printData("Emails Repository", emailContacts);

        //SYMMETRIC OPERATIONS ON A SET - returns the same results

        //Union of Sets
        Set<Contact> unionOfPhonesAndEmails = new HashSet<>();
        unionOfPhonesAndEmails.addAll(phoneContacts);
        unionOfPhonesAndEmails.addAll(emailContacts);

        printData("(Phones \u222A Emails) Union of emails and phones contacts", unionOfPhonesAndEmails);

        Set<Contact> intersectOfPhonesAndEmails = new HashSet<>(phoneContacts);
        intersectOfPhonesAndEmails.retainAll(emailContacts);
        printData("(Phones \u2229 Emails) Intersect of phones and emails contacts", intersectOfPhonesAndEmails);

        //result ->
        //Robin Hood: [] [(564) 789-3000]
        //Mickey Mouse: [] [(999) 888-7777]
        //Minnie Mouse: [] [(456) 780-5666]
        //only the numbers were added because no duplicates are allowed, and the first list to be added was the contacts list.

        Set<Contact> intersectOfEmailsAndPhones = new HashSet<>(emailContacts);
        intersectOfEmailsAndPhones.retainAll(phoneContacts);
        printData("(Emails \u2229 Phones) Intersect of emails and phones contacts", intersectOfEmailsAndPhones);

        //result ->
        //Robin Hood: [rhood@gmail.com] []
        //Mickey Mouse: [mckmouse@gmail.com] []
        //Minnie Mouse: [minnie@verizon.net] []

        //includes their emails this time, and leaves out the phones, cus no duplicates

        //ASYMMETRIC OPERATIONS ON A SET - returning a different result.

        Set<Contact> emailsMinusPhones = new HashSet<>(emailContacts);
        emailsMinusPhones.removeAll(phoneContacts);
        printData("(Emails - Phones) Set of Emails minus Phones", emailsMinusPhones);

        Set<Contact> phonesMinusEmails = new HashSet<>(phoneContacts);
        phonesMinusEmails.removeAll(emailContacts);
        printData("(Phones - Emails) Set of Phones minus Emails", phonesMinusEmails);



        //SYMMETRIC DIFFERENCE - items from all sets that don't intersect at all

        Set<Contact> symmetricDifference = new HashSet<>(emailsMinusPhones);
        symmetricDifference.addAll(phonesMinusEmails);
        printData("Symmetric Difference: of phones and emails", symmetricDifference);

        Set<Contact> symmetricDifference2 = new HashSet<>(unionOfPhonesAndEmails);
        symmetricDifference2.removeAll(intersectOfPhonesAndEmails);
        printData("Symmetric Difference: of phones and emails", symmetricDifference2);

        Set<Contact> symmetricDifference3 = new HashSet<>(unionOfPhonesAndEmails);
        symmetricDifference2.removeAll(intersectOfEmailsAndPhones);
        printData("Symmetric Difference: of phones and emails", symmetricDifference3);


//        int robinHoodIndex = emails.indexOf(new Contact("Robin Hood"));
//        Contact robinHood = emails.get(robinHoodIndex);
//        robinHood.addEmail("iRing Op");
//        robinHood.addEmail("iRing Op");
//        System.out.println(robinHood);
    }

    public static void printData(String header, Collection<Contact> contacts) {
        System.out.println("----------------------------------------------");
        System.out.println(header);
        System.out.println("----------------------------------------------");
        contacts.forEach(System.out::println);
    }
}
