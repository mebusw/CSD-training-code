/*
 * Created on Aug 26, 2007 by wyatt
 */
package refactor.budgetperiod;

import ca.digitalcave.moss.common.DateUtil;

import java.util.Date;

/**
 * Definition of a Monthly BudgetCategoryType.
 *  
 * @author wyatt
 *
 */
public class BudgetCategoryTypeMonthly extends BudgetCategoryType {
	
	public Date getStartOfBudgetPeriod(Date date) {
		return DateUtil.getStartOfMonth(date);
	}
	
	public Date getEndOfBudgetPeriod(Date date) {
		return DateUtil.getEndOfMonth(date);
	}
	
	public Date getBudgetPeriodOffset(Date date, int offset) {
		return getStartOfBudgetPeriod(DateUtil.addMonths(DateUtil.getStartOfMonth(date), 1 * offset));
	}
	
	public long getDaysInPeriod(Date date) {
		return DateUtil.getDaysInMonth(date);
	}
	
	public String getDateFormat() {
		return "MMM yyyy";
	}
			
	public String getName() {
		return "BUDGET_CATEGORY_TYPE_MONTH";
	}
	@Override
	public String getKey() {
		return "MONTH";
	}
}