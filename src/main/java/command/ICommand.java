/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package command;

import java.sql.SQLException;

/**
 *
 * @author caiof
 */
public interface ICommand {    
    void executar() throws SQLException;
}
