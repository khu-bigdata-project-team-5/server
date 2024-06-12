package khu.bigdata.infou.repository;

import khu.bigdata.infou.domain.LectureDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureDetailRepository extends JpaRepository<LectureDetail, Long> {

    Optional<LectureDetail> findByLectureUdemyId(Long lectureId);

    Optional<LectureDetail> findByLectureInflearnId(Long lectureId);
}
