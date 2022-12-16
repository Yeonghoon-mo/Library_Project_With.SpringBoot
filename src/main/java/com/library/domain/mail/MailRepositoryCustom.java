package com.library.domain.mail;

import java.util.Optional;

public interface MailRepositoryCustom {

    // 이메일 인증 시간 만료여부 확인
    Optional<Mail> findValidAuthByEmail(final MailRequest params);

}
