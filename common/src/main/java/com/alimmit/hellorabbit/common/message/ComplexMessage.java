package com.alimmit.hellorabbit.common.message;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class ComplexMessage implements Serializable {

    static final long serialVersionUID = 1L;

    private final String message;
    private final Date timestamp;
    private final UUID uuid;

    public ComplexMessage(final String message, final Date timestamp, final UUID uuid) {
        this.message = message;
        this.timestamp = timestamp;
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "ComplexMessage{" + "message='" + message + '\'' + ", timestamp=" + timestamp + ", uuid=" + uuid + '}';
    }
}
