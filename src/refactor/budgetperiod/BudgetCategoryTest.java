/*
 * Created on Aug 24, 2007 by wyatt
 */
package refactor.budgetperiod;

import ca.digitalcave.moss.common.DateUtil;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;



public class BudgetCategoryTest {
	
	@Test
	public void testBudgetCategory(){
		try {
			BudgetCategoryType bct = new BudgetCategoryTypeMonthly();
			BudgetCategory bc = new BudgetCategoryImpl(bct);

			bc.setAmount(DateUtil.getDate(2007, Calendar.APRIL, 1), 100);
			bc.setAmount(DateUtil.getDate(2007, Calendar.MAY, 1), 200);
			bc.setAmount(DateUtil.getDate(2007, Calendar.JUNE, 1), 240);
			bc.setAmount(DateUtil.getDate(2007, Calendar.JULY, 1), 10);
			bc.setAmount(DateUtil.getDate(2007, Calendar.AUGUST, 1), 130);
			bc.setAmount(DateUtil.getDate(2007, Calendar.SEPTEMBER, 1), 13);
			bc.setAmount(DateUtil.getDate(2007, Calendar.OCTOBER, 1), 333);
			bc.setAmount(DateUtil.getDate(2007, Calendar.NOVEMBER, 1), 331);
			
			assertEquals((double) 100, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 1)), 1);
			assertEquals((double) 100, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 10)), 1);
			assertEquals((double) 100, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 28)), 1);
			
			assertEquals((double) 300, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 1), DateUtil.getDate(2007, Calendar.MAY, 31)), 1);
			assertEquals((double) 149, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 15), DateUtil.getDate(2007, Calendar.MAY, 15)), 1);
			
		}
		catch (Exception e){
			fail("Exception: " + e);
		}
	}
	
	@Test
	public void budgetPeriodWeekly() throws Exception {
		BudgetCategoryType bct = new BudgetCategoryTypeWeekly();
		BudgetCategory bc = new BudgetCategoryImpl(bct);
		
		bc.setAmount(DateUtil.getDate(2007, Calendar.SEPTEMBER, 10), 100);
		bc.setAmount(DateUtil.getDate(2007, Calendar.SEPTEMBER, 17), 100);
		bc.setAmount(DateUtil.getDate(2007, Calendar.SEPTEMBER, 24), 100);
		bc.setAmount(DateUtil.getDate(2007, Calendar.OCTOBER, 1), 100);
		bc.setAmount(DateUtil.getDate(2007, Calendar.OCTOBER, 8), 100);
		bc.setAmount(DateUtil.getDate(2007, Calendar.OCTOBER, 15), 100);
		bc.setAmount(DateUtil.getDate(2007, Calendar.OCTOBER, 22), 100);
		bc.setAmount(DateUtil.getDate(2007, Calendar.OCTOBER, 29), 100);
		bc.setAmount(DateUtil.getDate(2007, Calendar.NOVEMBER, 5), 100);
		bc.setAmount(DateUtil.getDate(2007, Calendar.NOVEMBER, 12), 100);
	
		assertEquals(100l,
				bc.getAmount(DateUtil.getDate(2007, Calendar.OCTOBER, 1)));
		assertEquals(14l,
				bc.getAmount(
						DateUtil.getDate(2007, Calendar.OCTOBER, 1),
						DateUtil.getDate(2007, Calendar.OCTOBER, 1)));		
		assertEquals(28l,
				bc.getAmount(
						DateUtil.getDate(2007, Calendar.OCTOBER, 1),
						DateUtil.getDate(2007, Calendar.OCTOBER, 2)));
		assertEquals(100l,
				bc.getAmount(
						bct.getStartOfBudgetPeriod(DateUtil.getDate(2007, Calendar.OCTOBER, 1)),
						bct.getEndOfBudgetPeriod(DateUtil.getDate(2007, Calendar.OCTOBER, 1))));
		assertEquals(200l,
				bc.getAmount(
						bct.getStartOfBudgetPeriod(DateUtil.getDate(2007, Calendar.OCTOBER, 1)),
						bct.getEndOfBudgetPeriod(bct.getBudgetPeriodOffset(DateUtil.getDate(2007, Calendar.OCTOBER, 1), 1))));
		assertEquals(400l,
				bc.getAmount(
						bct.getStartOfBudgetPeriod(DateUtil.getDate(2007, Calendar.OCTOBER, 1)),
						bct.getEndOfBudgetPeriod(bct.getBudgetPeriodOffset(DateUtil.getDate(2007, Calendar.OCTOBER, 1), 3))));
		assertEquals(442l,
				bc.getAmount(
						DateUtil.getDate(2007, Calendar.OCTOBER, 1),
						DateUtil.getDate(2007, Calendar.OCTOBER, 31)));
	}
	
	@Test
	public void budgetedAmounts() throws Exception {
		BudgetCategoryType bct = new BudgetCategoryTypeMonthly();
		BudgetCategory bc = new BudgetCategoryImpl(bct);
		
		bc.setAmount(DateUtil.getDate(2007, Calendar.NOVEMBER, 5), 100);
		bc.setAmount(DateUtil.getDate(2007, Calendar.DECEMBER, 12), 100);
	
		assertEquals(2, bc.getBudgetedDates().size());
		assertEquals(DateUtil.getDate(2007, Calendar.DECEMBER, 1), bc.getBudgetedDates().get(0));
		assertEquals(DateUtil.getDate(2007, Calendar.NOVEMBER, 1), bc.getBudgetedDates().get(1));
	}
	
}