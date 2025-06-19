package dictionary;

import java.util.HashSet;

public class CacheManager {
	private int size;
    private CacheReplacementPolicy crp;
    private HashSet<String> cache;
	
    public CacheManager(int size, CacheReplacementPolicy crp){
        this.size = size;
        this.crp = crp;
        this.cache = new HashSet<String>();
    } 

    public boolean query (String word){
        return cache.contains(word);
    }

    public void add(String word){
        crp.add(word);
        cache.add(word);
        if(size < cache.size()){
            cache.remove(crp.remove());
        }
    }
}
