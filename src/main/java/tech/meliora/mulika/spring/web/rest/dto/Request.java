package tech.meliora.mulika.spring.web.rest.dto;

public class Request {

    private int a;
    private int b;

    private String channel;

    private String telco;

    public Request() {
    }

    public Request(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public Request(int a, int b, String channel, String telco) {
        this.a = a;
        this.b = b;
        this.channel = channel;
        this.telco = telco;
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


    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTelco() {
        return telco;
    }

    public void setTelco(String telco) {
        this.telco = telco;
    }

    @Override
    public String toString() {
        return "Request{" +
                "a=" + a +
                ", b=" + b +
                ", channel='" + channel + '\'' +
                ", telco='" + telco + '\'' +
                '}';
    }
}
