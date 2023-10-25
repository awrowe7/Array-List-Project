/**
 * This file contains a MyArrayList class used to create, structure, organize, add to, remove from, and access an array of data. 
 * 
 * @author Aiden Rowe 
 * 
 * This file is a part of a lab project from Oberlin College that was assigned in Professor Adam Eck's Data Structures 151 class on Oct 5, 2023. 
 * 
 * This file was authored in its entirety by Aiden Rowe but uses instructions contributed to by the following faculty members in the Oberlin College Computer Science Department: Stephen Checkoway, Adam Eck, Molly Q Feldman, Blair Rossetti, Alexa Sharp, Sam Taggart, Cynthia Taylor, Emily Wang, Tom Wexler, Lucas Draper. The lab instructions were also contributed to by the following Oberlin Students: Veronica Ayars, Hannah Babe, Tara Bobinac, Meg Davis, Eliza Bomfim Guimaraes Giane, William Knowles-Kellett, Pascale Leone, Mist Newman, Marilyn Recarte, Shiying Zheng.
*/

// IMPORTS 
import java.util.AbstractList;

// -------------------------------------------------------------------

// MY ARRAY LIST CLASS (implements ABSTRACT LIST)
public class MyArrayList<T> extends AbstractList<T> {

    // CLASS VARIABLES
    /* Sets the two variables that'll be used across the class */
    private T[] items; // Array for storing items contained in ArrayList
    private int size; // Current number of items in ArrayList

    // -------------------------------------------------------------------

    // MAIN (USED FOR TESTING)
    public static void main(String[] args){
        /*  
          * Used for initial testing. Makes two new arrays, one of length 5 and one of length 0. Then checks each of their sizes is correctly returns by the size method
          * 
          * Input: Command line arguments as an array of Strings 
          * Output: Nothing
        */

        // Setup
        MyArrayList<Integer> test1 = new MyArrayList<>(5);
        MyArrayList<Integer> test2 = new MyArrayList<>();

        // Testing
        System.out.println(test1.size());
        System.out.println(test2.size());
    }

    // -------------------------------------------------------------------

    // CONSTRUCTOR 1
    @SuppressWarnings("unchecked")
    public MyArrayList(int startLength){
        /*
         * Sets the items to a new array of the input length, and sets the size to 0 for each instance of this class
         * 
         * Input: A length input by the user (int)
         * Output: Nothing
        */

        // Setup
        this.items = (T[]) new Object[startLength];
        this.size = 0;
    }

    // -------------------------------------------------------------------

    // CONSTRUCTOR 2
    public MyArrayList() {
        /* 
         * Creates an instance of the class with an array of length 2 and a size of 0
         * 
         * Input: Nothing
         * Output: Nothing 
        */

        // Setup
        this(2);
    }

    // -------------------------------------------------------------------

    // ARRAY SIZE
    public int size() {
        /*
         * Returns the number of items currently stored in the array
         *
         * Input: Nothing
         * Output: Size of the items in the array
        */

        // Return
        return this.size;
    } 

    // -------------------------------------------------------------------

    // RESIZER 
    @SuppressWarnings("unchecked")
    private void resize(){
        /* 
         * Doubles the size of the current item, makes an array of that doubled size, copies over all values, then assigns this new array to this.item
         * 
         * Input: Nothing
         * Output: Reassigns a new array to this.item that's double the length with all the same values. Nothing explicitely returned
        */
        
        // New Array
        T[] newArray = (T[]) new Object[this.items.length * 2];

        // Old Copy
        for (int i = 0; i < this.items.length; i++) {
            newArray[i] = this.items[i];
        }

        // Setting
        this.items = newArray;
    }

    // -------------------------------------------------------------------

    // ADDER
    public void add(int index, T item) {
        /*
         * Adds an inputted item into the array at the input index 
         * 
         * Input: The index (int) and the actual item (T)
         * Output: Nothing
        */
        
        // Checking Index In Range 
        /* Checks if the index is in the range of the current array. If so, moves on. If not, doubles array size */
        if (0 <= index && index <= this.size) {
            
            // Extra Space 
            /* Checks to see if there's extra space in the array */
            if (this.size < this.items.length){
                
                // Index Already Assigned Checker 
                /* Checks if the index requested already has items. If it does, shifts all items over one place. If not, assigns item to index */
                if (index < this.size){ // Index has content 
                    for (int i = this.size; i > index; i--){
                        this.items[i] = this.items[i - 1]; 
                    }
                    this.items[index] = item;
                    this.size++; // Increases size
                } else { // Index is new
                    this.items[index] = item;
                    this.size++; // Increases size
                }
            } else if (this.size == this.items.length) {

                // Resizer- In the case that the index is out of range for the current array 
                resize();
                this.items[index] = item;
                this.size++; // Increases size
            }
        } else {

            // Error Message
            throw new IndexOutOfBoundsException("Tried to add an item to a MyArrayList with too large of an index. Index:" + index + " but size is " + this.size); // Throws an error when the index is not between 0 and the current length 
        }
    }

    // -------------------------------------------------------------------

    // END-OF-ARRAY ADDER
    public boolean add(T item) {
        /*
         * Calls the other Add method with inputted item and with index of the current size (being the number that'll be used to input the item under)
         * 
         * Input: The item (T)
         * Ouput: The Boolean "true" 
        */

        // Adding
        /* Calls the add function with the size as the index */
        add(this.size, item);

        // Return 
        return true;
    }

    // -------------------------------------------------------------------

    // ITEM GETTER 
    public T get(int index) {
        /* 
         * Gets a requested item from the array. Checks to make sure the index is in the array first and throws an error if not
         * 
         * Input: Index of the requested item
         * Output: Returns the item (T)
        */

        // Index in Range Checker 
        if (0 <= index && index <= this.size - 1) { //Ensures the Index is not less than 0 or greater than the index of the last item
            
            // Returning Requested Item
            return this.items[index];
        } else {

            // Error
            throw new IndexOutOfBoundsException("Tried to get an item from MyArrayList with too large of an index. Index:" + index + " but size is " + this.size); // Throws an error when the index is not in the current array   
        }
    }

    // -------------------------------------------------------------------

    // REPLACER
    public T set(int index, T item) {
        /*
         * Changes the item stored at a given index. Then it returns the old item that used to be stored at that index 
         * 
         * Input: The requested index (int) and the item (T)
         * Output: The old item (T) at the given index
        */

        // Index in Range Checker
        if (0 <= index && index <= this.size - 1) {

            // Old Storage 
            /* Saves the old item for returning later */
            T oldItem = this.items[index];

            // Change 
            /* Reassigns the item at the index */
            this.items[index] = item;

            // Return
            /* Returns old item */
            return oldItem;
        } else {

            // Error
            throw new IndexOutOfBoundsException("Tried to set an item from MyArrayList with too large of an index. Index:" + index + " but size is " + this.size); // Throws an error when the index is not in the current array   
        }
    }

    // -------------------------------------------------------------------

    // REMOVER 
    public T remove(int index) {
        if (0 <= index && index <= this.size - 1) {
            
            // Old Storage 
            /* Saves the old item for returning later */
            T oldItem = this.items[index];

            // Shift 
            /* Shifts all the items down one and then decreases the size by 1 */
            for (int i = index; i < this.size - 1; i++) {
                this.items[i] = this.items[i + 1];
            }
            this.size--;

            // Return
            /* Returns the item that was removed */
            return oldItem;
            
        } else {
            throw new IndexOutOfBoundsException("Tried to remove an item from MyArrayList with too large of an index. Index:" + index + " but size is " + this.size); // Throws an error when the index is not in the current array   
        }
    }

    // -------------------------------------------------------------------

    // Empty? 
    public boolean isEmpty() {
        /*
         * Checks if the array is empty or not. 
         * 
         * Input: Nothing
         * Output: Returns a Boolean that signifies if the array is empty or not
        */

        // Checking Emptiness 
        boolean empty = true; 
        if (this.size != 0) { 
            empty = false;
        }

        // Return
        return empty;
    }

    // -------------------------------------------------------------------

    // Remove All Items 
    public void clear() {
        /*
         * Clears the entire array of all items 
         * 
         * Input: Nothing
         * Output: Nothing 
        */

        // Clearing Every Item
        for (int i = 0; i <= this.size; i++) {
            this.items[i] = null;
        }

        // Sets Size to 0
        this.size = 0;
    }
}