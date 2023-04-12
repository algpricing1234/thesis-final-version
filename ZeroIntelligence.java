package de.unipassau.simulation;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ZeroIntelligence {


    private final double stateActionSet[][];
    private final Random rnd;           // Random-number generator for exploration
    private int numberOfEpisodes;       // Number of episodes that have been conducted.

    /**
     * Constructor of ZeroIntelligence algorithm
     */
    ZeroIntelligence(int seed) {
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
     *runs one episode of the ZI algorithm
     */
    int runEpisode(Integer[] actionsOfOtherFirms) {

        int action = getRandomAction();

        numberOfEpisodes++;

        return action;
    }

    /**
     * draws a random number in range [0;100]
     */
    private int getRandomAction() {
        int result = -1;

        // Select random number in range [0,100]

        result = rnd.nextInt(SimulationManager.sizeOfActionSet);

        return result;
    }
}

