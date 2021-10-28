package com.company;

import java.util.Locale;

public class Main {

    public static void main(String[] args)
    {
	    //Frame2 frame = new Frame2();
        readFile get_array = new readFile();
        String[] businessesNames_array = get_array.readNamesArray();
        String[] businessesIDs_array = get_array.readIDArray();
        String[] reviews_array = get_array.readReviewArray();
        HashTable table = new HashTable();

        ifidf stuff = new ifidf(businessesNames_array, businessesIDs_array, reviews_array);

        for(int i = 0; i < businessesNames_array.length; i++)
        {
            table.put(businessesNames_array[i], businessesIDs_array[i],reviews_array[i].replaceAll("\\.", ""), i);
        }

        //table.printOne("Melissa Eckstrom - State Farm Insurance Agent");
        table.create_Frequencies();
        table.printAll();
        //table.printFreqTables();
        //System.out.println(stuff.documents_with_term("nothing" ));
        //table.printOne("Carlos Santo, NMD");
        System.out.println(stuff.get_similiar("Enterprise Rent-A-Car"));

        /*
        for(int i = 0; i < businessesNames_array.length; i++)
        {
            table.put(businessesNames_array[i], businessesIDs_array[i],reviews_array[i], i);
        }

        String test = "Nazar is lovely";
        //table.printAll();
        table.printFreqTables();
        System.out.println(table.get("Pet Planet"));
        System.out.println("Hash: " + test.hashCode());
        System.out.println(test.hashCode() & 20);

        String[] word = test.split("\\W+");
        for(int i = 0; i < word.length; i++)
        {
            System.out.println(word[i].toLowerCase(Locale.ROOT));
        }

        System.out.println("*********************************************************");

         */



    }
}
