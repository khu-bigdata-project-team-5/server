package khu.bigdata.infou.implement;

import khu.bigdata.infou.business.LectureConverter;
import khu.bigdata.infou.domain.LectureUdemy;
import khu.bigdata.infou.repository.LectureInflearnRepository;
import khu.bigdata.infou.repository.LectureUdemyRepository;
import khu.bigdata.infou.web.dto.LectureResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class LectureService {

    // 이건 걍 없이 ㄱ
    private final LectureInflearnRepository lectureInflearnRepository;

    private final LectureUdemyRepository lectureUdemyRepository;


    public LectureResponseDTO.CategoryRecommandLectureDto findRecommendedLectureByCategory(String category) {

        if (category == null) {
            // 카테고리 프로그래밍 언어로 고정해서 가져와라
            category = "Programming Languages";
        }
        List<LectureUdemy> lectureUdemyList = lectureUdemyRepository.findAllBySubcategory(category);

        // 일단 가져오고 상위 값 끊어내자

        List<LectureUdemy> sortedList = lectureUdemyList.stream()
                .sorted(Comparator.comparingDouble(lecture -> lecture.getAvgRating() * lecture.getNumReviews()))
                .collect(Collectors.toList());

        return LectureConverter.toCategoryRecommandLectureDto(sortedList);
    }
}
