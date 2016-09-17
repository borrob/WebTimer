# WebTimer
An egg timer over the internet.

## Why?
Perhaps egg timer is not the right name for it. Off&lt;&gt;zz timer would be
better! [Off&lt;&gt;zz](http://www.keyboardsunite.com/offzz/) is a laptop and
piano duo experimenting with electro-acoustic music via improvisation. Their
performances show a diverse range of computer sounds in combination with
improvised piano music.

Interested? Have a look at the work of @narcode, or take a look at the
Off&lt&gtzz [youtube channel]
(https://www.youtube.com/channel/UCJCFCrJFEm1yLS9itcUNjuQ) or [SoundCloud]
(https://soundcloud.com/offzz).

## What does it do?
The webtimer runs a clock on the server. Users can visit a webpage to see the
current countdown, the next interval for the countdown clock and the next next
interval for the clock.

Users can interact with the countdown. They can change the next interval
durations up or down by 10 seconds (but not more than 120 seconds and no less
than 20 seconds). Users can also leave a comment or suggestion to the
performers. Once the timer runs out, the screen starts flashing and it loads
the new timer interval. All the users share the same countdown clock and
comments, so it becomes an entire interactive performance experience.

There are a couple of special commands (links) to reset the timer, to clear the
user comments, or to set a special set of intervals. The special set can be a
series of 120, 110, 100, ..., 10 seconds, or a set number of the same interval
time. When the series ends, no new intervals are added anymore and the timer
ends with a red screen (until you set a new set of timers or reset the clock).

## How to build, install and run?
The WebTimer is a JAVA web application. You can tell maven to 'package' the
project and it generates a war-file (or alternatively you can pick any war from
the release directory).

Upload this war file to your JAVA application server (like [tomcat](
https://tomcat.apache.org)) and visit the application url.

Enjoy!
