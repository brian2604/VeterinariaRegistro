package ventanas;

import controlador.Coordinador;
import dao.MascotaDao;
import dao.PersonaDao;
import vo.MascotaVo;
import vo.PersonaVo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMascotas extends JFrame implements ActionListener {
    private Coordinador coordinador;
    private MascotaDao mascotaDao = new MascotaDao();

    JLabel lblId,lblNombre, lblRaza, lblSexo;
    JTextField txtId, txtNombre, txtRaza, txtSexo;
    JButton btnRegistrar, btnConsultar, btnActualizar, btnEliminar, btnLista, btnLimpiar;
    JTextArea areaResultado;
    JScrollPane scroll;

    public VentanaMascotas() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("GESTIONAR MASCOTAS");
        setSize(530, 650);
        setLocationRelativeTo(null);
        setLayout(null);

        lblId = new JLabel("Id Dueño:");
        lblNombre = new JLabel("Nombre:");
        lblRaza = new JLabel("Raza:");
        lblSexo = new JLabel("Sexo:");

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtRaza = new JTextField();
        txtSexo = new JTextField();

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

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(this);
        btnLimpiar.setBounds(170, 570, 170, 25);



        areaResultado = new JTextArea();
        scroll = new JScrollPane(areaResultado);

        lblId.setBounds(20, 60, 80, 20);
        txtId.setBounds(90, 60, 180, 20);

        lblNombre.setBounds(20, 90, 80, 20);
        txtNombre.setBounds(90, 90, 180, 20);

        lblRaza.setBounds(280, 60, 80, 20);
        txtRaza.setBounds(330, 60, 160, 20);

        lblSexo.setBounds(280, 90, 80, 20);
        txtSexo.setBounds(330, 90, 160, 20);

        btnRegistrar.setBounds(20, 140, 210, 25);
        btnConsultar.setBounds(280, 140, 210, 25);
        btnActualizar.setBounds(20, 180, 210, 25);
        btnEliminar.setBounds(280, 180, 210, 25);
        btnLista.setBounds(20, 220, 470, 25);

        scroll.setBounds(20, 260, 470, 300);

        add(lblId);
        add(txtId);
        add(lblNombre);
        add(txtNombre);
        add(lblRaza);
        add(txtRaza);
        add(lblSexo);
        add(txtSexo);
        add(btnRegistrar);
        add(btnConsultar);
        add(btnActualizar);
        add(btnEliminar);
        add(btnLista);
        add(scroll);
        add(btnLimpiar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) {

            MascotaVo mascota = guardarDatos();
            try {
                coordinador.registrarMascota(mascota);
                areaResultado.setText("Mascota registrada");
            }catch (RuntimeException ex){
                areaResultado.setText(ex.getMessage());
            }
            limpiarCampos();

        } else if (e.getSource() == btnConsultar) {

            validarCampos();
            MascotaVo mascota = mascotaDao.consultar(txtNombre.getText().trim());

            if (mascota != null) {
                areaResultado.setText("Resultado: \n" + mascota.mostrarInfo());
            }else {
                areaResultado.setText("Mascota no encontrada");
            }
            limpiarCampos();

        } else if (e.getSource() == btnActualizar) {
            validarCampos();
            MascotaVo mascota  = guardarDatos();
            if (mascota != null) {
                coordinador.actualizarMascota(mascota);
                areaResultado.setText("Macota actualizad");
            }
            limpiarCampos();

        } else if (e.getSource() == btnEliminar) {
            validarCampos();
            String doc = txtNombre.getText().trim();
            if (!doc.isEmpty()) {
                coordinador.eliminarMascota(doc);
                areaResultado.setText("Mascota eliminadaa");
            }
            limpiarCampos();

        } else if (e.getSource() == btnLista) {

            String lista = coordinador.listarMacota();
            areaResultado.setText(lista);

        } else if (e.getSource() == btnLimpiar) {
                areaResultado.setText("");
        }

    }

    public MascotaVo guardarDatos(){
        MascotaVo m = new MascotaVo();
        m.setIdDueno(txtId.getText().trim());
        m.setNombre(txtNombre.getText().trim());
        m.setRaza(txtRaza.getText().trim());
        m.setSexo(txtSexo.getText().trim());
        return m;
    }

    public void limpiarCampos(){
        txtId.setText("");
        txtNombre.setText("");
        txtRaza.setText("");
        txtSexo.setText("");

    }

    public void validarCampos(){
        if  (txtId.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty() || txtSexo.getText().trim().isEmpty() || txtRaza.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tiene que llenar todos los campos");
        }
    }

    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }

}
