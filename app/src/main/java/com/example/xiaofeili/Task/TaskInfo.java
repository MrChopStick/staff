package com.example.xiaofeili.Task;

import java.util.List;

/**
 * Created by XiaofeiLi on 2016/11/23.
 */
public class TaskInfo {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * task_dead_time : 10
         * task_state : 0
         * task_id : 1
         * plan_id : 3
         * task_start_time : 09:00:00
         */

        private String task_dead_time;
        private String task_state;
        private String task_id;
        private String plan_id;
        private String task_start_time;

        public String getTask_dead_time() {
            return task_dead_time;
        }

        public void setTask_dead_time(String task_dead_time) {
            this.task_dead_time = task_dead_time;
        }

        public String getTask_state() {
            return task_state;
        }

        public void setTask_state(String task_state) {
            this.task_state = task_state;
        }

        public String getTask_id() {
            return task_id;
        }

        public void setTask_id(String task_id) {
            this.task_id = task_id;
        }

        public String getPlan_id() {
            return plan_id;
        }

        public void setPlan_id(String plan_id) {
            this.plan_id = plan_id;
        }

        public String getTask_start_time() {
            return task_start_time;
        }

        public void setTask_start_time(String task_start_time) {
            this.task_start_time = task_start_time;
        }
    }
}
