package agenciaviajes.presentacion;

import agenciaviajes.negocio.Cliente;
import agenciaviajes.negocio.GestorClientes;
import agenciaviajes.utils.Utilidades;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mvcf.AActionController;
import mvcf.AModel;
import mvcf.AView;

/**
 *
 * @author Libardo, Ricardo, Julio
 */
public class GUIClientesController extends AActionController {

    private final GestorClientes gestor; // Modelo
    private final GUIClientesPotenciales vista; //Vista

    public GUIClientesController(AModel myModel, AView myView) {
        super(myModel, myView);
        this.gestor = (GestorClientes) myModel;
        this.vista = (GUIClientesPotenciales) myView;
    }

    /**
     * Este método se invoca automáticamente desde GUICliente al presionar los
     * botones de accion
     *
     * @param e objeto tipo ActionEvent que indica qué boton se presionó
     */
    @Override
    public void actualizar(ActionEvent e) {
        
        ArrayList<Cliente> lista = gestor.obtenerClientesServidor();
        
        
    }

   

}
