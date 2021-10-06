package com.fatec.backjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.backjava.domain.DadosEntropicosUser;

@Repository
public interface DadosEntropicosUserRepository extends JpaRepository<DadosEntropicosUser, Long>{

}