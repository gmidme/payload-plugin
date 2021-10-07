package com.yufei.elasticsearch.plugin;

import com.yufei.elasticsearch.payload.PayloadSearchQueryBuilder;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.plugins.SearchPlugin;

import java.util.Collections;
import java.util.List;

public class PayloadPlugin extends Plugin
        implements SearchPlugin {
    @Override
    public List<QuerySpec<?>> getQueries() {
        return Collections.singletonList(
                new QuerySpec<>(PayloadSearchQueryBuilder.NAME,
                        PayloadSearchQueryBuilder::new, PayloadSearchQueryBuilder::fromXContent)
        );
    }
}


