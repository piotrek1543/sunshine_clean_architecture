/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.piotrek1543.android.boilerplate.ui.utils

import android.content.Context
import android.text.format.DateUtils
import com.piotrek1543.android.boilerplate.ui.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Class for handling date conversions that are useful for Sunshine.
 */
object SunshineDateUtils {

    /**
     * This method returns the number of milliseconds (UTC time) for today's date at midnight in
     * the local time zone. For example, if you live in California and the day is September 20th,
     * 2016 and it is 6:30 PM, it will return 1474329600000. Now, if you plug this number into an
     * Epoch time converter, you may be confused that it tells you this time stamp represents 8:00
     * PM on September 19th local time, rather than September 20th. We're concerned with the GMT
     * date here though, which is correct, stating September 20th, 2016 at midnight.
     *
     *
     * As another example, if you are in Hong Kong and the day is September 20th, 2016 and it is
     * 6:30 PM, this method will return 1474329600000. Again, if you plug this number into an Epoch
     * time converter, you won't get midnight for your local time zone. Just keep in mind that we
     * are just looking at the GMT date here.
     *
     *
     * This method will ALWAYS return the date at midnight (in GMT time) for the time zone you
     * are currently in. In other words, the GMT date will always represent your date.
     *
     *
     * Since UTC / GMT time are the standard for all time zones in the world, we use it to
     * normalize our dates that are stored in the database. When we extract values from the
     * database, we adjust for the current time zone using time zone offsets.
     *
     * @return The number of milliseconds (UTC / GMT) for today's date at midnight in the local
     * time zone
     */
    /*
         * This number represents the number of milliseconds that have elapsed since January
         * 1st, 1970 at midnight in the GMT time zone.
         *//*
         * This TimeZone represents the device's current time zone. It provides us with a means
         * of acquiring the offset for local time from a UTC time stamp.
         *//*
         * The getOffset method returns the number of milliseconds to add to UTC time to get the
         * elapsed time since the epoch for our current time zone. We pass the current UTC time
         * into this method so it can determine changes to account for daylight savings time.
         *//*
         * UTC time is measured in milliseconds from January 1, 1970 at midnight from the GMT
         * time zone. Depending on your time zone, the time since January 1, 1970 at midnight (GMT)
         * will be greater or smaller. This variable represents the number of milliseconds since
         * January 1, 1970 (GMT) time.
         *//* This method simply converts milliseconds to days, disregarding any fractional days *//*
         * Finally, we convert back to milliseconds. This time stamp represents today's date at
         * midnight in GMT time. We will need to account for local time zone offsets when
         * extracting this information from the database.
         */ val normalizedUtcDateForToday: Long
        get() {
            val utcNowMillis = System.currentTimeMillis()
            val currentTimeZone = TimeZone.getDefault()
            val gmtOffsetMillis = currentTimeZone.getOffset(utcNowMillis).toLong()
            val timeSinceEpochLocalTimeMillis = utcNowMillis + gmtOffsetMillis
            val daysSinceEpochLocal = TimeUnit.MILLISECONDS.toDays(timeSinceEpochLocalTimeMillis)

            return TimeUnit.DAYS.toMillis(daysSinceEpochLocal)
        }

    /**
     * This method returns the number of days since the epoch (January 01, 1970, 12:00 Midnight UTC)
     * in UTC time from the current date.
     *
     * @param utcDate A date in milliseconds in UTC time.
     * @return The number of days from the epoch to the date argument.
     */
    private fun elapsedDaysSinceEpoch(utcDate: Long): Long {
        return TimeUnit.MILLISECONDS.toDays(utcDate)
    }

    /**
     * Helper method to convert the database representation of the date into something to display
     * to users. As classy and polished a user experience as "1474061664" is, we can do better.
     *
     *
     * The day string for forecast uses the following logic:
     * For today: "Today, June 8"
     * For tomorrow:  "Tomorrow
     * For the next 5 days: "Wednesday" (just the day name)
     * For all days after that: "Mon, Jun 8" (Mon, 8 Jun in UK, for example)
     *
     * @param context      Context to use for resource localization
     * @param date         The date in milliseconds (UTC midnight)
     * @return A user-friendly representation of the date such as "Today, June 8", "Tomorrow",
     * or "Friday"
     */
    fun getFriendlyDateString(context: Context, date: Long, position: Int): String {

        /*
         * NOTE: localDate should be localDateMidnightMillis and should be straight from the
         * database
         *
         * Since we normalized the date when we inserted it into the database, we need to take
         * that normalized date and produce a date (in UTC time) that represents the local time
         * zone at midnight.
         */

        /*
         * In order to determine which day of the week we are creating a date string for, we need
         * to compare the number of days that have passed since the epoch (January 1, 1970 at
         * 00:00 GMT)
         */
        val daysFromEpochToProvidedDate = elapsedDaysSinceEpoch(date)

        /*
         * As a basis for comparison, we use the number of days that have passed from the epoch
         * until today.
         */
        val daysFromEpochToToday = elapsedDaysSinceEpoch(System.currentTimeMillis())
        val dayName = getDayName(context, date)
        val localizedDayName = SimpleDateFormat("EEEE", Locale.ENGLISH).format(date)
        var flags = (DateUtils.FORMAT_NO_YEAR
                or DateUtils.FORMAT_SHOW_WEEKDAY
                or DateUtils.FORMAT_SHOW_TIME)
        var readableDate = getReadableDateString(context, date, flags)

        if (daysFromEpochToProvidedDate == daysFromEpochToToday) {
            /*
             * If the date we're building the String for is today's date, the format
             * is "Today, June 24"
             */

            if (daysFromEpochToProvidedDate - daysFromEpochToToday < 2) {
                /*
                 * Since there is no localized format that returns "Today" or "Tomorrow" in the API
                 * levels we have to support, we take the name of the day (from SimpleDateFormat)
                 * and use it to replace the date from DateUtils. This isn't guaranteed to work,
                 * but our testing so far has been conclusively positive.
                 *
                 * For information on a simpler API to use (on API > 18), please check out the
                 * documentation on DateFormat#getBestDateTimePattern(Locale, String)
                 * https://developer.android.com/reference/android/text/format/DateFormat.html#getBestDateTimePattern
                 */
                if (position == 0)
                    flags = (DateUtils.FORMAT_SHOW_DATE
                            or DateUtils.FORMAT_NO_YEAR
                            or DateUtils.FORMAT_SHOW_WEEKDAY
                            or DateUtils.FORMAT_SHOW_TIME)

                readableDate = getReadableDateString(context, date, flags)
                return readableDate.replace(localizedDayName, dayName)
            } else {


                return readableDate
            }
        } else if (daysFromEpochToProvidedDate < daysFromEpochToToday + 2) {
            /* If the input date is less than a week in the future, just return the day name. */
            return readableDate.replace(localizedDayName, dayName)
        } else {
            flags = (DateUtils.FORMAT_SHOW_DATE
                    or DateUtils.FORMAT_ABBREV_WEEKDAY
                    or DateUtils.FORMAT_NO_YEAR
                    or DateUtils.FORMAT_SHOW_WEEKDAY
                    or DateUtils.FORMAT_SHOW_TIME)

            return DateUtils.formatDateTime(context, date, flags)
        }
    }

    /**
     * Returns a date string in the format specified, which shows an abbreviated date without a
     * year.
     *
     * @param context      Used by DateUtils to format the date in the current locale
     * @param timeInMillis Time in milliseconds since the epoch (local time)
     * @return The formatted date string
     */
    private fun getReadableDateString(context: Context, timeInMillis: Long, flags: Int): String {
        return DateUtils.formatDateTime(context, timeInMillis, flags)
    }

    /**
     * Given a day, returns just the name to use for that day.
     * E.g "today", "tomorrow", "Wednesday".
     *
     * @param context      Context to use for resource localization
     * @param dateInMillis The date in milliseconds (UTC time)
     * @return the string day of the week
     */
    private fun getDayName(context: Context, dateInMillis: Long): String {
        /*
         * If the date is today, return the localized version of "Today" instead of the actual
         * day name.
         */
        val daysFromEpochToProvidedDate = elapsedDaysSinceEpoch(dateInMillis)
        val daysFromEpochToToday = elapsedDaysSinceEpoch(System.currentTimeMillis())

        return when ((daysFromEpochToProvidedDate - daysFromEpochToToday).toInt()) {
            0 -> context.getString(R.string.today)
            1 -> context.getString(R.string.tomorrow)

            else -> {
                val dayFormat = SimpleDateFormat("EEEE", Locale.ENGLISH)
                dayFormat.format(dateInMillis)
            }
        }
    }
}