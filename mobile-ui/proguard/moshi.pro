#### OkHttp, Retrofit and Moshi
-dontwarn okhttp3.**
-dontwarn retrofit2.Platform$Java8
-dontwarn okio.**
-dontwarn javax.annotation.**
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}
-keep @com.squareup.moshi.JsonQualifier interface *
-dontwarn org.jetbrains.annotations.**
-keep class kotlin.Metadata { *; }
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}

-keepclassmembers class * {
    @com.squareup.moshi.FromJson <methods>;
    @com.squareup.moshi.ToJson <methods>;
}

-keepnames @kotlin.Metadata class com.piotrek1543.android.boilerplate.remote.model.**
-keep class com.piotrek1543.android.boilerplate.remote.model.** { *; }
-keepclassmembers class com.piotrek1543.android.boilerplate.remote.model.** { *; }

-keepnames @kotlin.Metadata class com.piotrek1543.android.boilerplate.cache.model.**
-keep class com.piotrek1543.android.boilerplate.cache.model.** { *; }
-keepclassmembers class com.piotrek1543.android.boilerplate.cache.model.** { *; }