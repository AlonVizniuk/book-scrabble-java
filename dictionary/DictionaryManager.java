package dictionary;


import java.util.HashMap;
import java.util.Map;

public class DictionaryManager {
    private static DictionaryManager dm=null;
    public static Map<String, Dictionary> map=new HashMap<>();

    public DictionaryManager() {
    }
    public static DictionaryManager get(){
        if(dm == null)
            dm=new DictionaryManager();
        return dm;
    }
    public static boolean query(String...books){
        String last=books[books.length-1];
        boolean flag=false;
        for(int i=0; i< books.length-1; i++){
            if(!map.containsKey(books[i]))
                map.put(books[i],new Dictionary(books[i]));
        }
        for(Dictionary d: map.values())
            if(d.query(last))
                flag=true;
        return flag;
    }
    public static boolean challenge(String...books){
        String last=books[books.length-1];
        boolean flag=false;
        for(int i=0; i< books.length-1; i++){
            if(!map.containsKey(books[i]))
                map.put(books[i],new Dictionary(books[i]));
        }
        for(Dictionary d: map.values())
            if(d.challenge(last))
                flag=true;
        return flag;
    }
    public int getSize(){
        return map.size();
    }
}