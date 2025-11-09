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
}
