package dictionary;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class LFU implements CacheReplacementPolicy {
    
        private LinkedHashMap<String, Integer> cache;

        public LFU(){
            cache = new LinkedHashMap<String, Integer>();
        }

        @Override
        public int hashCode() {return Objects.hash(cache);}
        
        public void add(String word) {
            if(cache.containsKey(word)){
                cache.put(word, cache.get(word) + 1);
            } else {
                cache.put(word, 1);
            }
        }
    
        @Override
        public String remove() {
            String test = cache.keySet().iterator().next();
            int minVal = cache.get(test);
            for(Map.Entry<String,Integer> entry : cache.entrySet()){
                if(minVal > entry.getValue()){
                    minVal=entry.getValue();
                    test=entry.getKey();
                }
            }
            cache.remove(test);
            return test;
        }
}
