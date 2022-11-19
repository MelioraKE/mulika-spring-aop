package tech.meliora.mulika.spring.web.rest.dto;

public class Answer {

    private int ans;

    public Answer() {
    }

    public Answer(int product) {
        this.ans = product;
    }

    public int getAns() {
        return ans;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "ans=" + ans +
                '}';
    }
}
