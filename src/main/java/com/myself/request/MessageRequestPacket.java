package com.myself.request;

import com.myself.command.Packet;
import lombok.Data;

import static com.myself.command.Command.MESSAGE_REQUEST;

@Data
public class MessageRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }

    private String message;
}
