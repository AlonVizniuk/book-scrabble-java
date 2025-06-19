package dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {

    private String[] fileNames;
    private CacheManager cacheLRU;
    private CacheManager cacheLFU;
    private BloomFilter bloomFilter;

    public Dictionary(String...fileNames){
        this.fileNames = fileNames;
        this.cacheLRU = new CacheManager(400, new LRU());
        this.cacheLFU = new CacheManager(100, new LFU());
        this.bloomFilter = new BloomFilter(256, "MD5" , "SHA1");

        for(String file : this.fileNames){
            try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                String line;
                while ((line = reader.readLine()) != null){
                    String[] wordsInLine = line.split("\\s+");
                    for(String word : wordsInLine){bloomFilter.add(word);}
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }       
    }

    public boolean query(String word){
        if (cacheLRU.query(word)) {
            return true;
        }

        else if (cacheLFU.query(word)) {
            return false;           
        }

        else if (bloomFilter.contains(word)) {
            cacheLRU.add(word);
            return true;            
        }
        else{
            cacheLFU.add(word);
            return false;
        }
    }

    public boolean challenge(String word) {
        try{
            if (IOSearcher.search(word, this.fileNames)) {
                cacheLRU.add(word);
                return true;
            }
            else {
                cacheLFU.add(word);
                return false;
            }
        }
        catch (Exception e){return false;}
    }
}
