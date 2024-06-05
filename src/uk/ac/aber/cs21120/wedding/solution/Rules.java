package uk.ac.aber.cs21120.wedding.solution;

import uk.ac.aber.cs21120.wedding.interfaces.IPlan;
import uk.ac.aber.cs21120.wedding.interfaces.IRules;

import java.util.*;

public class Rules implements IRules {
    private ArrayList<ArrayList<String>> friends = new ArrayList<>(); //ArrayList of ArrayList<String> to save all friends
    private ArrayList<ArrayList<String>> enemies = new ArrayList<>(); //ArrayList of ArrayList<String> to save all the enemies

    /**Function takes in two Strings (a,b).
     *Declares an ArrayList of String named "friendsAb" as a new ArrayList of strings. This then adds
     *Strings a and b as a new ArrayList in friends.#
     *A new ArrayList of Strings named "friendsBa" is declared to save the same information but with first
     * String input as b,a and is then saved as a new ArrayList in friends**/
    public void addMustBeTogether(String a, String b) {
        //initiates a new ArrayList where String "a" and "b" is stored. First String a and then String "b"
        ArrayList<String> friendsAb = new ArrayList<>();
        friendsAb.add(a);
        friendsAb.add(b);
        //adds arraylist to list of friends
        friends.add(friendsAb);
        //initiates a new ArrayList where the reverse is stored. First String "a" and then String "b"
        ArrayList<String> friendsBa = new ArrayList<>();
        friendsBa.add(b);
        friendsBa.add(a);
        //adds arraylist to list of friends
        friends.add(friendsBa);
    }
    /**Function takes in two Strings (a,b).
     *Declares an ArrayList of String named "enemiesAb" as a new ArrayList of strings. This then adds
     *Strings a and b as a new ArrayList in enemies.#
     *A new ArrayList of Strings named "enemiesAb" is declared to save the same information but with first
     * String input as b,a and is then saved as a new ArrayList in enemies**/
    @Override
    public void addMustBeApart(String a, String b) {
        //initiates a new ArrayList where String "a" and "b" is stored. First String a and then String "b"
        ArrayList<String> enemiesAb = new ArrayList<>();
        enemiesAb.add(a);
        enemiesAb.add(b);
        //adds arraylist to list of enemies
        enemies.add(enemiesAb);
        //initiates a new ArrayList where the reverse is stored. First String "a" and then String "b"
        ArrayList<String> enemiesBa = new ArrayList<>();
        enemiesBa.add(b);
        enemiesBa.add(a);
        //adds arraylist to list of enemies
        enemies.add(enemiesBa);


    }

    /**Function to check if the plan is ok and does not breach any rules.
     * Uses a For loop to check every table. Then to saves the guest at each table as string g(for guest).
     * Another For loop checks through the guests at table. If the table does not hold two friends, it returns falls
     * as it would break the rules.
     * If friends are seated together. Another For loop checks weather there are any enemies are seated on the table.
     * If there are any enemies on seated together on a table. It returns False as it would breach the rules.
     * If everything is correct. Friends are seated together and no enemies are seated on the same table. The function
     * returns true and the plan is ok.**/
    @Override
    public boolean isPlanOK(IPlan p) {
        //checks through every table
        for (int i = 0; i < p.getNumberOfTables(); i++) {
            //saves guest at the table as g for guest
            for (String g : p.getGuestsAtTable(i)) {
                //checks through friends lists
                for (int j = 0; j < friends.size(); j++) {
                    //if the first guest at each array list in friends is the same as guest at table
                    if (friends.get(j).get(0).equals(g)) {
                        //and if the corresponding friend is not on the table and there are no more seats left.
                        if (!p.getGuestsAtTable(i).contains(friends.get(j).get(1)) && p.getGuestsAtTable(i).size() == p.getSeatsPerTable()) {
                            return false;
                        }
                    }
                }
                //checks through enemies
                for (int j = 0; j < enemies.size(); j++) {
                    //if a guests  on tha table has an enemy
                    if (enemies.get(j).get(0).equals(g)) {
                        //If the guests enemy is seated on the same table, return false.
                        if (p.getGuestsAtTable(i).contains(enemies.get(j).get(1))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
