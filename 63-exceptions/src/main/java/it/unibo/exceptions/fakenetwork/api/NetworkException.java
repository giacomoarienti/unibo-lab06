package it.unibo.exceptions.fakenetwork.api;

import java.io.IOException;

public class NetworkException extends IOException {
    
    public NetworkException(final String message) throws IOException {
        throw new IOException("Network error while sending message: " + message);
    }

    public NetworkException() throws IOException {
        this("no response");
    }
}
