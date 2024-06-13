package khu.bigdata.infou.repository;

import khu.bigdata.infou.domain.PlatformStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatformStudentRepository extends JpaRepository<PlatformStudent, Long> {
    //    @Query("SELECT p FROM PlatformStudent p WHERE p.udemyUserId IS NOT NULL")
    List<PlatformStudent> findTop1000ByUdemyUserIdIsNotNull();
}

