package com.library.domain.entity;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    private LocalDateTime createdDate; // 생성 일시
    private LocalDateTime modifiedDate; // 수정 일시

    // DB에 해당 테이블의 insert 연산을 실행할 때 같이 실행해라 라는 의미의 어노테이션
    @PrePersist
    public void prePersist() {
        createdDate = LocalDateTime.now();
    }

    // 실제 DB에 있는 데이터가 변경되는 경우 호출된다.
    @PreUpdate
    public void preUpdate() {
        modifiedDate = LocalDateTime.now();
    }

}
