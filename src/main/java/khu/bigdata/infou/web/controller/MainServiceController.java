package khu.bigdata.infou.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import khu.bigdata.infou.implement.LectureService;
import khu.bigdata.infou.web.dto.LectureResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class MainServiceController {

    private final LectureService lectureService;

    @GetMapping("/lectures/categories")
    @Operation(summary = "카테고리별 추천 강좌 조회 API", description = "카테고리: Programming Language, Web Development, Game Development, Mobile Development, Data Science")
    public ResponseEntity<LectureResponseDTO.CategoryRecommendLectureDto> getRecommendedLectureByCategory(
            @RequestParam(name = "category") String category
    ) {
        return ResponseEntity.ok(lectureService.findRecommendedLectureByCategory(category));
    }


    @GetMapping("/curriculum/recommendation")
    @Operation(summary = "선택된 키워드별 추천 강좌 조회 API", description = "유저로부터 입력받은 키워드와 일치하는 강좌를 조회합니다.")
    public ResponseEntity<LectureResponseDTO.KeywordRecommendLectureDto> getRecommendedLectureByKeyword(
            @RequestParam(name = "keyword") String keyword
    ) {
        return ResponseEntity.ok(lectureService.findRecommendedLectureByKeyword(keyword));
    }


    @GetMapping("/lectures/{lectureId}")
    @Operation(summary = "강좌 세부 조회 API", description = "특정 강좌의 lecture_id를 입력받습니다.")
    public ResponseEntity<LectureResponseDTO.LectureDetailDto> getLectureDetail(
            @PathVariable(name = "lectureId") Integer lectureId
    ) {
        return ResponseEntity.ok(lectureService.findLectureDetail(lectureId));
    }

    /**
     * TODO
     */
    @GetMapping("/others")
    @Operation(summary = "다른 수강생들의 커리큘럼 전체 조회 API")
    public ResponseEntity<LectureResponseDTO.OtherStudentsDto> getOtherStudents() {
        return ResponseEntity.ok(lectureService.findOtherStudents());
    }

    @GetMapping("/others/{userId}/lectures")
    @Operation(summary = "특정 수강생이 들은 강좌 목록 조회 API", description = "특정 유저의 user_id를 입력받습니다.")
    public ResponseEntity<LectureResponseDTO.OtherLectureListDto> getOtherLectureList(
            @PathVariable(name = "userId") Integer userId
    ) {
        return ResponseEntity.ok(lectureService.findOtherLectureList());
    }
}
