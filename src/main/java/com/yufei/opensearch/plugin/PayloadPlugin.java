package com.yufei.opensearch.plugin;

import com.yufei.opensearch.payload.PayloadSearchQueryBuilder;
import org.opensearch.plugins.Plugin;
import org.opensearch.plugins.SearchPlugin;

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


