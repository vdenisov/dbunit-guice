package org.plukh.dbunitguice.util;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multisets;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.fail;

public class TestUtils {
    public static <T> void assertUnorderedCollectionsMatch(Collection<? extends T> first, Collection<? extends T> second) {
        HashMultiset<T> firstSet = HashMultiset.create(first);
        HashMultiset<T> secondSet = HashMultiset.create(second);
        if (!firstSet.equals(secondSet)) fail(getFailMessage(firstSet, secondSet));
    }

    public static <T> void assertOrderedCollectionsMatch(Collection<? extends T> first, Collection<? extends T> second) {
        List<T> firstList = new LinkedList<>(first);
        List<T> secondList = new LinkedList<>(second);
        if (!firstList.equals(secondList)) fail(getFailMessage(firstList, secondList));
    }

    private static <T> String getFailMessage(HashMultiset<? extends T> firstSet, HashMultiset<? extends T> secondSet) {
        return "First and second sets do not match, difference first/second: " +
                Multisets.difference(firstSet, secondSet) + ", second/first: " + Multisets.difference(secondSet,
                firstSet);
    }

    private static <T> String getFailMessage(Collection<? extends T> firstList, Collection<? extends T> secondList) {
        List<T> firstCopy = new LinkedList<>(firstList);
        List<T> secondCopy = new LinkedList<>(secondList);

        return "First and second collections do not match, difference first/second: " +
                firstCopy.removeAll(secondList) + ", second/first: " + secondCopy.removeAll(firstList);
    }
}
