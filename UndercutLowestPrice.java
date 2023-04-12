package de.unipassau.simulation;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UndercutLowestPrice {


    private final double stateActionSet[][];
    private final Random rnd;           // Random-number generator for exploration
    private int numberOfEpisodes;       // Number of episodes that have been conducted.
    private int valueForUndercut = 50;       // Holds the lowest prIce / highest quantity set by competing firms in the previous episode.

    /**
     * Constructor of MatchLowPrice algorithm
     */
    UndercutLowestPrice(int seed) {
        stateActionSet = new double[SimulationManager.sizeOfActionSet][SimulationManager.sizeOfActionSet];
        rnd = new Random(seed);
    }

    /**
     * Getter method for state action set
     */
    double[][] getStateActionSet() {
        return stateActionSet;
    }

    /**
     *
     */
    int runEpisode(Integer[] actionsOfOtherFirms) {

        valueForUndercut = getValueForUndercut(actionsOfOtherFirms);

        // Choose A from S using policy.
        int action = selectAction(valueForUndercut);

        numberOfEpisodes++;


        return action;
    }

    /**
     * get the lowest price/quantity of competing firms
     */
    private int getValueForUndercut(Integer[] actionsOfOtherFirms) {
        List<Integer> list = Arrays.asList(actionsOfOtherFirms);
        int result = -1;

        // Get the value set by the Q-Learning agent to match
            int qAction = list.get(0);
            result = qAction;


        return result;
    }

    /**
     * Selects an action equal to the lowest price/quantity set by the other firms in the last period
     */
    private int selectAction(int valueForMatch) {

        int action = 0;

        //for price competition: set the price one value below the price of the Q-Learning agent
        //for quantity competition: set the quantity one value above the quantity of the Q-Learning agent
        if (SimulationManager.COMPETITION_TYPE == Competition.Type.PRICE) {

            action = valueForMatch - 1;

        } else if (SimulationManager.COMPETITION_TYPE == Competition.Type.QUANTITY) {

            action = valueForMatch + 1;

        }
        //make sure to keep the action in range [0,100]
        if (action < 0) {
            action = 0;
        }
        if (action > 100) {
            action = 100;
        }

        //set limit for Undercut Lowest Price with a Minimum Price
        /*if (SimulationManager.COMPETITION_TYPE == Competition.Type.QUANTITY && valueForMatch > 70) {
            action = 70;
        } else if (SimulationManager.COMPETITION_TYPE == Competition.Type.PRICE && valueForMatch < 30) {
            action = 30;
        }*/

        return action;
    }
}

