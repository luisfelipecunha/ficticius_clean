package com.ficticiusclean.ficticiusclean.repositories;

import com.ficticiusclean.ficticiusclean.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {}
