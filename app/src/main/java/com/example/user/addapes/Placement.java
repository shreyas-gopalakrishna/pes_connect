package com.example.user.addapes;

public class Placement {

        String name;//company name
        String date;//Date
        String time;//Time
        String branch;//Branches Applicable
        int id;//icon

        public Placement(String name,String date,String time,String branch, int id) {
            this.name = name;
            this.date = date;
            this.time = time;
            this.branch = branch;
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {

            this.id = id;
        }
        public String getBranch() {
            return branch;
        }
        public void setBranch(String name) {
            this.branch = name;
        }
        public String getTime() {
            return time;
        }
        public void setTime(String time) {
            this.time = time;
        }
        public String getDate() {
            return date;
        }
        public void setDate(String date) {
            this.date = date;
        }

    }