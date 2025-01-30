import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class Window extends JFrame {
    private Dictionary dictionary;

    private JPanel wrapper;
    private WindowQuestion question;
    private WindowInput input;
    private WindowPhase phase;

    Window(String dictionary_path) {
        super("openphase");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (dictionary_path.isEmpty()) {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Bibliothek öffnen - openphase");

            if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
                System.exit(0);
            }

            dictionary_path = chooser.getSelectedFile().getPath().toString();
        }

        dictionary = new Dictionary(dictionary_path);
        setTitle(dictionary_path + " - openphase");

        wrapper = new JPanel();
        question = new WindowQuestion();
        input = new WindowInput(this);
        phase = new WindowPhase();

        wrapper.setLayout(new BorderLayout(10, 10));
        wrapper.setBorder(new EmptyBorder(10, 10, 10, 10));
        wrapper.add(question, BorderLayout.PAGE_START);
        wrapper.add(input, BorderLayout.CENTER);
        wrapper.add(phase, BorderLayout.PAGE_END);

        add(wrapper);
        pack();

        setVisible(true);

        nextQuestion();
    }

    private void nextQuestion() {
        if (!dictionary.nextQuestion()) {
            int result = JOptionPane.showConfirmDialog(
                this,
                "Alle Fragen sind in Phase 6.\nMöchtest Du nochmal von vorne beginnen?",
                "Fertig! - openphase",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.YES_OPTION) {
                dictionary.resetPhases();
                dictionary.nextQuestion();
            }
            else {
                System.exit(0);
            }
        }

        question.setText(dictionary.getCurrentQuestion());
        phase.setPhase(dictionary.getCurrentPhase());
        input.setText("");
        input.setEnabled(true);
        input.requestFocus();
    }

    public void checkInput(String text) {
        input.setEnabled(false);

        int old_phase = dictionary.getCurrentPhase();

        if (!dictionary.checkInput(text)) {
            JOptionPane.showMessageDialog(
                this,
                "Richtige Antwort:\n" + dictionary.getCurrentAnswer(),
                "Falsch! - openphase",
                JOptionPane.PLAIN_MESSAGE
            );
        }

        int new_phase = dictionary.getCurrentPhase();

        if (new_phase != old_phase) {
            phase.animatePhase(new_phase);
        }

        new Thread(() -> {
            try {
                Thread.sleep(old_phase == new_phase ? 500 : 1500);
                nextQuestion();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
}
