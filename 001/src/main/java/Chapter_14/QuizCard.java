package Chapter_14;

public class QuizCard {
    String question;
    String answer;

    public QuizCard(String q, String a){
        question = q;
        answer = a;
    }

    public String getQuestion(){
        return this.question;
    }

    public String getAnswer(){
        return this.answer;
    }
}
