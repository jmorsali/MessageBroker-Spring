package RabbirMQ.example.RabbirMQDemo.Repository;

import RabbirMQ.example.RabbirMQDemo.Entity.ProductCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCodeRepository extends JpaRepository<ProductCode,Long> {

    @Query("SELECT c From ProductCode c WHERE c.code = :code ")
    public ProductCode getByCode(@Param("code") String code);
}
