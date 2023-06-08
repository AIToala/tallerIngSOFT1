// Copyright (C) 2020
// All rights reserved
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.InputMismatchException;
/**VacationCost.
 * @author ai_to
 */
public final class VacationCost {
    /**baseCost.
     */
    private final double baseCost = 1000;
    /**subCost.
     */
    private double subCost;
    /**penaltyFee.
     */
    private final double penaltyFee = 200;
    /**promotionPolicy.
     */
    private final double promotionPolicy = 200;
    /**discount10 after certain conditions.
     */
    private final double discount10 = 0.1;
    /**discount20 after certain conditions.
     */
    private final double discount20 = 0.2;
    /**conditional to discount20.
     */
    private final int discLimit20 = 10;
    /**conditional to discount10.
     */
    private final int discLimit10 = 4;
    /**limit of travelers.
     */
    private final int limitT = 80;
    /**price for Paris.
     */
    private final int priceParis = 500;
    /**price for NY.
     */
    private final int priceNY = 600;
    /**price for extra package all inclusive.
     */
    private final int priceAllInclusive = 200;
    /**price for extra package adventure.
     */
    private final int priceAdventure = 150;
    /**price for adventure package.
     */
    private final int priceSpaWellness = 100;

    /**time limit for promotion fee.
     */
    private final int timeLimitProm = 30;
    /**limit of travelers for promotion fee.
     */
    private final int limitProm = 2;
    /**time conditional for penalty.
     */
    private final int timePenal = 7;
    /**total.
     */
    private double total;
    /**Constructor of vacationCost.
     */
    private VacationCost() {
        total = 0;
        subCost = 0;
    }
    /**Getter of all inclusive price.
     * @return double
     */
    private int getPriceAllInclusive() {
        return priceAllInclusive;
    }

    /**Getter of adventure price.
     * @return double
     */
    private int getPriceAdventure() {
        return priceAdventure;
    }
    /**Getter of all sa price.
     * @return double
     */
    private int getPriceSpaWellness() {
        return priceSpaWellness;
    }
    /**Getter of baseCost.
     * @return double
     */
    private double getBaseCost() {
        return baseCost;
    }
    /**Getter of subCost.
     * @return double
     */
    private double getSubCost() {
        return subCost;
    }
    /**Getter of peanltyFee.
     * @return double
    */
    private double getPenaltyFee() {
        return penaltyFee;
    }
    /**Getter of promotionPolicy.
     * @return double
     */
    private double getPromotionPolicy() {
        return promotionPolicy;
    }
    /**Getter of discount10.
     * @return double
     */
    private double getDiscount10() {
        return discount10;
    }
    /**Getter of discount20.
     * @return double
     */
    private double getDiscount20() {
        return discount20;
    }
    /**Getter of discLimit20.
     * @return double
     */
    private int getDiscLimit20() {
        return discLimit20;
    }
    /**Getter of discLimit10.
     * @return double
     */
    private int getDiscLimit10() {
        return discLimit10;
    }
    /**Getter of limitT.
     * @return double
     */
    @SuppressWarnings("unused")
    private int getLimitT() {
        return limitT;
    }
    /**Getter of priceParis.
     * @return double
     */
    private int getPriceParis() {
        return priceParis;
    }
    /**Getter of priceNY.
     * @return double
     */
    private int getPriceNY() {
        return priceNY;
    }
    /**Getter of timeLimitProm.
     * @return double
     */
    private int getTimeLimitProm() {
        return timeLimitProm;
    }
    /**Getter of limitProm.
     * @return double
     */
    private int getLimitProm() {
        return limitProm;
    }
    /**Getter of timePenal.
     * @return double
     */
    private int getTimePenal() {
        return timePenal;
    }
    /**Getter of total.
     * @return double
     */
    @SuppressWarnings("unused")
    private double getTotal() {
        return total;
    }
    /**Method to calculate cost of packages of vacation.
     * @param dest
     * @param travelers
     * @param time
     * @return double
     */
    private double getCost(
        final String dest, final int travelers, final int time
        ) {
        double subtotal = getSubCost();
        subtotal += getBaseCost();
        if (dest == "PARIS") {
            subtotal += getPriceParis();
        } else if (dest == "NEW YORK CITY") {
            subtotal += getPriceNY();
        }
        if (travelers > getDiscLimit20()) {
            subtotal -= subCost * getDiscount20();
        } else if (travelers > getDiscLimit10()) {
            subtotal -= subCost * getDiscount10();
        }
        if (time > getTimeLimitProm() && travelers == getLimitProm()) {
            subtotal -= getPromotionPolicy();
        } else if (time < getTimePenal()) {
            subtotal += getPenaltyFee();
        }
        subCost = subtotal;
        total = subtotal;
        return total;
    }
    //
    private double calculatespecial1s(
    final String allInclusive, final String adventure, final String spaWellness
    ) {
        if (allInclusive == "y") {
            total += getPriceAllInclusive();
        }
        if (adventure == "y") {
            total += getPriceAdventure();
        }
        if (spaWellness == "y") {
            total += getPriceSpaWellness();
        }
        return total;
    }
    /**Main program.
     * Vacation cost calculator
     * @param args
     */
    //CHECKSTYLE:OFF
    public static void main(final String[] args) {
    //CHECKSTYLE:ON
        boolean exit = false;
        String dest = null;
        int travelers = 0;
        int time = 0;
        VacationCost vc = new VacationCost();
        Scanner s = new Scanner(System.in);
        Logger logger = Logger.getLogger(VacationCost.class.getName());
        while (!exit) {
            System.out.print("\nVACATION - COST\n");
            try {
                System.out.print("\nDestino: ");
                dest = s.nextLine().toUpperCase();
                System.out.print("\nNumber of travelers: ");
                travelers = s.nextInt();
                System.out.print("\ntime in days: ");
                time = s.nextInt();
                validation(dest, travelers, time);
                vc.getCost(dest, travelers, time);
                System.out.println("Do you wish to add "
                + "special options to your vacation package? (y)");
                if (s.nextLine().toLowerCase() == "y") {
                    System.out.print("\n\nDo you wist to add an "
                    + "all-inclusive package for $200 extra? (y): ");
                    String special1 = s.nextLine();
                    System.out.print("\nDo you wist to add an adventure "
                    + "activities package for $150 extra? (y): ");
                    String special2 = s.nextLine();
                    System.out.print("\nDo you wist to add a spa and wellness"
                    + " package for $100 extra? (y): ");
                    String special3 = s.nextLine();
                    vc.calculatespecial1s(special1, special2, special3);
                }
            } catch (InputMismatchException ex) {
                logger.log(null, "Input Mismatch");
            } catch (Exception ex) {
                logger.log(null, "Exception");
            } finally {
                s.close();
            }
            System.out.printf("Total cost: %.2f\n", vc.getTotal());
            System.out.println("Thanks for your purchase...");
        }
    }
    /**Validation of variables using Scanner.
     * @param dest
     * @param travel
     * @param time
     * @throws Exception
     */
    public static void validation(
        final String dest, final int travel, final int time
    ) throws Exception {
        if (dest == null) {
            throw new Exception("Destino esta vacio");
        }
        if (travel <= 0) {
            throw new Exception("Viajeros no registrados.");
        }
        if (time <= 0) {
            throw new Exception("Tiempo no registrados.");
        }
    }
}
