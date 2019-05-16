# Mobile Coding Challenge

## Idea of the App

The task is to implement a small app that will list the most starred Github repos that were created in the last 30 days. <br />You'll be fetching the sorted JSON data directly from the Github API (Github API explained down below).

## Features

The User can list the most starred Github repos that were created in the last 30 days.<br />
The results are displayed in the form of a list, one repository per row.<br />
The User is able to see the following details for each repo/row:<br />
- Repository name
- Repository description
- Numbers of stars for the repo.
- Username and avatar of the owner.

## Future Work/Enhacements
The Application is still missing the "[BONUS] As a User I should be able to keep scrolling and new results should appear (pagination)" feature. An updated version should include this feature. <br />
The Setting's navigation has no usefullness for the moment, features of this application part should be defined and implemented.

## Used Libraries
**Picasso :**<br />
Picasso is an image library for Android. It's created and maintained by Square, and caters to image loading and processing. It simplifies the process of displaying images from external locations.<br />
I chose to use this library to facilitate the display of the user avatars for each repository by only using the fetched URL of the avatar/image. <br />
Github repo of library: https://github.com/square/picasso <br />
Website: http://square.github.io/picasso/ <br />

## How to Use
After launching the application, you get prompted to click the only button on the screen to be able to list the trending repositories. Clicking the button, lists the different repositories in a list. You can also switch between the Trending and Settings Navigation tabs. 
