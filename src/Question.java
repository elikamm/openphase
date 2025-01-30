public class Question {
    private String question;
    private String answer;
    private int phase;

    Question(String question, String answer, int phase) {
        this.question = question;
        this.answer = answer;
        this.phase = phase;
    }

    String getQuestion() {
        return question;
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
        return question + '\t' + answer + '\t' + phase;
    }
}
