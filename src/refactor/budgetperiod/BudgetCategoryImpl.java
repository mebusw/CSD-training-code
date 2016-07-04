/**
 * This repo is to refactor the getAmount(startDate, endDate) method of BudgetCategoryImpl class which is cloned from http://git.code.sf.net/p/buddi/code
 */

/*
 * Created on Jul 29, 2007 by wyatt
 */
package refactor.budgetperiod;

import ca.digitalcave.moss.common.DateUtil;

import java.util.*;
import java.util.logging.Logger;

/**
 * Default implementation of an BudgetCategory.java.  You should not create this object directly;
 * instead, please use the ModelFactory to create it, as this will ensure that all
 * required fields are correctly set.
 *
 * @author wyatt
 */
public class BudgetCategoryImpl implements BudgetCategory {
    private boolean income;
    private boolean expanded;
    private BudgetCategoryType periodType;
    private BudgetCategory parent;
    private Map<String, Long> amounts;
    private List<BudgetCategory> children;
    private List<BudgetCategory> allChildren;


    public BudgetCategoryImpl(BudgetCategoryType bct) {
        this.periodType = bct;
    }

    public BudgetCategoryImpl() {

    }

    public Map<String, Long> getAmounts() {
        if (amounts == null)
            amounts = new HashMap<String, Long>();
        return amounts;
    }

    public void setAmounts(Map<String, Long> amounts) {
        this.amounts = amounts;
    }

    /**
     * Returns the budgeted amount associated with the given budget category, for
     * the date in which the given period date exists.
     *
     * @param periodDate
     * @return
     */
    public long getAmount(Date periodDate) {
        Long l = getAmounts().get(getPeriodKey(periodDate));
        if (l == null)
            return 0;
        return l;
    }
//
//    public List<BudgetCategory> getChildren() {
//        if (children == null)
//            children = new FilteredLists.BudgetCategoryListFilteredByDeleted(getDocument(), new FilteredLists.BudgetCategoryListFilteredByParent(getDocument(), getDocument().getBudgetCategories(), this));
//        return children;
//    }
//
//    public List<BudgetCategory> getAllChildren() {
//        if (allChildren == null)
//            allChildren = new FilteredLists.BudgetCategoryListFilteredByParent(getDocument(), getDocument().getBudgetCategories(), this);
//        return allChildren;
//    }

    public long getAmount(Date startDate, Date endDate) {
        if (startDate.after(endDate))
            throw new RuntimeException("Start date cannot be before End Date!");

        Logger.getLogger(this.getClass().getName()).info("Starting to calculate the budgeted amount for " + "getFullName()" + " between " + startDate + " and " + endDate + ".");

        //If Start and End are in the same budget period
        if (getBudgetPeriodType().getStartOfBudgetPeriod(startDate).equals(
                getBudgetPeriodType().getStartOfBudgetPeriod(endDate))) {
//			Logger.getLogger().info("Start Date and End Date are in the same period.");
            long amount = getAmount(startDate);
//			Logger.getLogger().info("Amount = " + amount);
            long daysInPeriod = getBudgetPeriodType().getDaysInPeriod(startDate);
//			Logger.getLogger().info("Days in Period = " + daysInPeriod);
            long daysBetween = DateUtil.getDaysBetween(startDate, endDate, true);
//			Logger.getLogger().info("Days Between = " + daysBetween);

//			Logger.getLogger().info("Returning " + (long) (((double) amount / (double) daysInPeriod) * daysBetween));
//			Logger.getLogger().info("Finished calculating the budget amount.\n\n");
            return (long) (((double) amount / (double) daysInPeriod) * daysBetween);
        }

        //If the area between Start and End overlap at least two budget periods.
        if (getBudgetPeriodType().getBudgetPeriodOffset(startDate, 1).equals(
                getBudgetPeriodType().getStartOfBudgetPeriod(endDate))
                || getBudgetPeriodType().getBudgetPeriodOffset(startDate, 1).before(
                getBudgetPeriodType().getStartOfBudgetPeriod(endDate))) {
//			Logger.getLogger().info("Start Date and End Date are in different budget periods.");
            long amountStartPeriod = getAmount(startDate);
//			Logger.getLogger().info("Amount Start Period = " + amountStartPeriod);
            long daysInStartPeriod = getBudgetPeriodType().getDaysInPeriod(startDate);
//			Logger.getLogger().info("Days in Start Period = " + daysInStartPeriod);
            long daysAfterStartDateInStartPeriod = DateUtil.getDaysBetween(startDate, getBudgetPeriodType().getEndOfBudgetPeriod(startDate), true);
//			Logger.getLogger().info("Days After Start Date in Start Period = " + daysAfterStartDateInStartPeriod);
            double totalStartPeriod = (((double) amountStartPeriod / (double) daysInStartPeriod) * daysAfterStartDateInStartPeriod);
//			Logger.getLogger().info("Total in Start Period = " + totalStartPeriod);

            double totalInMiddle = 0;
            for (String periodKey : getBudgetPeriods(
                    getBudgetPeriodType().getBudgetPeriodOffset(startDate, 1),
                    getBudgetPeriodType().getBudgetPeriodOffset(endDate, -1))) {
                totalInMiddle += getAmount(getPeriodDate(periodKey));
                Logger.getLogger(this.getClass().getName()).info("Added " + getAmount(getPeriodDate(periodKey)) + " to total for one period in between; current value is " + totalInMiddle);
            }
//			Logger.getLogger().info("Total in Middle = " + totalInMiddle);

            long amountEndPeriod = getAmount(endDate);
//			Logger.getLogger().info("Amount End Period = " + amountEndPeriod);
            long daysInEndPeriod = getBudgetPeriodType().getDaysInPeriod(endDate);
//			Logger.getLogger().info("Days in End Period = " + daysInEndPeriod);
            long daysBeforeEndDateInEndPeriod = DateUtil.getDaysBetween(getBudgetPeriodType().getStartOfBudgetPeriod(endDate), endDate, true);
//			Logger.getLogger().info("Days before End Period = " + daysBeforeEndDateInEndPeriod);
            double totalEndPeriod = (long) (((double) amountEndPeriod / (double) daysInEndPeriod) * daysBeforeEndDateInEndPeriod);
//			Logger.getLogger().info("Total in End Period = " + totalEndPeriod);

//			Logger.getLogger().info("Sum of Start Period, Middle, and End Period = " + (totalStartPeriod + totalInMiddle + totalEndPeriod));
//			Logger.getLogger().info("Finished Calculating the Budget Amount\n\n");
            return (long) (totalStartPeriod + totalInMiddle + totalEndPeriod);
        }

        throw new RuntimeException("You should not be here.  We have returned all legitimate numbers from getAmount(Date, Date) in BudgetCategoryImpl.  Please contact Wyatt Olson with details on how you got here (what steps did you perform in Buddi to get this error message).");
    }

    /**
     * Returns a list of BudgetPeriods, covering the entire range of periods
     * occupied by startDate to endDate.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<String> getBudgetPeriods(Date startDate, Date endDate) {
        List<String> budgetPeriodKeys = new LinkedList<String>();

        Date temp = getBudgetPeriodType().getStartOfBudgetPeriod(startDate);

        while (temp.before(getBudgetPeriodType().getEndOfBudgetPeriod(endDate))) {
            budgetPeriodKeys.add(getPeriodKey(temp));
            temp = getBudgetPeriodType().getBudgetPeriodOffset(temp, 1);
        }

        return budgetPeriodKeys;
    }

    //    /**
//     * Sets the budgeted amount for the given time period.
//     * @param periodDate
//     * @param amount
//     */
    public void setAmount(Date periodDate, long amount) {
        if (getAmount(periodDate) != amount)
//            setChanged();
            getAmounts().put(getPeriodKey(periodDate), amount);
    }


    public BudgetCategoryType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(BudgetCategoryType periodType) {
        this.periodType = periodType;
//        setChanged();
    }

    public boolean isIncome() {
        return income;
    }

    public void setIncome(boolean income) {
        this.income = income;
//        setChanged();
    }

    public void setName(String name) throws InvalidValueException {
//		if (getDocument() != null){
//			for (BudgetCategory bc : ((Document) getDocument()).getBudgetCategories()) {
//				if (bc.getName().equalsIgnoreCase(name)
//						&& !bc.equals(this)
//						&& ((bc.getParent() == null && this.getParent() == null)
//								|| (bc.getParent() != null && this.getParent() != null && bc.getParent().equals(this.getParent()))))
//					throw new InvalidValueException("The budget category name must be unique for nodes which share the same parent");
//			}
//		}
//        super.setName(name);
    }

    public BudgetCategory getParent() {
        return parent;
    }

    @Override
    public List<BudgetCategory> getChildren() {
        return null;
    }

    @Override
    public List<BudgetCategory> getAllChildren() {
        return null;
    }

    public void setParent(BudgetCategory parent) throws InvalidValueException {
        if (children != null) {
            boolean invalidParent = isChild(parent) || parent == this;
            if (invalidParent) {
                throw new InvalidValueException();
            }
        }

        this.parent = parent;
//        setChanged();
    }

    private boolean isChild(BudgetCategory category) {

        boolean isChild = children.contains(category);

        for (BudgetCategory child : children) {
            isChild = isChild || ((BudgetCategoryImpl) child).isChild(category);
        }

        return isChild;
    }


    /**
     * Returns the key which is associated with the date contained within the
     * current budget period.  The string is constructed as follows:
     * <p>
     * <code>String periodKey = getPeriodType() + ":" + getStartOfBudgetPeriod(periodDate).getTime();</code>
     *
     * @param periodDate
     * @return
     */
    private String getPeriodKey(Date periodDate) {
        Date d = getBudgetPeriodType().getStartOfBudgetPeriod(periodDate);
        return getBudgetPeriodType().getName() + ":" + DateUtil.getYear(d) + ":" + DateUtil.getMonth(d) + ":" + DateUtil.getDay(d);
    }

    /**
     * Parses a periodKey to get the date
     *
     * @param periodKey
     * @return
     */
    private Date getPeriodDate(String periodKey) {
        String[] splitKey = periodKey.split(":");
        if (splitKey.length == 4) {
            int year = Integer.parseInt(splitKey[1]);
            int month = Integer.parseInt(splitKey[2]);
            int day = Integer.parseInt(splitKey[3]);
            return getBudgetPeriodType().getStartOfBudgetPeriod(DateUtil.getDate(year, month, day));
        }

        throw new RuntimeException("Cannot parse date from key " + periodKey);
    }

    /**
     * Returns the Budget Period type.  One of the values in Enum BudgePeriodKeys.
     *
     * @return
     */
    public BudgetCategoryType getBudgetPeriodType() {
        if (getPeriodType() == null)
            setPeriodType(new BudgetCategoryTypeMonthly());
        return getPeriodType();
    }

    public List<Date> getBudgetedDates() {
        List<Date> budgetedDates = new ArrayList<Date>();

        Map<String, Long> amounts = getAmounts();
        for (String key : amounts.keySet()) {
            if (amounts.get(key) != null && amounts.get(key) != 0)
                budgetedDates.add(getPeriodDate(key));
        }

        return budgetedDates;
    }

}