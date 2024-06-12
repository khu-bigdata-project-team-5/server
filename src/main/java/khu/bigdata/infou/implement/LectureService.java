package khu.bigdata.infou.implement;

import khu.bigdata.infou.business.LectureConverter;
import khu.bigdata.infou.domain.LectureDetail;
import khu.bigdata.infou.domain.LectureUdemy;
import khu.bigdata.infou.domain.PlatformStudent;
import khu.bigdata.infou.repository.LectureDetailRepository;
import khu.bigdata.infou.repository.LectureInflearnRepository;
import khu.bigdata.infou.repository.LectureUdemyRepository;
import khu.bigdata.infou.repository.PlatformStudentRepository;
import khu.bigdata.infou.web.dto.LectureResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class LectureService {

    private final PlatformStudentRepository platformStudentRepository;
    private final LectureInflearnRepository lectureInflearnRepository;
    private final LectureUdemyRepository lectureUdemyRepository;
    private final LectureDetailRepository lectureDetailRepository;


    /**
     * 카테고리별 추천 강좌 조회
     */
    public LectureResponseDTO.CategoryRecommendLectureDto findRecommendedLectureByCategory(String category) {

        if (category == null) {
            // 카테고리 프로그래밍 언어로 고정해서 가져와라
            category = "Programming Languages";
        }
        List<LectureUdemy> lectureUdemyList = lectureUdemyRepository.findAllBySubcategory(category);

        // 일단 가져오고 상위 값 추출
        List<LectureUdemy> sortedList = lectureUdemyList.stream()
                .sorted(Comparator.comparingDouble((LectureUdemy lecture) -> lecture.getAvgRating() * lecture.getNumReviews()).reversed())
                .limit(1000) // 1000개로 제한
                .collect(Collectors.toList());

        return LectureConverter.toCategoryRecommendLectureDto(sortedList);
    }

    /**
     * 선택된 키워드별 추천 강좌 조회
     */
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
                .limit(1000) // 1000개로 제한
                .collect(Collectors.toList());

        // 조회된 강의 목록을 DTO로 변환하여 반환
        return LectureConverter.toKeywordRecommendLectureDto(sortedList);
    }

    /**
     * 강좌 세부 조회
     */
    public LectureResponseDTO.LectureDetailDto findLectureDetail(Integer lectureId) {

        // lectureId가 null인 경우에 대한 예외 처리
        if (lectureId == null) {
            throw new IllegalArgumentException("LectureId must not be null");
        }

        LectureUdemy lectureUdemy = lectureUdemyRepository.findByLectureId(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("Lecture not found"));
        LectureDetail lectureDetail = lectureDetailRepository.findByLectureUdemyId(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("Lecture detail not found"));

        return LectureConverter.toLectureDetailDto(lectureUdemy, lectureDetail);
    }

    /**
     * 다른 수강생들의 커리큘럼 전체 조회
     */
    public LectureResponseDTO.OtherStudentsDto findOtherStudents() {

        Pageable pageable = PageRequest.of(0, 1000);

        // 쿼리 실행
        List<Object[]> topStudents = platformStudentRepository.findTopStudents(pageable);

        // 조회된 데이터를 List로 변환
        List<PlatformStudent> studentList = new ArrayList<>();
        for (Object[] result : topStudents) {
            Integer userId = (Integer) result[0];
            List<PlatformStudent> students;
            if (userId != null) {
                students = platformStudentRepository.findByInflearnUserId(userId);
                if (students.isEmpty()) {
                    students = platformStudentRepository.findByUdemyUserId(userId);
                }
            } else {
                students = new ArrayList<>();
            }
            studentList.addAll(students);
        }

        return LectureConverter.toOtherStudentsDto(studentList);
    }

    public LectureResponseDTO.OtherLectureListDto findOtherLectureList() {
        return null;
    }

}
