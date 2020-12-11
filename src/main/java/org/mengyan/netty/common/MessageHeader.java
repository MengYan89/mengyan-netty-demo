package org.mengyan.netty.common;

public class MessageHeader {

    private int version = 1;
    private long streamId;
    private int opCode;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getStreamId() {
        return streamId;
    }

    public void setStreamId(long streamId) {
        this.streamId = streamId;
    }

    public int getOpCode() {
        return opCode;
    }

    public void setOpCode(int opCode) {
        this.opCode = opCode;
    }

    @Override
    public String toString() {
        return "MessageHeader{" +
                "version=" + version +
                ", streamId=" + streamId +
                ", opCode=" + opCode +
                '}';
    }
}
