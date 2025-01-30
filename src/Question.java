public class Question {
    private String question_text;
    private String question_image;
    private String answer;
    private int phase;

    Question(String question_text, String question_image, String answer, int phase) {
        this.question_text = question_text;
        this.question_image = question_image;
        this.answer = answer;
        this.phase = phase;
    }

    String getQuestionText() {
        return question_text;
    }

    String getQuestionImage() {
        return question_image;
    }

    String getAnswer() {
        return answer;
    }

    int getPhase() {
        return phase;
    }

    void setPhase(int phase) {
        this.phase = phase;
    }

    String getDump() {
        return question_text + '\t' + question_image + '\t' + answer + '\t' + phase;
    }
}
