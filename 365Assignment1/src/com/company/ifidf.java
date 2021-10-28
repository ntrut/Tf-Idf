package com.company;

import java.util.ArrayList;
import java.util.*;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

public class ifidf
{
    private final HashTable table = new HashTable();


    public ifidf(String[] businessesNames_array, String[] businessesIDs_array, String[] reviews_array)
    {

        for(int i = 0; i < businessesNames_array.length; i++)
        {
            table.put(businessesNames_array[i], businessesIDs_array[i],reviews_array[i].replaceAll("\\.",""), i);
        }
        table.create_Frequencies();
    }

    public String get_similiar(String business_name)
    {
        String return_name = "";
        if(table.get(business_name) != -1)
        {
            /*found in the hashtable*/
            int index = table.get(business_name);

            for(HashTable.Node e = table.getTable()[index]; e != null; e = e.getNext())
            {
                if(business_name.equals(e.getKey()))
                {
                    return_name = calculate(e.getFrequencies(), business_name, e.getText());
                }
            }


            return return_name;
        }
        else
        {
            /*business name not found*/
            return "Opps! That Business Name does not exist in this dataset!";
        }
    }

    /*calculates the similarity for the input of the user*/
    public String calculate(FreqHastTable frequencies, String business_name, String text)
    {
        Calculate calculate = new Calculate();
        ididf_all_reviews test = new ididf_all_reviews();

        int total_terms = 0;
        ArrayList<String> loads = frequencies.loads();
        ArrayList<Double> idfvalues_input = new ArrayList<>();
        for(int i = 0; i < loads.toArray().length; i++)
        {
            System.out.println(loads.get(i));
            total_terms = total_terms + frequencies.getFrequency(loads.get(i));
        }

        /*Go word by word getting their if-idf values*/
        for(int i = 0; i < loads.toArray().length; i++)
        {
            double idf = calculate.IF(total_terms, frequencies.getFrequency(loads.get(i))) * calculate.IDF(loads.get(i), table);
            idfvalues_input.add(idf);
        }

        double max = 0;
        String yes = "";
        String intable = "";
        for(int index = 0; index < table.getTable().length; index++)
        {
            for(HashTable.Node e = table.getTable()[index]; e != null; e = e.getNext())
            {
                    if(!business_name.equals(e.getKey()))
                    {
                        ArrayList<String> loads_for_comparing = e.getFrequencies().loads();
                        Set<String> hash_set = new HashSet<String>();

                        hash_set.addAll(loads_for_comparing);
                        hash_set.addAll(loads);
                        //System.out.println(hash_set);

                        ArrayList<Double> input_tfidf = new ArrayList<Double>();
                        ArrayList<Double> compare_to = new ArrayList<Double>();

                        Iterator<String> iterator = hash_set.iterator();
                        while(iterator.hasNext())
                        {
                            String value = iterator.next();
                            double t = test.get_one_ifidf(value, e.getFrequencies(), table);
                            double d = test.get_one_ifidf(value, frequencies, table);
                            input_tfidf.add(d);
                            compare_to.add(t);
                        }

                        Cosine cosine = new Cosine();
                        double v = cosine.cosine_similarity(input_tfidf, compare_to);
                        if(v > max)
                        {
                            max = v;
                            yes = e.getKey();
                            intable =e.getText();
                        }
                        System.out.println(index + " SIMILIARTY FINANLLLYYY: " + v);
                    }
            }
        }
        System.out.println("We found that " + yes + " with " + max + " " + " is equal to " + business_name);
        System.out.println(business_name + " review: " + text);
        System.out.println(yes + " review: " + intable);
        return "";
    }
}
