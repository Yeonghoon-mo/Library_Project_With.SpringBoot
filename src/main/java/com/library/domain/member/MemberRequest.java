package com.library.domain.member;

import com.library.domain.attach.FileResponse;
import com.library.domain.regularmember.RegularMember;
import com.library.status.YnStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class MemberRequest {

    // 회원 member 변수
    private MemberType type;          // 회원 유형
    private String loginId;           // 이메일(ID)
    private String password;          // 비밀번호
    private String name;              // 회원 이름
    private String profileImage;      // 프로필 이미지
    private String authorityYn;       // 정회원 승인여부
    private YnStatus deleteYn;        // 삭제 여부

    // 정회원 member 변수
    private String phone;             // 연락처

    /** Entity Class 컬럼에 등록되어 있는 데이터를 받는 것이 아닌 직접 선언한 멤버변수 */
    private MultipartFile thumbnail;       // 프로필 이미지

    @Builder
    public MemberRequest(MemberType type, String loginId, String password, String name, String profileImage, YnStatus deleteYn, String authorityYn, String phone) {
        // 일반회원
        this.type = type;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
        this.authorityYn = authorityYn;
        this.deleteYn = deleteYn;

        // 정회원 추가
        this.phone = phone;
    }

    public void encodingPassword(String encodedPassword) {
        if (StringUtils.isNotBlank(encodedPassword)) {
            this.password = encodedPassword;
        }
    }

    // 관리자 Entity 객체 생성
    public Member toEntity() {
        return Member.builder()
                .type(type)
                .loginId(loginId)
                .password(password)
                .name(name)
                .authorityYn(authorityYn)
                .deleteYn(deleteYn)
                .build();
    }

    // 회원 Entity 객체 생성
    public Member toEntity(FileResponse profileImage) {
        return Member.builder()
                .type(type)
                .loginId(loginId)
                .password(password)
                .name(name)
                .profileImage((profileImage == null) ? "" : profileImage.getSaveFilename())
                .authorityYn(authorityYn)
                .deleteYn(deleteYn)
                .build();
    }

    // 정회원 Entity 객체 생성
    public RegularMember toEntity(Member member) {
        return RegularMember.builder()
                .member(member)
                .phone(phone)
                .build();
    }
}
