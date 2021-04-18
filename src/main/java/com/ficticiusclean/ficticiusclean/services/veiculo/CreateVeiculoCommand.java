package com.ficticiusclean.ficticiusclean.services.veiculo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CreateVeiculoCommand {
	@NonNull
	@NotBlank
	String nome;

	@NonNull
	@NotBlank
	String marca;

	@NonNull
	@NotBlank
	String modelo;

	@NonNull
	@NotNull
	@Min(value = 1600)
	Integer anoDeFabricacao;

	@NonNull
	@NotNull
	BigDecimal consumoMedioCidade;

	@NonNull
	@NotNull
	BigDecimal consumoMedioRodovias;
}

