package khu.bigdata.infou.repository;

import khu.bigdata.infou.domain.LectureTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureTagRepository extends JpaRepository<LectureTag, Long> {

    Optional<LectureTag> findByLectureUdemyId(Integer lectureId);
}
