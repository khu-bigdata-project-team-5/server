package khu.bigdata.infou.repository;

import khu.bigdata.infou.domain.LectureDetail;
import khu.bigdata.infou.domain.LectureUdemy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureDetailRepository extends JpaRepository<LectureDetail, Long> {

    Optional<LectureDetail> findByLectureUdemy(LectureUdemy lectureUdemy);

    Optional<LectureDetail> findByLectureId(Long lectureId);
}
