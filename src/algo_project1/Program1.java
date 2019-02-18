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
        /*for(int loc = 0; loc < allocation.getServerSlots().size(); loc++){

        }

        */
        System.out.println(allocation.toString());

        return false; /* TODO remove this line */
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
