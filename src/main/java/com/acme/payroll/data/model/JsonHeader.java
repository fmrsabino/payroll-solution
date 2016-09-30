package com.acme.payroll.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonHeader<M extends RootItem> {
    @SerializedName("total_items") private final int totalItems;
    private final int page;
    @SerializedName("page_size") private final int pageSize;
    private final List<M> items;

    public JsonHeader(int totalItems, int page, int pageSize, List<M> items) {
        this.totalItems = totalItems;
        this.page = page;
        this.pageSize = pageSize;
        this.items = items;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<M> getItems() {
        return items;
    }
}
