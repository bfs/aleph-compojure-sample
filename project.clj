(defproject aleph-compojure-sample "0.1.0-SNAPSHOT"
 :description "Aleph Compojure Sample"
 :repositories {
                "conjars" "http://conjars.org/repo"
                "sun"  "http://download.java.net/maven/2"	
                }
 :dependencies [[org.clojure/clojure "1.2.0"]
                [org.clojure/clojure-contrib "1.2.0"]
                [aleph "0.2.0-beta1"]
                [compojure "0.6.5"]]
 :main aleph-compojure-sample.core)