package org.skypro.skyshop.search;

import java.util.LinkedList;
import java.util.List;

public class SearchEngene {
    private final LinkedList<Searchable> searchables;
    private int count;

    public SearchEngene() {
        this.searchables = new LinkedList<>();
    }

    public void add(Searchable searchable) {
        searchables.add(searchable);
    }

    public Searchable[] search(String query) {
        List<Searchable> resultsList = new LinkedList<>();

        for (Searchable current : searchables) {
            if (current.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                resultsList.add(current);
                if (resultsList.size() >= 5) {
                    break;
                }
            }
        }

        return resultsList.toArray(new Searchable[0]);
    }

    public Searchable findBestMatch(String searchQuery) throws BestResultNotFound {
        Searchable bestMatch = null;
        int maxCount = -1;

        for (Searchable current : searchables) {
            String searchTerm = current.getSearchTerm().toLowerCase();
            String query = searchQuery.toLowerCase();

            int count = countOccurrences(searchTerm, query);
            if (count > maxCount) {
                maxCount = count;
                bestMatch = current;
            }
        }

        if (maxCount == 0 || bestMatch == null) {
            throw new BestResultNotFound(searchQuery);
        }

        return bestMatch;
    }

    private int countOccurrences(String text, String subString) {
        if (text == null || subString == null || subString.isEmpty() || text.isEmpty()) {
            return 0;
        }
        int occurrences = 0;
        int index = 0;
        int indexOfSubstring = text.indexOf(subString, index);
        while (indexOfSubstring != -1) {
            occurrences++;
            index = indexOfSubstring + subString.length();
            indexOfSubstring = text.indexOf(subString, index);
        }
        return occurrences;
    }

}
