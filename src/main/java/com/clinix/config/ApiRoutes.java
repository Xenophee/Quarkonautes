package com.clinix.config;


public class ApiRoutes {

    public static final String API = "/api";

    private ApiRoutes() {}


    public static class Astronauts {
        public static final String BASE = "/astronauts";
        public static final String ROOT = API + BASE;

        private Astronauts() {}
    }

    public static class Ships {
        public static final String BASE = "/ships";
        public static final String ROOT = API + BASE;

        private Ships() {}
    }


}
