package com.nagarro.amcart.connect.message.type;

/**
 * The Enum MessageType define all messages type.
 */
public enum MessageType {

    /** The product. */
    PRODUCT("product"),
    
    /** The category. */
    CATEGORY("category");

    /** The type. */
    private final String type;

    /**
     * Instantiates a new message type.
     *
     * @param type
     *            the type
     */
    private MessageType(String type) {
        this.type = type;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }
}
