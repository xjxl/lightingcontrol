package com.acme.xxlightingcontrol.lib.net.udp;

import java.net.InetSocketAddress;

/**
 * @author jx9@msn.com
 */
public class Message {
    private final String content;
    private final InetSocketAddress recipient;

    public Message(String content, InetSocketAddress recipient) {
        this.content = content;
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public InetSocketAddress getRecipient() {
        return recipient;
    }
}