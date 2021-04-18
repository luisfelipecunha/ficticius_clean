package com.ficticiusclean.ficticiusclean.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ficticiusclean.ficticiusclean.FicticiuscleanApplicationTests;
import com.ficticiusclean.ficticiusclean.builders.CreateVeiculoCommandBuilder;
import com.ficticiusclean.ficticiusclean.repositories.VeiculoRepository;
import com.ficticiusclean.ficticiusclean.services.veiculo.VeiculoService;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@FieldDefaults(level = PRIVATE)
public class RankingControllerTest extends FicticiuscleanApplicationTests {
	MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	VeiculoRepository veiculoRepository;

	@Autowired
	VeiculoService veiculoService;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.build();
		var commands = CreateVeiculoCommandBuilder.variosVeiculos();
		commands.forEach(this.veiculoService::create);
	}

	@AfterEach
	public void tearDown() {
		this.veiculoRepository.deleteAll();
	}

	@Test
	public void getRanking_200() throws Exception {
		this.mockMvc.perform(
				get(RankingController.BASE_URL)
					.param("precoGasolina", "9.5")
					.param("distanciaCidade", "100")
					.param("distanciaRodovias", "200")
		)
		.andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$.[0].nome").value("Clio"))
		.andExpect(jsonPath("$.[0].qtdeDeCombustivel").value("23"))
		.andExpect(jsonPath("$.[0].valorGasto").value("218.5"));
	}
}
