package student;

import com.google.common.annotations.VisibleForTesting;

import java.util.Objects;

public class ArrayListOfString {
    // If this constant is changed, also change the constructor javadoc.
    private static final int INITIAL_CAPACITY = 10;

    // The number of strings in the array, not the size of the array
    protected int size;

    protected String[] array;

    @VisibleForTesting
    public int getCapacity() {
        return array.length;
    }

    /**
     * Creates an empty list with an initial capacity of ten.
     */
    public ArrayListOfString() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Creates an empty instance with the specified initial capacity.
     * Providing a capacity improves efficiency by avoiding unnecessary work
     * as elements are added.
     *
     * @param initialCapacity the initial capacity of the array
     */
    public ArrayListOfString(int initialCapacity) {
        array = new String[initialCapacity];
        size = 0;
    }

    /**
     * Gets the number of strings in this list.
     *
     * @return the number of strings in this list.
     */
    public int size() {
        return size;
    }

    /**
     * Gets the string at the specified index.
     *
     * @param index the 0-based index
     * @return the string at the specified index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public String get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d is out of bounds for list of size %d",
                            index, size()));
        }
        return array[index];
    }

    /**
     * Increases the capacity of this list, if necessary, to ensure that it can
     * hold at least the number of elements specified by the minimum capacity
     * argument.
     * <p>
     * If the argument is less than the current capacity, no change will be
     * made.
     *
     * @param minCapacity the new minimum capacity
     */
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > getCapacity()) {
            // Allocate a bigger array.
            String[] newArray = new String[minCapacity];

            // Copy all elements of the old array into the new array.
            System.arraycopy(array, 0, newArray, 0, array.length);

            // Make the instance variable point to the new array.
            array = newArray;
        }
    }

    private boolean isFull() {
        return size == getCapacity();
    }

    private void doubleCapacity() {
        ensureCapacity(2 * getCapacity());
    }

    /**
     * Adds the given string at the end of this list.
     *
     * @param s the string to append
     */
    public void add(String s) {
        if (isFull()) {
            doubleCapacity();
        }
        array[size++] = s;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ArrayListOfString otherList)) {
            return false;
        }
        if (this.size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!Objects.equals(this.get(i), otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Reverses the order of the elements in this list.
     */
    public void reverse() {
        String[] reversed = new String[array.length];
        for (int i = this.size() - 1; i >= 0; i--) {
            reversed[this.size() - i - 1] = array[i];
        }
        if (this.size >= 0) {
            System.arraycopy(reversed, 0, array, 0, this.size);
        }
    }

    /**
     * Returns the concatenation of all the strings in this list, in order.
     *
     * @return the concatenation of all the strings in this list, or empty
     * string if the list is empty
     */
    public String combine() {
        StringBuilder combined = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            combined.append(this.get(i));
        }
        return combined.toString();
    }

    /**
     * Removes the first occurrence of the given string from this list.
     * If this list does not contain the string, no change is made.
     *
     * @param s the string to remove
     * @return true if the string was removed, false otherwise
     */
    public boolean removeFirst(String s) {
        String[] newStr = new String[array.length];
        for (int i = 0; i < size(); i++) {
            if ((s == null && array[i] == null) || Objects.equals(array[i], s)) {
                if (i != size() - 1) {
                    System.arraycopy(array, i + 1, newStr, i, size() - (i + 1));
                }
                array = newStr;
                size--;
                return true;
            }
            newStr[i] = array[i];
        }
        return false;
    }

    /**
     * Removes all occurrences of the given string from this list.
     * If this list does not contain the string, no change is made.
     *
     * @param s the string to remove
     * @return the number of copies of the string that were removed.
     */
    public int removeAll(String s) {
        int count = 0;
        while (removeFirst(s)) {
            count++;
        }
        return count;
    }
}
