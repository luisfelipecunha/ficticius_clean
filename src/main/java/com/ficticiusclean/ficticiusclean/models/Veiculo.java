package com.ficticiusclean.ficticiusclean.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity
@AllArgsConstructor
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Veiculo {
	@Id
	@GeneratedValue
	@Type(type = "uuid-char")
	UUID id;

	@NonNull
	String nome;

	@NonNull
	String marca;

	@NonNull
	String modelo;

	@NonNull
	Integer anoDeFabricacao;

	@NonNull
	BigDecimal consumoMedioCidade;

	@NonNull
	BigDecimal consumoMedioRodovia;
}
