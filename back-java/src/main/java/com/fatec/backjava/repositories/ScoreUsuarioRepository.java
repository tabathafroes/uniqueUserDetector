package com.fatec.backjava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fatec.backjava.domain.Usuario;
import com.fatec.backjava.dto.UsuarioUnicoDTO;

@Repository
public interface ScoreUsuarioRepository extends JpaRepository<Usuario, Long>{

}