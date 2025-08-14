package com.acme.xxlightingcontrol.net.udp;

import java.net.InetSocketAddress;

/**
 * @author jx9@msn.com
 */
public class ReceivedMessage {
    private final String content;
    private final InetSocketAddress sender;

    public ReceivedMessage(String content, InetSocketAddress sender) {
        this.content = content;
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public InetSocketAddress getSender() {
        return sender;
    }
}