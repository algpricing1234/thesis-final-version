package de.unipassau.simulation;


import java.util.*;

public class ProportionalTitForTat {

    private final double stateActionSet[][];
    private final Random rnd;                // Random-number generator for exploration
    private  int numberOfEpisodes;           // Number of episodes that have been conducted.
    private  int lastAction = -1;            //last action taken by the firm



    /**
     * Constructor of proportional Tit-for-Tat algorithm
     */
    ProportionalTitForTat(int seed) {
        stateActionSet = new double[SimulationManager.sizeOfActionSet][SimulationManager.sizeOfActionSet];
        rnd = new Random(seed);
    }
    double[][] getStateActionSet() {
        return stateActionSet;
    }

    /**
     * runs one episode of the proportional tit-for-tat algorithm
     */
    int runEpisode(Integer[] actionsOfOtherFirms) {

        int averageValue = getAverageDeviatingAction(actionsOfOtherFirms);
        int j = getJ(actionsOfOtherFirms);

        // Choose A from S using policy.
        int action = selectAction(averageValue, j);

        //store the action that was selected for the next period
        lastAction = action;

        numberOfEpisodes++;


        return action;
    }
    /**
     * Get the number of firms that set the same action as the current firm in the previous period => j
     */

    private int getJ(Integer[] actionsOfOtherFirms) {
        List<Integer> list = Arrays.asList(actionsOfOtherFirms);
        Collections.sort(list);
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            if(lastAction == list.get(i)) {
                j++;
            }
        }
        return j;
    }



    /**
     * Calculate the average action taken by firms that deviated from the action taken in the previous period
     */
    private  int getAverageDeviatingAction(Integer[] actionsOfOtherFirms) {
        List<Integer> list = Arrays.asList(actionsOfOtherFirms);
        Collections.sort(list);
        List<Integer> deviations = new ArrayList<>();
        int result;

        //add all actions by other firms to a list that aren't equal to the last action taken by the firm
        for (int i = 0; i < list.size(); i ++) {
            if (lastAction != list.get(i)) {
                deviations.add(list.get(i));
            }
        }
        //if other firms are deviating, calculate the mean action by those firms
        //to achieve this, the deviating actions are summed up first
        if (deviations.size() > 0) {
            int sum = 0;
            for (int i = 0; i < deviations.size(); i++) {
                sum += deviations.get(i);
            }
            // and then, the mean of the diverging actions is calculated
            result = (int) Math.ceil( (sum / (double) deviations.size()));
        }
        //if all the firms have set the same action in the previous period, return this action
        else{
            result = lastAction;
        }

        return result;
    }


    /**
     * Selects the average action of the other firms in the first period
     * Selects the matching action with probability j / (n-1) and the average deviating action with probability 1 -( j / (n-1) )
     * if j = 0 --> j / (n-1)  becomes 0 and the average deviating action is chosen with a probability of 1
     * if j = (n - 1) --> the matching action is chosen with a probability of 1
     */
    private int selectAction(int averageValue, int j) {
        //Set the same action as the average of the other firms in the first episode
        int newAction;

        if (numberOfEpisodes == 0) {
            newAction = averageValue;
        }
        else {
            //select the same action with probability of j / (n-1), select average deviating action with probability 1 -( j / (n-1) )
            double random = rnd.nextDouble();

            if (random < ( (double) j / (SimulationManager.MARKET_SIZE - 1) )) {
                newAction = lastAction;
            }
            else {
                newAction = averageValue;
            }
        }
        return newAction;
    }
}


