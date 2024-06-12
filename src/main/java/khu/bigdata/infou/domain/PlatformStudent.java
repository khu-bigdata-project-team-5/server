package khu.bigdata.infou.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 생성 로직 규정
@Table(name = "platform_student")
public class PlatformStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "udemy_user_id", nullable = false)
    private Integer udemyUserId;

    @Column(name = "inflearn_user_id", nullable = false)
    private Integer inflearnUserId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "topword1", length = 20)
    private String topword1;

    @Column(name = "topword2", length = 20)
    private String topword2;

    @Column(name = "topword3", length = 20)
    private String topword3;

    @Column(name = "topword4", length = 20)
    private String topword4;

    @Column(name = "topword5", length = 20)
    private String topword5;
}
