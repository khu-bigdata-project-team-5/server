package khu.bigdata.infou.repository;

import khu.bigdata.infou.domain.LectureUdemy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LectureUdemyRepository extends JpaRepository<LectureUdemy, Long> {


    @Query("select l from LectureUdemy l left join fetch l.lectureTag where l.subcategory = :subcategory")
    List<LectureUdemy> findAllBySubcategory(String subcategory);

    List<LectureUdemy> findAllByTopic(String topic);

    Optional<LectureUdemy> findByLectureId(Long lectureId);

}
