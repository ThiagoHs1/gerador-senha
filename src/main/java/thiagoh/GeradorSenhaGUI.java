package thiagoh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeradorSenhaGUI extends JFrame {

    private GeradorSenha geradorSenha;

    public GeradorSenhaGUI() {
        super("Gerador de Senhas");

        // Definindo um visual moderno usando Nimbus Look and Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800); // Aumentando o tamanho da janela para comportar o espaçamento extra
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        geradorSenha = new GeradorSenha();
        adicionarComponentesGUI();
    }

    private void adicionarComponentesGUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 20, 20, 20);

        JLabel titulo = new JLabel("Gerar Senha");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 48));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titulo, gbc);

        JTextArea SaidaSenha = new JTextArea();
        SaidaSenha.setEditable(false);
        SaidaSenha.setFont(new Font("SansSerif", Font.BOLD, 30));
        SaidaSenha.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        JScrollPane SaidaSenhaPane = new JScrollPane(SaidaSenha);
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.ipady = 50; // Aumenta a altura
        add(SaidaSenhaPane, gbc);

        JLabel senhaLargura = new JLabel("Tamanho da senha:");
        senhaLargura.setFont(new Font("SansSerif", Font.PLAIN, 26));
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.ipady = 0; // Reseta altura
        add(senhaLargura, gbc);

        JTextArea AreaSenha = new JTextArea();
        AreaSenha.setFont(new Font("SansSerif", Font.PLAIN, 26));
        AreaSenha.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(AreaSenha, gbc);

        // Adicionando botões de alternância com maior espaçamento
        JToggleButton BotaoMaiusculo = criarBotaoToggle("Maiúsculas");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(BotaoMaiusculo, gbc);

        JToggleButton BotaoMinusculo = criarBotaoToggle("Minúsculas");
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(BotaoMinusculo, gbc);

        JToggleButton BotaoNumeros = criarBotaoToggle("Números");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(BotaoNumeros, gbc);

        JToggleButton BotaoSimbolos = criarBotaoToggle("Símbolos");
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(BotaoSimbolos, gbc);

        JButton BotaoGerar = new JButton("Gerar");
        BotaoGerar.setFont(new Font("SansSerif", Font.PLAIN, 30));
        BotaoGerar.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        BotaoGerar.setFocusPainted(false); // Remove o foco feio
        BotaoGerar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        BotaoGerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (AreaSenha.getText().length() <= 0)
                    return;

                boolean qualquerToggleSelecionado = BotaoMinusculo.isSelected() ||
                        BotaoMaiusculo.isSelected() ||
                        BotaoNumeros.isSelected() ||
                        BotaoSimbolos.isSelected();

                int tamanhoSenha = Integer.parseInt(AreaSenha.getText());
                if (qualquerToggleSelecionado) {
                    String senhaGerada = geradorSenha.gerarSenha(tamanhoSenha,
                            BotaoMaiusculo.isSelected(),
                            BotaoMinusculo.isSelected(),
                            BotaoNumeros.isSelected(),
                            BotaoSimbolos.isSelected());

                    SaidaSenha.setText(senhaGerada);
                }
            }
        });
        add(BotaoGerar, gbc);
    }

    private JToggleButton criarBotaoToggle(String texto) {
        JToggleButton botao = new JToggleButton(texto);
        botao.setFont(new Font("SansSerif", Font.PLAIN, 26));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return botao;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GeradorSenhaGUI().setVisible(true));
    }
}
