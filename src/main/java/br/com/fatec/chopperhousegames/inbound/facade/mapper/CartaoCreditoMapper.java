package br.com.fatec.chopperhousegames.inbound.facade.mapper;

import br.com.fatec.chopperhousegames.core.domain.entity.CartaoCredito;
import br.com.fatec.chopperhousegames.inbound.facade.dto.CartaoCreditoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartaoCreditoMapper {

    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    CartaoCredito toCartao(CartaoCreditoDTO dto);

    CartaoCreditoDTO toCartaoDTO(CartaoCredito cartaoCredito);

    List<CartaoCreditoDTO> toCartaoCreditoDTOList(List<CartaoCredito> cartoes);

}
