package algo_project1;/*
 * Name: Meyer Dauber
 * EID: mjd3375
 */

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your solution goes in this class.
 *
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 *
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */
public class Program1 extends AbstractProgram1 {
    /**
     * Determines whether a candidate Matching represents a solution to the
     * Stable Marriage problem. Study the description of a Matching in the
     * project documentation to help you with this.
     */
    public boolean isStableMatching(Matching allocation) {

        //first check to see if all slots are full, if not then not stable
        int total_slots = 0;
        for (int i = 0; i < allocation.getUserCount(); i++) {
            if (allocation.getUserMatching().get(i) != -1) {
                total_slots++;
            }
        }
        if (total_slots != allocation.totalServerSlots())
            return false;

        //loop through users in the matching to find one that is assigned
        for(int user = 0; user < allocation.getUserMatching().size(); user++){
            if(allocation.getUserMatching().get(user) != -1){

                //if found assigned user, loop through users to check if it is assigned or not and then compare pref vals
                for(int user_prime = 0; user_prime < allocation.getUserMatching().size(); user_prime++) {
                    if (allocation.getUserMatching().get(user_prime) == -1 && user != user_prime) {

                        //if the user to compare is unassigned, check if the unassigned server has a higher pref value
                        //than the currently assigned one based on the preference list of the current server
                        if (allocation.getServerPreference().get(allocation.getUserMatching().get(user)).indexOf(user) >
                                allocation.getServerPreference().get(allocation.getUserMatching().get(user)).indexOf(user_prime)) {
                            return false;
                        }
                    }
                    //check for second type of instability, where both users are assigned but prefer the other's server
                    else if(user != user_prime){
                        int assigned_server = allocation.getUserMatching().get(user);
                        int assigned_server_prime = allocation.getUserMatching().get(user_prime);

                        //check if s prefers u′ to u
                        if(allocation.getServerPreference().get(assigned_server).indexOf(user_prime) <
                                    allocation.getServerPreference().get(assigned_server).indexOf(user))
                            //check if u′ prefers s to s′
                            if (allocation.getUserPreference().get(user_prime).indexOf(assigned_server) <
                                    allocation.getUserPreference().get(user_prime).indexOf(assigned_server_prime))
                                return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Determines a solution to the Stable Marriage problem from the given input
     * set. Study the project description to understand the variables which
     * represent the input to your solution.
     *
     * @return A stable Matching.
     */
    public Matching stableMarriageGaleShapley(Matching allocation) {
        //final long start = System.nanoTime();
        int unassigned = -1;
        ArrayList<Integer> assigned_slots = new ArrayList<Integer>();
        ArrayList<Integer> temp_matches = new ArrayList<Integer>();

        //create temp user matching array and set all to unassigned
        for(int loc = 0; loc < allocation.getUserCount(); loc++)
            temp_matches.add(unassigned);

        assigned_slots.addAll(allocation.getServerSlots());

        while (server_available(assigned_slots)) {

            //find first available server slot to assign
            int availableServer = 0;
            for(int server =0; server< assigned_slots.size(); server++){
                if(assigned_slots.get(server)>0){
                    availableServer = server;
                }
            }

            //loop through to check all users to propose to
            for(int prefs=0; prefs< allocation.getServerPreference().get(availableServer).size() && assigned_slots.get(availableServer) > 0; prefs++){
                int proposal = allocation.getServerPreference().get(availableServer).get(prefs);

                //if current user is not taken, propose to him
                if(temp_matches.get(proposal)==unassigned){
                    assigned_slots.set(availableServer, assigned_slots.get(availableServer)-1);
                    temp_matches.set(proposal, availableServer);
                }
                //if the user is taken, look through preferences and reassign accordingly
                else {
                    int current_marriage = temp_matches.get(proposal);
                    int proposal_pref_val = allocation.getUserPreference().get(proposal).indexOf(availableServer);
                    int current_pref_val = allocation.getUserPreference().get(proposal).indexOf(current_marriage);

                    if ( current_pref_val > proposal_pref_val) {
                        assigned_slots.set(availableServer, assigned_slots.get(availableServer) - 1);
                        assigned_slots.set(current_marriage, assigned_slots.get(current_marriage) + 1);
                        temp_matches.set(proposal, availableServer);

                    }
                }
            }
        }

        allocation.setUserMatching(temp_matches);
        //final long end = System.nanoTime();
        //System.out.println(end-start + "\n");
        return allocation;
        /*

        While there is a server m who is free and hasn’t proposed to every user
        Choose such a server m
        Let w be the highest-ranked user in m’s preference list
        to whom m has not yet proposed If ~ is free then
        (m, ~) become engaged
        Else ~ is currently engaged to m’
        If ~ prefers m’ to m then m remains free
        Else w prefers m to m’ (m,~) become engaged nlI becomes free
        Endif Endif
        Endwhile
        Return the set S of engaged pairs
        */
    }
    private boolean server_available(ArrayList<Integer> slot_list){
        for (int server = 0; server < slot_list.size(); server++)
            if (slot_list.get(server) != 0)
                return true;
        return false;
    }
}
