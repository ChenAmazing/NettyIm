package com.myself.response;

import com.myself.command.Command;
import com.myself.command.Packet;
import lombok.Data;

@Data
public class JoinGroupResponsePacket extends Packet {
    private boolean success;
    private String groupId;
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
