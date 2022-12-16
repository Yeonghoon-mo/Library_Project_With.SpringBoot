package com.library.domain.mail;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.Optional;

public class MailRepositoryImpl implements MailRepositoryCustom {

    JPAQueryFactory jpaQueryFactory;

    public MailRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    /** 만료 여부 변경 */
    @Override
    public Optional<Mail> findValidAuthByEmail(final MailRequest params) {
        Mail mail = jpaQueryFactory
                .selectFrom(QMail.mail)
                .where(QMail.mail.email.eq(params.getEmail()),
                        QMail.mail.authToken.eq(params.getAuthToken()),
                        QMail.mail.expired.eq(false))
                .orderBy(QMail.mail.id.desc())
                .limit(1)
                .fetchFirst();

        return Optional.ofNullable(mail);
    }

}
