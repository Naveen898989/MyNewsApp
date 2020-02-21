
MyNewsApp
=========

Sample application designed to outline my capabilities as an Android developer.

Overview
--------
Application hosts a tab bar with 3 screens.
- "Top News" screen shows all the latest news articles.
- "For You" screen shows news articles picked based on users preference.
- "Profile" screen contains user information and preferences.
**Note**: All news articles are retrieved from http://newsapi.org
**Note**: Application retrieve news articles from the internet so please make sure your are connected to the internet.

Top News Screen
---------------
Shows all latest news articles from "US". Each news article is clickable which takes the user to the details screen.

For You Screen
--------------
Shows news articles based on category user has picked, they can do this by going to "Profile Screen". If user has not picked a category they will be shown a message. These news articles sorted by popularity. Each news article is clickable which takes the user to the details screen.

Profile Screen
--------------
Shows user preferences. Users can enter their personal information: username, email address and select preferences: news category they prefer to see. Moving away from the screen saves all the preferences.

**Note**: Application tracks errors using Firebase Crashlytics. Your data *might* be sent to Google.

Demo
----
![Demo video](https://raw.githubusercontent.com/Naveen898989/MyNewsApp/master/demo.gif)