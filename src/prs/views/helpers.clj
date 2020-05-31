(ns prs.views.helpers)

(defn formatted-time
  "Formats a duration in seconds to hours:minutes:seconds"
  [seconds]
  (let [[hours, hours-rem] [(quot seconds 3600) (rem seconds 3600)]
        [minutes, minutes-rem] [(quot hours-rem 60) (rem hours-rem 60)]]
    (str
     (when (> hours 0) (format "%02d:" hours))
     (format "%02d:%02d" minutes minutes-rem))))

(defn movement-record-value
  "Returns a formatted value for the given record"
  [pr-type value]
  (if (and (= pr-type "time") value)
    (formatted-time value)
    value))