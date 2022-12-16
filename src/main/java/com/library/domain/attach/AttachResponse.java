package com.library.domain.attach;

import com.library.status.YnStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttachResponse {

    private Long id; // 파일 번호(PK)
    private String originalName; // 원본 파일명
    private String saveName; // 저장 파일명
    private Long size; // 파일 사이즈
    private YnStatus deleteYn; // 삭제 여부
    private LocalDateTime createdDate; // 생성일
    private LocalDateTime modifiedDate; // 삭제일

    public AttachResponse(Attach entity){
        this.id = entity.getId();
        this.originalName = entity.getOriginalName();
        this.saveName = entity.getSaveName();
        this.size = entity.getSize();
        this.deleteYn = entity.getDeleteYn();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }

    public AttachResponse(String saveName) {
        this.saveName = saveName;
    }

}
