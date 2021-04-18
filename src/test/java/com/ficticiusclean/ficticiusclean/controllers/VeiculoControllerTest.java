package com.ficticiusclean.ficticiusclean.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ficticiusclean.ficticiusclean.FicticiuscleanApplicationTests;
import com.ficticiusclean.ficticiusclean.repositories.VeiculoRepository;
import com.ficticiusclean.ficticiusclean.services.veiculo.CreateVeiculoCommand;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@FieldDefaults(level = PRIVATE)
public class VeiculoControllerTest extends FicticiuscleanApplicationTests {
	MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	VeiculoRepository veiculoRepository;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.build();
	}

	@AfterEach
	public void tearDown() {
		this.veiculoRepository.deleteAll();
	}

	@Test
	public void criaVeiculo_201() throws Exception {
		var veiculo = new CreateVeiculoCommand(
			"Corolla",
			"Toyota",
			"XEI",
			2001,
			new BigDecimal("9.5"),
			new BigDecimal("12.5")
		);
		String command = this.objectMapper.writeValueAsString(veiculo);
		this.mockMvc.perform(
				post(VeiculoController.BASE_URL)
				.content(command)
				.contentType(MediaType.APPLICATION_JSON)
		)
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.nome").value(veiculo.getNome()))
		.andExpect(jsonPath("$.marca").value(veiculo.getMarca()))
		.andExpect(jsonPath("$.modelo").value(veiculo.getModelo()))
		.andExpect(jsonPath("$.anoDeFabricacao").value(veiculo.getAnoDeFabricacao()))
		.andExpect(jsonPath("$.consumoMedioCidade").value(veiculo.getConsumoMedioCidade()))
		.andExpect(jsonPath("$.consumoMedioRodovia").value(veiculo.getConsumoMedioRodovias()));
	}

	@Test
	public void criaVeiculo_400() throws Exception {
		var veiculo = new CreateVeiculoCommand(
				"Corolla",
				"Toyota",
				"XEI",
				0,
				new BigDecimal("9.5"),
				new BigDecimal("12.5")
		);
		String command = this.objectMapper.writeValueAsString(veiculo);
		this.mockMvc.perform(
				post(VeiculoController.BASE_URL)
						.content(command)
						.contentType(MediaType.APPLICATION_JSON)
		)
		.andDo(print())
		.andExpect(status().isBadRequest());
	}
}
