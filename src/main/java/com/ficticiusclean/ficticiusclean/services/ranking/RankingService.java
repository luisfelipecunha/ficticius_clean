package com.ficticiusclean.ficticiusclean.services.ranking;

import com.ficticiusclean.ficticiusclean.models.ItemDoRanking;
import com.ficticiusclean.ficticiusclean.repositories.VeiculoRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class RankingService {
	VeiculoRepository veiculoRepository;

	public List<ItemDoRanking> getRanking(
			RankingParams rankingParams,
			Pageable pageable
	) {
		var pageRequest = PageRequest.of(
				pageable.getPageNumber(),
				pageable.getPageSize(),
				Sort.Direction.DESC,
				"consumoMedioCidade", "consumoMedioRodovia"
		);
		var veiculos = this.veiculoRepository.findAll(pageRequest);

		return veiculos
				.stream()
				.map(veiculo -> {

					var combustivelCidade = rankingParams
							.getTotalKmPercorridoCidade()
							.divide(veiculo.getConsumoMedioCidade(), RoundingMode.HALF_EVEN);

					var combustivelRodovia = rankingParams
							.getTotalKmPercorridoRodovia()
							.divide(veiculo.getConsumoMedioRodovia(), RoundingMode.HALF_EVEN);

					var totalCombustivel = combustivelCidade.add(combustivelRodovia);
					var valorGasto = rankingParams.getPrecoGasolina()
							.multiply(totalCombustivel);

					return new ItemDoRanking(
							veiculo.getNome(),
							veiculo.getMarca(),
							veiculo.getModelo(),
							veiculo.getAnoDeFabricacao(),
							totalCombustivel,
							valorGasto
					);
				})
				.collect(Collectors.toList());
	}
}
