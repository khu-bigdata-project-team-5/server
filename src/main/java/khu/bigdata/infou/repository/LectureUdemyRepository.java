package khu.bigdata.infou.repository;

import khu.bigdata.infou.domain.LectureUdemy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureUdemyRepository extends JpaRepository<LectureUdemy, Long> {


    public List<LectureUdemy> findAllBySubcategory(String subcategory);

    public List<LectureUdemy> findAllByTopic(String topic);


}
