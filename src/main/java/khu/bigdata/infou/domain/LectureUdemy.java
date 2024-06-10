package khu.bigdata.infou.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Entity
@Getter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 생성 로직 규정
@Table(name = "lecture_udemy")
public class LectureUdemy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_udemy_id")
    private int id;

    @Column(name = "lecture_id", nullable = false)
    private int lectureId;

    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @Column(name = "is_paid")
    private boolean isPaid;

    @Column(name = "price")
    private float price;

    @Column(name = "headline", columnDefinition = "TEXT")
    private String headline;

    @Column(name = "num_subscribers")
    private float numSubscribers;

    @Column(name = "avg_rating")
    private float avgRating;

    @Column(name = "num_reviews")
    private float numReviews;

    @Column(name = "num_comments")
    private float numComments;

    @Column(name = "num_lectures")
    private float numLectures;

    @Column(name = "content_length_min")
    private float contentLengthMin;

    @Column(name = "category", length = 255)
    private String category;

    @Column(name = "subcategory", length = 50)
    private String subcategory;

    @Column(name = "topic", length = 50)
    private String topic;

    @Column(name = "language", length = 20)
    private String language;

    @Column(name = "course_url", columnDefinition = "TEXT")
    private String courseUrl;

    @Column(name = "instructor_name", length = 50)
    private String instructorName;

    @Column(name = "instructor_url", columnDefinition = "TEXT")
    private String instructorUrl;

    @Column(name = "thumbnail", columnDefinition = "TEXT")
    private String thumbnail;

    @Column(name = "published_time")
    @Temporal(TemporalType.DATE)
    private Date publishedTime;

    @Column(name = "last_update_date")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private PlatformStudentLecture platformStudentLecture;

    @OneToOne(mappedBy = "lectureUdemy")
    private LectureTag lectureTag;

    public void setPlatformStudentLecture(PlatformStudentLecture platformStudentLecture) {
        if (this.platformStudentLecture != null) {
            this.platformStudentLecture.getLectureUdemyList().remove(this);
        }
        this.platformStudentLecture = platformStudentLecture;
    }

    // getters and setters

}


