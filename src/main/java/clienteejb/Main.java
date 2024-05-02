package clienteejb;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import ec.edu.ups.ppw63.demo63.business.GestionClientesRemoto;
import ec.edu.ups.ppw63.demo63.model.Cliente;
import ec.edu.ups.ppw63.demo63.view.ClienteCRUD;
import ec.edu.ups.ppw63.demo63.view.FacturaCRUD;

public class Main {

    public static void main(String[] args) {

        try {
            final Hashtable<String, String> jndiProperties = new Hashtable<>();
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            jndiProperties.put(Context.SECURITY_PRINCIPAL, "edu");
            jndiProperties.put(Context.SECURITY_CREDENTIALS, "edu12345");
            jndiProperties.put("jboss.naming.client.ejb.context", "true");

            final Context context = new InitialContext(jndiProperties);
            GestionClientesRemoto gestionClientes = (GestionClientesRemoto) context.lookup("ejb:/demo63/GestionClientes!ec.edu.ups.ppw63.demo63.business.GestionClientesRemoto");

            // Uso del EJB
            Cliente cliente = new Cliente();
            cliente.setDni("0101010101");
            cliente.setNombre("Juan Perez");
            gestionClientes.guardarClientes(cliente);
            System.out.println("Cliente guardado!");
            ClienteCRUD clienteCRUD = new ClienteCRUD(gestionClientes);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
