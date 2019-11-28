package com.myself.request;

import com.myself.command.Command;
import com.myself.command.Packet;
import lombok.Data;

import java.util.List;

@Data
public class CreateGroupRequestPacket extends Packet {
    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
