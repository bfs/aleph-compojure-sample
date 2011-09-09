# aleph-compojure-sample

Sample aleph web app with compojure routes.

## Usage

lein run


## What routes and handlers look like

```clj

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

## License

Copyright (C) 2011 Boris Shimanovsky 

Distributed under the MIT license. 
