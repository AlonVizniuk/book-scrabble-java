package dictionary;

import java.util.LinkedHashSet;
import java.util.Iterator;

public class LRU implements CacheReplacementPolicy {

    private LinkedHashSet<String> cache;
    
    public LRU(){
        cache = new LinkedHashSet<String>();
    }

    @Override
    public void add(String word) {
        cache.remove(word); // It's the same complexity as checking if it contains.
        cache.add(word);
    }

    @Override
    public String remove(){
        Iterator<String>  it = cache.iterator();
        String temp = it.next();
        cache.remove(temp);
        return temp;
    }
}
