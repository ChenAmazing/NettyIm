package com.myself.response;

import com.myself.command.Packet;
import lombok.Data;

import static com.myself.command.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {
    private String message;


    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
