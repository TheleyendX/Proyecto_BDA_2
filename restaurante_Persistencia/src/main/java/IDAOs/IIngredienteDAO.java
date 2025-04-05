/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import Entidades.Ingrediente;
import Excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface IIngredienteDAO {
    
    public Ingrediente registrarIngrediente(Ingrediente ingrediente) throws PersistenciaException;
    
    public List<Ingrediente> obtenerIngredientes ()throws PersistenciaException;
    
    public Ingrediente buscarNombre (String nombre) throws PersistenciaException;
    
    public Ingrediente buscarPorUnidad (Enum unidadMedida) throws PersistenciaException;

    public Ingrediente eliminarIngrediente (Ingrediente ingrediente) throws PersistenciaException;

    public Ingrediente editarIngrediente (Ingrediente ingrediente) throws PersistenciaException; 
    
}
