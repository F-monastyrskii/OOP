package org.skypro.skyshop.search;

import java.util.Comparator;

public class SearchableComparator implements Comparator<Searchable> {

    @Override
    public int compare (Searchable o1, Searchable o2) {
        int length = Integer.compare(o2.getName().length(),o1.getName().length());
        if (length == 0) {
            return o1.getName().compareTo(o2.getName());
        } else {
            return length;
    }
}
}

