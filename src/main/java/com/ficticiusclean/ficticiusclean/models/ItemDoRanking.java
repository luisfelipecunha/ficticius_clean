package com.ficticiusclean.ficticiusclean.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ItemDoRanking {
	String nome;
	String marca;
	String modelo;
	Integer anoDeFabricacao;
	BigDecimal qtdeDeCombustivel;
	BigDecimal valorGasto;
}
