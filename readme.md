[![Build Status](https://travis-ci.org/piotrek1543/sunshine_clean_architecture.svg?branch=master)](https://travis-ci.org/piotrek1543/sunshine_clean_architecture) 
[![codecov](https://codecov.io/gh/piotrek1543/sunshine_clean_architecture/branch/master/graph/badge.svg)](https://codecov.io/gh/piotrek1543/sunshine_clean_architecture) 
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6ee987d2a95e48bdb175f7b9dbcf830f)](https://www.codacy.com/app/piotrek1543/sunshine_clean_architecture?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=piotrek1543/sunshine_clean_architecture&amp;utm_campaign=Badge_Grade)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

# Sunshine Clean Architecture Example

Sunshine is a work-in-progress weather app based on Udacity's course project. 

The main purpose of this project is to master clean architecture approaches and testing. It is still in its really __EARLY stages of development__  and currently contains only one piece of UI.

## Languages, libraries and tools used

* [Kotlin](https://kotlinlang.org/)
* [Room](https://developer.android.com/topic/libraries/architecture/room.html)
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
* [AndroidX Support Libraries](https://developer.android.com/jetpack/androidx)
* [Material Components for Android](https://github.com/material-components/material-components-android)
* [RxJava2](https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0)
* [Dagger2](https://github.com/google/dagger)
* [Glide](https://github.com/bumptech/glide)
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)
* [Timber](https://github.com/JakeWharton/timber)
* [KtLint](https://ktlint.github.io/)
* [Detekt](https://arturbosch.github.io/detekt/)
* [Mockito](http://site.mockito.org/)
* [Espresso](https://developer.android.com/training/testing/espresso/index.html)
* [Robolectric](http://robolectric.org/)

## Requirements

* JDK 1.8
* [Android SDK](https://developer.android.com/studio/index.html)
* Android O ([API 26](https://developer.android.com/preview/api-overview.html))
* Latest Android SDK Tools and build tools.

## Architecture

The architecture of `Sunshine` project follows the principles of Clean Architecture. Here's how the sample project implements it:

![architecture](https://github.com/bufferapp/clean-architecture-components-boilerplate/blob/master/art/architecture.png?raw=true)

The sample app when run will show you a simple list of all the forecasts (check `ForecastView` for implementation details).
<p align="center">
<img src="art/device_screenshot.png" alt="Screenshot" style="width: 350px;"/>
</p>

The diagram below presents how each of the architecture layers play their roles in this project:

![architecture](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/art/ui.png?raw=true)

## Credits

This project wouldn't be created if some repositories weren't created before

- https://github.com/googlesamples/android-architecture (Encyclopedia of architecture patterns and battle-tested techniques on Android)

- https://github.com/android10/Android-CleanArchitecture (the simplest way to implement clean architecture on Android)

- https://github.com/bufferapp/android-clean-architecture-boilerplate (this project is based on it)
