# SpringBootMovieBookingApplication

Please follow below steps to build and run the application

mvn clean install

mvn sping-boot🇧🇳

open swagger url locally to execute all endpoints related to Movie booking application

http://localhost:8081/swagger-ui/index.html

-----------------------------------------------------------------------------------------------------
Application Feature details are given below

UltraPlex Cinema Movie Booking System


Abstract

Write a REST API service for a big cinema company. The service should support the following:


Add Cinemas

Add Screens to Cinemas

Add Movies

Add Screenings for Movies (for multiple Screens)

Add Bookings for Movies screenings

Some Queries


Requirements
Business
A cinema has multiple screens.

A movie can be played on multiple screens (in multiple cinemas).

A screen can show multiple movies on a day.

A screening of a movie can have multiple bookings.


Come up with some meaningful properties for the entities


Technical
Use Java21

Use SpringBoot >= 3.3

Support Postgresql DB

At least draft/think about access protection for the API

Provide Unittests (no need for 100% coverage - just show some meaningful examples)

Of course you can use additional frameworks/tools to your liking (e.g., lombok, ...)



API
Create/Update endpoints as you see fit

Get a paged list of all screenings for a cinema for a specific day (see the required JSON response below) with options for sorting by start time or movie name

Get a paged list of all screenings of a specific movie for a specific day (see the required JSON response below)



{

"cinemaName": "string",

"screenName": "string",

"start": "ISO Date",

"movie": {

"name": "string",

// some movie meta data

...

}

}

