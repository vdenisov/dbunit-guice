package org.plukh.dbunitguice.util;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.plukh.dbunitguice.util.TestUtils.assertOrderedCollectionsMatch;
import static org.plukh.dbunitguice.util.TestUtils.assertUnorderedCollectionsMatch;

public class TestUtilsTest {
    private Collection<MyObject> first;
    private Collection<MyObject> second;

    @Before
    public void setUp() throws Exception {
        first = new ArrayList<>();
        second = new ArrayList<>();
    }

    private class MyObject {
        private int primary;
        private int secondary;

        public MyObject(int primary, int secondary) {
            this.primary = primary;
            this.secondary = secondary;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MyObject)) return false;

            MyObject myObject = (MyObject) o;

            return primary == myObject.primary;
        }

        @Override
        public int hashCode() {
            return primary;
        }

        @Override
        public String toString() {
            return "{" +
                    "primary=" + primary +
                    ", secondary=" + secondary +
                    '}';
        }
    }

    @Test
    public void testEqualsInMyObject() throws Exception {
        MyObject one = new MyObject(1, 1);
        MyObject two = new MyObject(1, 2);

        assertEquals(one, two);
    }

    @Test
    public void testHashCodeInMyObject() throws Exception {
        MyObject one = new MyObject(1, 1);
        MyObject two = new MyObject(1, 2);

        assertEquals(one.hashCode(), two.hashCode());
    }

    //Ordered equals() tests

    @Test
    public void testOrderedCollectionsMatch() throws Exception {
        first.add(new MyObject(1, 1));
        second.add(new MyObject(1, 1));

        assertOrderedCollectionsMatch(first, second);
    }

    @Test
    public void testOrderedEmptyCollectionsMatch() throws Exception {
        assertOrderedCollectionsMatch(first, second);
    }

    @Test(expected = AssertionError.class)
    public void testOrderedCollectionsNotMatchOnPrimary() throws Exception {
        first.add(new MyObject(1, 1));
        second.add(new MyObject(2, 2));

        assertOrderedCollectionsMatch(first, second);
    }

    @Test
    public void testOrderedCollectionsMatchOnSecondary() throws Exception {
        first.add(new MyObject(1, 1));
        second.add(new MyObject(1, 2));

        assertOrderedCollectionsMatch(first, second);
    }

    @Test(expected = AssertionError.class)
    public void testOrderedCollectionsNotMatchOnOrder() throws Exception {
        first.add(new MyObject(1, 1));
        first.add(new MyObject(2, 2));
        second.add(new MyObject(2, 2));
        second.add(new MyObject(1, 1));

        assertOrderedCollectionsMatch(first, second);
    }

    //Unordered equals() tests

    @Test
    public void testUnorderedCollectionsMatch() throws Exception {
        first.add(new MyObject(1, 1));
        second.add(new MyObject(1, 1));

        assertUnorderedCollectionsMatch(first, second);
    }

    @Test
    public void testUnorderedEmptyCollectionsMatch() throws Exception {
        assertUnorderedCollectionsMatch(first, second);
    }

    @Test(expected = AssertionError.class)
    public void testUnorderedCollectionsNotMatchOnPrimary() throws Exception {
        first.add(new MyObject(1, 1));
        second.add(new MyObject(2, 2));

        assertUnorderedCollectionsMatch(first, second);
    }

    @Test
    public void testUnorderedCollectionsMatchOnSecondary() throws Exception {
        first.add(new MyObject(1, 1));
        second.add(new MyObject(1, 2));

        assertUnorderedCollectionsMatch(first, second);
    }

    @Test
    public void testUnorderedCollectionsMatchOnDifferentOrder() throws Exception {
        first.add(new MyObject(1, 1));
        first.add(new MyObject(2, 2));
        second.add(new MyObject(2, 2));
        second.add(new MyObject(1, 1));

        assertUnorderedCollectionsMatch(first, second);
    }


}