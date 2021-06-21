package microServiceBye.repository;

import microServiceBye.model.ByeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ByeEntityRepository extends JpaRepository<ByeEntity, Long> {
    @Query("SELECT b FROM ByeEntity b WHERE b.id = :id")
    ByeEntity getByIdName(String id);
}
