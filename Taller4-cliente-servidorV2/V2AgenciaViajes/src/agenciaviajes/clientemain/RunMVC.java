package agenciaviajes.clientemain;

import agenciaviajes.negocio.GestorClientes;

import agenciaviajes.presentacion.GUIClientesController;
import agenciaviajes.presentacion.GUIClientesPotenciales;
import mvcf.AView;

/**
 * Es el pegamento de la aplición
 *
 * @author Libardo, Julio, Ricardo
 */
public class RunMVC {

    public RunMVC() {
        GestorClientes gestor = new GestorClientes();

        GUIClientesPotenciales viewClientesPot = new GUIClientesPotenciales();
        GUIClientesController viewController = new GUIClientesController(gestor, viewClientesPot);
        
        gestor.addView(viewClientesPot);
        viewClientesPot.obtenerButon().addActionListener(viewController);

     
        


    }
}
