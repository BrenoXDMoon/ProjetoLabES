package br.com.fatec.ChopperHouseGames.inbound.facade.mapper;

import br.com.fatec.ChopperHouseGames.core.domain.CartaoCredito;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.CartaoCreditoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartaoCreditoMapper {

    CartaoCredito toCartao(CartaoCreditoDTO dto);

    CartaoCreditoDTO toCartaoDTO(CartaoCredito cartaoCredito);

    List<CartaoCreditoDTO> toCartaoCreditoDTOList(List<CartaoCredito> cartoes);

}
