package com.ficticiusclean.ficticiusclean.builders;

import com.ficticiusclean.ficticiusclean.services.veiculo.CreateVeiculoCommand;

import java.math.BigDecimal;
import java.util.List;

public class CreateVeiculoCommandBuilder {
	CreateVeiculoCommand command;

	public CreateVeiculoCommand build() {
		return this.command;
	}

	public static CreateVeiculoCommandBuilder umVeiculo() {
		var commandBuilder = new CreateVeiculoCommandBuilder();
		commandBuilder.command = new CreateVeiculoCommand(
				"Corolla",
				"Toyota",
				"XEI",
				2001,
				new BigDecimal("9.5"),
				new BigDecimal("12.5")
		);
		return commandBuilder;
	}

	public static List<CreateVeiculoCommand> variosVeiculos() {
		return List.of(
				new CreateVeiculoCommand(
						"Corolla",
						"Toyota",
						"XEI",
						2001,
						new BigDecimal("9.5"),
						new BigDecimal("12.5")
				),
				new CreateVeiculoCommand(
						"Fiesta",
						"Ford",
						"SE",
						2014,
						new BigDecimal("10.5"),
						new BigDecimal("14.5")
				),
				new CreateVeiculoCommand(
						"Dakota",
						"Dodge",
						"Sport CE 3.9 V6",
						1998,
						new BigDecimal("5.5"),
						new BigDecimal("9.5")
				),
				new CreateVeiculoCommand(
						"Clio",
						"Renault",
						"Sed. Privil",
						2006,
						new BigDecimal("11.5"),
						new BigDecimal("14.5")
				)
		);
	}
}
