package org.skypro.skyshop.search;

public class BestResultNotFound extends Exception {
    private final String searchQuery;

    public BestResultNotFound(String searchQuery) {
        super("Не найдено подходящих результатов для поискового запроса: '" + searchQuery + "'");
        this.searchQuery = searchQuery;
    }

    public String getSearchQuery() {
        return searchQuery;
    }
}
