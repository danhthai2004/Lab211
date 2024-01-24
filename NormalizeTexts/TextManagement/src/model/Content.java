package model;


public class Content {
    private String text;

    public Content() {
    }

    public Content(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public boolean isEmpty() {
        return text.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "Content{" + "text=" + text + '}';
    }
}
