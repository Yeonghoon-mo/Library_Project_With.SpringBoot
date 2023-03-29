package com.library.domain.member;

import com.library.domain.attach.FileResponse;
import com.library.domain.entity.BaseEntity;
import com.library.status.YnStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tb_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                        // PK

    @Enumerated(EnumType.STRING)
    private MemberType type;                // 회원 유형

    private String loginId;                 // 이메일(ID)
    private String password;                // 비밀번호
    private String name;                    // 회원 이름
    private String profileImage;            // 프로필 이미지
    private String authorityYn;             // 정회원 승인여부
    private YnStatus deleteYn;              // 삭제 여부

    @Builder
    public Member(MemberType type, String loginId, String password, String name, String profileImage, String authorityYn, YnStatus deleteYn) {
        this.type = type;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
        this.authorityYn = authorityYn;
        this.deleteYn = deleteYn;
    }

    // 회원 정보 수정
    public void update(MemberRequest params) {
        this.password = params.getPassword();
        this.name = params.getName();
        this.deleteYn = params.getDeleteYn();
    }

    // 프로필 이미지 수정
    public void updateProfileImage(FileResponse profileImage) {
        this.profileImage = (profileImage) == null ? this.profileImage : profileImage.getSaveFilename();
    }

    // 비밀번호 수정
    public void updatePassword(String password) {
        this.password = password;
    }

    // 회원 탈퇴
    public void delete() {
        this.deleteYn = YnStatus.Y;
    }

    // 정회원 회원가입 승인
    public void regularMemberAuthorityYn() {
        this.authorityYn = "Y";
    }

}
