package khu.bigdata.infou.repository;

import khu.bigdata.infou.domain.LectureUdemy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureUdemyRepository extends JpaRepository<LectureUdemy, Long> {


    List<LectureUdemy> findAllBySubcategory(String subcategory);

    List<LectureUdemy> findAllByTopic(String topic);

    Optional<LectureUdemy> findByLectureId(Integer lectureId);

}
