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

        //loop through users in the matching to find one that is assigned
        for(int user = 0; user < allocation.getUserMatching().size(); user++){
            if(allocation.getUserMatching().get(user) != -1){

                //if found assigned user, loop through users to check if it is assigned or not and then compare pref vals
                for(int user_compare = 0; user_compare < allocation.getUserMatching().size(); user_compare++) {
                    if (allocation.getUserMatching().get(user_compare) == -1 && user != user_compare) {

                            //if the user to compare is unassigned, check if the unassigned server has a higher pref value
                            //than the currently assigned one based on the preference list of the current server
                            if (allocation.getServerPreference().get(allocation.getUserMatching().get(user)).indexOf(user) >
                                    allocation.getServerPreference().get(allocation.getUserMatching().get(user)).indexOf(user_compare)) {
                                return false;
                            }
                    }
                    //NEED TO ADD IF??????????
                    else if( user != user_compare){
                        //if the user to compare is assigned, loop through the servers to see if there is a user who prefers
                        for (int server = 0; server < allocation.getServerCount(); server++) {
                            if (allocation.getUserPreference().get(user).indexOf(server) >
                                    allocation.getUserPreference().get(user_compare).indexOf(server)) {
                                return false;
                            }
                        }
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
        /*
        While there is a man m who is free and hasn’t proposed to every woman
        Choose such a man m
        Let w be the highest-ranked woman in m’s preference list
        to whom m has not yet proposed If ~ is free then
        (m, ~) become engaged
        Else ~ is currently engaged to m’
        If ~ prefers m’ to m then m remains free
        Else w prefers m to m’ (m,~) become engaged nlI becomes free
        Endif Endif
        Endwhile
        Return the set S of engaged pairs
        */
        return null; /* TODO remove this line */
    }
}
