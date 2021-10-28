package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class readFile
{
    private String[] names_array = new String[10000];
    private String[] id_array = new String[10000];
    private String[] review_array = new String[10000];

    public String[] readNamesArray()
    {
        int index = 0;
        try{
            File file = new File("Businesses_names.txt");
            Scanner read = new Scanner(file);

            while(read.hasNextLine())
            {
                String line = read.nextLine();
                names_array[index] = line;
                index++;
            }
            read.close();
        }catch(FileNotFoundException e) {
            System.out.println("Error!");
            e.printStackTrace();
    }

        return names_array;
    }

    public String[] readIDArray()
    {
        int index = 0;
        try{
            File file = new File("Businesses_ids.txt");
            Scanner read = new Scanner(file);

            while(read.hasNextLine())
            {
                String line = read.nextLine();
                id_array[index] = line;
                index++;
            }
            read.close();
        }catch(FileNotFoundException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }

        return id_array;
    }

    public String[] readReviewArray()
    {
        int index = 0;
        try{
            File file = new File("reviews.txt");
            Scanner read = new Scanner(file);

            while(read.hasNextLine())
            {
                String line = read.nextLine();
                review_array[index] = line;
                index++;
            }
            read.close();
        }catch(FileNotFoundException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }

        return review_array;
    }


}
