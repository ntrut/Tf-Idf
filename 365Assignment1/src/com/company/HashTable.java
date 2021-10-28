package com.company;

import java.util.Locale;

public class HashTable
{
    /*Onto the hashtable stuff*/
    private final int size = 8;
    private Node[] table = new Node[size];
    int count;

    /*This will be used for the linked list inside the HashTables*/
    static class Node
    {
        final String key;
        private String id;
        private int  index;
        private String text;
        private Node next;
        private FreqHastTable frequencies = new FreqHastTable();


        public Node(String key, String id, String text, int index, Node next) {
            this.key = key;
            this.index = index;
            this.text = text;
            this.next = next;
            this.id = id;
        }


        public FreqHastTable getFrequencies() {
            return frequencies;
        }

        public void setFrequencies(FreqHastTable frequencies) {
            this.frequencies = frequencies;
        }

        public String getKey() {
            return key;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int val) {
            this.index = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }



    public void put(String key, String id, String text, int val)
    {
        int asciiCODE = key.hashCode();     //sum of ASCII codes
        int index = asciiCODE & (table.length - 1);   // gets the index number of the string

        /*Now we have the index, next we need to go through the linked list in that index*/
        for(Node e = table[index]; e != null; e = e.getNext())
        {
            /*check if there already is the same key*/
            if(key.equals(e.getKey()))
            {
               return;
            }
        }

        /*setting a new node to the beginning of the index*/
        if(table[index] == null)
        {
            count++;
        }
        table[index] = new Node(key, id, text, index, table[index]);


        /*RESIZE*/
        if(((double) count / (double)table.length) > 0.75)
        {
            resize();
        }

    }

    /*Returns the index of where the key is in the hashtable
    * returns -1 if not found*/
    public int get(String key)
    {
        int asciiCODE = key.hashCode();     //sum of ASCII codes
        int index = asciiCODE & (table.length - 1);   //gets the index number of the string

        for(Node e = table[index]; e != null; e = e.getNext())
        {
            if(e.getKey().equals(key))
            {
                return e.getIndex();
            }
        }
        return -1;
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
                newTable[index] = new Node(e.getKey(), e.getId(), e.getText(), index, newTable[index]);


            }

        }
        table = newTable;
    }
    /*nothing asd sadasd nothing.*/
    /*break up the review text into a array
    * then record it into the frequency table in a node*/
    public void break_up_text(Node node)
    {
        String text = node.getText();
        text.replace(".", "");
        String[] words = text.split("\\W");
        for(int i = 0; i < words.length; i++)
        {
            node.frequencies.record(words[i].toLowerCase(Locale.ROOT));
        }

    }

    public Node[] getTable() {
        return table;
    }

    public void setTable(Node[] table) {
        this.table = table;
    }

    public void create_Frequencies()
    {
        for(int i = 0; i < table.length; i++)
        {
            for(Node e = table[i]; e != null; e = e.getNext())
            {
                /*Add the text into a frequency table for that new node*/
                break_up_text(e);
            }
        }
    }

    /*Prints the whole hashtable without the frequencies*/
     public void printAll()
    {
        for(int i = 0; i < table.length; i++)
        {
            System.out.print(i + " ");
            for(Node e = table[i]; e != null; e = e.getNext())
            {
                System.out.print(" --> " + "[" + e.getKey() + ", id:" + e.getId() + "]");
                /*Add the text into a frequency table for that new node*/
                break_up_text(e);
            }
            System.out.println();
        }
    }

    /*prints each frequency table in each node*/
    public void printFreqTables()
    {
        for(int i = 0; i < table.length; i++)
        {
            for(Node e = table[i]; e != null; e = e.getNext())
            {
                System.out.printf(e.getKey());
               e.frequencies.printAll();
            }

        }
    }

    /*print one frequency table based on the name*/
    public void printOne(String name)
    {
        int index = this.get(name);

        for(Node e = table[index]; e != null; e = e.getNext())
        {
            e.frequencies.printAll();
        }

    }
}

