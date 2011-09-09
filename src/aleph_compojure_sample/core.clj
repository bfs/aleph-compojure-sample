(ns aleph-compojure-sample.core
 (:use 
   aleph.http
   aleph.http.utils
   lamina.core
   compojure.core
   clojure.contrib.command-line
   ring.middleware.keyword-params)
 (:require [compojure.route :as route]))

(defn keyify-params [target]
  (cond
    (map? target)
    (into {}
      (for [[k v] target]
        [(keyword k) (keyify-params v)]))
    (vector? target)
    (vec (map keyify-params target))
    :else
    target))

(defn parse-request-params
  "Parses the request object and returns a map of common arguments"
  [request]
  (let [route-params (:route-params request)
        query-params (keyify-params (aleph.http.utils/query-params request))]
    (merge route-params query-params)))

(defn hello-handler
  "Our handler for the /hello path"
  [ch request]
  (let [params (parse-request-params request)
        name (params :name)]
    (enqueue ch
      {:status 200
       :headers {}
       :body (str "Hello " name)})))


(defroutes my-routes
  (GET ["/hello/:name", :name #"[a-zA-Z]+"] {} (wrap-aleph-handler hello-handler))
  (route/not-found "Page not found"))

(defn start
  "Start our server in the specified port"
  [port]
  (start-http-server (wrap-ring-handler my-routes) {:port port}))

(defn -main [& args]
  (with-command-line args "Aleph Compojure Sample"
    [[port p "The port on which to run this server" 8080]]
    (start port)))