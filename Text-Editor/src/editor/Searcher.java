package editor;

import java.util.*;
import java.util.regex.*;

class Searcher { 

    class SearchResult {
        int startInclusive;
        int endExclusive;
        SearchResult (int startInclusive, int endExclusive) {
            this.startInclusive = startInclusive;
            this.endExclusive = endExclusive;
        }
        @Override
        public String toString() {
            return "[" + startInclusive + "," + endExclusive + "]";
        }
    }
    
    private final long mod = 1_000_000_000 + 9;
    private final int pow = 53;
    
    private long charToLong(char ch) {
        return ch + 1;
    }
    
    private long getRKHash(String input){
        long hash = 0;
        long tempPow = 1;
        for (int i = 0; i < input.length(); i++) {
            hash += charToLong(input.charAt(i)) * tempPow;
            hash %= mod;
            tempPow = (tempPow * pow) % mod;
        }
        return hash % mod;
    }
    
    private long[] getPrefixHashes(String input) {
        long[] result = new long[input.length() + 1];
        result[0] = 0L;

        long hash = 0;
        long tempPow = 1;
        for (int i = 0; i < input.length(); i++) {
            hash += charToLong(input.charAt(i)) * tempPow;
            hash %= mod;
            result[i + 1] = hash;
            tempPow = (tempPow * pow) % mod;
        }
        return result;
    }

    public List<SearchResult> stringSearch(String input, String searchTerm){
        List<SearchResult> results = new ArrayList<>();
        if (input == null || searchTerm == null){
            return results;
        }

        final long[] prefixes = getPrefixHashes(input);
        final int inputLength = input.length();
        final int searchTermLength = searchTerm.length();
              
        long substringHash = prefixes[searchTerm.length()];
        long patternHash = getRKHash(searchTerm);
        long mult = 1;
        for (int i = 0; i <= inputLength - searchTermLength; i++){
            substringHash = (prefixes[i + searchTermLength] - prefixes[i] + mod) % mod;
            patternHash = (getRKHash(searchTerm) * mult) % mod;
            if (patternHash == substringHash){
                for (int j = 0; j < searchTermLength; j++){
                    char a = searchTerm.charAt(j);
                    char b = input.charAt(i + j);
                    if (a != b){
                        break;
                    }
                }
                results.add(new SearchResult(i, i + searchTermLength));   
            }            
            mult = (mult * pow) % mod;
        }
        return results;
    }

    public List<SearchResult> regexSearch(String input, String searchTerm){
        List<SearchResult> allMatches = new ArrayList<>();
        Matcher m = Pattern.compile(searchTerm).matcher(input);
        while (m.find()) {
          allMatches.add(new SearchResult(m.start(), m.end()));
        }        
        return allMatches;
    }
}
