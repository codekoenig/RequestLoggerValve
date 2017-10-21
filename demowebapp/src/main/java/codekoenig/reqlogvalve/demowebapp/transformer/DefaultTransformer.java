package codekoenig.reqlogvalve.demowebapp.transformer;

import codekoenig.reqlogvalve.shared.RequestDto;

class DefaultTransformer extends Transformer {
    DefaultTransformer(RequestDto requestDto) {
        super(requestDto);
    }

    @Override
    public RequestDto doTransform(RequestDto requestDto) {
        return requestDto;
    }
}
