package khu.bigdata.infou.repository;

import khu.bigdata.infou.domain.LectureTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureTagRepository extends JpaRepository<LectureTag, Long> {

    Optional<LectureTag> findByLectureUdemyId(Integer lectureId);

    List<LectureTag> findByTopword1ContainingOrTopword2ContainingOrTopword3ContainingOrTopword4ContainingOrTopword5Containing(
            String keyword1, String keyword2, String keyword3, String keyword4, String keyword5);
}
