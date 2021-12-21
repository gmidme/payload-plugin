package com.yufei.opensearch.payload;

import org.apache.lucene.index.Term;
import org.apache.lucene.queries.payloads.AveragePayloadFunction;
import org.apache.lucene.queries.payloads.PayloadScoreQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.spans.SpanTermQuery;
import org.opensearch.index.query.BaseTermQueryBuilder;
import org.apache.lucene.util.BytesRef;
import org.opensearch.common.xcontent.XContentParser;
import  org.apache.lucene.queries.payloads.PayloadDecoder;
import org.apache.lucene.analysis.payloads.PayloadHelper;
import org.opensearch.common.io.stream.StreamInput;
import org.opensearch.index.query.QueryShardContext;

import java.io.IOException;
import java.util.Objects;

public class PayloadSearchQueryBuilder extends BaseTermQueryBuilder<PayloadSearchQueryBuilder> {

    public static final String NAME = "payload_term";

    /** 省略多个构造函数 **/

    public PayloadSearchQueryBuilder(String fieldName, Object value) {
        super(fieldName, value);
    }
    public PayloadSearchQueryBuilder(StreamInput in) throws IOException {
        super(in);
    }

    @Override
    protected Query doToQuery(QueryShardContext queryShardContext) throws IOException {
        return new PayloadScoreQuery(new SpanTermQuery(new Term(fieldName, (BytesRef) value)), new AveragePayloadFunction(), new FloatPayloadDecoder(), false);
    }


    @Override
    public String getWriteableName() {
        return NAME;
    }




    public static PayloadSearchQueryBuilder fromXContent(XContentParser parser) throws IOException {
        XContentParser.Token token = parser.currentToken();
        if (token == XContentParser.Token.START_OBJECT) {
            token = parser.nextToken();
        }
        assert token == XContentParser.Token.FIELD_NAME;
        String fieldName = parser.currentName();
        token = parser.nextToken();
        Object value;
        if (token == XContentParser.Token.START_OBJECT) {
            throw new RuntimeException();
        } else {
            value = parser.objectBytes();
            parser.nextToken();
        }
        return new PayloadSearchQueryBuilder(fieldName, value);
    }

}

class FloatPayloadDecoder implements PayloadDecoder{
    @Override
    public float computePayloadFactor(BytesRef payload) {
        return Objects.isNull(payload) ? 0.0f : PayloadHelper.decodeFloat(payload.bytes, payload.offset);
    }


}

