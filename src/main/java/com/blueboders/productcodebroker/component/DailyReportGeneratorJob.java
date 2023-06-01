package com.blueboders.productcodebroker.component;

import com.blueboders.productcodebroker.entity.DailyReport;
import com.blueboders.productcodebroker.repository.DailyReportRepository;
import com.blueboders.productcodebroker.repository.ProductCodeRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class DailyReportGeneratorJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ApplicationContext applicationContext = getApplicationContext(context);
        var dailyReportRepository = applicationContext.getBean(DailyReportRepository.class);
        var productCodeRepository = applicationContext.getBean(ProductCodeRepository.class);

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            var today = formatter.format(new Date());
            List<DailyReport> todayReportItems = dailyReportRepository.getDailyReport(today);

            if (todayReportItems.isEmpty()) {
                var codeListCodeReportItems = productCodeRepository.getCodeListCodeReport(today);
                for (var item : codeListCodeReportItems) {
                    var reportItem = new DailyReport();
                    reportItem.setReportDate(today);
                    reportItem.setCodeListCode(item[0].toString());
                    reportItem.setTotalItemCount(Integer.valueOf(item[1].toString()));
                    dailyReportRepository.save(reportItem);
                }
            }
        } catch (Exception e) {
            System.out.println("Async task exception :: " + e.getMessage());
        }
        System.out.println("Async task executed");
    }

    private static ApplicationContext getApplicationContext(JobExecutionContext context) {
        ApplicationContext applicationContext = null;
        try {
            applicationContext = (ApplicationContext)
                    context.getScheduler().getContext().get("applicationContext");
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return applicationContext;
    }

}
