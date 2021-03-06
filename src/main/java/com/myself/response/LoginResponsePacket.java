package com.myself.response;

import com.myself.command.Packet;
import lombok.Data;

import static com.myself.command.Command.LOGIN_RESPONSE;
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
