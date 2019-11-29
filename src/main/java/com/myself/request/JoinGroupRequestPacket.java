package com.myself.request;

import com.myself.command.Command;
import com.myself.command.Packet;
import lombok.Data;

@Data
public class JoinGroupRequestPacket extends Packet {
    private String GroupId;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }
}
