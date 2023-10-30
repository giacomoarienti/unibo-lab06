package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private UseListsAndMaps() {
    }

    private static void testPerformanceAdd(List<Integer> list, int elems) {
        /*
         * Prepare a variable for measuring time
         */
        long time = System.nanoTime();
        /*
         * Run the benchmark
         */
        for (int i = 1; i <= elems; i++) {
            list.add(0, (Integer) i);
        }
        /*
         * Compute the time and print result
         */
        time = System.nanoTime() - time;
        final var millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(// NOPMD
            "Adding "
                + elems
                + " ints in a " + list.getClass() + " took "
                + time
                + "ns ("
                + millis
                + "ms)"
        );
    }

    private static void testPerformanceRead(List<Integer> list, int times) {
        /*
         * Prepare a variable for measuring time
         */
        long time = System.nanoTime();
        /*
         * Run the benchmark
         */
        for (int i = 1; i <= times; i++) {
            list.get(list.size()/2);
        }
        /*
         * Compute the time and print result
         */
        time = System.nanoTime() - time;
        final var millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(// NOPMD
            "Reading "
                + times
                + " ints from a " + list.getClass()
                + time
                + "ns ("
                + millis
                + "ms)"
        );
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final List<Integer> arraylist = new ArrayList<>();
        for(int i = 1000; i < 2000; i++) {
            arraylist.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final List<Integer> linkedlist = new LinkedList<>(arraylist);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        final var tmp = linkedlist.get(0);
        linkedlist.set(0, linkedlist.size()-1);
        linkedlist.set(linkedlist.size()-1, tmp);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (Integer el : arraylist) {
            System.out.print(el + ", ");
        }
        System.out.println();
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        testPerformanceAdd(arraylist, 100000);
        testPerformanceAdd(linkedlist, 100000);
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        testPerformanceRead(arraylist, 1000);
        testPerformanceRead(linkedlist, 1000);
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        final Map<String, Long> map = Map.of("Africa", 1110635000L,
                "Americas", 972005000L,
                "Antarctica", 0L,
                "Asia", 4298723000L,
                "Europe", 742452000L,
                "Oceania", 38304000L);
        /*
         * 8) Compute the population of the world
         */
        Long population = 0L;
        for (var localPopulation : map.values()) {
            population = population + localPopulation;
        }
        System.out.println("Population: " + population);
    }
}
