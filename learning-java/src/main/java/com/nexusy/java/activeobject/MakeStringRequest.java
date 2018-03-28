package com.nexusy.java.activeobject;

public class MakeStringRequest extends MethodRequest<String>{

    private final int count;
    private final char fillchar;

    public MakeStringRequest(Servant servant, FutureResult<String> future, int count, char fillChar) {
        super(servant, future);
        this.count = count;
        this.fillchar = fillChar;
    }

    @Override
    public void execute() {
        Result<String> result = servant.makeString(count, fillchar);
        future.setResult(result);
    }
}
