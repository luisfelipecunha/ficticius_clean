package com.ficticiusclean.ficticiusclean.services.veiculo;

import com.ficticiusclean.ficticiusclean.models.Veiculo;
import com.ficticiusclean.ficticiusclean.repositories.VeiculoRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class VeiculoService {
	VeiculoRepository veiculoRepository;

	public Veiculo create(CreateVeiculoCommand command) {
		var veiculo = new Veiculo(
				command.getNome(),
				command.getMarca(),
				command.getModelo(),
				command.getAnoDeFabricacao(),
				command.getConsumoMedioCidade(),
				command.getConsumoMedioRodovias()
		);

		return this.veiculoRepository.save(veiculo);
	}
}
