import java.util.*;
import java.io.*;

public class Dictionary {
    private String path;
    private ArrayList<Question> questions = new ArrayList<>();
    private int current_question = -1;

    Dictionary(String path) {
        this.path = path;

        loadQuestions();
    }

    private void loadQuestions() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while (line != null) {
                String[] tokens = line.split("(?<!\\\\),");

                if (tokens.length == 4) {
                    for (int i = 0; i < tokens.length; ++i) {
                        tokens[i] = tokens[i].replaceAll("\\\\,", ",");
                    }

                    int phase = Integer.parseInt(tokens[3]);

                    if (phase >= 0 && phase <= 5) {
                        questions.add(
                            new Question(
                                tokens[0],
                                tokens[1],
                                tokens[2],
                                phase
                            )
                        );
                    }
                }

                line = reader.readLine();
            }

            reader.close();
        } catch (Exception error) {
            System.err.println(error);
        }
    }
    
    private void saveQuestions() {
        try {
            FileWriter writer = new FileWriter(path);

            for (Question question : questions) {
                writer.write(question.getDump() + '\n');
            }
        
            writer.close();
        } catch (Exception error) {
            System.err.println(error);
        }
    }

    private boolean existOtherQuestions() {
        boolean found = false;

        for (int i = 0; i < questions.size(); ++i) {
            if (i != current_question && questions.get(i).getPhase() < 5) {
                found = true;
            }
        }
        
        return found;
    }

    private boolean compareStrings(String a, String b) {
        a = a.replaceAll("\\s+","").toLowerCase();
        b = b.replaceAll("\\s+","").toLowerCase();

        return a.equals(b);
    }

    public boolean nextQuestion() {
        int old_question = current_question;

        if (existOtherQuestions()) {
            do {
                current_question = (int)Math.floor(Math.random() * questions.size());
            }
            while (current_question == old_question || getCurrentPhase() >= 5 || getCurrentPhase() >= Math.random() * 5);
        }
        else if (getCurrentPhase() >= 5) {
            return false;
        }

        return true;
    }

    public String getCurrentQuestionText() {
        if (current_question < 0) return "";

        return questions.get(current_question).getQuestionText();
    }

    public String getCurrentQuestionImage() {
        if (current_question < 0) return "";

        return questions.get(current_question).getQuestionImage();
    }

    public String getCurrentAnswer() {
        if (current_question < 0) return "";

        return questions.get(current_question).getAnswer();
    }

    public int getCurrentPhase() {
        if (current_question < 0) return 5;

        return questions.get(current_question).getPhase();
    }

    public boolean checkInput(String input) {
        String answer = getCurrentAnswer();

        if (compareStrings(input, answer)) {
            questions.get(current_question).setPhase(getCurrentPhase() + 1);
            saveQuestions();
            return true;
        }
        else {
            questions.get(current_question).setPhase(0);
            saveQuestions();
            return false;
        }
    }

    public void resetPhases() {
        for (Question question : questions) {
            question.setPhase(0);
        }
        saveQuestions();
    }
}
