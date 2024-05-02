package ec.edu.ups.ppw63.demo63.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ec.edu.ups.ppw63.demo63.business.GestionClientesRemoto;
import ec.edu.ups.ppw63.demo63.business.GestionDetalleFacturasRemoto;
import ec.edu.ups.ppw63.demo63.model.Cliente;
import ec.edu.ups.ppw63.demo63.model.DetalleFactura;

public class FacturaCRUD extends JFrame{
	  private JTextField codigoField, cantidadField, nombreField, precioField;
	    private JButton crearButton, leerButton, actualizarButton, borrarButton;
	    private GestionDetalleFacturasRemoto gestionDetalleFacturasRemoto;
	    private JTextArea resultadoTextArea;
	    
	    public FacturaCRUD(GestionDetalleFacturasRemoto gestion) {
	        super("Cliente CRUD");
	        this.gestionDetalleFacturasRemoto = gestion;

	        // Crear los campos de texto
	        codigoField = new JTextField(20);
	        cantidadField = new JTextField(20);
	        nombreField = new JTextField(20);
	        precioField = new JTextField(20);

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
	        panel.add(new JLabel("Cantidad:"), gbc);
	        gbc.gridy++;
	        panel.add(new JLabel("Nombre:"), gbc);
	        gbc.gridy++;
	        panel.add(new JLabel("Precio:"), gbc);

	        gbc.gridx = 1;
	        gbc.gridy = 0;
	        gbc.anchor = GridBagConstraints.EAST;
	        gbc.fill = GridBagConstraints.HORIZONTAL;
	        panel.add(codigoField, gbc);
	        gbc.gridy++;
	        panel.add(cantidadField, gbc);
	        gbc.gridy++;
	        panel.add(nombreField, gbc);
	        gbc.gridy++;
	        panel.add(precioField, gbc);

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
	                int cantidad = 0;
	                String nombre = nombreField.getText();
	                double precio = 0.0;
	                try {
	                    cantidad = Integer.parseInt(cantidadField.getText());
	                    precio = Double.parseDouble(precioField.getText());
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(null, "Por favor, ingrese números válidos en los campos de cantidad y precio.", "Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
	                if (nombre.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre válido.", "Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	                try {
	                    DetalleFactura detalle = new DetalleFactura();
	                    detalle.setCantidad(cantidad);
	                    detalle.setNombre(nombre);
	                    detalle.setPrecio(precio);

	                    gestionDetalleFacturasRemoto.guardarDetalles(detalle);
	                    resultadoTextArea.append("Agregado ");

	                    // Limpiar los campos de texto después de guardar
	                    limpiarCampos();
	                } catch (Exception ex) {
	                    JOptionPane.showMessageDialog(null, "Error al guardar el detalle: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                    ex.printStackTrace();
	                }
	            }
	        });

	        leerButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Obtener los datos de los campos de texto
	                int codigo = 0;
	                try {
	                    codigo = Integer.parseInt(codigoField.getText());
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un código válido.", "Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	                try {
	                    DetalleFactura detalleFactura = gestionDetalleFacturasRemoto.leerDetalle(codigo);
	                    if (detalleFactura != null) {
	                        resultadoTextArea.append("Los datos obtenidos del detalle con " + codigo + " son: ");
	                        resultadoTextArea.append(detalleFactura.getNombre());
	                    } else {
	                        JOptionPane.showMessageDialog(null, "No se encontró ningún detalle con el código proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                    // Limpiar los campos de texto después de leer
	                    limpiarCampos();
	                } catch (Exception ex) {
	                    JOptionPane.showMessageDialog(null, "Error al leer el detalle: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                    ex.printStackTrace();
	                }
	            }
	        });

	        borrarButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Obtener los datos de los campos de texto
	                int codigo = 0;
	                try {
	                    codigo = Integer.parseInt(codigoField.getText());
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un código válido.", "Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	                try {
	                    gestionDetalleFacturasRemoto.borrarDetalle(codigo);
	                    resultadoTextArea.append("El detalle con id " + codigo + " ha sido eliminado ");
	                    // Limpiar los campos de texto después de borrar
	                    limpiarCampos();
	                } catch (Exception ex) {
	                    JOptionPane.showMessageDialog(null, "Error al borrar el detalle: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                    ex.printStackTrace();
	                }
	            }
	        });

	        actualizarButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Obtener los datos de los campos de texto
	                int cantidad = 0;
	                int codigo = 0;
	                double precio = 0.0;
	                String nombre = nombreField.getText();
	                try {
	                    cantidad = Integer.parseInt(cantidadField.getText());
	                    codigo = Integer.parseInt(codigoField.getText());
	                    precio = Double.parseDouble(precioField.getText());
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(null, "Por favor, ingrese números válidos en los campos de cantidad, código y precio.", "Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
	                if (nombre.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre válido.", "Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	                try {
	                    DetalleFactura detalleFactura = gestionDetalleFacturasRemoto.leerDetalle(codigo);
	                    if (detalleFactura != null) {
	                        detalleFactura.setCantidad(cantidad);
	                        detalleFactura.setNombre(nombre);
	                        detalleFactura.setPrecio(precio);
	                        gestionDetalleFacturasRemoto.actualizarDetalle(detalleFactura);
	                        
	                        // Limpiar los campos de texto después de actualizar
	                        limpiarCampos();
	                    } else {
	                        JOptionPane.showMessageDialog(null, "No se encontró ningún detalle con el código proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                } catch (Exception ex) {
	                    JOptionPane.showMessageDialog(null, "Error al actualizar el detalle: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                    ex.printStackTrace();
	                }
	            }
	        });
	    }

	    
	    
	    private void limpiarCampos() {
	        codigoField.setText("");
	        precioField.setText("");
	        nombreField.setText("");
	        cantidadField.setText("");
	    }
	    
	    
	    
	    

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                
	            }
	        });
	    }
}