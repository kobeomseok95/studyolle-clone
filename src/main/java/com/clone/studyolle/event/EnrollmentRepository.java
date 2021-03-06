package com.clone.studyolle.event;

import com.clone.studyolle.account.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByEventAndAccount(Event event, Account account);

    Enrollment findByEventAndAccount(Event event, Account account);

    @EntityGraph("Enrollment.withEventAndStudy")
    Enrollment findByAccountAndAcceptedOrderByEnrolledAtDesc(Account account, boolean accepted);
}
