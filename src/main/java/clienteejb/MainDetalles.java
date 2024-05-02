package clienteejb;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ec.edu.ups.ppw63.demo63.business.GestionClientesRemoto;
import ec.edu.ups.ppw63.demo63.business.GestionDetalleFacturasRemoto;
import ec.edu.ups.ppw63.demo63.model.Cliente;
import ec.edu.ups.ppw63.demo63.model.DetalleFactura;
import ec.edu.ups.ppw63.demo63.view.ClienteCRUD;
import ec.edu.ups.ppw63.demo63.view.FacturaCRUD;

public class MainDetalles {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
            
			try {
				final Hashtable<String, String> jndiProperties = new Hashtable<>();
	            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
	            jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
	            jndiProperties.put(Context.SECURITY_PRINCIPAL, "edu"); // Reemplaza 'username' con tu nombre de usuario
	            jndiProperties.put(Context.SECURITY_CREDENTIALS, "edu12345"); // Reemplaza 'password' con tu contraseña
	            jndiProperties.put("jboss.naming.client.ejb.context", "true");

	            Context context;
				context = new InitialContext(jndiProperties);
				GestionDetalleFacturasRemoto gestionDetalles = (GestionDetalleFacturasRemoto) context.lookup("ejb:/demo63/GestionDetalleFacturas!ec.edu.ups.ppw63.demo63.business.GestionDetalleFacturasRemoto");

	            // Uso del EJB
	            DetalleFactura detalle = new DetalleFactura();
	            detalle.setCodigo(1);
	            detalle.setCantidad(2);
	            detalle.setNombre("Condon");
	            detalle.setPrecio(3.0);
	            gestionDetalles.guardarDetalles(detalle);
	            System.out.println("Detalle creado");
	            
	            FacturaCRUD facturaCRUD = new FacturaCRUD(gestionDetalles);
	            
	            
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            

        

	}

}