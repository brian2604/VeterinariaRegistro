package ventanas;

import controlador.Coordinador;
import dao.MascotaDao;
import dao.PersonaDao;
import vo.MascotaVo;
import vo.PersonaVo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPersonas extends JFrame implements ActionListener {
    private Coordinador coordinador;
    private PersonaDao personaDao = new PersonaDao();


    JLabel lblDocumento, lblNombre, lblTelefono;
    JTextField txtDocumento, txtNombre, txtTelefono;
    JButton btnRegistrar, btnConsultar, btnActualizar, btnEliminar, btnLista, btnLimpiar;
    JTextArea areaResultado;
    JScrollPane scroll;

    public VentanaPersonas() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("GESTIONAR PERSONAS");
        setSize(530, 650);
        setLocationRelativeTo(null);
        setLayout(null);

        lblDocumento = new JLabel("Documento:");
        lblNombre = new JLabel("Nombre:");
        lblTelefono = new JLabel("Teléfono:");

        txtDocumento = new JTextField();
        txtNombre = new JTextField();
        txtTelefono = new JTextField();

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(this);

        btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(this);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(this);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);

        btnLista = new JButton("ConsultarLista");
        btnLista.addActionListener(this);

        areaResultado = new JTextArea();
        scroll = new JScrollPane(areaResultado);

        // Posicionamiento
        lblDocumento.setBounds(20, 60, 80, 20);
        txtDocumento.setBounds(110, 60, 180, 20);

        lblNombre.setBounds(20, 90, 80, 20);
        txtNombre.setBounds(110, 90, 180, 20);

        lblTelefono.setBounds(300, 60, 80, 20);
        txtTelefono.setBounds(370, 60, 120, 20);

        btnRegistrar.setBounds(20, 140, 210, 25);
        btnConsultar.setBounds(280, 140, 210, 25);
        btnActualizar.setBounds(20, 180, 210, 25);
        btnEliminar.setBounds(280, 180, 210, 25);
        btnLista.setBounds(20, 220, 470, 25);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(this);
        btnLimpiar.setBounds(170, 570, 170, 25);

        scroll.setBounds(20, 260, 470, 300);

        // Agregar al JFrame
        add(lblDocumento);
        add(txtDocumento);
        add(lblNombre);
        add(txtNombre);
        add(lblTelefono);
        add(txtTelefono);
        add(btnRegistrar);
        add(btnConsultar);
        add(btnActualizar);
        add(btnEliminar);
        add(btnLista);
        add(scroll);
        add(btnLimpiar);
    }

    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) {
            validarCampos();
            if (coordinador.personaYaExiste(txtDocumento.getText().trim())) {
                areaResultado.setText("ESTA PERSONA YA EXISTE");
            }else {
                PersonaVo persona = guardarDatos();
                coordinador.registrarPersona(persona);
            }
            limpiarCampos();
        }else if(e.getSource() == btnConsultar){
            validarCampos();
            PersonaVo persona = personaDao.consultar(txtDocumento.getText().trim());
            if (persona != null) {
                areaResultado.setText("Resultado: \n" + persona.mostrarInfo());
            }else {
                areaResultado.setText("Persona no encontrada");
            }
            limpiarCampos();

        } else if (e.getSource() == btnActualizar) {
            validarCampos();
            PersonaVo persona  = guardarDatos();
            if (persona != null) {
                coordinador.actualizarPersona(persona);
                areaResultado.setText("Persona actualizad");
            }
            limpiarCampos();

        } else if (e.getSource() == btnEliminar) {

            validarCampos();
            String doc = txtDocumento.getText().trim();
            if (!doc.isEmpty()) {
                try {
                    coordinador.eliminarPersona(doc);
                    areaResultado.setText("Persona eliminadaa");
                }catch (RuntimeException ex){
                    areaResultado.setText(ex.getMessage());
                }

            }
            limpiarCampos();

        } else if (e.getSource() == btnLista) {
            String lista = coordinador.listarPersona();
            areaResultado.setText(lista);
            limpiarCampos();

        } else if (e.getSource() == btnLimpiar) {
            areaResultado.setText("");
        }
    }

    public PersonaVo guardarDatos() {
        String doc = txtDocumento.getText().trim();
        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (doc.isEmpty() || nombre.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tiene que llenar todos los campos");
            return null;
        }

            PersonaVo p = new PersonaVo();
            p.setDocumento(doc);
            p.setNombre(nombre);
            p.setTelefono(telefono);

        return p;
    }

    public void limpiarCampos(){
        txtDocumento.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");

    }

    public void validarCampos(){
        if  (txtDocumento.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty() || txtTelefono.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tiene que llenar todos los campos");
        }
    }

}
