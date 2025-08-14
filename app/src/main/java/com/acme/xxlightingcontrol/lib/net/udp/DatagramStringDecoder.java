package com.acme.xxlightingcontrol.lib.net.udp;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

/**
 * @author jx9@msn.com
 */
public class DatagramStringDecoder extends MessageToMessageDecoder<DatagramPacket> {
    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket packet, List<Object> out) {
        ByteBuf buf = packet.content();
        if (buf.isReadable()) {
            String message = buf.toString(CharsetUtil.UTF_8);
            out.add(new ReceivedMessage(message, packet.sender()));
        }
    }
}