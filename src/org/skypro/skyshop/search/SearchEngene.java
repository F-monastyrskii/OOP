package org.skypro.skyshop.search;

public class SearchEngene {
    private final Searchable[] searchables;
    private int count;

    public SearchEngene(int capacity) throws BestResultNotFound {
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
        if (count == 0) {
            throw new BestResultNotFound(searchQuery);
        }

        Searchable bestMatch = null;
        int maxCount = -1;

        for (int i = 0; i < count; i++) {
            Searchable current = searchables[i];
            String searchTerm = current.getSearchTerm().toLowerCase();
            String query = searchQuery.toLowerCase();

            int count = countOccurrences(searchTerm, query);

            if (count > maxCount) {
                maxCount = count;
                bestMatch = current;
            }

            if (maxCount == 0) {
                throw new BestResultNotFound(searchQuery);
            }
            return bestMatch;
        }
        return bestMatch;
    }

    private int countOccurrences(String text, String subString) {
        if (text == null || subString == null || subString.isEmpty()) {
            return 0;
        }
        return 0;
    }
}
