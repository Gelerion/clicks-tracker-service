# clicks-tracker-service

Simple web service written in Clojure used for tracking new in-apps clicks.

It was born to show some best practices in using Clojure libraries such as
 mount, logging, channels

Request: GET /new-click
Params: app_id, advertiser_id, pid, campaign_id, [ttl]
Response Messages:
 Status code: 200 OK
 Status code: 400 Invalid click
 Status code: 500 Internal Error
 
 

## Usage

    $ java -jar clicks-tracker-service-1.0.0-standalone.jar
