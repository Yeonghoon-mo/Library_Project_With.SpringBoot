package com.library.domain.attach;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachRepository extends JpaRepository<Attach, Long>, AttachRepositoryCustom {

}
