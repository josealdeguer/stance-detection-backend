package josealdeguer.tfg.sdbackend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetRepository  extends JpaRepository<Tweet, Integer> {
    Tweet findByIdentifier(Integer identifier);
    List<Tweet> findByStanceIgnoreCase(String stance);
    @Query("SELECT stance, COUNT(t.identifier) FROM Tweet AS t GROUP BY stance")
    List<?> countByStance();
    @Query("SELECT t.fecha, COUNT(t.identifier) FROM Tweet AS t WHERE t.fecha IS NOT NULL GROUP BY t.fecha")
    List<?> countByDate();
    @Query("SELECT stance, t.fecha, COUNT(t.identifier) FROM Tweet AS t WHERE t.fecha IS NOT NULL GROUP BY stance, t.fecha")
    List<?> countByStanceAndDate();

}
