package chatCompletion;

import java.util.List;

public class RequestBodyImpl {
    private String model;
    private List<Message> messages;

    public RequestBodyImpl(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}
