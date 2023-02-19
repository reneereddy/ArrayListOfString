// This is a repackaged copy of StaffArrayListOfStringTest
package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListOfStringTest {
    ArrayListOfString listEmpty;
    ArrayListOfString listAB;
    ArrayListOfString list1;
    ArrayListOfString listBA;
    ArrayListOfString listWord;
    ArrayListOfString listDrow;
    ArrayListOfString listNull;
    ArrayListOfString listBigAB;

    @BeforeEach
    public void setup() {
        listEmpty = new ArrayListOfString();
        listAB = new ArrayListOfString();
        listAB.add("A");
        listAB.add("B");
        list1 = new ArrayListOfString();
        list1.add("A");
        list1.add("a");
        list1.add("A");
        list1.add("A");
        listBA = new ArrayListOfString();
        listBA.add("B");
        listBA.add("A");
        listWord = new ArrayListOfString();
        listWord.add("W");
        listWord.add("o");
        listWord.add("r");
        listWord.add("d");
        listDrow = new ArrayListOfString();
        listDrow.add("d");
        listDrow.add("r");
        listDrow.add("o");
        listDrow.add("W");
        listNull = null;
        listBigAB = new ArrayListOfString(20);
        listBigAB.add("A");
        listBigAB.add("B");
    }

    @Test
    public void getCapacityWorksForNewList() {
        assertEquals(10, new ArrayListOfString().getCapacity());
    }

    @Test
    public void sizeWorksForEmptyList() {
        assertEquals(0, new ArrayListOfString().size());
    }

    @Test
    public void sizeWorksForNonEmptyList() {
        assertEquals(2, listAB.size());
        listAB.add("C");
        assertEquals(3, listAB.size());
    }

    @Test
    public void getWorksForLegalIndices() {
        assertEquals("A", listAB.get(0));
        assertEquals("B", listAB.get(1));
    }

    @Test
    public void getThrowsExceptionsForIllegalIndices() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> listAB.get(-1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> listAB.get(2));
    }

    @Test
    public void ensureCapacityGrowsListWhenBigger() {
        assertEquals(10, listAB.getCapacity());
        listAB.ensureCapacity(15);
        assertTrue(listAB.getCapacity() >= 15);
    }

    @Test
    public void ensureCapacityWorksWithSmallerArgument() {
        listAB.ensureCapacity(1);
        assertTrue(listAB.getCapacity() >= 1);
        // Ensure that we can still access element 1.
        assertEquals("B", listAB.get(1));
    }

    @Test
    public void addSingleString() {
        listAB.add("C");
        assertEquals("C", listAB.get(2));
    }

    @Test
    public void addManyStrings() {
        assertEquals(2, listAB.size());
        for (int i = 0; i < 10; i++) {
            listAB.add(String.valueOf(i));
        }
        assertEquals(12, listAB.size());
        assertTrue(listAB.getCapacity() >= listAB.size());
        for (int i = 2; i < 12; i++) {
            assertEquals(i - 2, Integer.valueOf(listAB.get(i)));
        }
    }

    @Test
    public void equalsReturnsTrueForEqualValues() {
        assertEquals(listAB, listAB);
        assertEquals(listEmpty, new ArrayListOfString());
        // Should array lists with different capacities be equal?
        assertEquals(new ArrayListOfString(20), new ArrayListOfString(5));
    }

    @Test
    public void equalsReturnsFalseForUnequalValues() {
        assertNotEquals(listAB, listEmpty);
        assertNotEquals(listEmpty, listAB);
    }

    @Test
    public void reverseEmptyList() {
        listEmpty.reverse();
        assertEquals(new ArrayListOfString(), listEmpty);
    }

    @Test
    public void reverseNonEmptyList() {
        listBA.reverse();
        assertEquals(listAB, listBA);
        listDrow.reverse();
        assertEquals(listWord, listDrow);
        listWord.reverse();
        assertNotEquals(listDrow, listWord);
        listWord.reverse();
        listWord.add(null);
        assertNotEquals(listWord, listDrow);
    }

    @Test
    public void combineEmptyList() {
        assertEquals("", listEmpty.combine());
    }

    @Test
    public void combineNonEmptyList() {
        assertEquals("AB", listAB.combine());
        assertEquals("AaAA", list1.combine());
        assertEquals("Word", listWord.combine());
        listWord.add(null);
        assertEquals("Wordnull", listWord.combine());
    }

    @Test
    public void removeFirstContainsString() {
        assertTrue(listAB.removeFirst("B"));
        assertEquals("A", listAB.combine());
        assertTrue(list1.removeFirst("A"));
        assertEquals("aAA", list1.combine());
        assertEquals("a", list1.get(0));
        assertTrue(listBigAB.removeFirst("B"));
        assertEquals("A", listBigAB.combine());
    }

    @Test
    public void removeFirstContainsNull() {
        listWord.add(null);
        assertEquals(5, listWord.size());
        assertEquals("Wordnull", listWord.combine());
        assertTrue(listWord.removeFirst(null));
        assertEquals(4, listWord.size());
        assertEquals("Word", listWord.combine());
        listWord.add(null);
        listWord.add(null);
        assertEquals(6, listWord.size());
        assertEquals("Wordnullnull", listWord.combine());
        assertTrue(listWord.removeFirst(null));
        assertEquals("Wordnull", listWord.combine());
        assertEquals(5, listWord.size());
        assertNull(listWord.get(4));
    }

    @Test
    public void removeFirstDoesNotContainString() {
        assertFalse(listAB.removeFirst(" "));
        assertFalse(listAB.removeFirst("a"));
        assertFalse(listAB.removeFirst(""));
    }

    @Test
    public void removeAllContainsString() {
        assertEquals(0, listAB.removeAll("C"));
        assertEquals(3, list1.removeAll("A"));
    }

    @Test
    public void removeAllContainsNull() {
        listWord.add(null);
        assertEquals(5, listWord.size());
        assertEquals(1, listWord.removeAll(null));
        assertEquals(4, listWord.size());
        listWord.add(null);
        listWord.add(null);
        assertEquals(6, listWord.size());
        assertEquals(2, listWord.removeAll(null));
        assertEquals(4, listWord.size());
    }

    @Test
    public void removeAllDoesNotContainsString() {
        assertNotEquals(1, listAB.removeAll("b"));
        assertNotEquals(4, list1.removeAll("a"));
    }

}

