package com.ficticiusclean.ficticiusclean.services.ranking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class RankingParams {
	@NotNull
	@NonNull
	BigDecimal precoGasolina;

	@NotNull
	@NonNull
	BigDecimal totalKmPercorridoCidade;

	@NotNull
	@NonNull
	BigDecimal totalKmPercorridoRodovia;
}
