package de.unipassau.simulation;


import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class SimultaneousSimulationRun extends SimulationRun {

    /**
     * Carries out simulation periods by running an episode for all firms and then setting these actions
     * (prices or quantities). All remaining firm data is calculated and the Q-Learning algorithms are updated once
     * all actions of a period were observed. Finally, it is checked whether the firm's actions have changed compared
     * to the previous period. If no termination condition is fulfilled, the next period will be simulated.
     */
    @Override
    public void simulate() {
        List<Firm> firms = super.getFirms();
        ArrayList<String> data = new ArrayList<String>();

        // Array to store periodical actions of all firms.
        int[] actions = new int[SimulationManager.MARKET_SIZE];

        /*
        Run simulation periods as long as no termination condition is fulfilled:
            - the actual number of periods of this simulation run (numberOfPeriods)
                has to be below its upper limit (maxNumberOfPeriods); and
            - the actual number of converged periods (numberOfConvergedPeriods)
                has to be below its upper limit (minNumberOfConvergedPeriods)
        */
        while (super.getNumberOfPeriods() < SimulationManager.maxNumberOfPeriods
                && super.getNumberOfConvergedPeriods() < SimulationManager.minNumberOfConvergedPeriods) {



            actions[0] = firms.get(0).chooseActionQ();
            for (int i = 1; i < SimulationManager.MARKET_SIZE; i++) {
                // cause a deviation with the if-statement

                /*if (getNumberOfPeriods() == 51000005) {
                    //pro-competitive deviation
                        //for quantity competition:

                    //actions[1] = 67;

                        // for price competition:

                    //actions[1]=33;

                    //pro collusive deviation:

                    actions[1]=50;
                } else {*/
                /**
             * Selection of the algorithms:
             * The first firm runs the Q-Learning algorithm while the other firms use a rule-based algorithm or another version of Q-Learning
             * select the second algorithm by commenting out the others in the else clause
             */
                    actions[i] = firms.get(i).chooseActionMLP();
                    //actions[i] = firms.get(i).chooseActionULP();
                    //actions[i] = firms.get(i).chooseActionZI();
                    //actions[i] = firms.get(i).chooseActionBR();
                    //actions[i] = firms.get(i).chooseActionQ();
                    //actions[i] = firms.get(i).chooseActionPTFT();
                }

            //}

            // Set all buffered and possibly updated actions.
            for (int i = 0; i < SimulationManager.MARKET_SIZE; i++) {
                firms.get(i).setAction(actions[i]);
            }

            // Calculate all remaining data and update the Q-Learning once the new actions were observed.
            for (Firm firm : firms) {
                firm.calculateData();

                // Do not update the Q-Learning algorithms until every firm has chosen an action at least twice.
                //only update the firms that use the Q-Learning algorithm
                if (super.getNumberOfPeriods() > 0) {
                    firm.updateQlearning();
                }


                if (super.getNumberOfPeriods() >= 51000000 && super.getNumberOfPeriods() <= 51000050) {
                    for (Firm firm1 : firms) {

                        data.add("number of periods: " + super.getNumberOfPeriods() +
                                        ", action: " + (firm1.getAction())
                                //+
                                //      ", profits: " + firm1.getProfits()
                        );
                    }
                }
            }
            // Check if all firms' current actions are equal to their previous ones.
            if (allActionsOfLastTwoPeriodsAreIdentical(firms)) {
                super.setNumberOfConvergedPeriods(super.getNumberOfConvergedPeriods() + 1);
            } else {
                super.setNumberOfConvergedPeriods(0);
            }

            super.setNumberOfPeriods(super.getNumberOfPeriods() + 1);

        }
            // For analysis: Write csv file for ArrayList "data"
            try {

                File csvFileActions = new File("out/SIM-Q-2/AllActions_RunNo_" + getSimulationRunNumber() + ".csv");
                PrintWriter out = new PrintWriter(csvFileActions);
                for (String action : data) {
                    out.println(action);
                }
                out.close();
            } catch (Exception ex) {

            }
        }


        /**
         * @return new SimultaneousSimulationRun object
         */
        @Override
        public SimulationRun clone () {
            return new SimultaneousSimulationRun();
        }
    }


