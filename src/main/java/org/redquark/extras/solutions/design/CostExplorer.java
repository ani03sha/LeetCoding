package org.redquark.extras.solutions.design;

import java.time.LocalDate;
import java.time.Month;
import java.util.EnumMap;
import java.util.Map;

public class CostExplorer {

    public Map<Month, Double> getMonthlyReport(LocalDate startDate, Plan plan) {
        final Map<Month, Double> report = new EnumMap<>(Month.class);
        final Month currentMonth = startDate.getMonth();
        for (Month month : Month.values()) {
            if (month.getValue() >= currentMonth.getValue()) {
                report.put(month, plan.getMonthlyCost());
            }
        }
        return report;
    }

    public double getYearlyEstimate(LocalDate startDate, Plan plan) {
        final int remainingMonths = 12 - startDate.getMonthValue() + 1;
        return remainingMonths * plan.getMonthlyCost();
    }

    public Map<Month, Double> getMonthlyReportWithTrial(LocalDate startDate, Plan plan, int trialDays) {
        final LocalDate billStartDate = startDate.plusDays(trialDays);
        return getMonthlyReport(billStartDate, plan);
    }

    public double getYearlyEstimateWithTrial(LocalDate startDate, Plan plan, int trialDays) {
        final LocalDate billStartDate = startDate.plusDays(trialDays);
        return getYearlyEstimate(billStartDate, plan);
    }

    enum Plan {
        BASIC(9.99),
        STANDARD(49.99),
        PREMIUM(249.99);

        private final double monthlyCost;

        Plan(double cost) {
            this.monthlyCost = cost;
        }

        public double getMonthlyCost() {
            return this.monthlyCost;
        }
    }

    public static void main(String[] args) {
        final CostExplorer costExplorer = new CostExplorer();

        System.out.println("START DATE: June 15, 2025\n");
        LocalDate startDate = LocalDate.of(2025, 6, 15);
        Plan plan = Plan.STANDARD;

        System.out.println("=== Monthly Report ===");
        Map<Month, Double> report = costExplorer.getMonthlyReport(startDate, plan);
        report.forEach((month, cost) -> System.out.println(month + ": " + cost));

        System.out.println("\n=== Yearly Estimate ===");
        System.out.println("Total: " + costExplorer.getYearlyEstimate(startDate, plan));

        System.out.println("\n=== Monthly report with 14-Day Trial ===");
        System.out.println("Total (Trial): " + costExplorer.getMonthlyReportWithTrial(startDate, plan, 14));

        System.out.println("\n=== With 14-Day Trial ===");
        System.out.println("Total (Trial): " + costExplorer.getYearlyEstimateWithTrial(startDate, plan, 14));

        System.out.println("\n-------------------------------------\n");
        System.out.println("START DATE: June 18, 2025\n");
        startDate = LocalDate.of(2025, 6, 15);
        plan = Plan.STANDARD;

        System.out.println("=== Monthly Report ===");
        report = costExplorer.getMonthlyReport(startDate, plan);
        report.forEach((month, cost) -> System.out.println(month + ": " + cost));

        System.out.println("\n=== Yearly Estimate ===");
        System.out.println("Total: " + costExplorer.getYearlyEstimate(startDate, plan));

        System.out.println("\n=== Monthly report with 14-Day Trial ===");
        System.out.println("Total (Trial): " + costExplorer.getMonthlyReportWithTrial(startDate, plan, 14));

        System.out.println("\n=== With 14-Day Trial ===");
        System.out.println("Total (Trial): " + costExplorer.getYearlyEstimateWithTrial(startDate, plan, 14));
    }
}
