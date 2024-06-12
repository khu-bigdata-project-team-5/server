package khu.bigdata.infou.repository;

import khu.bigdata.infou.domain.PlatformStudent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlatformStudentRepository extends JpaRepository<PlatformStudent, Long> {

    @Query("SELECT p.inflearnUserId AS userId, COUNT(p) AS cnt FROM PlatformStudent p WHERE p.inflearnUserId IS NOT NULL GROUP BY p.inflearnUserId " +
            "UNION " +
            "SELECT p.udemyUserId AS userId, COUNT(p) AS cnt FROM PlatformStudent p WHERE p.udemyUserId IS NOT NULL GROUP BY p.udemyUserId " +
            "ORDER BY cnt DESC")
    List<Object[]> findTopStudents(Pageable pageable);

    List<PlatformStudent> findByInflearnUserId(Integer userId);

    List<PlatformStudent> findByUdemyUserId(Integer userId);
}

