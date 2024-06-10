package khu.bigdata.infou.implement;

import khu.bigdata.infou.business.LectureConverter;
import khu.bigdata.infou.domain.LectureUdemy;
import khu.bigdata.infou.repository.LectureDetailRepository;
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

    private final LectureInflearnRepository lectureInflearnRepository;
    private final LectureUdemyRepository lectureUdemyRepository;
    private final LectureDetailRepository lectureDetailRepository;


    // 카테고리별 추천 강좌 조회
    public LectureResponseDTO.CategoryRecommendLectureDto findRecommendedLectureByCategory(String category) {

        if (category == null) {
            // 카테고리 프로그래밍 언어로 고정해서 가져와라
            category = "Programming Languages";
        }
        List<LectureUdemy> lectureUdemyList = lectureUdemyRepository.findAllBySubcategory(category);

        // 일단 가져오고 상위 값 추출
        List<LectureUdemy> sortedList = lectureUdemyList.stream()
                .sorted(Comparator.comparingDouble((LectureUdemy lecture) -> lecture.getAvgRating() * lecture.getNumReviews()).reversed())
                .collect(Collectors.toList());

        return LectureConverter.toCategoryRecommendLectureDto(sortedList);
    }

    // 선택된 키워드별 추천 강좌 조회
    public LectureResponseDTO.KeywordRecommendLectureDto findRecommendedLectureByKeyword(String keyword) {
        // 키워드가 null이거나 빈 문자열인 경우에 대한 예외 처리
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword must not be null or empty");
        }

        // 키워드를 포함하는 강의 목록을 조회(topic 데이터에 있는지 확인)
        List<LectureUdemy> lectureUdemyList = lectureUdemyRepository.findAllByTopic(keyword);

        // 조회된 강의 목록을 AvgRating과 NumReviews의 값을 곱한 값으로 내림차순 정렬
        List<LectureUdemy> sortedList = lectureUdemyList.stream()
                .sorted(Comparator.comparingDouble((LectureUdemy lecture) -> lecture.getAvgRating() * lecture.getNumReviews()).reversed())
                .collect(Collectors.toList());

        // 조회된 강의 목록을 DTO로 변환하여 반환
        return LectureConverter.toKeywordRecommendLectureDto(sortedList);
    }

    public LectureResponseDTO.LectureDetailDto findLectureDetail() {
        return null;
    }

    public LectureResponseDTO.OtherStudentsDto findOtherStudents() {
        return null;
    }

    public LectureResponseDTO.OtherLectureListDto findOtherLectureList() {
        return null;
    }


    public LectureResponseDTO.StudentTopwordDto findStudentTopword() {
        return null;
    }
}
