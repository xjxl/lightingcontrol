package com.acme.xxlightingcontrol.lib.net.udp;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author jx9@msn.com
 */
public class DatagramStringEncoder extends MessageToMessageEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> out) {
        ByteBuf buf = ctx.alloc().buffer();
        buf.writeBytes(msg.getContent().getBytes(CharsetUtil.UTF_8));
        out.add(new DatagramPacket(buf, msg.getRecipient()));
    }
}