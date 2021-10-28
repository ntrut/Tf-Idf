package com.company;

import java.util.ArrayList;
import java.util.Hashtable;

public class ididf_all_reviews
{

    public double get_one_ifidf(String word, FreqHastTable frequencies, HashTable table)
    {
        Calculate calculate = new Calculate();
        if(frequencies.getFrequency(word) != -1)
        {
            return calculate.IF(get_total_frequencies(frequencies), frequencies.getFrequency(word)) * calculate.IDF(word,table);
        }
        else
            return 0;

    }

    /*get total frequencies in a review*/
    public int get_total_frequencies(FreqHastTable frequencies)
    {
        int total_terms = 0;
        ArrayList<String> loads = frequencies.loads();

        for(int i = 0; i < loads.toArray().length; i++)
        {
            //System.out.println(loads.get(i));
            total_terms = total_terms + frequencies.getFrequency(loads.get(i));
        }

        return total_terms;
    }

}
