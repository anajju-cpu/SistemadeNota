import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class SistemaInova {

    private CardLayout cardLayout;
    private JPanel painelConteudo;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SistemaInova().criarTela());
    }

    public void criarTela() {
        JFrame frame = new JFrame("Sistema Inova");
        frame.setSize(1000, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(255, 193, 7));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titulo = new JLabel("Seja Bem Vindo!", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        header.add(titulo, BorderLayout.CENTER);
        frame.add(header, BorderLayout.NORTH);

        // CONTEÚDO
        cardLayout = new CardLayout();
        painelConteudo = new JPanel(cardLayout);

        painelConteudo.add(telaHome(), "HOME");
        painelConteudo.add(telaCadastro(), "CADASTRO");

        frame.add(painelConteudo, BorderLayout.CENTER);

        // MENU
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(new Color(80, 80, 80));
        menu.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        menu.setPreferredSize(new Dimension(260, 0));

        menu.add(criarCard("Home", "HOME"));
        menu.add(Box.createVerticalStrut(10));
        menu.add(criarCard("Cadastrar NFE", "CADASTRO"));

        frame.add(menu, BorderLayout.WEST);

        frame.setVisible(true);
    }

    // HOME 
    private JPanel telaHome() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 193, 7));

        ImageIcon icon = new ImageIcon("inova.jpg");
        Image img = icon.getImage().getScaledInstance(600, 300, Image.SCALE_SMOOTH);

        JLabel imagem = new JLabel(new ImageIcon(img));
        imagem.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(imagem, BorderLayout.CENTER);

        return panel;
    }

    // CADASTRO
    private JPanel telaCadastro() {

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(new Color(240, 240, 240));

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(255, 193, 7));
        header.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titulo = new JLabel("Cadastro de Notas Fiscais Eletrônicas");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));

        header.add(titulo, BorderLayout.WEST);
        container.add(header, BorderLayout.NORTH);

        // CAMPOS
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.weightx = 1;

        JTextField dataCriacao = new JTextField();
        JTextField numeroNota = new JTextField();
        JTextField fornecedor = new JTextField();
        JTextField CPFCNPJ = new JTextField();
        JTextField dataVencimento = new JTextField();
        JTextField dataEntrada = new JTextField();
        JTextField aprovadores = new JTextField();
        JTextField Preenchido = new JTextField();
        JTextField FormadePagamento = new JTextField();
        JTextField LancadoPor = new JTextField();
        

        int y = 0;
        y = addCampo (form, gbc, y,"Data de Criação:", dataCriacao);
        y = addCampo(form, gbc, y, "*Nº NF/FATURA:", numeroNota);
        y = addCampo(form, gbc, y, "*Fornecedor:", fornecedor);
        y = addCampo (form, gbc, y, "*CPF/ CNPJ", CPFCNPJ);
        y = addCampo(form, gbc, y, "*Data de Vencimento:", dataVencimento);
        y = addCampo(form, gbc, y, "*Data de Entrada:", dataEntrada);
        y = addCampo(form, gbc, y ,"*Aprovadores:", aprovadores);
        y = addCampo(form, gbc, y, "*Preenchido por: ", Preenchido);
        y = addCampo(form, gbc, y, "*Forma de Pagamento:", FormadePagamento);
        y = addCampo (form, gbc, y , "*Lançado por:", LancadoPor);

        // BOTÕES
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoes.setBackground(Color.WHITE);

        JButton cancelar = new JButton("Cancelar");
        JButton enviar = new JButton("Enviar");

        enviar.setBackground(new Color(153, 102, 0));
        enviar.setForeground(Color.WHITE);

        enviar.addActionListener(e -> validarFormulario(
                dataCriacao, numeroNota, fornecedor, dataVencimento, dataEntrada
        ));

        botoes.add(cancelar);
        botoes.add(enviar);

        gbc.gridy = y;
        form.add(botoes, gbc);

        JScrollPane scroll = new JScrollPane(form);
        scroll.setBorder(null);

        container.add(scroll, BorderLayout.CENTER);

        return container;
    }

    // CRIAR CAMPO
    private int addCampo(JPanel panel, GridBagConstraints gbc, int y, String labelText, JTextField campo) {

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 13));

        gbc.gridy = y;
        panel.add(label, gbc);

        gbc.gridy = y + 1;

        campo.setPreferredSize(new Dimension(200, 35));
        campo.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        panel.add(campo, gbc);

        return y + 2;
    }

    //  VALIDAÇÃO //
    private void validarFormulario(
            JTextField dataCriacao,
            JTextField numeroNota,
            JTextField fornecedor,
            JTextField dataVencimento,
            JTextField dataEntrada
    ) {

        boolean valido = true;

        limparBorda(dataCriacao);
        limparBorda(numeroNota);
        limparBorda(fornecedor);
        limparBorda(dataVencimento);
        limparBorda(dataEntrada);

        if (dataCriacao.getText().isEmpty()) {
            erro(dataCriacao);
            valido = false;
        }

        if (numeroNota.getText().isEmpty()) {
            erro(numeroNota);
            valido = false;
        }

        if (fornecedor.getText().isEmpty()) {
            erro(fornecedor);
            valido = false;
        }

        if (!validarData(dataVencimento.getText())) {
            erro(dataVencimento);
            valido = false;
        }

        if (!validarData(dataEntrada.getText())) {
            erro(dataEntrada);
            valido = false;
        }

        if (valido) {
            JOptionPane.showMessageDialog(null, "Enviado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Preencha corretamente!");
        }
    }

    private boolean validarData(String data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.parse(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void erro(JTextField campo) {
        campo.setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    private void limparBorda(JTextField campo) {
        campo.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
    }

    // MENU CARD
    private JPanel criarCard(String texto, String tela) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setMaximumSize(new Dimension(240, 60));
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 14));

        card.add(label, BorderLayout.CENTER);

        card.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(230, 230, 230));
            }

            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
            }

            public void mouseClicked(MouseEvent e) {
                cardLayout.show(painelConteudo, tela);
            }
        });

        return card;
    }
      
}