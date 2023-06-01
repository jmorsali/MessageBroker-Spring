package com.blueboders.productcodebroker.repository;

import com.blueboders.productcodebroker.entity.DailyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DailyReportRepository extends JpaRepository<DailyReport,Long> {

    @Query("Select c From DailyReport c where c.reportDate= :reportDate ")
    public List<DailyReport> getDailyReport(@Param("reportDate") Date ReportDate);
}
