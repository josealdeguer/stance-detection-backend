package josealdeguer.tfg.sdbackend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoronavirusRepository extends JpaRepository<Coronavirus, Integer> {
    Coronavirus findByIdentifier(Integer identifier);
    List<Coronavirus> findByStanceIgnoreCase(String stance);
    @Query("SELECT stance, COUNT(t.identifier) FROM Coronavirus AS t GROUP BY stance")
    List<?> countByStance();
    @Query("SELECT t.fecha, COUNT(t.identifier) FROM Coronavirus AS t WHERE t.fecha IS NOT NULL GROUP BY t.fecha")
    List<?> countByDate();
    @Query("SELECT stance, t.fecha, COUNT(t.identifier) FROM Coronavirus AS t WHERE t.fecha IS NOT NULL GROUP BY stance, t.fecha")
    List<?> countByStanceAndDate();

}
