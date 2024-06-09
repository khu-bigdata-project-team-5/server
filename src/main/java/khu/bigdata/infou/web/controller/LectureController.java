package khu.bigdata.infou.web.controller;

import khu.bigdata.infou.implement.LectureService;
import khu.bigdata.infou.web.dto.LectureResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class LectureController {

    private final LectureService lectureService;

    @GetMapping("/lectures/categories")
    public ResponseEntity<LectureResponseDTO.CategoryRecommendLectureDto> getRecommendedLectureByCategory(
            @RequestParam(name = "category") String category
    ) {
        return ResponseEntity.ok(lectureService.findRecommendedLectureByCategory(category));
    }


    @GetMapping("/curriculum/recommendation")
    // 설명 시작
    public ResponseEntity<LectureResponseDTO.KeywordRecommendLectureDto> getRecommendedLectureByKeyword(
            @RequestParam(name = "keyword") String keyword
    ) {
        return ResponseEntity.ok(lectureService.findRecommendedLectureByKeyword(keyword));
    }

}
