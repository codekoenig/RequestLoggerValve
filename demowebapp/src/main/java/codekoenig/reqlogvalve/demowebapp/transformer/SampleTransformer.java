package codekoenig.reqlogvalve.demowebapp.transformer;

import codekoenig.reqlogvalve.shared.RequestDto;

class SampleTransformer extends Transformer {
    SampleTransformer(RequestDto requestDto) {
        super(requestDto);
    }

    @Override
    public RequestDto doTransform(RequestDto requestDto) {
        requestDto.getHeaders().put("X-Transformed", this.getClass().toString());
        return requestDto;
    }
}
