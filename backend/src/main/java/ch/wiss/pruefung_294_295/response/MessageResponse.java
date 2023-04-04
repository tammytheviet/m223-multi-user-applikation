package ch.wiss.pruefung_294_295.response;

/**
 * Diese Klasse wird verwendet, um die Antwort auf die Anfrage zu geben.
 * @class MessageResponse
 * @author Fabio Facundo & Tam Lai Nguyen
 * @version 1.0
 * 
 * @param message: Nachricht
 */
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}