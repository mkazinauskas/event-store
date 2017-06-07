# Event store

[![Build Status](https://travis-ci.org/modestukasai/event-store.svg?branch=master)](https://travis-ci.org/modestukasai/event-store)

## Usage

* Add event POST `/events` 

Sample request
```
{
   uniqueId: 'uniqueId'
   topic   : 'topic'
   value   : 'value'
}
```

* Retrieve events GET `/events`. Optional request params `size`, `page`

* Retrieve next event GET `/events/next?uniqueId={currentEventUniqueId}`