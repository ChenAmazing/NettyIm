package com.myself.request;

import com.myself.command.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.myself.command.Command.MESSAGE_REQUEST;

@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
    private String message;
    private String toUserId;

    public MessageRequestPacket(String toUserId,String message){
        this.message = message;
        this.toUserId = toUserId;
    }

}
