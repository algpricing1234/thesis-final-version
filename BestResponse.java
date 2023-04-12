package de.unipassau.simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BestResponse {
    private int valueForBestResponse = 50;
    private int numberOfEpisodes;
    private final double stateActionSet[][];
    private final Random rnd;           // Random-number generator for exploration
    BestResponse(int seed) {
        stateActionSet = new double[SimulationManager.sizeOfActionSet][SimulationManager.sizeOfActionSet];
        rnd = new Random(seed);
    }
    /**
     * Calculate the profit in price / quantity competition depending on the own action (price/quantity)
     * and the action set by the other firm in the last period
     * (including the scaling factors)
     */
    private static double profitInQuantityCompetition(int ownQuantity, int actionsOfOtherFirms) {

        double profit = (100 - (0.6 * ownQuantity + (2.0 / 3.0) * 0.6 * actionsOfOtherFirms)) * ownQuantity;
        return profit;
    }
    private static double profitInPriceCompetition(int ownPrice, int actionsOfOtherFirms) {
        double profit = (60 - 1.8 * ownPrice + 1.2 * actionsOfOtherFirms) * ownPrice;
        return profit;
    }
    /**
     *  Runs one episode of the best response algorithm
     */

    int runEpisode(Integer[] actionsOfOtherFirms) {

        valueForBestResponse = getValueForBestResponse(actionsOfOtherFirms);

        // Choose A from S using policy.
        int action = selectAction(valueForBestResponse);

        numberOfEpisodes++;


        return action;
    }

    /**
     * get the price/quantity of Q-Learning agent
     */
    private int getValueForBestResponse(Integer[] actionsOfOtherFirms) {
        List<Integer> list = Arrays.asList(actionsOfOtherFirms);
        int result = -1;

        // Get the value set by the Q-Learning agent to match
        int qAction = list.get(0);
        result = qAction;


        return result;
    }

    /**
     * Finds the best response for the quantity set by the Q-Learning firm
     * The loop tries out every possible action and compares the profit
     * If the profit is higher than in a previous action, it is stored as the maxProfit together with the action maximizing the profit
     * After iterating through the whole action set, the maxQuantity that promises the highest profit is returned
     */

    public static int bestResponseQuantity(int valueForBestResponse) {


            double maxProfit = -1;
            int maxQuantity = 0;
            for (int ownQuantity = 0; ownQuantity <= 100; ownQuantity++) {
                double profit = profitInQuantityCompetition(ownQuantity, valueForBestResponse);
                if (profit > maxProfit) {
                    maxProfit = profit;
                    maxQuantity = ownQuantity;
                }
            }
            int bestResponseToOtherFirmQuantity = maxQuantity;

        return bestResponseToOtherFirmQuantity;
    }

    /**
     * Finds the best response for the price set by the Q-Learning firm
     * The loop tries out every possible action and compares the profit
     * If the profit is higher than in a previous action, it is stored as the maxProfit together with the action maximizing the profit
     * After iterating through the whole action set, the maxPrice that promises the highest profit is returned
     */
    public static int bestResponsePrice(int valueForBestResponse) {


            double maxProfit = -1;
            int maxPrice = 0;
            for (int ownPrice = 0; ownPrice <= 100; ownPrice++) {
                double profit = profitInPriceCompetition(ownPrice, valueForBestResponse);
                if (profit > maxProfit) {
                    maxProfit = profit;
                    maxPrice = ownPrice;
                }
            }
            int bestResponseToOtherFirmPrice = maxPrice;

        return bestResponseToOtherFirmPrice;
    }

    /**
     * the action is selected depending on the form of competition
     */

    private int selectAction(int valueForMatch) {
        int action = -1;
        if (SimulationManager.COMPETITION_TYPE == Competition.Type.QUANTITY) {
            action = bestResponseQuantity(valueForMatch);
        }
        else if (SimulationManager.COMPETITION_TYPE == Competition.Type.PRICE) {
            action = bestResponsePrice(valueForMatch);
        }
        return action;
    }

    //test
    public static void main(String[] args) {
        System.out.println(bestResponseQuantity(63));
        System.out.println(bestResponsePrice(27));
        //System.out.println(allBestResponsePricesInRange());
    }
    // method to create a list of the best responses (here for price competition)
    public static List<Integer> allBestResponsePricesInRange() {

        List<Integer> allPrices = new ArrayList<>();

        for (int valueForBestResponse = 0; valueForBestResponse <= 100; valueForBestResponse++) {
            int bestPrice = bestResponsePrice(valueForBestResponse);
            allPrices.add(bestPrice);
        }

        return allPrices;
    }
}

