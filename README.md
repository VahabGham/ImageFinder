ImageFinder app
==================

First offline app that fetch images for specific subject.
I have a plan to make it like app to search images for different subject. For now to just have an api call and have a first structure it only calls an Specific term of "CAR"  


# Technical features

The app is full Kotlin and Jetpack Compose.
Its a first offline app.
Used MVVM.
Hilt for a Nice DI.


# Architecture

The app follows an `MVVM` architecture and `Version Catalog` system for handling and sharing dependencies.

# Modularization

The app has only one module now 
- `app` which contains the application entry point, the MainActivity and ;
- exposing and make the data layer reusable,
- defining the core navigation, 
- defining the Jetpack Compose theme,
- defining some Jetpack Compose reusable components, etc...


# UI

The app follows Material Design guidelines and does support light and dark mode theme as per default Android Studio New Project template. No huge adjustments have been made yet.

# Testing

Unit tests have been implemented in order to test the main business logic.
Still no UI tests.


# Future Steps
- Search feature
- Ui-Test