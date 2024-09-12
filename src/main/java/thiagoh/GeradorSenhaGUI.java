package thiagoh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeradorSenhaGUI extends JFrame {

    private GeradorSenha geradorSenha;

    public GeradorSenhaGUI() {
        super("Gerador de Senhas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(540, 700);
        setLocationRelativeTo(null);
        setLayout(null); // Definindo layout como nulo
        geradorSenha = new GeradorSenha();
        adicionarComponentesGUI();
    }

    private void adicionarComponentesGUI() {
        JLabel titulo = new JLabel("Gerar senha");
        titulo.setFont(new Font("Dialog", Font.BOLD, 32));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(0, 10, 540, 39);
        add(titulo);

        JTextArea SaidaSenha = new JTextArea();
        SaidaSenha.setEditable(false);
        SaidaSenha.setFont(new Font("Dialog", Font.BOLD, 32));
        JScrollPane SaidaSenhaPane = new JScrollPane(SaidaSenha);
        SaidaSenhaPane.setBounds(25, 97, 479, 70);
        SaidaSenhaPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(SaidaSenhaPane);

        JLabel senhaLargura = new JLabel("Largura da senha: ");
        senhaLargura.setFont(new Font("Dialog", Font.PLAIN, 32));
        senhaLargura.setBounds(25, 215, 272, 39);
        add(senhaLargura);

        JTextArea AreaSenha = new JTextArea();
        AreaSenha.setFont(new Font("Dialog", Font.PLAIN, 32));
        AreaSenha.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        AreaSenha.setBounds(310, 215, 192, 39);
        add(AreaSenha);

        JToggleButton BotaoMaiusculo = new JToggleButton("Maiúsculas");
        BotaoMaiusculo.setFont(new Font("Dialog", Font.PLAIN, 26));
        BotaoMaiusculo.setBounds(25, 302, 225, 56);
        add(BotaoMaiusculo);

        JToggleButton BotaoMinusculo = new JToggleButton("Minúsculas");
        BotaoMinusculo.setFont(new Font("Dialog", Font.PLAIN, 26));
        BotaoMinusculo.setBounds(282, 302, 225, 56);
        add(BotaoMinusculo);

        JToggleButton BotaoNumeros = new JToggleButton("Números");
        BotaoNumeros.setFont(new Font("Dialog", Font.PLAIN, 26));
        BotaoNumeros.setBounds(25, 373, 225, 56);
        add(BotaoNumeros);

        JToggleButton BotaoSimbolos = new JToggleButton("Símbolos");
        BotaoSimbolos.setFont(new Font("Dialog", Font.PLAIN, 26));
        BotaoSimbolos.setBounds(282, 373, 225, 56);
        add(BotaoSimbolos);

        JButton BotaoGerar = new JButton("Gerar");
        BotaoGerar.setFont(new Font("Dialog", Font.PLAIN, 32));
        BotaoGerar.setBounds(155, 477, 222, 41);
        BotaoGerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validação
                if (AreaSenha.getText().length() <= 0)
                    return;
                boolean qualquerToggleSelecionado = BotaoMinusculo.isSelected() ||
                        BotaoMaiusculo.isSelected() ||
                        BotaoNumeros.isSelected() ||
                        BotaoSimbolos.isSelected();

                // Gerar senha
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
        add(BotaoGerar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GeradorSenhaGUI().setVisible(true));
    }
}
