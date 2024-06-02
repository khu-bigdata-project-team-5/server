package khu.bigdata.infou.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lecture")
@Getter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 생성 로직 규정
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private boolean isPaid;
    private double price;
    private String headline;
    private int numSubscribers;
    private double avgRating;
    private int numReviews;
    private int numComments;
    private int numLectures;
    private int contentLengthMin;
    private Date publishedTime;
    private Date lastUpdateDate;
    private String category;
    private String subcategory;
    private String topic;
    private String language;
    private String courseUrl;
    private String instructorName;
    private String instructorUrl;
    private String thumbnail;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private List<LectureTag> lectureTagList = new ArrayList<>();

    // Getters and Setters
}
