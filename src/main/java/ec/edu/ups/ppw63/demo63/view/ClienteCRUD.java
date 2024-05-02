
package ec.edu.ups.ppw63.demo63.view;
import javax.swing.*;

import ec.edu.ups.ppw63.demo63.business.GestionClientesRemoto;
import ec.edu.ups.ppw63.demo63.model.Cliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteCRUD extends JFrame {

    private JTextField codigoField, dniField, nombreField, direccionField;
    private JButton crearButton, leerButton, actualizarButton, borrarButton;
    private GestionClientesRemoto gestionClientesRemoto;
    private JTextArea resultadoTextArea;
    
    public ClienteCRUD(GestionClientesRemoto gestionClientesRemoto) {
        super("Cliente CRUD");
        this.gestionClientesRemoto = gestionClientesRemoto;

        // Crear los campos de texto
        codigoField = new JTextField(20);
        dniField = new JTextField(20);
        nombreField = new JTextField(20);
        direccionField = new JTextField(20);

        // Crear los botones
        crearButton = new JButton("Crear");
        leerButton = new JButton("Leer");
        actualizarButton = new JButton("Actualizar");
        borrarButton = new JButton("Borrar");
        
        resultadoTextArea = new JTextArea(10, 30);
        resultadoTextArea.setEditable(false);

        // Configurar el panel para organizar los elementos
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        panel.add(new JLabel("Código:"), gbc);
        gbc.gridy++;
        panel.add(new JLabel("DNI:"), gbc);
        gbc.gridy++;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridy++;
        panel.add(new JLabel("Dirección:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(codigoField, gbc);
        gbc.gridy++;
        panel.add(dniField, gbc);
        gbc.gridy++;
        panel.add(nombreField, gbc);
        gbc.gridy++;
        panel.add(direccionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(crearButton, gbc);
        gbc.gridy++;
        panel.add(leerButton, gbc);
        gbc.gridy++;
        panel.add(actualizarButton, gbc);
        gbc.gridy++;
        panel.add(borrarButton, gbc);
        panel.add(new JScrollPane(resultadoTextArea), gbc);

        // Configurar el JFrame
        add(panel);
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos de los campos de texto
                String codigo = codigoField.getText();
                String dni = dniField.getText();
                String nombre = nombreField.getText();
                String direccion = direccionField.getText();

                // Verificar si algún campo está vacío
                if (codigo.isEmpty() || dni.isEmpty() || nombre.isEmpty() || direccion.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Detener la ejecución si algún campo está vacío
                }

                // Crear un nuevo cliente con los datos ingresados
                Cliente cliente = new Cliente();
                cliente.setCodigo(Integer.parseInt(codigo));
                cliente.setDni(dni);
                cliente.setNombre(nombre);
                cliente.setDireccion(direccion);

                // Llamar al método guardarClientes del objeto gestionClientesRemoto
                try {
                    gestionClientesRemoto.guardarClientes(cliente);
                    resultadoTextArea.append("El cliente de id " + codigoField.getText() + " y nombre " + nombreField.getText() + " han sido creados de forma exitosa ! ");
                    // Limpiar los campos de texto después de guardar
                    limpiarCampos();
                } catch (Exception ex) {
                    // Manejar cualquier excepción que pueda ocurrir al guardar el cliente
                    JOptionPane.showMessageDialog(null, "Error al guardar el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace(); // Mostrar la traza de la excepción en la consola
                }
            }
        });
        
        leerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos de los campos de texto
                String dni = dniField.getText();
                if ( dni.isEmpty() ) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un dni .", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Detener la ejecución si algún campo está vacío
                }

                // Crear un nuevo cliente con los datos ingresados
                Cliente cliente = new Cliente();
                

                // Llamar al método guardarClientes del objeto gestionClientesRemoto
                try {
					cliente = gestionClientesRemoto.getClientePorCedula(dni);
					String nombre = cliente.getNombre();
	                String direccion = cliente.getDireccion();
	                System.out.println("Edit correcto");
	                resultadoTextArea.append("Los datos obtenidos del usuario con " + dni +" " + " son: " + cliente.getDireccion() + cliente.getNombre());
	                resultadoTextArea.append(nombre);
	                resultadoTextArea.append(direccion);
	                resultadoTextArea.append(cliente.getDni());
	                // Limpiar los campos de texto después de guardar
	                limpiarCampos();
	                dniField.setText(cliente.getDni());
	                direccionField.setText(cliente.getDireccion());
	                nombreField.setText(cliente.getNombre());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al leer el cliente: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
                
            }
        });
        
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos de los campos de texto
            	String codigoo = codigoField.getText();
                int codigo = Integer.parseInt(codigoo);
                if ( codigoo.isEmpty() ) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un codigo .", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Detener la ejecución si algún campo está vacío
                }

                // Crear un nuevo cliente con los datos ingresados
                Cliente cliente = new Cliente();
                

                // Llamar al método guardarClientes del objeto gestionClientesRemoto
                try {
					gestionClientesRemoto.borrarCliente(codigo);
	                resultadoTextArea.append("El cliente con id " + codigo + " " + "ha sido eliminado ");
	                // Limpiar los campos de texto después de guardar
	                limpiarCampos();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al borrar el cliente: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
                
            }
        });
        
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos de los campos de texto
                String cedula = dniField.getText();
                String codigo = codigoField.getText();
                String direccion = direccionField.getText();
                String nombre = nombreField.getText();

             // Verificar si algún campo está vacío
                if (codigo.isEmpty() || cedula.isEmpty() || nombre.isEmpty() || direccion.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Detener la ejecución si algún campo está vacío
                }
                // Crear un nuevo cliente con los datos ingresados
                

                // Llamar al método guardarClientes del objeto gestionClientesRemoto
                try {
					Cliente cliente = gestionClientesRemoto.getClientePorCedula(cedula);
					cliente.setDireccion(dniField.getText());
					cliente.setNombre(nombre);
					cliente.setDni(cedula);
					gestionClientesRemoto.actualizarCliente(cliente);
	                
	                // Limpiar los campos de texto después de guardar
	                limpiarCampos();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al actualizar el cliente: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
                
            }
        });
    }
    
    
    private void limpiarCampos() {
        codigoField.setText("");
        dniField.setText("");
        nombreField.setText("");
        direccionField.setText("");
    }
    
    
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }
}
