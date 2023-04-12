package de.unipassau.simulation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MatchLowPrice {


    private final double stateActionSet[][];
    private final Random rnd;           // Random-number generator for exploration
    private int numberOfEpisodes;       // Number of episodes that have been conducted.
    private int valueForMatch;       // Holds the price / quantity set by the Q-Learning agent in the previous episode.

    /**
     * Constructor of MatchLowPrice algorithm
     */
    MatchLowPrice(int seed) {
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
     *runs one episode of the MLP algorithm
     */
    int runEpisode(Integer[] actionsOfOtherFirms) {

        valueForMatch = getValueForMatch(actionsOfOtherFirms);

        // Choose A from S using policy.
        int action = selectAction(valueForMatch);

        numberOfEpisodes++;


        return action;
    }

    /**
     * gets the price/quantity set by the Q-agent
     */
    private int getValueForMatch(Integer[] actionsOfOtherFirms) {
        List<Integer> list = Arrays.asList(actionsOfOtherFirms);
        int result = -1;

        // Get the value set by the Q-Learning agent to match
            int qAction = list.get(0);
            result = qAction;


        return result;
    }

    /**
     * Selects an action equal to the price/quantity set by the Q-agent in the last period
     */
    private int selectAction(int valueForMatch) {

        int action;

        action = valueForMatch;

        //set limit for Target Lowest Price with a Minimum Price (optional)
        /*if (SimulationManager.COMPETITION_TYPE == Competition.Type.QUANTITY && valueForMatch > 70) {
            action = 70;
        } else if (SimulationManager.COMPETITION_TYPE == Competition.Type.PRICE && valueForMatch < 30) {
            action = 30;
        }*/

        return action;
    }
}

