package refactor.budgetperiod;

/*
 * Created on Aug 26, 2007 by wyatt
 */

import ca.digitalcave.moss.common.DateUtil;

import java.util.Date;

/**
 * Definition of a Weekly BudgetCategoryType
 *
 * @author wyatt
 */
public class BudgetCategoryTypeWeekly extends BudgetCategoryType {

    public Date getStartOfBudgetPeriod(Date date) {
        return DateUtil.getStartOfWeek(date);
    }

    public Date getEndOfBudgetPeriod(Date date) {
        return DateUtil.getEndOfWeek(date);
    }

    public Date getBudgetPeriodOffset(Date date, int offset) {
        return getStartOfBudgetPeriod(DateUtil.addDays(DateUtil.getStartOfWeek(date), 7 * offset));
    }

    public long getDaysInPeriod(Date date) {
        return 7;
    }

    public String getDateFormat() {
        return "dd MMM yyyy";
    }

    public String getName() {
        return "BUDGET_CATEGORY_TYPE_WEEK";
    }

    @Override
    public String getKey() {
        return "WEEK";
    }
}