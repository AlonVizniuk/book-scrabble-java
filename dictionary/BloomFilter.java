package dictionary;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class BloomFilter {

    private BitSet bitSets;
    private List<MessageDigest> arrFunc;

    public BloomFilter(int size, String...algs){
        bitSets = new BitSet(size);
        arrFunc = new ArrayList<>();
        for(String func : algs){
            try{
                MessageDigest md = MessageDigest.getInstance(func);
                arrFunc.add(md);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void add(String word) {
        for(MessageDigest func : arrFunc) {
            byte[] bts = func.digest(word.getBytes());
            BigInteger bi = new BigInteger(bts);
            int num = bi.intValue();
            num = Math.abs(num);
            num = num % bitSets.size();
            if(!(bitSets.get(num)))
                bitSets.flip(num);
        }
    }

    public boolean contains(String word){
        for(MessageDigest func : arrFunc) {
            byte[] bts = func.digest(word.getBytes());
            BigInteger bi = new BigInteger(bts);
            int num = bi.intValue();
            num = Math.abs(num);
            num = num % bitSets.size();
            if (!bitSets.get(num)) return false; 
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(bitSets.size());
        for(int i = 0 ; i < bitSets.length() ; i++){
            str.append(bitSets.get(i)? "1" : "0");
        }
        return str.toString();
    }
}
