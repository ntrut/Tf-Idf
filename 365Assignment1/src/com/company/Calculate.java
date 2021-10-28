package com.company;

public class Calculate 
{
    public double IF(int total_terms, int word_freq)
    {
        //System.out.println("IF: " + (double) word_freq / (double)total_terms);
        return (double) word_freq / total_terms;

    }

    public double IDF(String word, HashTable table)
    {
        //System.out.println("IDF docuemnt: " + documents_with_term(word));
        return Math.log(10000.0 / (double) documents_with_term(word, table));
    }

    /*Takes in a word and returns the number of documents where the word is
    for calculating idf
    * log_e(Total number of documents / Number of documents with term in it).*/
    public int documents_with_term(String word, HashTable table)
    {
        int total_word_in_document = 0;
        for(int i = 0; i < table.getTable().length; i++)
        {
            for(HashTable.Node e = table.getTable()[i]; e != null; e = e.getNext())
            {

                if(e.getFrequencies().getFrequency(word) != -1)
                {
                    //System.out.println(e.getKey());
                    total_word_in_document++;
                }
            }
        }
        return total_word_in_document;
    }
}
