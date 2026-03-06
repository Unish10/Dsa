package questionnumber1;

import java.util.*;

public class wordbreakallsentences {

    public static List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dictionary = new HashSet<>(wordDict);
        Map<String, List<String>> memo = new HashMap<>();
        return backtrack(s, dictionary, memo);
    }

    private static List<String> backtrack(String s, Set<String> dict, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) return memo.get(s);

        List<String> result = new ArrayList<>();

        if (s.length() == 0) {
            result.add("");
            return result;
        }

        for (String word : dict) {
            if (s.startsWith(word)) {
                List<String> sublist = backtrack(s.substring(word.length()), dict, memo);

                for (String sub : sublist) {
                    result.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }

        memo.put(s, result);
        return result;
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> dict = Arrays.asList("cat", "cats", "and", "sand", "dog");

        List<String> sentences = wordBreak(s, dict);

        for (String sentence : sentences) {
            System.out.println(sentence);
        }
    }
}
