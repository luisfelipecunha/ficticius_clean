package com.ficticiusclean.ficticiusclean.controllers;

import com.ficticiusclean.ficticiusclean.models.Veiculo;
import com.ficticiusclean.ficticiusclean.services.veiculo.CreateVeiculoCommand;
import com.ficticiusclean.ficticiusclean.services.veiculo.VeiculoService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/veiculos")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class VeiculoController {
	static String BASE_URL = "/veiculos";
	VeiculoService veiculoService;

	@PostMapping
	public ResponseEntity<Veiculo> create(
			@Valid @RequestBody CreateVeiculoCommand command,
			UriComponentsBuilder uriComponentsBuilder
	) {
		var veiculoCriado = this.veiculoService.create(command);

		var uri = uriComponentsBuilder
				.path(BASE_URL)
				.buildAndExpand(veiculoCriado.getId())
				.toUri();

		return ResponseEntity
				.created(uri)
				.body(veiculoCriado);
	}
}
