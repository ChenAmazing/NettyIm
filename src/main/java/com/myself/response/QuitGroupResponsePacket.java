package com.myself.response;

import com.myself.command.Command;
import com.myself.command.Packet;
import lombok.Data;

@Data
public class QuitGroupResponsePacket extends Packet {
    private boolean success;
    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_RESPONSE;
    }
}
