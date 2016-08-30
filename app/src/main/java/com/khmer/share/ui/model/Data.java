package com.khmer.share.ui.model;

import java.util.List;

/**
 * @Copy Right 2012-2016, Afinos, Inc., or its affiliates
 * @Author: Afinos Team
 **/
public class Data {
    private List<User> data;
    private Pagination paging;

    public Pagination getPaging() {
        return paging;
    }

    public void setPaging(Pagination paging) {
        this.paging = paging;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    public static class Pagination {
        private String next;
        private String previous;

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getPrevious() {
            return previous;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        private PageCursor cursors;

        public PageCursor getCursors() {
            return cursors;
        }

        public void setCursors(PageCursor cursors) {
            this.cursors = cursors;
        }
    }

    public static class PageCursor {
        private String before;
        private String after;

        public String getBefore() {
            return before;
        }

        public void setBefore(String before) {
            this.before = before;
        }

        public String getAfter() {
            return after;
        }

        public void setAfter(String after) {
            this.after = after;
        }
    }
}
