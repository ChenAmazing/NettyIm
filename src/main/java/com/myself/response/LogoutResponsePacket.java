package com.myself.response;

import com.myself.command.Command;
import com.myself.command.Packet;
import lombok.Data;

@Data
public class LogoutResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }

    private boolean success;

    private String reason;
}
