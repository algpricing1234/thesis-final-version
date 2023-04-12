package de.unipassau.simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

abstract class SimulationRun {

    private final List<Firm> firms;         // Array to store firms of the market
    private int numberOfPeriods;            // Number of elapsed periods
    private int numberOfConvergedPeriods;   // Number of periods with unchanged actions (prices/quantities)
    final int[][] seedArray;
    private int simulationRunNumber;

    /**
     * Constructor of a SimulationRun object
     */
    SimulationRun() {
        firms = new ArrayList<>();
        // 250 * 4 random seed array
        seedArray = new int[][]{{89568, 76494, 44494, 7947}, {78649, 37482, 5549, 85497}, {7523, 44045, 93960, 9778}, {33075, 96516, 22842, 88343}, {5656, 62827, 9700, 67555}, {2339, 33173, 9339, 32193}, {60059, 42928, 47356, 70155}, {13169, 43663, 47893, 82954}, {40769, 17174, 78956, 42533}, {44920, 30333, 90980, 44891}, {41498, 67810, 50660, 9528}, {73237, 15616, 14483, 66906}, {12817, 70871, 46536, 38811}, {46957, 5853, 27030, 73974}, {13722, 52368, 84046, 92150}, {54313, 79345, 13139, 74062}, {67191, 17157, 97939, 18150}, {75609, 99931, 10900, 48671}, {68281, 49586, 75864, 60484}, {1272, 38121, 3574, 45450}, {19025, 17006, 96878, 48022}, {76413, 9675, 33866, 68422}, {61829, 37705, 27783, 49717}, {29981, 27908, 57686, 40201}, {43861, 82870, 55542, 50960}, {88469, 59709, 88629, 78727}, {48670, 13481, 94324, 21030}, {79025, 90109, 61780, 66308}, {16931, 1378, 66499, 77905}, {50712, 87844, 9998, 87476}, {93421, 74096, 93563, 1821}, {43113, 14542, 57194, 8995}, {24082, 58171, 29401, 50247}, {10237, 15073, 77645, 69568}, {39609, 84347, 66861, 67732}, {65343, 8143, 70915, 93889}, {32504, 16452, 25507, 46553}, {56444, 33023, 56999, 2266}, {85909, 67757, 79978, 83965}, {50677, 9249, 59088, 88661}, {38281, 97930, 53589, 42376}, {40367, 63453, 87065, 70322}, {22605, 78181, 8987, 66842}, {96071, 16651, 49601, 19834}, {74327, 8888, 23595, 29294}, {46557, 66043, 94352, 30582}, {7620, 14007, 90492, 20237}, {55719, 70920, 60189, 19465}, {69915, 69737, 30983, 73012}, {30110, 91280, 9241, 16107}, {1124, 62236, 27380, 82714}, {78459, 85845, 4062, 5455}, {23064, 84921, 78738, 52989}, {22490, 19873, 38503, 18594}, {22937, 58006, 70213, 77675}, {9757, 33167, 65956, 26176}, {2307, 9317, 8462, 3812}, {84532, 95844, 69284, 53264}, {40137, 97121, 49983, 37792}, {21221, 43057, 1704, 82791}, {68050, 40861, 82803, 70280}, {16721, 23065, 84194, 14012}, {11051, 95796, 20460, 80726}, {18455, 14844, 501, 61173}, {19875, 29586, 92980, 74303}, {45544, 71571, 93724, 56676}, {50235, 67803, 83422, 20380}, {51160, 91410, 68366, 39381}, {18002, 32437, 69176, 51972}, {27370, 97315, 75335, 79473}, {14997, 9544, 80442, 94480}, {76671, 51535, 62163, 97024}, {4033, 10450, 25532, 34563}, {81232, 35494, 31536, 25104}, {45121, 15566, 36755, 29207}, {46799, 69280, 7394, 82531}, {98549, 11988, 70199, 32027}, {44074, 14887, 62497, 92837}, {49610, 40247, 58951, 69071}, {25873, 61199, 62134, 21666}, {98036, 38207, 86836, 5637}, {9345, 59381, 84134, 2671}, {96598, 1545, 50423, 47503}, {9240, 87096, 53775, 34820}, {81573, 26194, 91872, 84909}, {83671, 59502, 15860, 48451}, {83308, 28399, 17395, 39447}, {72716, 23337, 93308, 95814}, {68632, 14324, 38211, 98956}, {2052, 83955, 40746, 84203}, {3538, 86021, 82431, 57283}, {59467, 36540, 38397, 53166}, {8221, 41943, 4793, 69730}, {97314, 40132, 49522, 57429}, {71952, 88926, 5303, 76125}, {50969, 65448, 21386, 98815}, {30868, 83743, 26517, 60093}, {27150, 27980, 70332, 6169}, {84322, 8791, 20384, 2485}, {84897, 57001, 74509, 95493}, {60711, 4679, 28280, 34170}, {26946, 48842, 58711, 39361}, {40048, 6038, 99052, 67478}, {34663, 88400, 45804, 86791}, {15878, 74956, 43495, 40017}, {86801, 73362, 45707, 26068}, {38257, 55455, 25196, 98739}, {72539, 29824, 74451, 74652}, {690, 99411, 1727, 96936}, {87408, 18099, 25774, 82922}, {34611, 90590, 53613, 1564}, {30611, 71350, 34856, 85570}, {42921, 99145, 57545, 28326}, {83942, 91520, 67402, 56775}, {80923, 10992, 35110, 32635}, {55905, 22743, 94387, 82501}, {11506, 94409, 62929, 47877}, {97585, 84532, 8392, 62108}, {26793, 62850, 92866, 26912}, {58403, 26776, 14686, 73779}, {62633, 70716, 62150, 74073}, {96733, 98495, 65394, 56158}, {78856, 71082, 31578, 94840}, {93377, 78901, 34815, 59261}, {74850, 89032, 52011, 57521}, {84892, 31215, 35530, 85696}, {54667, 76564, 78193, 39654}, {46041, 7939, 92937, 79396}, {76228, 86555, 91537, 71963}, {14937, 63360, 12493, 63559}, {32006, 37820, 9417, 55631}, {78191, 10564, 24243, 36981}, {7877, 93363, 90914, 96280}, {27552, 69095, 25132, 28307}, {86414, 86660, 43751, 19091}, {56574, 64797, 87000, 71703}, {875, 96689, 60954, 54152}, {98248, 15350, 58752, 56062}, {94553, 4427, 19608, 65273}, {66526, 51210, 12048, 85973}, {20240, 1150, 48565, 5066}, {86844, 95574, 31298, 41957}, {94342, 80363, 62348, 95413}, {12153, 3694, 64403, 9101}, {61850, 25817, 28008, 56018}, {19249, 97119, 682, 51453}, {53068, 46943, 2149, 857}, {28216, 61203, 76829, 66118}, {52156, 52575, 71670, 96137}, {27921, 78725, 76578, 51078}, {76646, 91584, 48518, 79334}, {2259, 67554, 47402, 66403}, {96191, 13556, 27522, 86203}, {72008, 53171, 32843, 64704}, {76311, 14642, 89370, 10535}, {7248, 68673, 48576, 9563}, {40045, 98518, 69300, 18531}, {92649, 85837, 67233, 28375}, {27574, 60329, 7402, 42775}, {74372, 9835, 83730, 74238}, {95271, 94606, 69740, 51671}, {65460, 7047, 98293, 79563}, {59338, 67243, 4985, 94882}, {72493, 84664, 58611, 36086}, {12734, 25293, 77007, 9433}, {36162, 38263, 87944, 20447}, {50537, 3018, 43742, 60592}, {37336, 49940, 90252, 51308}, {83308, 78827, 86817, 91412}, {79169, 89990, 96612, 15243}, {83762, 26398, 34504, 24824}, {14213, 90743, 71150, 63997}, {52243, 39746, 43604, 1884}, {20440, 33645, 75065, 2422}, {31217, 98521, 33395, 77603}, {50231, 88144, 97754, 67745}, {74844, 50342, 11714, 62004}, {62227, 965, 52726, 69665}, {12279, 16487, 71816, 48969}, {83720, 60581, 91063, 9942}, {36783, 87462, 24567, 79169}, {94907, 62863, 50441, 71339}, {44918, 90241, 11472, 69639}, {31631, 2514, 49534, 35886}, {28820, 27963, 788, 99047}, {19213, 77249, 74394, 26239}, {437, 81813, 7181, 85258}, {78264, 42008, 12961, 33022}, {94859, 29190, 86583, 31349}, {36689, 2703, 81791, 20720}, {28459, 21941, 63928, 78900}, {81895, 85319, 77022, 66007}, {76347, 31435, 44568, 24666}, {7509, 3667, 94694, 86412}, {76701, 46427, 82425, 18713}, {91269, 67661, 53786, 38835}, {48418, 7841, 15842, 23349}, {68861, 93315, 79179, 51419}, {6433, 39518, 62440, 34564}, {3919, 11866, 35380, 24677}, {44275, 39474, 78265, 18347}, {7176, 36204, 69102, 72351}, {34938, 72181, 56741, 99028}, {51563, 81983, 51947, 83053}, {27457, 50440, 71272, 2512}, {32570, 30839, 64676, 65428}, {96692, 65590, 65587, 55301}, {97292, 82506, 28348, 21101}, {48660, 40533, 28051, 48146}, {35773, 69131, 77511, 60487}, {77843, 471, 25656, 72996}, {65709, 51099, 29194, 45482}, {61628, 37421, 87124, 22351}, {49538, 48002, 87400, 22007}, {98348, 12442, 93366, 23698}, {13792, 69618, 47148, 27489}, {4226, 2903, 50714, 85980}, {87482, 90412, 46957, 35925}, {36257, 4598, 57885, 88018}, {99845, 35451, 53265, 62134}, {93099, 9994, 31022, 39691}, {81515, 60186, 84339, 48788}, {6108, 83218, 52857, 99797}, {39269, 76534, 22448, 25341}, {87621, 3308, 59518, 82725}, {52477, 60599, 81470, 79855}, {7953, 90697, 72635, 24626}, {9197, 63187, 93431, 55319}, {68324, 72221, 36941, 38056}, {40520, 53875, 52615, 3766}, {79653, 96398, 4904, 82720}, {19388, 35832, 88378, 89599}, {99529, 66086, 48733, 5155}, {6192, 19111, 38424, 54224}, {91391, 87572, 49033, 13639}, {73840, 30728, 78993, 29363}, {34103, 53591, 71676, 28359}, {68087, 14412, 11300, 7038}, {54027, 98955, 93546, 35559}, {1839, 7039, 48546, 86875}, {3890, 27941, 30015, 97475}, {22857, 97082, 33949, 98876}, {79415, 74274, 66315, 87986}, {77551, 73048, 96907, 11093}, {85566, 26079, 95937, 18339}, {73296, 9296, 47892, 94853}, {70494, 54745, 55600, 89300}, {54314, 91480, 56318, 39351}, {57741, 95754, 69083, 68176}, {78008, 50434, 67682, 66242}};
    }

    /**
     * Carries out simulation periods according to the timing of interaction across the firms.
     */
    public abstract void simulate();

    /**
     * @return clone of a SimulationRun object
     */
    public abstract SimulationRun clone();

    /**
     * @return all firms of this simulation run
     */
    public List<Firm> getFirms() {
        // Initialize firms first if list is still empty
        int randomNum;
        if (firms.isEmpty()) {
            // Create the number of firms according to the MARKET_SIZE and store them in the List
            for (int i = 0; i < SimulationManager.MARKET_SIZE; i++) {
                // nextInt is normally exclusive of the top value,
                // so add 1 to make it inclusive
                //randomNum = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
                randomNum = seedArray[simulationRunNumber][i];
                //System.out.println("Initialize seed " + randomNum + " for run number " + simulationRunNumber +" and firm " + i);
                firms.add(new Firm(this,randomNum));
            }
        }
        return firms;
    }

    /**
     * @return number of elapsed periods
     */
    public int getNumberOfPeriods() {
        return numberOfPeriods;
    }

    /**
     * @param numberOfPeriods number of elapsed periods to be set.
     */
    public void setNumberOfPeriods(int numberOfPeriods) {
        this.numberOfPeriods = numberOfPeriods;
    }

    /**
     * @return number of periods with unchanged actions of all firms
     */
    public int getNumberOfConvergedPeriods() {
        return numberOfConvergedPeriods;
    }

    /**
     *
     * @param numberOfConvergedPeriods  number of periods with unchanged actions of all firms to be set.
     */
    public void setNumberOfConvergedPeriods(int numberOfConvergedPeriods) {
        this.numberOfConvergedPeriods = numberOfConvergedPeriods;
    }

    /**
     * Return Q Matrix of firm k (k=0: firmA, k=1: firmB, k=2, firmC).
     *
     * @return Q matrix of firm k.
     */
    double[][] getQMatrix(int k) {
        double[][] qMatrix = firms.get(k).getQMatrix();
         return  qMatrix;
    }

    /**
     * Calculates the mean price of all firms on the market (= market price).
     *
     * @return market price rounded to 2 decimals.
     */
    double getMeanPrice() {
        List<Double> allPrices = new ArrayList<>();

        for (Firm firm : firms) {
            List<Double> pricesOfFirm = firm.getPrices();
            allPrices.addAll(pricesOfFirm.subList(pricesOfFirm.size() - SimulationManager.sizeOfExaminationInterval, pricesOfFirm.size()));
        }

        return Calculation.round(Calculation.getMean(allPrices), 2);
    }

    public List<Double> getMeanPriceOfFirm() {
        ArrayList<Double> meanPriceOfSingleFirm = new ArrayList<>();

        for (Firm firm : firms) {
            List<Double> pricesOfFirm = firm.getPrices();
            double meanPrice = Calculation.round(Calculation.getMean(pricesOfFirm.subList(pricesOfFirm.size() - SimulationManager.sizeOfExaminationInterval, pricesOfFirm.size())), 2);
            meanPriceOfSingleFirm.add(meanPrice);
        }

        return meanPriceOfSingleFirm;
    }

    /**
     * Calculates the minimum price of all firms on the market (= market price).
     *
     * @return minimum price rounded to 2 decimals.
     */
    double getMinPrice() {
        List<Double> allPrices = new ArrayList<>();

        for (Firm firm : firms) {
            List<Double> pricesOfFirm = firm.getPrices();
            allPrices.addAll(pricesOfFirm.subList(pricesOfFirm.size() - SimulationManager.sizeOfExaminationInterval, pricesOfFirm.size()));
        }

        return Calculation.round(Collections.min(allPrices), 2);
    }

    /**
     * Calculates the maximum price of all firms on the market (= market price).
     *
     * @return maximum price rounded to 2 decimals.
     */
    double getMaxPrice() {
        List<Double> allPrices = new ArrayList<>();

        for (Firm firm : firms) {
            List<Double> pricesOfFirm = firm.getPrices();
            allPrices.addAll(pricesOfFirm.subList(pricesOfFirm.size() - SimulationManager.sizeOfExaminationInterval, pricesOfFirm.size()));
        }

        return Calculation.round(Collections.max(allPrices), 2);
    }

    /**
     * Calculates the standard deviation of the market price.
     *
     * @return standard deviation of market price rounded to 2 decimals.
     */
    double getSDPrice() {
        ArrayList<Double> allPrices = new ArrayList<>();

        for (Firm firm : firms) {
            List<Double> pricesOfFirm = firm.getPrices();
            allPrices.addAll(pricesOfFirm.subList(pricesOfFirm.size() - SimulationManager.sizeOfExaminationInterval, pricesOfFirm.size()));
        }

        return Calculation.round(Calculation.getSD(allPrices), 2);
    }

    /**
     * Calculates the mean quantity of all firms on the market (= market quantity).
     *
     * @return market quantity rounded to 2 decimals.
     */
    double getMeanQuantity() {
        ArrayList<Double> allQuantities = new ArrayList<>();

        for (Firm firm : firms) {
            List<Double> quantitiesOfFirm = firm.getQuantities();
            allQuantities.addAll(quantitiesOfFirm.subList(quantitiesOfFirm.size() - SimulationManager.sizeOfExaminationInterval, quantitiesOfFirm.size()));
        }

        return Calculation.round(Calculation.getMean(allQuantities), 2);
    }
    /**
     * Calculates the mean quantity of a single firm
     */
    public List<Double> getMeanQuantityOfFirm() {
        ArrayList<Double> meanQuantityOfSingleFirm = new ArrayList<>();

        for (Firm firm : firms) {
            List<Double> quantitiesOfFirm = firm.getQuantities();
            double meanQuantity = Calculation.round(Calculation.getMean(quantitiesOfFirm.subList(quantitiesOfFirm.size() - SimulationManager.sizeOfExaminationInterval, quantitiesOfFirm.size())), 2);
            meanQuantityOfSingleFirm.add(meanQuantity);
        }

        return meanQuantityOfSingleFirm;
    }

    /**
     * Calculates the minimum quantity of all firms on the market (= market price).
     *
     * @return minimum quantity rounded to 2 decimals.
     */
    double getMinQuantity() {
        ArrayList<Double> allQuantities = new ArrayList<>();

        for (Firm firm : firms) {
            List<Double> quantitiesOfFirm = firm.getQuantities();
            allQuantities.addAll(quantitiesOfFirm.subList(quantitiesOfFirm.size() - SimulationManager.sizeOfExaminationInterval, quantitiesOfFirm.size()));
        }

        return Calculation.round(Collections.min(allQuantities), 2);
    }

    /**
     * Calculates the minimum quantity of all firms on the market (= market price).
     *
     * @return minimum quantity rounded to 2 decimals.
     */
    double getMaxQuantity() {
        ArrayList<Double> allQuantities = new ArrayList<>();

        for (Firm firm : firms) {
            List<Double> quantitiesOfFirm = firm.getQuantities();
            allQuantities.addAll(quantitiesOfFirm.subList(quantitiesOfFirm.size() - SimulationManager.sizeOfExaminationInterval, quantitiesOfFirm.size()));
        }

        return Calculation.round(Collections.max(allQuantities), 2);
    }

    /**
     * Calculates the mean profit of all firms on the market (= market profit).
     *
     * @return market profit rounded to 2 decimals.
     */
    double getMeanProfit() {
        ArrayList<Double> allProfits = new ArrayList<>();

        for (Firm firm : firms) {
            List<Double> profitsOfFirm = firm.getProfits();
            allProfits.addAll(profitsOfFirm.subList(profitsOfFirm.size() - SimulationManager.sizeOfExaminationInterval, profitsOfFirm.size()));
        }

        return Calculation.round(Calculation.getMean(allProfits), 2);
    }

    /**
     * Calculates the degree of tacit collusion as the ratio of the market price to the price in Nash
     * equilibrium and the one in JPM equilibrium. Nash and JPM equilibria are retrieved for each setting.
     *
     * @return degree of tacit collusion rounded to 3 decimals.
     */
    double getDegreeOfTacitCollusion() {
        double price = getMeanPrice();
        Competition competition = SimulationManager.COMPETITION_TYPE.getCompetition();
        double priceInNashEquilibrium = competition.getPriceInNashEquilibrium(SimulationManager.MARKET_SIZE);
        double priceInJpmEquilibrium = competition.getPriceInJpmEquilibrium();

        return Calculation.round((price - priceInNashEquilibrium) /
                (priceInJpmEquilibrium - priceInNashEquilibrium), 3);
    }
    /**
     * Calculates the mean profit of every firm
     */
    public List<Double> getMeanProfitOfFirm() {
        ArrayList<Double> meanProfitOfSingleFirm = new ArrayList<>();

        for (Firm firm : firms) {
            List<Double> profitsOfFirm = firm.getProfits();
            double meanProfit = Calculation.round(Calculation.getMean(profitsOfFirm.subList(profitsOfFirm.size() - SimulationManager.sizeOfExaminationInterval, profitsOfFirm.size())), 2);
            meanProfitOfSingleFirm.add(meanProfit);
        }

        return meanProfitOfSingleFirm;
    }

    /**
     * Calculates simulation run data in one method
     *
     * @return simulation run data.
     */
    List<Double> getSimulationRunData() {

        List<Double> simulationData = new ArrayList<>();
        List<Double> allPrices = new ArrayList<>();
        ArrayList<Double> allQuantities = new ArrayList<>();
        ArrayList<Double> allProfits = new ArrayList<>();
        ArrayList<Double> meanProfitOfSingleFirm = new ArrayList<>();
        ArrayList<Double> meanPriceOfSingleFirm = new ArrayList<>();
        ArrayList<Double> meanQuantityOfSingleFirm = new ArrayList<>();


        for (Firm firm : firms) {
            List<Double> pricesOfFirm = firm.getPrices();
            allPrices.addAll(pricesOfFirm.subList(pricesOfFirm.size() - SimulationManager.sizeOfExaminationInterval, pricesOfFirm.size()));
            List<Double> quantitiesOfFirm = firm.getQuantities();
            allQuantities.addAll(quantitiesOfFirm.subList(quantitiesOfFirm.size() - SimulationManager.sizeOfExaminationInterval, quantitiesOfFirm.size()));
            List<Double> profitsOfFirm = firm.getProfits();
            allProfits.addAll(profitsOfFirm.subList(profitsOfFirm.size() - SimulationManager.sizeOfExaminationInterval, profitsOfFirm.size()));
            //add the mean profit of all firms to a list
            double meanProfit = Calculation.round(Calculation.getMean(profitsOfFirm.subList(profitsOfFirm.size() - SimulationManager.sizeOfExaminationInterval, profitsOfFirm.size())), 2);
            meanProfitOfSingleFirm.add(meanProfit);
            //add the mean price of all firms to a list
            double meanPrice = Calculation.round(Calculation.getMean(pricesOfFirm.subList(pricesOfFirm.size() - SimulationManager.sizeOfExaminationInterval, pricesOfFirm.size())), 2);
            meanPriceOfSingleFirm.add(meanPrice);
            //add the mean quantity off all firms to a list
            double meanQuantity = Calculation.round(Calculation.getMean(quantitiesOfFirm.subList(quantitiesOfFirm.size() - SimulationManager.sizeOfExaminationInterval, quantitiesOfFirm.size())), 2);
            meanQuantityOfSingleFirm.add(meanQuantity);

        }

        // meanPrice
        double meanPrice = Calculation.round(Calculation.getMean(allPrices), 2);
        simulationData.add(meanPrice);

        //minPrice
        simulationData.add(Calculation.round(Collections.min(allPrices), 2));

        //maxPrice
        simulationData.add(Calculation.round(Collections.max(allPrices), 2));

        //SDPrice
        simulationData.add(Calculation.round(Calculation.getSD(allPrices), 2));

        //mean quantity
        simulationData.add(Calculation.round(Calculation.getMean(allQuantities), 2));

        //minQuantity
        simulationData.add(Calculation.round(Collections.min(allQuantities), 2));

        //maxQuantity
        simulationData.add(Calculation.round(Collections.max(allQuantities), 2));

        //meanProfit
        simulationData.add(Calculation.round(Calculation.getMean(allProfits), 2));

        //degreeOfTacitCollusion
        Competition competition = SimulationManager.COMPETITION_TYPE.getCompetition();
        double priceInNashEquilibrium = competition.getPriceInNashEquilibrium(SimulationManager.MARKET_SIZE);
        double priceInJpmEquilibrium = competition.getPriceInJpmEquilibrium();

        simulationData.add(Calculation.round((meanPrice - priceInNashEquilibrium) /
                (priceInJpmEquilibrium - priceInNashEquilibrium), 3));

        simulationData.add((double) this.numberOfConvergedPeriods);

        //mean profit of all firms
        for (int i = 0; i < meanProfitOfSingleFirm.size(); i ++) {
            simulationData.add(meanProfitOfSingleFirm.get(i));
        }

        //mean prices of all firms
        for (int i = 0; i < meanPriceOfSingleFirm.size(); i ++) {
            simulationData.add(meanPriceOfSingleFirm.get(i));
        }
        //mean quantities of all firms
        for (int i = 0; i < meanQuantityOfSingleFirm.size(); i ++) {
            simulationData.add(meanQuantityOfSingleFirm.get(i));
        }
        
        return simulationData;
    }

    /**
     * Compares actions of all firms in the current and the previous period. If a action is different for at
     * least one firm, false is returned and the method terminates. Otherwise (meaning
     * current variables are identical to the ones of the previous period for all firms), true is returned.
     *
     * @return true if current actions are identical to the ones of the previous period for all firms.
     */
    boolean allActionsOfLastTwoPeriodsAreIdentical(List<Firm> firms) {
        for (Firm firm : firms) {
            // One false is enough to interrupt, since periods have to be identical for all firms
            if (!firm.actionsOfLastTwoPeriodsAreIdentical()) {
                return false;
            }
        }

        return true;
    }

    public void setSimulationRunNumber(int simulationRunNumber) {
        this.simulationRunNumber = simulationRunNumber;
    }

    public int getSimulationRunNumber() {
        return simulationRunNumber;
    }

    enum Timing {
        /*
        Create a new SimulationRun object, when retrieving the enum.
        */
        SIMULTANEOUS(new SimultaneousSimulationRun()), SEQUENTIAL(new SequentialSimulationRun());

        private final SimulationRun simulationRun;

        /**
         * Creates a MarketTiming.Type object from the SimulationRun.
         *
         * @param simulationRun underlying SimulationRun object
         */
        Timing(SimulationRun simulationRun) {
            this.simulationRun = simulationRun;
        }

        /**
         * @return underlying SimulationRun object.
         */
        public SimulationRun getSimulationRun() {
            return simulationRun;
        }
    }
}
