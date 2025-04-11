//package Mapper;
//
//import DTOs.ComandaDTO;
//import Entidades.Comanda;
//import Entidades.DetallesComanda;
//import java.util.List;
//import java.util.stream.Collectors;
//import DTOs.DetallesComandaDTO;
//
///**
// *
// * @author mario
// */
//public class ComandaMapper {
//
//    private final ClienteMapper clienteMapper = new ClienteMapper();
//    private final MesaMapper mesaMapper = new MesaMapper();
//    private final DetallesComandaMapper detallesMapper = new DetallesComandaMapper();
//
//    public Comanda toEntity(ComandaDTO dto) {
//        if (dto == null) {
//            return null;
//        }
//
//        List<DetallesComanda> detalles = dto.getDetallesComanda() != null
//                ? dto.getDetallesComanda().stream()
//                        .map(detallesMapper::toEntity)
//                        .collect(Collectors.toList())
//                : null;
//
//        return new Comanda(
//                dto.getId(),
//                dto.getEstado(),
//                dto.getFechaHora(),
//                dto.getFolio(),
//                dto.getTotalVenta(),
//                detalles,
//                clienteMapper.toEntity(dto.getCliente()),
//                mesaMapper.toEntity(dto.getMesa())
//        );
//    }
//
//    public ComandaDTO toDTO(Comanda entity) {
//        if (entity == null) {
//            return null;
//        }
//
//        List<DetallesComandaDTO> detalles = entity.getDetallesComanda() != null
//                ? entity.getDetallesComanda().stream()
//                        .map(detallesMapper::toDTO) // Este debe devolver DetallesComandaDTO
//                        .collect(Collectors.toList())
//                : null;
//
//        return new ComandaDTO(
//                entity.getId(),
//                entity.getEstado(),
//                entity.getFechaHora(),
//                entity.getFolio(),
//                entity.getTotalVenta(),
//                detalles,
//                clienteMapper.toDTO(entity.getCliente()),
//                mesaMapper.toDTO(entity.getMesa())
//        );
//    }
//}
