package khu.bigdata.infou.repository;

import khu.bigdata.infou.domain.PlatformStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlatformStudentRepository extends JpaRepository<PlatformStudent, Long> {

    @Query("SELECT p.inflearnUserId, COUNT(p) FROM PlatformStudent p WHERE p.inflearnUserId IS NOT NULL GROUP BY p.inflearnUserId ORDER BY COUNT(p) DESC")
    List<Object[]> countInflearnUserId();

    @Query("SELECT p.udemyUserId, COUNT(p) FROM PlatformStudent p WHERE p.udemyUserId IS NOT NULL GROUP BY p.udemyUserId ORDER BY COUNT(p) DESC")
    List<Object[]> countUdemyUserId();

    List<PlatformStudent> findByInflearnUserId(Long inflearnUserId);

    List<PlatformStudent> findByUdemyUserId(Long udemyUserId);
}

