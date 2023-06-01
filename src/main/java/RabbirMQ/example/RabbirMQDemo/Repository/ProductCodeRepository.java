package RabbirMQ.example.RabbirMQDemo.Repository;

import RabbirMQ.example.RabbirMQDemo.Entity.DailyReport;
import RabbirMQ.example.RabbirMQDemo.Entity.ProductCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCodeRepository extends JpaRepository<ProductCode,Long> {

    @Query("SELECT c From ProductCode c WHERE c.code = :code ")
    public ProductCode getByCode(@Param("code") String code);


    @Query("SELECT c.codeListCode,Count(c) From ProductCode c WHERE c.fromDate = :reportDate  Group By c.codeListCode")
    public List<Object[]> getCodeListCodeReport(@Param("reportDate") String reportDate);

}
