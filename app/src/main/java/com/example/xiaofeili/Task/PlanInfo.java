package com.example.xiaofeili.Task;

import java.util.List;
public class PlanInfo {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {

        private String plan_start_time;
        private String plan_cycle;
        private String plan_start_date;
        private String plan_id;
        private String plan_name;

        public String getPlan_start_time() {
            return plan_start_time;
        }

        public void setPlan_start_time(String plan_start_time) {
            this.plan_start_time = plan_start_time;
        }

        public String getPlan_cycle() {
            return plan_cycle;
        }

        public void setPlan_cycle(String plan_cycle) {
            this.plan_cycle = plan_cycle;
        }

        public String getPlan_start_date() {
            return plan_start_date;
        }

        public void setPlan_start_date(String plan_start_date) {
            this.plan_start_date = plan_start_date;
        }

        public String getPlan_id() {
            return plan_id;
        }

        public void setPlan_id(String plan_id) {
            this.plan_id = plan_id;
        }

        public String getPlan_name() {
            return plan_name;
        }

        public void setPlan_name(String plan_name) {
            this.plan_name = plan_name;
        }
    }
}
