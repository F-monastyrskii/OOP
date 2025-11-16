package org.skypro.skyshop.search;

public class SearchEngene {
    private final Searchable[] searchables;
    private int count;

    public SearchEngene(int capacity) {
        this.searchables = new Searchable[capacity];
        this.count = 0;
    }

    public void add(Searchable searchable) {
        if (count < searchables.length) {
            searchables[count] = searchable;
            count++;
        } else {
            System.out.println("Поисковый движок заполнен");
        }
    }

    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int resultsCount = 0;

        for (int i = 0; i < count && resultsCount < 5; i++) {
            Searchable current = searchables[i];
            if (current.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results[resultsCount] = current;
                resultsCount++;
            }
        }

        return results;
    }

    public Searchable findBestMatch(String searchQuery) throws BestResultNotFound {
        Searchable bestMatch = null;
        int maxCount = -1;

        for (Searchable current : searchables) {
            if (current == null)
                continue;

            String searchTerm = current.getSearchTerm().toLowerCase();
            String query = searchQuery.toLowerCase();

            int count = countOccurrences(searchTerm, query);
            if (count > maxCount) {
                maxCount = count;
                bestMatch = current;
            }
        }

        if (maxCount == 0) {
            throw new BestResultNotFound(searchQuery);
        }

        return bestMatch;
    }

    private int countOccurrences(String text, String subString) {
        if (text == null || subString == null || subString.isEmpty() || text.isEmpty()) {
            return 0;
        }
        int index = 0;
        int indexOfSubstring = text.indexOf(subString, index);
        while (indexOfSubstring != -1) {
            count++;
            index = indexOfSubstring + subString.length();
            indexOfSubstring = subString.indexOf(index);
        }
        return index;
    }

}
