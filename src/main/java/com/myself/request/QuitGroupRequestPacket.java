package com.myself.request;

import com.myself.command.Command;
import com.myself.command.Packet;
import lombok.Data;

@Data
public class QuitGroupRequestPacket extends Packet {
    private String groupId;
    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_REQUEST;
    }
}
