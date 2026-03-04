import java.util.*;

public class PlagiarismDetectionSystem {

    private HashMap<String, Set<String>> ngramIndex = new HashMap<>();

    public List<String> generateNgrams(String text, int n) {

        String[] words = text.split(" ");
        List<String> grams = new ArrayList<>();

        for (int i = 0; i <= words.length - n; i++) {

            String gram = "";

            for (int j = 0; j < n; j++) {
                gram += words[i + j] + " ";
            }

            grams.add(gram.trim());
        }

        return grams;
    }

    public void indexDocument(String docId, String text) {

        List<String> grams = generateNgrams(text, 3);

        for (String g : grams) {

            ngramIndex.putIfAbsent(g, new HashSet<>());

            ngramIndex.get(g).add(docId);
        }
    }

    public void findMatches(String text) {

        List<String> grams = generateNgrams(text, 3);

        HashMap<String, Integer> matches = new HashMap<>();

        for (String g : grams) {

            if (ngramIndex.containsKey(g)) {

                for (String doc : ngramIndex.get(g)) {

                    matches.put(doc, matches.getOrDefault(doc, 0) + 1);
                }
            }
        }

        System.out.println(matches);
    }

    public static void main(String[] args) {

        PlagiarismDetectionSystem p = new PlagiarismDetectionSystem();

        p.indexDocument("doc1", "this is a simple essay example");
        p.indexDocument("doc2", "this essay example is for testing");

        p.findMatches("this is a simple essay");
    }
}