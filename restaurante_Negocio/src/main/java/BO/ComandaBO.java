/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;


import DAOs.ComandaDAO;
import IDAOs.IComandaDAO;
import Interfaces.IComandaBO;

/**
 *
 * @author mmax2
 */
public class ComandaBO implements IComandaBO{
    private final IComandaDAO comanda = new ComandaDAO();
}
