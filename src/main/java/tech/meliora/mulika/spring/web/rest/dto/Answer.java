package tech.meliora.mulika.spring.web.rest.dto;

public class Answer {

    private int product;

    public Answer() {
    }

    public Answer(int product) {
        this.product = product;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "product=" + product +
                '}';
    }
}
