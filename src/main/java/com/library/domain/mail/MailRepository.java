package com.library.domain.mail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<Mail, Long>, MailRepositoryCustom {

}
