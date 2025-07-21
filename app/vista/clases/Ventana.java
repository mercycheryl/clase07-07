package app.vista.clases;

import app.controlador.Servicio;
import app.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class Ventana extends JFrame {
    private JPanel jpanel1;
    private JButton btnMostrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnNuevo;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JTextField txtPrecio;
    private JTable tbProd;
    private JButton imprimirReporteButton;
    private DefaultTableModel model;
    private JScrollPane scrollPane=null;

    // tabla
    private Object[] columns={"Id","Codigo","Producto","Precio"};
    private Object[] row= new Object[4];
    private Map<Integer, app.modelo.Producto> mapa=null;
    private String clave;
    private Servicio controlador=new Servicio();
    // ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    public Ventana() {
        setTitle("Venta");
        setSize(500, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(jpanel1);
        setLocationRelativeTo(null);
        obtenerRegistrosTabla();
        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProductos();
            }
        });

        //tabla
        tbProd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i= tbProd.getSelectedRow();
                clave=model.getValueAt(i,0).toString();
                txtCodigo.setText(model.getValueAt(i,1).toString());
                txtNombre.setText(model.getValueAt(i,2).toString());
                txtPrecio.setText(model.getValueAt(i,3).toString());
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obtenerRegistrosTabla();
            }
        });
        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo=txtCodigo.getText();
                String nombre=txtNombre.getText();
                double precio=Double.parseDouble(txtPrecio.getText());
                controlador.insertar(new app.modelo.Producto(codigo,nombre,precio));
                obtenerRegistrosTabla();
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=Integer.parseInt(clave);
                String codigo=txtCodigo.getText();
                String nombre=txtNombre.getText();
                String precioStr=txtPrecio.getText().replace(",",".");
                double precio=Double.parseDouble(precioStr);
                controlador.actualizar(new app.modelo.Producto(id,codigo,nombre,precio));
                obtenerRegistrosTabla();
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=Integer.parseInt(clave);
                controlador.eliminar(id);
                obtenerRegistrosTabla();
            }
        });
        imprimirReporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    tbProd.print();
                }catch (Exception e2){
                    System.out.println(e2.getMessage());
                }
            }
        });
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            new Ventana().setVisible(true);
        });
    }

    public void agregarProductos(){
        String nombre=txtNombre.getText();
        String codigo=txtCodigo.getText();
        String precio=txtPrecio.getText();

    }

    public void obtenerRegistrosTabla() {
        model = new DefaultTableModel() {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                return false;}
        };
        model.setColumnIdentifiers(columns);
        while (model.getRowCount() > 0) {
            model.removeRow(0);}
        mapa = controlador.seleccionarTodo();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+mapa.size());
        for (Map.Entry<Integer, Producto> entry : mapa.entrySet()) {
            row[0] = entry.getKey();
            row[1] = entry.getValue().getCodigo();
            row[2] = entry.getValue().getNombre();
            row[3] = String.format("%.2f", entry.getValue().getPrecio());
            model.addRow(row);
        }
        tbProd.setModel(model);
        tbProd.getColumnModel().getColumn(0).setPreferredWidth(30);
        tbProd.getColumnModel().getColumn(1).setPreferredWidth(50);
        tbProd.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbProd.getColumnModel().getColumn(3).setPreferredWidth(50);
        limpiarCampos();
    }

    public void limpiarCampos(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }
}
