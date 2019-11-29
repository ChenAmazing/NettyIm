package com.myself.response;

import com.myself.command.Command;
import com.myself.command.Packet;
import com.myself.session.Session;
import lombok.Data;

import java.util.List;

@Data
public class ListGroupMembersResponsePacket extends Packet {
    private List<Session> sessionList;
    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
