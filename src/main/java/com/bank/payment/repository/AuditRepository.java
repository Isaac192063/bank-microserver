package com.bank.payment.repository;

import com.bank.payment.entity.AuditTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<AuditTransaction, Long> {
}
