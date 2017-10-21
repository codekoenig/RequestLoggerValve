package codekoenig.reqlogvalve.demowebapp.transformer;

import codekoenig.reqlogvalve.shared.RequestDto;

public abstract class Transformer {
    private final RequestDto requestDto;

    public static Transformer of(RequestDto requestDto) {
        // using this as a factory, we could return different implementations of
        // Transformer for different types of request
        return new SampleTransformer(requestDto);
    }

    Transformer(RequestDto requestDto) {
        this.requestDto = requestDto;
    }

    public RequestDto transform() {
        return doTransform(requestDto);
    }

    protected abstract RequestDto doTransform(RequestDto requestDto);
}
