(ns prs.response)

(defn redirect
  "Issue a 302 redirect to a url and set the given options on the response"
  ([url] (redirect url {}))
  ([url options]
   (merge
    {:status 302
     :headers {"Location" url}
     :body ""} options)))

