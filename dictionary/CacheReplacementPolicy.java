package dictionary;

public interface CacheReplacementPolicy{
	void add(String word);
	String remove(); 
}
