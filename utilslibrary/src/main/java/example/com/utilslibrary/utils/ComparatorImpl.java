package example.com.utilslibrary.utils;

import java.util.Comparator;

public class ComparatorImpl implements Comparator<String> {
    
        @Override
        public int compare(String lhs, String rhs) {
            return lhs.compareTo(rhs);
        }
    }