package uk.ac.aber.cs21120.wedding.solution;

import uk.ac.aber.cs21120.wedding.interfaces.IPlan;
import uk.ac.aber.cs21120.wedding.interfaces.IRules;
import uk.ac.aber.cs21120.wedding.interfaces.ISolver;

import java.nio.file.attribute.FileAttribute;
import java.util.HashSet;
import java.util.Set;

public class Solver implements ISolver {
    private final String[] guestList; //guestLists for guestList and saves all guests
    private final IPlan plan; //p for Plan used to reference functions from Plan
    private final IRules rules; //iR for IRules used to reference functions from IRules
    private final Set<String> seatedGuests;

    /**Constructor for the Solver
     * -Takes in a set of String[guests] as guestLists
     * -Takes in a Plan and saves it as p
     * -Takes in IRules and saves is as iR**/
    public Solver(String[] guests, IPlan plan, IRules rules) {
        this.guestList = guests;
        this.plan = plan;
        this.rules = rules;
        this.seatedGuests = new HashSet<>();
    }

    /**The following function is used to solve the plan passed into the constructor.
     * for each table, it checks that for each infilled seat, a guest is placed.
     * If a guest is not placed then it places one. It then checks wif the plan is ok so far.
     * If it is Ok then it recursively goes back to the top and starts over until its no longer valid.
     * If the guest could not fit or the plan could not be solved. The guest is removed from the plan.
     * It will then return false as we could not fit any guest in the seat.
     * Otherwise, return true as all guests have been seated and the result of plan is valid.
     * **/
    @Override
    public boolean solve() {
        //check over every table
        return solveRecursive();
    }

    private boolean solveRecursive() {
        for (int tableIndex = 0; tableIndex < plan.getNumberOfTables(); tableIndex++) {
            if (hasUnfilledSeats(tableIndex)) {
                for (String guest : guestList) {
                    if (tryToSeatGuest(guest, tableIndex)) {
                        if (rules.isPlanOK(plan) && solveRecursive()) {
                            return true;
                        }
                        unseatGuest(guest);
                    }
                }
                return false;
            }
        }
        return allGuestsSeated();
    }

    private boolean hasUnfilledSeats(int tableIndex) {
        int unfilledSeats = plan.getSeatsPerTable() - plan.getGuestsAtTable(tableIndex).size();
        return unfilledSeats > 0;
    }

    private boolean tryToSeatGuest(String guest, int tableIndex) {
        if (!seatedGuests.contains(guest)) {
            plan.addGuestToTable(tableIndex, guest);
            seatedGuests.add(guest);
            return true;
        }
        return false;
    }

    private void unseatGuest(String guest) {
        plan.removeGuestFromTable(guest);
        seatedGuests.remove(guest);
    }

    private boolean allGuestsSeated() {
        return seatedGuests.size() == guestList.length;
    }

}


