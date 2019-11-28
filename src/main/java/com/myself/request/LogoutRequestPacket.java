package com.myself.request;

import com.myself.command.Command;
import com.myself.command.Packet;
import lombok.Data;

@Data
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
