package com.company;

import java.util.ArrayList;


public class FreqHastTable
{

     static class Node
     {
        String key;
        int frequency;
        Node next;

         public Node(String key, int frequency, Node next) {
             this.key = key;
             this.frequency = frequency;
             this.next = next;

         }

         public Node getNext() {
             return next;
         }

         public void setNext(Node next) {
             this.next = next;
         }

         public String getKey() {
             return key;
         }

         public void setKey(String key) {
             this.key = key;
         }

         public int getFrequency() {
             return frequency;
         }

         public void setFrequency(int frequency) {
             this.frequency = frequency;
         }
     }

    private final int size = 8;
    private Node[] table = new Node[size];
    private int count;

    public void record(String key)
    {
        int asciiCODE = key.hashCode();     //sum of ASCII codes
        int index = asciiCODE & (table.length - 1);   // gets the index number of the string

        for(Node e = table[index]; e != null; e = e.getNext())
        {
            if(e.getKey().equals(key))
            {
                e.setFrequency(e.getFrequency() + 1);
                return;
            }
        }

        /*we add a new node since the key doesnt exists*/
        table[index] = new Node(key, 1, table[index]);
        count++;

        /*RESIZE*/
        if(((double) count / (double)table.length) > 0.75)
        {
            resize();
        }
    }

    /*return the frequency of one word*/
    public int getFrequency(String word)
    {
        int asciiCODE = word.hashCode();              //sum of ASCII codes
        int index = asciiCODE & (table.length - 1);   //gets the index number of the string

        for(Node e = table[index]; e != null; e = e.getNext())
        {
            if(e.getKey().equals(word))
            {
                //System.out.println("in freqhasttable: " + e.getKey());
                //System.out.println("In here: " + e.getFrequency());
                return e.getFrequency();
            }
        }
        return -1;
    }

    public ArrayList<String> loads()
    {
        ArrayList<String> array = new ArrayList<>();

        for(int i = 0; i < table.length; i++)
        {
            for(Node e = table[i]; e != null; e = e.getNext())
            {
                array.add(e.getKey());
            }
        }
        return array;
    }

    public Node[] getTable() {
        return table;
    }

    public void setTable(Node[] table) {
        this.table = table;
    }

    public void printAll()
    {
        for(int i = 0; i < table.length; i++)
        {

            for(Node e = table[i]; e != null; e = e.getNext())
            {
                System.out.print(" --> " + " [" + e.getKey() + ": " + e.getFrequency() + "]");
            }
            System.out.println();
        }
    }

    public void resize()
    {
        Node[] oldtable = table;
        Node[] newTable = new Node[oldtable.length * 2];

        for(int i = 0; i < table.length; i++)
        {
            for(Node e = table[i]; e != null; e = e.getNext())
            {
                int h = e.getKey().hashCode();
                int index = h & (newTable.length - 1);
                newTable[index] = new Node(e.getKey(), e.getFrequency(), newTable[index]);
            }

        }
        table = newTable;
    }
}
