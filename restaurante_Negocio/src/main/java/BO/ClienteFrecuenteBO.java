/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAOs.ClienteFrecuenteDAO;
import DTOs.ClienteFrecuenteDTO;
import DTOs.ComandaDTO;
import Entidades.ClienteFrecuente;
import Excepciones.NegocioException;
import Excepciones.PersistenciaException;
import IDAOs.IClienteFrecuenteDAO;
import Mapper.ClienteFrecuenteMapper;
import java.util.List;

/**
 *
 * @author katia
 */
public class ClienteFrecuenteBO {
    private final IClienteFrecuenteDAO clienteFrecuenteDAO;
    private final ClienteFrecuenteMapper mapperClienteFrecuente;

    public ClienteFrecuenteBO() {
        this.clienteFrecuenteDAO = new ClienteFrecuenteDAO();
        this.mapperClienteFrecuente = new ClienteFrecuenteMapper();
    }

    public ClienteFrecuenteDTO registrarClienteFrecuente(ClienteFrecuenteDTO dto) throws NegocioException {
        dto.setNombre(dto.getNombre().trim());
        dto.setApellidoP(dto.getApellidoP().trim());
        if (dto.getApellidoM() != null) {
            dto.setApellidoM(dto.getApellidoM().trim());
        }
        dto.setTelefono(dto.getTelefono().trim());
        if (dto.getCorreo() != null) {
            dto.setCorreo(dto.getCorreo().trim());
        }
        
        validarCliente(dto);
        validarDuplicados(dto);

        ClienteFrecuente clienteFrecuenteEntidad = mapperClienteFrecuente.toEntity(dto);

        try {
            ClienteFrecuente registrado = clienteFrecuenteDAO.registrarClienteFrecuente(clienteFrecuenteEntidad);
            return mapperClienteFrecuente.toDTO(registrado);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar cliente frecuente.", e);
        }
    }

    public List<ClienteFrecuenteDTO> obtenerTodos() throws NegocioException {
        try {
            List<ClienteFrecuente> clientes = clienteFrecuenteDAO.obtenerTodos();
            return clientes.stream().map(mapperClienteFrecuente::toDTO).toList();
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener clientes frecuentes.", e);
        }
    }

    public List<ClienteFrecuenteDTO> filtrarClientesFrecuentes(String nombre, String telefono, String correo) throws NegocioException {
        try {
            List<ClienteFrecuente> clientes = clienteFrecuenteDAO.filtrarClientesFrecuentes(nombre, telefono, correo);
            return clientes.stream().map(mapperClienteFrecuente::toDTO).toList();
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al filtrar clientes frecuentes.", e);
        }
    }

    public ClienteFrecuenteDTO buscarPorId(Long id) throws NegocioException {
        try {
            ClienteFrecuente cliente = clienteFrecuenteDAO.buscarPorId(id);
            if (cliente == null) {
                throw new NegocioException("No se encontró el cliente con ID: " + id);
            }
            return mapperClienteFrecuente.toDTO(cliente);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar cliente frecuente por ID.", e);
        }
    }

    private void validarCliente(ClienteFrecuenteDTO dto) throws NegocioException {
        if (dto.getNombre() == null || dto.getNombre().isBlank())
            throw new NegocioException("El nombre es obligatorio.");
        if (dto.getApellidoP() == null || dto.getApellidoP().isBlank())
            throw new NegocioException("El apellido paterno es obligatorio.");
        if (dto.getTelefono() == null || dto.getTelefono().isBlank())
            throw new NegocioException("El teléfono es obligatorio.");
        if (!dto.getTelefono().matches("\\d{10}"))
            throw new NegocioException("El teléfono debe contener 10 dígitos.");
        if (dto.getCorreo() != null && !dto.getCorreo().isBlank() &&
            !dto.getCorreo().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new NegocioException("El correo electrónico no tiene un formato válido.");
        }
    }

    private void validarDuplicados(ClienteFrecuenteDTO clienteFDTO) throws NegocioException {
        try {
            List<ClienteFrecuenteDTO> existentes = obtenerTodos();
            String telefono = clienteFDTO.getTelefono();
            String correo = clienteFDTO.getCorreo();

            for (ClienteFrecuenteDTO existente : existentes) {
                if (existente.getTelefono().equals(telefono)) {
                    throw new NegocioException("Ya existe un cliente con el mismo teléfono.");
                }
                if (correo != null && !correo.isBlank() &&
                    existente.getCorreo() != null &&
                    existente.getCorreo().equalsIgnoreCase(correo)) {
                    throw new NegocioException("Ya existe un cliente con el mismo correo.");
                }
            }
        } catch (NegocioException e) {
            throw e;
        }
    }
}
