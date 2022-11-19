package tech.meliora.mulika.spring.web.rest.dto;

public class Request {

    private int a;
    private int b;

    public Request() {
    }

    public Request(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Request{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
