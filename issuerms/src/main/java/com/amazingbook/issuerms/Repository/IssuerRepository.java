package com.amazingbook.issuerms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazingbook.issuerms.model.IssuerBO;

public interface IssuerRepository extends JpaRepository<IssuerBO, Integer>{
}
