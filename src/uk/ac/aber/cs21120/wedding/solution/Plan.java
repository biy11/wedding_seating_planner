package uk.ac.aber.cs21120.wedding.solution;

import uk.ac.aber.cs21120.wedding.interfaces.IPlan;

import java.util.*;

public class Plan implements IPlan {
    private int seats; //Integer seats to store number of seats per table
    private int tables; //Integer to store the number of tables at venue
    private  ArrayList<Set<String>> guests = new ArrayList<>();     //Arraylist of Set Strings to store the guests at each table


    /**Constructor for the Plan
     * -Takes in the number of tables and the number of seats for each table
     * -saves seats as seatsPerTable and tables as Number of  tables
     * -Creates new HasSets for each table to be able to store the guests at separate tables  **/
    public Plan(int numberOfTables, int seatsPerTable){
        seats = seatsPerTable;
        tables = numberOfTables;
        //creates a new HasSet for each table
        for(int i=0; i < tables; i++){
            guests.add(new HashSet<>());
        }
    }

    /**function to return the number of seats each table has **/
    @Override
    public int getSeatsPerTable() {
        return seats;
    }
    /**function to return the number of tables at venue**/
    @Override
    public int getNumberOfTables() {
        return tables;
    }

    /**Function takes in an int for the table number and string guest for the guest
     * Uses a for loop to go through each table and if guest is not already placed or there
     * is still space left in the table, it adds guest to table**/
    @Override
    public void addGuestToTable(int table, String guest) {
            if (guests.get(table).size() != seats && !isGuestPlaced(guest)) {
                guests.get(table).add(guest);
        }
    }
    /**Function to remove a guest which takes in a String guest
     * uses a for loop to check all tables to find the table at which the guest is placed
     * if the table contains the guest, it removes it from the table**/
    @Override
    public void removeGuestFromTable(String guest) {
        for (int i = 0; i < tables; i++) {
            if(guests.get(i).contains(guest)){
                guests.get(i).remove(guest);
            }
        }
    }
    /**Function to check weather a guest is placed. Takes in a String of guest.
     * Uses a for loop to go through each table to check if guest is placed in any of the tables.
     * If the guest is found in one of the tables, it returns true.
     * If not found it returns false**/
    @Override
    public boolean isGuestPlaced(String guest) {
        for(int i=0; i < tables; i++){
            if (guests.get(i).contains(guest)){
                return true;
            };
        }
        return false;
    }

    /**Function to return guests at a table.
     * Takes in an int t and uses that to navigate to the table wanted**/
    @Override
    public Set<String> getGuestsAtTable(int t){

        return guests.get(t);
        }
}
