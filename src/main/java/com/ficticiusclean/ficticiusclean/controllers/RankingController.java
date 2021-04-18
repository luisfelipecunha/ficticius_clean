package com.ficticiusclean.ficticiusclean.controllers;

import com.ficticiusclean.ficticiusclean.models.ItemDoRanking;
import com.ficticiusclean.ficticiusclean.services.ranking.RankingParams;
import com.ficticiusclean.ficticiusclean.services.ranking.RankingService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/ranking")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class RankingController {
	static String BASE_URL = "/ranking";
	RankingService rankingService;

	@GetMapping
	public ResponseEntity<List<ItemDoRanking>> getRanking(
			@RequestParam BigDecimal precoGasolina,
			@RequestParam BigDecimal distanciaCidade,
			@RequestParam BigDecimal distanciaRodovias,
			@PageableDefault Pageable pageable
	) {
		var params = new RankingParams(
				precoGasolina,
				distanciaCidade,
				distanciaRodovias
		);

		var ranking = this.rankingService.getRanking(params, pageable);

		return ResponseEntity.ok(ranking);
	}
}
